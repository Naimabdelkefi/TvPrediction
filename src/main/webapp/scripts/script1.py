isTrain = 0
import sys
sys.stdout = open('file', 'w')
sys.stderr = open('file', 'w')
from math import sqrt
from datetime import timedelta, date
import numpy as np
from numpy import concatenate
from matplotlib import pyplot
from datetime import datetime
from pandas import read_csv
from pandas import DataFrame
from pandas import concat
from sklearn.preprocessing import LabelEncoder
from sklearn.metrics import mean_squared_error
from keras.models import Sequential
from keras.layers import Dense
from keras.layers import LSTM,GRU
from keras.callbacks import ModelCheckpoint
import bisect



# convert series to supervised learning
def series_to_supervised(data, n_in=1, n_out=1, dropnan=True):
	n_vars = 1 if type(data) is list else data.shape[1]
	df = DataFrame(data)
	cols, names = list(), list()
	# input sequence (t-n, ... t-1)
	for i in range(n_in, 0, -1):
		cols.append(df.shift(i))
		names += [('var%d(t-%d)' % (j+1, i)) for j in range(n_vars)]
	# forecast sequence (t, t+1, ... t+n)
	for i in range(0, n_out):
		cols.append(df.shift(-i))
		if i == 0:
			names += [('var%d(t)' % (j+1)) for j in range(n_vars)]
		else:
			names += [('var%d(t+%d)' % (j+1, i)) for j in range(n_vars)]
	# put it all together
	agg = concat(cols, axis=1)
	agg.columns = names
	# drop rows with NaN values
	if dropnan:
		agg.dropna(inplace=True)
	return agg
 
# load dataset
dataset = read_csv('pollution.csv', header=-1)
values = dataset.values
# integer encode direction
dateEncoder = LabelEncoder()
d = date(2017, 9, 1)
dates = np.array([])
while d <= date(2018, 5, 31):
	dates = np.append(dates,[d.strftime('%d-%m-%Y')])
	d += timedelta(days=1)
dateEncoder.fit(dates)

timeEncoder = LabelEncoder()
a = datetime(100,1,1,00,00,00)
hours = np.array([])
while a <=  datetime(100,1,2,00,00,00):
	hours = np.append(hours,[a.strftime('%Hh%M')])
	a += timedelta(0,300)
hours = np.append(hours,["Unknown"])
timeEncoder.fit(hours)
emissionEncoder = LabelEncoder()
chaineEncoder = LabelEncoder()

Dates = values[:,0]
Emission = values[:,1]
Chaines = values[:,2]
Times = values[:,3]
Arr = np.empty(values.shape)
Arr = Arr.astype('float32')
Arr[:,0] = dateEncoder.transform(Dates)/np.amax(dateEncoder.transform(Dates))
Arr[:,1] = emissionEncoder.fit_transform(Emission)
Arr[:,2] = chaineEncoder.fit_transform(Chaines)/np.amax(chaineEncoder.transform(Chaines))
Arr[:,3] = timeEncoder.transform(Times)/np.amax(timeEncoder.transform(Times))
Arr[:,4] = np.array([ float(k.replace("%",""))*0.01 for k in values[:,4] ])
# ensure all data is float

# normalize features
#print(Arr[:,0])
#myArr = np.empty((values.shape[0],2))
##myArr[:,0] = Arr[:,1]
#myArr[:,1] = Arr[:,4]
#scaled = scaler.fit_transform(myArr)
#Arr[:,4] = scaled[:,1];
# frame as supervised learning
#reframed = series_to_supervised(Arr, 1, 1)
#print(reframed)

# drop columns we don't want to predict
#reframed.drop(reframed.columns[[5,6,7,8]], axis=1, inplace=True)

# split into train and test sets
newvalues = Arr

train_size = int(len(dataset) * 0.70)
test_size = len(dataset) - train_size

train, test =  newvalues[0:train_size,:],  newvalues[train_size:len(dataset),:]

train_X, train_y = train[:, 0:4], train[:, 4]
test_X, test_y = test[:, 0:4], test[:, 4]

# reshape input to be 3D [samples, timesteps, features]
# 	train = values
train_X = train_X.reshape((train_X.shape[0], 1, train_X.shape[1]))
test_X = test_X.reshape((test_X.shape[0], 1, test_X.shape[1]))
# print(train_X.shape, train_y.shape, test_X.shape, test_y.shape)


# design network
model = Sequential()
model.add(LSTM(50, input_shape=(train_X.shape[1], train_X.shape[2])))
model.add(Dense(1))
model.compile(loss='mae', optimizer='adam')
# fit network


model_json = model.to_json()
with open("model.json", "w") as json_file:
    json_file.write(model_json)
print("Saved model to disk")


callbacks = [
    ModelCheckpoint("model.h5", monitor='val_loss', save_best_only=True, verbose=0),
]
if isTrain == 1:
	history = model.fit(train_X, train_y, epochs=100, batch_size=64,validation_data=(test_X, test_y), shuffle=False, callbacks=callbacks)



model.load_weights("model.h5")

# plot history
#pyplot.plot(history.history['loss'], label='train')
#pyplot.plot(history.history['val_loss'], label='test')
#pyplot.legend()
#pyplot.show()


pred = np.empty((1,4))
pred = pred.astype('float32')
pred[0][0] = dateEncoder.transform( [sys.argv[1]] )/np.amax(dateEncoder.transform(Dates))
pred[0][1] = emissionEncoder.transform([sys.argv[2]])
pred[0][2] = chaineEncoder.transform([sys.argv[3]])/np.amax(chaineEncoder.transform(Chaines))
pred[0][3] = timeEncoder.transform([sys.argv[4]])/np.amax(timeEncoder.transform(Times))




#print(predicter)

#predicter = predicter.astype('float32')
#print(predicter)
# normalize features
#scaled = scaler.fit_transform(predicter)
#scaled = predicter
#print(scaled)
#exit();
#pred = series_to_supervised(scaled, 1, 1)
#pred.drop(pred.columns[[5,6,7]], axis=1, inplace=True)
#print(train_X.shape, train_y.shape, test_X.shape, test_y.shape)
#b = np.empty((1,1,4))
#pred["var1(t)"] = 0
#b[0] = pred
#print(pred.shape)
b = np.array([pred])
#print(b)
#print(b.shape)
a = model.predict(b)
#a = np.append(emissionEncoder.transform(["Tellement vrai"]),a)
#c = scaler.inverse_transform(a)
sys.stdout = sys.__stdout__
#inverse_transform(
if a[0][0] < 0:
	print(0)
else:
	print(a[0][0]*100)
#print(c)