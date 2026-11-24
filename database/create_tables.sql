use flexpath_final;

drop table if exists cookbook_recipes;
drop table if exists cookbooks;
drop table if exists recipes;
drop table if exists categories;
drop table if exists countries;



create table countries (
	id int not null auto_increment primary key,
	name varchar(100) not null unique
);

create table categories (
	id int not null auto_increment primary key ,
	name varchar(100) not null unique
);

create table recipes (
	country_id int not null,
	category_id int not null,
	is_public boolean not null default false,
    id int auto_increment primary key,
    title varchar(150) not null,
    description text,
    ingredients text,
    instructions text,
    prep_time int,
   owner varchar(255) not null,
   created_at timestamp not null default current_timestamp, # for sorting option

constraint fk_recipe_country
	foreign key (country_id)
    references countries (id),
    
constraint fk_recipe_category
	foreign key (category_id)
    references categories (id),
    
constraint fk_recipe_owner
	foreign key (owner)
    references users (username)
    on delete cascade,
    
constraint chk_prep_time
	check (prep_time is null or prep_time >= 0)
    );
    
create table cookbooks (
	id int auto_increment primary key,
	name varchar(150) not null,
	description text,
	is_public boolean not null default false,
	owner varchar(255) not null,
    
constraint fk_cookbook_owner
	foreign key (owner)
    references users(username)
    on delete cascade
);


create table cookbook_recipes (
	cookbook_id int not null,
    recipe_id int not null,
# composite primary key so the same recipe will not be duplicated to the same cookbook 
primary key (cookbook_id, recipe_id),

constraint fk_cr_cookbook
	foreign key (cookbook_id)
    references cookbooks(id)
    on delete cascade,
    
constraint fk_cr_recipe
	foreign key (recipe_id)
    references recipes(id)
    on delete cascade
    
);

describe recipes;

show create table cookbook_recipes;


