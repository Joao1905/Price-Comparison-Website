import json
import mysql.connector

def ConnectionFactory():
	mydb = mysql.connector.connect(
	  host="localhost",
	  user="root",
	  passwd="Ha56sete4GBholoo8!",
	  database="trace"
	)
	return mydb

def InsertInto(mydb, x, y, z):
	mycursor = mydb.cursor()
	sql = "INSERT INTO models (product_id, unique_name, name, designer) VALUES (%s, %s, %s, %s)"
	val = (1, x, y, z)
	mycursor.execute(sql, val)
	mydb.commit()



#Open JSON File
with open('trace_spider/JSON/kabumGPU.json') as json_file:
	data = json.load(json_file)

#Parse and add JSON File to DB
mydb = ConnectionFactory()
for length in range(len(data)):
	try:
		y = data[length].get("Name")
	except:
		y = None

	try:
		x = data[length].get("Model")
	except:
		x = None

	if "AMD" in y or "amd" in y or "Amd" in y:
		z = "AMD"
	elif "Intel" in y or "intel" in y or "INTEL" in y:
		z = "Intel"
	else:
		z = None

	try:
		InsertInto(mydb, x, y, z)
	except:
		next