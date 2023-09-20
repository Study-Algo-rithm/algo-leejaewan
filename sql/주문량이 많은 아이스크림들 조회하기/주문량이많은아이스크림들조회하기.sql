-- 코드를 입력하세요
select flavor
from (select a.flavor, 
      rank() over (order by (a.total_order+b.total_order)desc) as rank
from first_half a inner join 
(select flavor,sum(total_order) as total_order from july group by flavor) b
on a.flavor=b.flavor)
where rank<=3;