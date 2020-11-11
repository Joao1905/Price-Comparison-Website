import json

class CustomJSON:
	def OpenJSON (file):
		with open(file) as json_file:
			return json.load(json_file)

	def AppendJSON (files=[]):
		data=[]
		for i in range(0, len(files)):
			for j in range(0, len(files[i])):
				data.append(files[i][j])
		return data

	def JSONMagic(*files):
		data = []
		for i in range(len(files)):
			data.append(CustomJSON.OpenJSON(files[i]))

	def JSONMagicForFunction(files=[]):
		data = []
		for i in range(len(files)):
			data.append(CustomJSON.OpenJSON(files[i]))
		
		return CustomJSON.AppendJSON(data)

#To use, use only JSONMagic as follows:

#x = CustomJSON.JSONMagic("JSON/kabumCPU.json", "JSON/kabumCPU.json")
#print (len(x))
#print (x[101])