from urllib.request import urlopen
from bs4 import BeautifulSoup
import csv
from datetime import timedelta, date
	
	
writer = csv.writer(open("file.csv", 'w' , encoding='utf-8'), lineterminator='\n' , delimiter=',', quoting=csv.QUOTE_ALL)
d = date.today() - timedelta(1)
m = True
if d.weekday() < 5:
	m = False
end_date = date.today()
delta = timedelta(days=1)
while d <= end_date:
	print(d)
	d += delta
	soup = BeautifulSoup(urlopen("http://www.programme-television.org/audiences/"+d.strftime('%d-%m-%Y')).read(), 'lxml')
	sp = BeautifulSoup(urlopen("http://www.programme-tv.net/programme/toutes-les-chaines/"+d.strftime('%Y-%m-%d')+"/").read(), 'lxml')
	for item in soup.find_all('ul' , {"class": "row"})[0].find_all('li'):	
		row = []
		title = item.find( "span",{"class","titre"} ).getText().strip().strip()
		rate = item.find( "span",{"class","result"} ).find("em").getText().strip().strip()
		channel = item.find("a",{"class":"logochaine"})['class'][2]
		progheur = "Unknown"
		for k in sp.find_all('div' , {"class": "clearfix p-v-md bgc-white bb-grey-3"}):
			prog = k.find("a", {"class": "prog_name"}).getText().strip().strip()
			progh = k.find("div", {"class": "prog_heure"}).getText().strip().strip()
			#print(prog);
			#print(progh);
			if(prog == title):
				progheur = progh
				break;
			#exit()
		#print(title);
		#print(progheur);
		#exit()
		row.append(d.strftime('%Y-%m-%d'))
		row.append(title)
		row.append(channel)
		row.append(progheur)
		#row.append(m)
		row.append(rate)
		writer.writerow(row)
		#print(title + " " + channel + " " + rate)

