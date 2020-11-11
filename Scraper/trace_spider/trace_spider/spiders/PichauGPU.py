import scrapy
from scrapy.http import HtmlResponse
from scrapy.http import Request

#This second Pichau spider scrapes from inside the product link (more requests, a little bit more safe, can be a viable alternative)

class PichauGPUSpider (scrapy.Spider):
	name= 'PichauGPUSpider'
	allowed_domains = ['pichau.com.br']
	max_page = 3 #Not included

	def start_requests (self):
		for i in range(1, self.max_page):
			yield Request('https://www.pichau.com.br/hardware/placa-de-video?p=%d&product_list_limit=32' % i, callback=self.parse_pichau_main)

	def parse_pichau_main (self, response):
		for next_page in response.xpath("//a[@class='product photo product-item-photo']/@href").getall():
			yield response.follow(next_page, callback=self.parse_pichau_individual)

	def parse_pichau_individual (self, response):
		#Model
		model = response.xpath("//div[@class='product attribute sku']/div/text()").get()
		
		#Price
		try:
			price = response.xpath("//span[@class='price-boleto']/span/text()").get().split()[-1]
			table = str.maketrans(dict.fromkeys('.'))		#remove .
			price = price.translate(table)
			table = str.maketrans(dict.fromkeys('R$'))		#remove R$
			price = price.translate(table)
			table = str.maketrans(dict.fromkeys(',','.'))	#Change , for . for MySQL 'real' variable type
			price = price.translate(table)
		except:
			price = None

		#URL
		url = response.request.url

		#Name
		#name = response.xpath("//div[@class='product title']/h1/text()").get()
		#name = name.split(",")[0]

		#Return
		yield {"Product": 2, "Model": model, "Shop": 2, "Price": price, "URL": url}

	# Section with name and price: //section[@class='listagem-box'
		# Product Name: //div[@class='listagem-titulo_descr']/h2/a/text()
		# Price: //div[@class='listagem-preco']/text()
