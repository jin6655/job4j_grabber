select  p.id as ID, p.name as Сотрудник, c.name as Компания
from person as p
join company as c
on p.company_id = c.id AND c.id != 5;
WITH table_count AS (
select c.id as id, c.name as name, count(p.id) as count
from company as c
join person as p
on c.id = p.company_id
group by c.name, c.id
)
select * from table_count where count = (select max(count) from table_count);
WITH table_1 AS (
select me.id as id, count(mm.*) as count
from meet as me
join members as mm
on mm.meet_id = me.id
group by me.id
)
select me.name as Мероприятие, t.count as Заявок_всего, count(mm.*) Участников
from meet as me
join table_1 as t
on t. id = me.id
join members as mm
on mm.meet_id = me.id AND mm.partic = true
group by me.name, t.count;
select me.name as Мероприятия_без_заявок, count(mm.partic) as Заявок
from meet as me
left join members as mm
on mm.meet_id = me.id
group by me.name
having count(mm.partic) = 0;