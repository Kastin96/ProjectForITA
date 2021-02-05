create database homework;

create table roles(
	id serial primary key,
	role VARCHAR(45) not null
);

insert into roles (role) values ('admin');
insert into roles (role) values ('trainer');
insert into roles (role) values ('user');

create table users (
	id serial primary key,
	login varchar(45) unique not null,
	password varchar(45) not null,
	full_name varchar(60),
	age integer,
	role integer not null,
	foreign key (role) references roles (id)
);

create table trainer_salary (
	id serial primary key,
	trainer_id integer not null,
	foreign key (trainer_id) references users (id),
	salary integer not null

);

create table groups (
	id serial primary key,
	group_name varchar(45) unique not null,
	trainer_id integer unique not null,
	foreign key (trainer_id) references users (id)
);

create table group_users (
	id serial primary key,
	user_id integer not null,
	foreign key (user_id) references users (id),
	group_id integer not null,
	foreign key (group_id) references groups (id)
);