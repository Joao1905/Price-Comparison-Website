import scrapy
from scrapy.http import HtmlResponse
from scrapy.http import Request

class PichauCPUSpider (scrapy.Spider):
	name= 'PichauCPUSpider'
	allowed_domains = ['pichau.com.br']
	max_page = 5 #Not included

	def start_requests (self):
		for i in range(1, self.max_page):
			yield Request('https://www.pichau.com.br/hardware/processadores?p=%d&product_list_limit=48' % i, callback=self.parse_pichau)

	def parse_pichau (self, response):
		for html in response.xpath("//li[@class='item product product-item']").extract():
			html = HtmlResponse(url="na", body=html, encoding='utf-8')

			#Model
			name_for_model = html.xpath("//a[@class='product-item-link']/@href").get()
			try:
				model_table = str.maketrans(dict.fromkeys('-',' '))	
				model = name_for_model.translate(model_table)
				model = model.split()[-1]
				try:
					if model[0:3] == "100":
						model = "100-"+model

				except:
					model = model

			except:
				model = None

			#Price
			try:
				price = html.xpath("//div[@class='price-box price-final_price']//span[@class='price-boleto']/span/text()").get().split()[-1]
				table = str.maketrans(dict.fromkeys('.'))		#remove .
				price = price.translate(table)
				table = str.maketrans(dict.fromkeys('R$'))		#remove R$
				price = price.translate(table)
				table = str.maketrans(dict.fromkeys(',','.'))	#Change , for . for MySQL 'real' variable type
				price = price.translate(table)
			except:
				price = None

			#Return
			yield {"Product": 1, "Model": model, "Shop": 2, "Price": price, "URL": name_for_model}

	# Section with name and price: //section[@class='listagem-box'
		# Product Name: //div[@class='listagem-titulo_descr']/h2/a/text()
		# Price: //div[@class='listagem-preco']/text()
