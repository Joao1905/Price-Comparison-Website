import scrapy
from scrapy.http import HtmlResponse
from scrapy.http import Request

class TerabyteGPUSpider (scrapy.Spider):
	name= 'TerabyteGPUSpider'
	allowed_domains = ['terabyteshop.com.br']

	def start_requests (self):
		yield Request('https://www.terabyteshop.com.br/hardware/placas-de-video', callback=self.parse_terabyte_main)

	def parse_terabyte_main (self, response):
		for next_page in response.xpath("//a[@class='prod-name']/@href").getall():
			yield response.follow("https://www.terabyteshop.com.br/"+next_page, callback=self.parse_terabyte_individual)

	def parse_terabyte_individual (self, response):
		#Model
		model = response.xpath("//span[@id='partnumber']/text()").get()
		clean_model = ""
		try:
			for length in range(1, len(model.split(" "))): #Skip number 0 ([0] = "CÓD: ")
				clean_model = clean_model +" "+model.split()[length]
		except:
			model = None
		
		#Price
		price = response.xpath("//p[@class='val-prod valVista']/text()").get()
		price_table = str.maketrans(dict.fromkeys('.'))
		price_table2 = str.maketrans(dict.fromkeys(',', '.'))
		try:
			price = price.split()[-1]
			price = price.translate(price_table)
			price = price.translate(price_table2)
		except:
			price = None

		#URL
		url = response.request.url

		yield {"Product": 2, "Model": clean_model, "Shop": 4, "Price": price, "URL": url}
