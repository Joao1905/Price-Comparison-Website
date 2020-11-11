import scrapy
from scrapy.http import HtmlResponse
from scrapy.http import Request
from ..items import MyItem
import json

class ImageSpider (scrapy.Spider):
	name= 'ImageSpider'

	#Rember to pass -a "search"=unique_model when running the command
	def start_requests (self):
		yield Request("https://www.google.co.in/search?q="+self.search+"&source=lnms&tbm=isch", callback=self.parse_image, meta={'search': self.search})

	def parse_image (self, response):
		item = MyItem()
		url = response.xpath("(//div[@class='rg_meta notranslate']/text())[2]").get()

		try:
			url = json.loads(url)
			url = url.get("ou")
			item["image_urls"] = url
			yield {"URL": url}
		except:
			yield response.follow(response.request.url, callback=self.parse_image)
