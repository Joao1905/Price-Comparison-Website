select * 
from (
SELECT models.model_id, models.product_id, shop_id, name, unique_name, designer, date, manufacturer, DATE_FORMAT(`date`, '%Y-%m-%d %H') as 'formated_date', price, image FROM price_history
	join models
    on price_history.model_id = models.model_id 
	where price < "10000" and INSTR(name, "2080") > 0 and date BETWEEN NOW()-INTERVAL 2 DAY-interval 5 minute and NOW()
) t1
where not exists (select * from (
SELECT models.model_id, models.product_id, shop_id, name, unique_name, designer, date, manufacturer, DATE_FORMAT(`date`, '%Y-%m-%d %H') as 'formated_date', price, image FROM price_history
	join models
    on price_history.model_id = models.model_id 
	where price < "10000" and INSTR(name, "2080") > 0 and date BETWEEN NOW()-INTERVAL 2 DAY-interval 5 minute and NOW()
) t2 where t2.model_id = t1.model_id and (t2.date > t1.date or (t2.date = t1.date and t2.price < t1.price)))