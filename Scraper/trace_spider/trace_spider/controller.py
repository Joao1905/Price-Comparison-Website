import json
import mysql.connector
from customjson import CustomJSON
import os

class Controller():

	def ConnectionFactory():
		mydb = mysql.connector.connect(
		  host="XXXXX",
		  user="XXXXX",
		  passwd="XXXXX",
		  database="trace"
		)
		return mydb

	def InsertInto(mydb, product_id, model_id, shop_id, price, product_url):
		mycursor = mydb.cursor()
		sql = "INSERT INTO price_history (date, product_id, model_id, shop_id, price, product_url) VALUES (now(), %s, %s, %s, %s, %s)"
		val = (product_id, model_id, shop_id, price, product_url)
		mycursor.execute(sql, val)
		mydb.commit()
		mycursor.close()

	def Select(mydb, table, column, condition):
		mycursor = mydb.cursor()
		sql = "SELECT * FROM "+table+" WHERE "+column+" = '"+condition+"'"
		mycursor.execute(sql)
		result = mycursor.fetchall()
		mycursor.close()
		return result
		
	def ExecuteController(files=[]):
		#Open Data files
		data = CustomJSON.JSONMagicForFunction(list(files))

		#Initialize MySQL connection for later
		mydb = Controller.ConnectionFactory()

		#Parse JSON File
			#JSON File must contain "Product":product_id, "Shop": shop_id, "Price": price, "Model": unique_name
			#Price must be already cleaned

		for length in range(len(data)):		#For every product found
			#product_id, shop_id, price, unique_name

			product_id = data[length].get("Product")
			shop_id = data[length].get("Shop")
			price = data[length].get("Price")
			unique_name = data[length].get("Model")
			product_url = data[length].get("URL")

			#Check if price is None
			if price is None or shop_id is None or product_id is None or unique_name is None or product_url is None:
				continue

			#Check if Model is registered
			x = Controller.Select(mydb, "models", "unique_name", data[length].get("Model"))
			try:
				if x[0][3]:
					Controller.InsertInto(mydb, product_id, x[0][0], shop_id, price, product_url)
				else:
					continue
			except:
				continue

		mydb.close()
