use meal_curator;

delete from ingredient where id > 0;
alter table ingredient auto_increment = 0;

insert into ingredient (name, contains_dairy, nut_based, meat, fish, animal_based, contains_gluten, contains_egg, contains_soy) 
select
name,
containts_dairy,
nut_based,
meat,
fish,
animal_based,
contains_gluten,
contains_egg,
contains_soy
from ingredients_data;

update ingredient i1
inner join ingredients_data d
on i1.name = d.name
inner join ingredient i2
on d.parent_name = i2.name
set i1.parent_id = i2.id
where i1.id > 0;

select * from ingredient;