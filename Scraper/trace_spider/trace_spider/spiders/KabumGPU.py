import scrapy
from scrapy.http import HtmlResponse
from scrapy.http import Request

class KabumGPUSpider (scrapy.Spider):
	name= 'KabumGPUSpider'
	allowed_domains = ['kabum.com.br']
	max_page = 10 #Not included

	def start_requests (self):
		for i in range(1, self.max_page):
			yield Request('https://www.kabum.com.br/hardware/placa-de-video-vga?string=&pagina=%d&ordem=5&limite=100' % i, callback=self.parse_kabum)

	def parse_kabum (self, response):
		for html in response.xpath("//section[@class='listagem-box']").extract():
			html = HtmlResponse(url="na", body=html, encoding='utf-8')

			#Name
			name = html.xpath("//div[@class='listagem-titulo_descr']/h2/a/text()").get()

			#Model
			try:
				model = name.split()[-1]
				if len(model) < 11:
					model = name.split()[-2] + " " + model
					if ")" in name.split()[-1] and "(" in name.split()[-2]:
						model = name.split()[-3]
					if model.startswith("- "):
						model = model.replace("- ", "")

			except:
				model = None

			#Price
			try:
				price = html.xpath("//div[@class='listagem-preco']/text()").get().split(" ")[1]
				table = str.maketrans(dict.fromkeys('.'))		#remove .
				price = price.translate(table)
				table = str.maketrans(dict.fromkeys(',','.'))	#Change , for . for MySQL 'real' variable type
				price = price.translate(table)
			except:
				price = None

			#URL
			url = html.xpath("//div[@class='listagem-titulo_descr']/h2/a/@href").get()

			#Name
			#if name.endswith(model):
			#	name = name[:-len(model)]
			#try:
			#	name = name.replace(" - ", "")
			#	if name.endswith(" "):
			#		name = name[:-1]
			#except:
			#	name = name


			#Return
			yield {"Product": 2, "Model": model, "Shop": 1, "Price": price, "URL": url}

	# Section with name and price: //section[@class='listagem-box'
		# Product Name: //div[@class='listagem-titulo_descr']/h2/a/text()
		# Price: //div[@class='listagem-preco']/text()
