-- 코드를 입력하세요
SELECT distinct(a.car_id), a.car_type, ROUND(DAILY_FEE * 30 - (DAILY_FEE * 30 * (DISCOUNT_RATE/100))) as fee
from CAR_RENTAL_COMPANY_CAR a 
inner join CAR_RENTAL_COMPANY_RENTAL_HISTORY b on (a.car_id=b.car_id) 
inner join CAR_RENTAL_COMPANY_DISCOUNT_PLAN c on (a.car_type=c.car_type)
where a.car_type in ('세단','SUV')
and a.car_id not in (select car_id 
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY b
    where start_date 
            between to_date('2022-11-01','YYYY-MM-DD') and to_date('2022-11-30','YYYY-MM-DD')
    or end_date 
            between to_date('2022-11-01','YYYY-MM-DD') and to_date('2022-11-30','YYYY-MM-DD')
    or start_date < to_date('2022-11-01','YYYY-MM-DD')
        and end_date > to_date('2022-11-30','YYYY-MM-DD'))
and ROUND(DAILY_FEE * 30 - (DAILY_FEE * 30 * (DISCOUNT_RATE/100))) between 500000 and 2000000
AND DURATION_TYPE = '30일 이상'
order by fee desc, a.car_type, a.car_id desc;