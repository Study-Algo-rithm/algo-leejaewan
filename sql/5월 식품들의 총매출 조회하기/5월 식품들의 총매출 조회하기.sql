-- 코드를 입력하세요
SELECT a.product_id, product_name, sum(price*amount) as total_sales
from food_order a inner join food_product b on (a.product_id=b.product_id)
where produce_date between to_date('2022-05-01','YYYY-MM-DD') and to_date('2022-05-31','YYYY-MM-DD')
group by a.product_id,product_name
order by total_sales desc, a.product_id