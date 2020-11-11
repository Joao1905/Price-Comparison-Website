select * 
from (
SELECT price, shop_id, DATE_FORMAT(`date`, '%d/%m/%Y') as 'date_format', date from price_history 
	where model_id = "1" and product_id=1 and shop_id="2" and date > now() - interval 180 day
    group by date
    order by date DESC) t1 where not exists (select * from(
SELECT price, shop_id, DATE_FORMAT(`date`, '%d/%m/%Y') as 'date_format', date from price_history 
	where model_id = "1" and product_id=1 and shop_id="2" and date > now() - interval 180 day
    group by date
    order by date DESC) t2 where t2.date_format = t1.date_format and t2.date > t1.date)