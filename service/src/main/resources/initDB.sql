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
	role integer not null,
	foreign key (role) references roles (id)
);

insert into users (login, password, role) values ('admin', 'admin', 1);
insert into users (login, password, role) values ('trainer', 'trainer', 2);
insert into users (login, password, role) values ('user', 'user', 3);

create table groups (
	id serial primary key,
	group_name varchar(45) not null,
	trainer_id integer not null,
	foreign key (trainer_id) references users (id)
);

insert into groups (group_name, trainer_id) values ('test_group',2);

create table group_users (
	id serial primary key,
	user_id integer not null,
	foreign key (user_id) references users (id),
	group_id integer not null,
	foreign key (group_id) references groups (id)
);

insert into group_users (user_id, group_id) values (3,1);