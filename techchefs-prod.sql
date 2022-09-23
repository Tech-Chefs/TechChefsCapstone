drop database if exists meal_curator;
create database meal_curator;

use meal_curator;

drop table if exists ingredient;
create table ingredient (
	id int primary key auto_increment,
    name varchar(50) not null,
    parent_id int,
    contains_dairy tinyint(1) not null,
    nut_based tinyint(1) not null,
    meat tinyint(1) not null,
    fish tinyint(1) not null,
    animal_based tinyint(1) not null,
    contains_gluten tinyint(1) not null,
    kosher tinyint(1) not null,
    contains_egg tinyint(1) not null,
    contains_soy tinyint(1) not null
);

drop table if exists recipe;
create table recipe (
	id int primary key auto_increment,
    name varchar(50) not null,
    description varchar(1000) not null
);

drop table if exists app_user;
create table app_user (
	id int primary key auto_increment,
    username varchar(50) not null unique,
    password_hash varchar(2048) not null
);

drop table if exists unit_unit;
drop table if exists unit;
create table unit (
	id int primary key auto_increment,
    short_name varchar(10) not null,
    long_name varchar(50) not null
);

drop table if exists unit_unit;
create table unit_unit (
	unit_1_id int,
    unit_2_id int,
    factor decimal(5) not null,
    constraint fk_unit_1_id
		foreign key (unit_1_id)
        references unit(id),
	constraint fk_unit_2_id
		foreign key (unit_2_id)
        references unit(id)
);

drop table if exists ingredient_unit_unit;
create table ingredient_unit_unit (
	ingredient_id int,
    unit_1_id int,
    unit_2_id int,
    factor decimal(5) not null,
    constraint fk_ingredient_id
		foreign key (ingredient_id)
        references ingredient(id),
    constraint fk_unit_1_id
		foreign key (unit_1_id)
        references unit(id),
	constraint fk_unit_2_id
		foreign key (unit_2_id)
        references unit(id)
);

drop table if exists recipe_ingredient;
CREATE TABLE recipe_ingredient (
  `recipe_id` INT NOT NULL,
  `ingredient_id` INT NOT NULL,
  `quantity` DECIMAL(5) NOT NULL,
  `unit_id` INT NOT NULL,
  `optional` TINYINT(1) NOT NULL,
  `preparation` VARCHAR(45),
  PRIMARY KEY (`recipe_id`, `ingredient_id`),
  CONSTRAINT `fk_recipe_id`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `mydb`.`recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ingredient_id`
    FOREIGN KEY (`ingredient_id`)
    REFERENCES ingredient (`id`),
  CONSTRAINT `fk_unit_id`
    FOREIGN KEY (`unit_id`)
    REFERENCES unit (`id`)
);

DROP TABLE IF EXISTS `shopping_list` ;
CREATE TABLE `shopping_list` (
  `id` INT primary key,
  `date_added` DATETIME NOT NULL,
  `user_id` INT NOT NULL,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `app_user` (`id`)
);

DROP TABLE IF EXISTS `list_item` ;
CREATE TABLE `list_item` (
  `list_id` INT NOT NULL,
  `ingredient_id` INT NOT NULL,
  `quantity` DECIMAL(5) NOT NULL,
  `unit_id` INT NOT NULL,
  PRIMARY KEY (`list_id`, `ingredient_id`),
  CONSTRAINT `fk_list_id`
    FOREIGN KEY (`list_id`)
    REFERENCES `shopping_list` (`id`),
  CONSTRAINT `fk_ingredient_id`
    FOREIGN KEY (`ingredient_id`)
    REFERENCES `ingredient` (`id`),
  CONSTRAINT `fk_unit_id`
    FOREIGN KEY (`unit_id`)
    REFERENCES `unit` (`id`)
);

insert into unit (short_name, long_name) values
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