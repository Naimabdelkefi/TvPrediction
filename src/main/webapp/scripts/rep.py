import csv
import fileinput

with open('file.csv', 'r') as fp:
    reader = csv.reader(fp, delimiter=',', quotechar='"')
    # next(reader, None)  # skip the headers
    data_read = [row for row in reader]
row = []
for i in range(0, len(data_read)):
	row.append(data_read[i][2]);
	
with open('pollution.csv', 'r') as fp:
    reader = csv.reader(fp, delimiter=',', quotechar='"')
    # next(reader, None)  # skip the headers
    data_read = [row for row in reader]
row1 = []
for i in range(0, len(data_read)):
	row1.append(data_read[i][2]);

print(row1);


for i in range(0, len(row)):
	with fileinput.FileInput('file.csv', inplace=True) as file:
		for line in file:
			print(line.replace(row[i], row1[i]), end='')