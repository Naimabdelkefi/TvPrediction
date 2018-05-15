import pymysql
import csv
import time  
# open connection to the database  
conn = pymysql.connect(host='127.0.0.1',    
                       user='root',
                       db='tvprediction',  
                       charset='utf8')  
cur = conn.cursor()  


x = conn.cursor()
with open('file.csv', 'r') as fp:
    reader = csv.reader(fp, delimiter=',', quotechar='"')
    # next(reader, None)  # skip the headers
    data_read = [row for row in reader]

	
'''
try:
	#x.execute("INSERT INTO AudienceData1 VALUES (STR_TO_DATE('01-09-2017', '%d-%m-%Y'),%s,%s,%s,%s);",('Les petits meurtres d''Agatha Christie','France 2','21h00','20.6%'))
	x.execute("""INSERT INTO AudienceData1 VALUES (%s,%s,%s,%s,%s,%s)""",(1,'01-09-2017','Les petits meurtres d''Agatha Christie','France 2','21h00','20.6%'))
	conn.commit()
except:
	conn.rollback()
	
conn.close()	
'''

for i in range(0, len(data_read)):
	if (data_read[i][4].replace("%","")!=""):
		#print("""INSERT INTO audiencedata VALUES (STR_TO_DATE('%s', '%d-%m-%Y'),%s,%s,%s,%f)""",(data_read[i][0],data_read[i][1],data_read[i][2],data_read[i][3]),float(data_read[i][4].replace("%","").replace(",",".")))
	
		try:
			x.execute("""INSERT INTO AudienceData VALUES (%s,%s,%s,%s,%s,%s)""",(i,data_read[i][0],data_read[i][1],data_read[i][2],data_read[i][3],data_read[i][4]))
			#x.execute("""INSERT INTO audiencedata1 (date,emission,chaine,heure,ratio) VALUES (STR_TO_DATE('%s', '%d-%m-%Y'),%s,%s,%s,%f);""",(data_read[i][0],data_read[i][1],data_read[i][2],data_read[i][3]),float(data_read[i][4].replace("%","").replace(",",".")))
			conn.commit()
		except:
			conn.rollback()
	
conn.close()


