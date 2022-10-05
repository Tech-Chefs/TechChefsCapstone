/*drop database if exists meal_curator;
create database meal_curator;*/

use meal_curator;

DROP TABLE IF EXISTS `list_item` ;
DROP TABLE IF EXISTS `shopping_list` ;
drop table if exists recipe_ingredient_substitute;
drop table if exists recipe_ingredient;
drop table if exists recipe_direction;
drop table if exists recipe;
 drop table if exists ingredient_unit_unit;
drop table if exists ingredient;
drop table if exists unit_unit;
drop table if exists unit;
drop table if exists app_user_role;
drop table if exists app_user;
drop table if exists app_role;

create table app_user (
    app_user_id int primary key auto_increment,
    username varchar(50) not null unique,
    password_hash varchar(2048) not null,
    disabled bit not null default(0)
);

select * from recipe_direction where recipe_id = 20;

create table app_role (
    app_role_id int primary key auto_increment,
    `name` varchar(50) not null unique
);

create table app_user_role (
    app_user_id int not null,
    app_role_id int not null,
    constraint pk_app_user_role
        primary key (app_user_id, app_role_id),
    constraint fk_app_user_role_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
    constraint fk_app_user_role_role_id
        foreign key (app_role_id)
        references app_role(app_role_id)
);

create table ingredient (
	id int primary key auto_increment,
    name varchar(50) not null,
    user_id int,
    parent_id int,
    contains_dairy bit not null,
    nut_based bit not null,
    meat bit not null,
    fish bit not null,
    animal_based bit not null,
    contains_gluten bit not null,
    contains_egg bit not null,
    contains_soy bit not null,
    constraint fk_ingredient_user_id
		foreign key (user_id)
        references app_user (app_user_id)
);

create table recipe (
	id int primary key auto_increment,
    user_id int not null,
    name varchar(50) not null,
    description varchar(2000) not null,
    constraint fk_recipe_user_id
		foreign key (user_id)
        references app_user (app_user_id)
);

drop table if exists unit;
create table unit (
	id int primary key auto_increment,
    short_name varchar(10) not null,
    long_name varchar(50) not null
);

create table unit_unit (
	unit_1_id int,
    unit_2_id int,
    factor decimal(7,2) not null,
    constraint fk_unit_1_id
		foreign key (unit_1_id)
        references unit(id),
	constraint fk_unit_2_id
		foreign key (unit_2_id)
        references unit(id)
);

create table ingredient_unit_unit (
	ingredient_id int,
    unit_1_id int,
    unit_2_id int,
    factor decimal(7,2) not null,
    constraint fk_unit_ingredient_id
		foreign key (ingredient_id)
        references ingredient(id),
    constraint fk_ingredient_unit_1_id
		foreign key (unit_1_id)
        references unit(id),
	constraint fk_ingredient_unit_2_id
		foreign key (unit_2_id)
        references unit(id)
);

CREATE TABLE recipe_ingredient (
  `recipe_id` INT NOT NULL,
  `ingredient_id` INT NOT NULL,
  `quantity` DECIMAL(7, 4) NOT NULL,
  `unit_id` INT NOT NULL,
  `optional` bit NOT NULL,
  `preparation` VARCHAR(45),
  PRIMARY KEY (`recipe_id`, `ingredient_id`),
  CONSTRAINT `fk_ingredient_recipe_id`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipe_ingredient_id`
    FOREIGN KEY (`ingredient_id`)
    REFERENCES ingredient (`id`),
  CONSTRAINT `fk_unit_id`
    FOREIGN KEY (`unit_id`)
    REFERENCES unit (`id`)
);

create table recipe_ingredient_substitute (
	recipe_id int not null,
    primary_ingredient_id int not null,
    substitute_ingredient_id int not null,
    primary key (recipe_id, primary_ingredient_id, substitute_ingredient_id),
    foreign key (recipe_id) references recipe (id),
    foreign key (primary_ingredient_id) references ingredient (id),
    foreign key (substitute_ingredient_id) references ingredient (id)
);

create table recipe_direction (
	recipe_id int not null,
    step_num int not null,
    instruction varchar(500),
    primary key (recipe_id, step_num),
    foreign key (recipe_id) references recipe (id)
);

CREATE TABLE `shopping_list` (
  `id` INT primary key,
  `date_added` DATETIME NOT NULL,
  `user_id` INT NOT NULL,
  CONSTRAINT `fk_list_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `app_user` (`app_user_id`)
);

CREATE TABLE `list_item` (
  `list_id` INT NOT NULL,
  `ingredient_id` INT NOT NULL,
  `quantity` DECIMAL(5) NOT NULL,
  `unit_id` INT NOT NULL,
  PRIMARY KEY (`list_id`, `ingredient_id`),
  CONSTRAINT `fk_list_id`
    FOREIGN KEY (`list_id`)
    REFERENCES `shopping_list` (`id`),
  CONSTRAINT `fk_list_ingredient_id`
    FOREIGN KEY (`ingredient_id`)
    REFERENCES `ingredient` (`id`),
  CONSTRAINT `fk_list_unit_id`
    FOREIGN KEY (`unit_id`)
    REFERENCES `unit` (`id`)
);

insert into unit (short_name, long_name) values
	('whole', 'whole'),
	('tsp', 'teaspoon'),
    ('tbsp', 'tablespoon'),
    ('floz', 'fluid ounce'),
    ('cup', 'cup'),
    ('pt', 'pint'),
    ('qt', 'quart'),
    ('gal', 'gallon'),
    ('ml', 'milliliter'),
    ('L', 'liter'),
    ('g', 'gram'),
    ('oz', 'ounce'),
    ('kg', 'kilogram'),
    ('lb', 'pound');
    
insert into unit_unit (unit_1_id, unit_2_id, factor) values
	((select id from unit where short_name = 'tsp'),
    (select id from unit where short_name = 'tbsp'), 3),
    ((select id from unit where short_name = 'tbsp'),
    (select id from unit where short_name = 'floz'), 2),
    ((select id from unit where short_name = 'tbsp'),
    (select id from unit where short_name = 'cup'), 16),
    ((select id from unit where short_name = 'cup'),
    (select id from unit where short_name = 'pt'), 2),
    ((select id from unit where short_name = 'pt'),
    (select id from unit where short_name = 'qt'), 2),
    ((select id from unit where short_name = 'ml'),
    (select id from unit where short_name = 'tbsp'), 15),
    ((select id from unit where short_name = 'ml'),
    (select id from unit where short_name = 'pt'), 500),
    ((select id from unit where short_name = 'ml'),
    (select id from unit where short_name = 'L'), 1000),
    ((select id from unit where short_name = 'L'),
    (select id from unit where short_name = 'qt'), 1),
    ((select id from unit where short_name = 'oz'),
    (select id from unit where short_name = 'g'), 0.35),
    ((select id from unit where short_name = 'lb'),
    (select id from unit where short_name = 'kg'), 2.2);

-- passwords are set to "P@ssw0rd!"    
insert into app_user (username, password_hash, disabled)
	values
    ('marySue82', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0),
    ('johnDoe64', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0),
    ('anneMann99', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0),
    ('admin1', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0);
    
insert into app_role (`name`) values
    ('USER'),
    ('ADMIN');
    
insert into app_user_role (app_user_id, app_role_id) values
	(1, 1),
    (2, 1),
    (3, 1),
    (4, 2);