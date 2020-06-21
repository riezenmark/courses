create table role (
    id serial primary key,
    "name" varchar(50) unique not null,
    description text
);

create table rule (
    id serial primary key,
    "name" varchar(50) unique not null,
    description text not null
);

create table role_rules (
    role_id int references role(id),
    rule_id int references rule(id),
    primary key (role_id, rule_id)
);

create table "user" (
    id serial primary key,
    "name" varchar(100) not null,
    surname varchar(100) not null,
    age int check(age > 0 and age < 100) not null,
    role_id int not null default 3 references role(id),
    constraint user_already_added unique("name", surname, age)
);

create table category (
    id serial primary key,
    "name" varchar(50) not null
);

create table state (
    id serial primary key,
    "name" varchar(50) not null,
    description text
);

create table item (
    id serial primary key,
    user_id int references "user"(id) not null,
    category_id int references category(id) not null default 1,
    "text" text,
    state_id int references state(id) not null default 1
);

create table comment (
    id serial primary key,
    "text" text not null,
    item_id int references item(id) not null
);

create table file (
    id serial primary key,
    "name" varchar(100),
    graphic boolean not null default false,
    item_id int references item(id) not null
);

insert into role (name, description) values
    ('admin', 'admin'),
    ('moderator', 'can not make other users moderators'),
    ('guest', 'likes to complain');

insert into rule (name, description) values
    ('make_moderator', 'making another user moderator'),
    ('add_user', 'users adding'),
    ('delete_user', 'deleting user'),
    ('add_item', 'creating new request'),
    ('delete_item', 'removing request');

insert into role_rules values
    (1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
    (2, 2), (2, 3), (2, 4), (2, 5),
    (3, 4), (3, 5);

insert into "user" (name, surname, age, role_id) values
    ('Mark', 'Riezen', 22, 1),
    ('Ivan', 'Ivanov', 23, 2),
    ('Petr', 'Petrov', 26, 2),
    ('Alexandr', 'Alexandrov', 23, 3),
    ('Mihail', 'Mihailov', 19, 3),
    ('Anna', 'Kolotushkina', 24, 3),
    ('Irina', 'Alexandrova', 25, 3),
    ('Alexandra', 'Ivanova', 28, 3);

insert into category (name) values
    ('question'),
    ('offer'),
    ('complaint');

insert into state (name, description) values
    ('unchecked', 'in the queue'),
    ('on_check', 'operator is currently checking the request'),
    ('checked', 'work completed');

insert into item (user_id, category_id, text, state_id) values
    (4, 1, 'some question', 1),
    (4, 2, 'some offer', 2),
    (5, 3, 'some complaint', 3),
    (5, 3, 'some another complaint', 1),
    (6, 2, 'some another offer', 3),
    (6, 1, 'some another question', 3),
    (7, 1, 'a', 1),
    (7, 2, 'b', 1),
    (8, 3, 'c', 3),
    (8, 3, 'd', 1);

insert into comment (text, item_id) values
    ('comment', 1),
    ('another comment', 1),
    ('comment', 5),
    ('comment', 7),
    ('another comment', 7);

insert into file (name, graphic, item_id) values
    ('filename', false, 8),
    ('picture', true, 8)
    ('filename', false, 3);
