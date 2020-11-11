import os
import mysql.connector
from customjson import CustomJSON
from resizer import Resizer
import urllib.request
import cv2

def ConnectionFactory():
	mydb = mysql.connector.connect(
	  host="localhost",
	  user="root",
	  passwd="Ha56sete4GBholoo8!",
	  database="trace"
	)
	return mydb

def SelectModel(product_id):
	mycursor = mydb.cursor()
	sql = "SELECT unique_name FROM models WHERE product_id="+str(product_id)+""
	mycursor.execute(sql)
	result = mycursor.fetchall()
	mycursor.close()
	return result

mydb = ConnectionFactory()
result = SelectModel(2)

for i in range(0, len(result)):

	test = cv2.imread("img/"+result[i][0]+".jpg")
	if test is None:
		os.system("scrapy crawl ImageSpider -a search="+result[i][0]+" -o json/"+result[i][0]+".json")
		try:
			file = CustomJSON.OpenJSON("json/"+result[i][0]+".json")
			urllib.request.urlretrieve(file[0].get("URL"), "img/"+result[i][0]+".jpg")
			perfect_rate=1.166666666666666666 			#perfect_rate = height/width ideal das imagem
			Resizer.resize("img/"+result[i][0]+".jpg", perfect_rate)
			os.remove("json/"+result[i][0]+".json")
		except:
			continue
		

#Alternative for when no image exists yet
#for i in range(0, len(result)):
#
#	os.system("scrapy crawl ImageSpider -a search="+result[i][0]+" -o json/"+result[i][0]+".json")
#	try:
#		file = CustomJSON.OpenJSON("json/"+result[i][0]+".json")
#		urllib.request.urlretrieve(file[0].get("URL"), "img/"+result[i][0]+".jpg")
#		perfect_rate=1.166666666666666666 			#perfect_rate = height/width ideal das imagem
#		Resizer.resize("img/"+result[i][0]+".jpg", perfect_rate)
#		os.remove("json/"+result[i][0]+".json")
#	except:
#		continue

mydb.close()