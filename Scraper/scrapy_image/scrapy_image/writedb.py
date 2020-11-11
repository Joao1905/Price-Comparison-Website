import mysql.connector

def ConnectionFactory():
	mydb = mysql.connector.connect(
	  host="localhost",
	  user="root",
	  passwd="Ha56sete4GBholoo8!",
	  database="trace"
	)
	return mydb

def Update (unique_id, mydb):
	mycursor = mydb.cursor()
	sql = "UPDATE models SET image = %s WHERE unique_name = %s"
	val = ("resources/img/gpu/"+unique_id+".jpg", unique_id)
	mycursor.execute(sql, val)
	mydb.commit()
	mycursor.close()

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
	Update(result[i][0], mydb)

mydb.close()
