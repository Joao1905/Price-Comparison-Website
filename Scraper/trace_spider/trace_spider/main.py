from controller import Controller
import os
import threading

class myThread (threading.Thread):
   def __init__(self, threadID, name, command):
      threading.Thread.__init__(self)
      self.threadID = threadID
      self.name = name
      self.command = command
   def run(self):
      print ("Starting " + self.name)
      os.system(self.command)
      print ("Exiting " + self.name)

thread1 = myThread(1, "Thread-1", "scrapy crawl KabumCPUSpider -o JSON/kabumCPU.json")
thread2 = myThread(2, "Thread-2", "scrapy crawl PichauCPUSpider -o JSON/pichauCPU.json")
thread3 = myThread(3, "Thread-3", "scrapy crawl CissaCPUSpider -o JSON/cissaCPU.json")
thread4 = myThread(4, "Thread-4", "scrapy crawl TerabyteCPUSpider -o JSON/terabyteCPU.json")

thread1.start()
thread2.start()
thread3.start()
thread4.start()

thread1.join()
thread2.join()
thread3.join()
thread4.join()

files = [
	"JSON/kabumCPU.json",
	"JSON/pichauCPU.json",
	"JSON/terabyteCPU.json",
	"JSON/cissaCPU.json"
]

try:
	Controller.ExecuteController(files)
except Exception as e:
	print("Fail")
finally:
	for file in files:
		os.remove(file)

thread5 = myThread(5, "Thread-5", "scrapy crawl KabumGPUSpider -o JSON/kabumGPU.json")
thread6 = myThread(6, "Thread-6", "scrapy crawl PichauGPUSpider -o JSON/pichauGPU.json")
thread7 = myThread(7, "Thread-7", "scrapy crawl CissaGPUSpider -o JSON/cissaGPU.json")
thread8 = myThread(8, "Thread-8", "scrapy crawl TerabyteGPUSpider -o JSON/terabyteGPU.json")

thread5.start()
thread6.start()
thread7.start()
thread8.start()

thread5.join()
thread6.join()
thread7.join()
thread8.join()

files = [
   "JSON/kabumGPU.json",
   "JSON/pichauGPU.json",
   "JSON/terabyteGPU.json",
   "JSON/cissaGPU.json"
]

try:
   Controller.ExecuteController(files)
except:
   print("Fail")
finally:
   for file in files:
      os.remove(file)