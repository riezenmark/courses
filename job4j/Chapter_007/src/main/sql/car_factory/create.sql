create table carcase (
    id serial primary key,
    type varchar(20) check(type in ('passenger', 'cargo')) default 'cargo',
    manufacturer varchar(20) not null,
    description text default 'test'
);

create table engine (
    id serial primary key,
    fuel varchar(20) check(fuel in ('gasoline', 'diesel')) default 'gasoline',
    manufacturer varchar(20) not null,
    description text default 'test'
);

create table transmission (
    id serial primary key,
    type varchar(20) check(type in ('auto', 'mechanic')) default 'mechanic',
    manufacturer varchar(20) not null,
    description text default 'test'
);

create table car (
    number serial primary key,
    carcase_id int references carcase,
    engine_id int references engine,
    transmission_id int references transmission
);

insert into carcase (type, manufacturer) values
    ('passenger', 'ford'),
    (default, 'ford'),
    ('passenger', 'volvo'),
    (default, 'volvo');

insert into engine (fuel, manufacturer) values
    (default, 'ford'),
    ('diesel', 'ford'),
    (default, 'volvo'),
    ('diesel', 'volvo');

insert into transmission (type, manufacturer) values
    ('auto', 'ford'),
    (default, 'ford'),
    ('auto', 'volvo'),
    (default, 'volvo');

insert into car (carcase_id, engine_id, transmission_id) values
    (1, 2, 3),
    (2, 3, 4),
    (3, 4, 1),
    (1, 3, 4),
    (2, 2, 3),
    (3, 2, 3),
    (3, 3, 4),
    (3, 3, 1),
    (3, 2, 1);