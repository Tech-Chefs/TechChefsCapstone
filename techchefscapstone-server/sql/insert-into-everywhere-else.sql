use meal_curator;

delete from recipe_ingredient_substitute where recipe_id > 0 and primary_ingredient_id > 0 and substitute_ingredient_id > 0;
delete from recipe_ingredient where recipe_id > 0 and ingredient_id > 0;
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

delete from recipe_direction where recipe_id > 0 and step_num > 0;

delete from recipe where id > 0;
alter table recipe auto_increment = 0;

insert into recipe (user_id, name, description)
    select
        u.app_user_id,
        d.name,
        d.description
    from recipes_data d
    inner join app_user u
    on d.username = u.username;
    
select * from recipe;

insert into recipe_ingredient (recipe_id, ingredient_id, unit_id, quantity, optional, preparation)
    select
        r.id,
        i.id,
        u.id,
        d.quantity,
        d.optional,
        d.preparation
    from recipe_ingredients_data d
    inner join recipe r
    on d.recipe_name = r.name
    inner join ingredient i
    on d.ingredient_name = i.name
    inner join unit u
    on d.unit_short_name = u.short_name;
    
select * from recipe_ingredient;

insert into recipe_ingredient_substitute (recipe_id, primary_ingredient_id, substitute_ingredient_id)
    select
        r.id,
        i1.id,
        i2.id
    from substitutions_data d
    inner join recipe r
    on d.recipe_name = r.name
    inner join ingredient i1
    on d.primary_ingredient_name = i1.name
    inner join ingredient i2
    on d.substitution_ingredient_name = i2.name;
    
select * from recipe_ingredient_substitute;

insert into recipe_direction (recipe_id, step_num, instruction)
    select
        r.id,
        d.step_num,
        d.instruction
    from directions_data d
    inner join recipe r
    on d.recipe_name = r.name;
    
select * from recipe_direction;