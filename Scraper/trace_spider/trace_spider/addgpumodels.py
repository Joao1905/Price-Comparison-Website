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

def InsertInto(mydb, x, y, z, zz):
	mycursor = mydb.cursor()
	sql = "INSERT INTO models (product_id, unique_name, name, designer, manufacturer) VALUES (%s, %s, %s, %s, %s)"
	val = (2, x, y, z, zz)
	mycursor.execute(sql, val)
	mydb.commit()



#Open JSON File
with open('JSON/pichauGPU.json') as json_file:
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

	if "RADEON" in y or "Radeon" in y or "radeon" in y or "AMD" in y or "Amd" in y or "amd" in y:
		z = "AMD"
	elif "GeForce" in y or "GEFORCE" in y or "geforce" in y or "Geforce" in y or "NVIDIA" in y or "Nvidia" in y or "nvidia" in y or " GT " in y or "Quadro" in y:
		z = "NVIDIA"
	else:
		z = None

	if "Afox" in y or "AFOX" in y or "afox" in y:
		zz = "Afox"
	elif "Aorus" in y or "AORUS" in y or "aorus" in y:
		zz = "Aorus"
	elif "ASRock" in y or "ASROCK" in y or "asrock" in y or "Asrock" in y:
		zz = "ASRock"
	elif "Asus" in y or "ASUS" in y or "asus" in y:
		zz = "ASUS"
	elif "Evga" in y or "EVGA" in y or "evga" in y:
		zz = "EVGA"
	elif "Gainward" in y or "GAINWARD" in y or "gainward" in y:
		zz = "Gainward"
	elif "Galax" in y or "GALAX" in y or "galax" in y:
		zz = "Galax"
	elif "Gigabyte" in y or "GIGABYTE" in y or "gigabyte" in y or "GigaByte" in y or "Giga Byte" in y or "GIGA BYTE" in y:
		zz = "Gigabyte"
	elif "Msi" in y or "MSI" in y or "msi" in y:
		zz = "MSI"
	elif "PCYes" in y or "PCYES" in y or "pcyes" in y or "Pcyes" in y or "Pc yes" in y or "PC Yes" in y  or "pc yes" in y or "PC yes" in y or "PC YES" in y:
		zz = "PCYes"
	elif "Pny" in y or "PNY" in y or "pny" in y:
		zz = "PNY"
	elif "Powercolor" in y or "POWERCOLOR" in y or "powercolor" in y or "PowerColor" in y or "Power Color" in y or "power color" in y:
		zz = "Powercolor"
	elif "Rise Mode" in y or "RISE MODE" in y or "rise mode" in y or "Risemode" in y or "RISEMODE" in y or "risemode" in y:
		zz = "Rise Mode"
	elif "Sapphire" in y or "SAPPHIRE" in y or "sapphire" in y:
		zz = "Sapphire"
	elif "Xfx" in y or "XFX" in y or "xfx" in y:
		zz = "XFX"
	elif "Zogis" in y or "ZOGIS" in y or "zogis" in y:
		zz = "Zogis"
	elif "Zotac" in y or "ZOTAC" in y or "zotac" in y:
		zz = "Zotac"
	else:
		zz = None

	if "Suporte" in y:
		continue

	try:
		InsertInto(mydb, x, y, z, zz)
	except:
		next