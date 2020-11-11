import scrapy
from scrapy.http import HtmlResponse
from scrapy.http import Request

class CissaCPUSpider (scrapy.Spider):
	name= 'CissaCPUSpider'
	allowed_domains = ['cissamagazine.com.br']
	max_page = 15 #Not included

	def start_requests (self):
		for i in range(1, self.max_page):
			yield Request('https://www.cissamagazine.com.br/hardware/processador?p=%d' % i, callback=self.parse_cissa_main)

	def parse_cissa_main (self, response):
		for next_page in response.xpath("//div[@class='product-wrapper']/a/@href").getall():
			yield response.follow("https:"+next_page, callback=self.parse_cissa_individual)

	def parse_cissa_individual (self, response):
		#Model
		model = response.xpath("//dl[@class='grupo-carac clearfix']/span[2]/dd/text()").get()
		
		#Price
		price = response.xpath("//div[@class='price-boleto']//span[@class='price-big']/span[@class='price']/text()").get()
		price_table = str.maketrans(dict.fromkeys('\n'))
		price_table2 = str.maketrans(dict.fromkeys(' '))
		price_table3 = str.maketrans(dict.fromkeys('R$'))
		price_table4 = str.maketrans(dict.fromkeys('.'))
		price_table5 = str.maketrans(dict.fromkeys(',', '.'))
		try:
			price = price.translate(price_table)
			price = price.translate(price_table2)
			price = price.translate(price_table3)
			price = price.translate(price_table4)
			price = price.translate(price_table5)
		except:
			prince = None

		#URL
		url = response.request.url

		#Name (to add models to database)
		#name = response.xpath("//div[@class='container header-product']/div/h1/text()").get()
		#name_table = str.maketrans(dict.fromkeys('\n'))
		#try:
		#	name = name.translate(name_table)
		#except:
		#	name = None

		yield {"Product": 1, "Model": model, "Shop": 3, "Price": price, "URL": url}
