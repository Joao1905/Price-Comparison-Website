select * 
from (
SELECT price_history.price_entry_id, price_history.date, models.model_id, models.product_id, shops.shop_id, shops.name as 'shop_name', models.name, unique_name, designer, manufacturer, DATE_FORMAT(`date`, '%Y-%m-%d %H') as 'formated_date', price, image, product_url, page_visits, page_clicks FROM price_history
	join models
    on price_history.model_id = models.model_id
    join shops
    on shops.shop_id = price_history.shop_id
	where models.unique_name = "YD2200C5FBBOX" and models.product_id=1 and date BETWEEN NOW()-INTERVAL 2 DAY-interval 5 minute and NOW()
	order by price
) t1
where not exists (select * from (
SELECT price_history.price_entry_id, price_history.date, models.model_id, models.product_id, shops.shop_id, shops.name as 'shop_name', models.name, unique_name, designer, manufacturer, DATE_FORMAT(`date`, '%Y-%m-%d %H') as 'formated_date', price, image, product_url, page_visits, page_clicks FROM price_history
	join models
    on price_history.model_id = models.model_id 
    join shops
    on shops.shop_id = price_history.shop_id
	where models.unique_name = "YD2200C5FBBOX" and models.product_id=1 and date BETWEEN NOW()-INTERVAL 2 DAY-interval 5 minute and NOW()
	order by price
) t2 where t2.shop_id = t1.shop_id and (t2.date > t1.date or (t2.date = t1.date and t2.price < t1.price)))