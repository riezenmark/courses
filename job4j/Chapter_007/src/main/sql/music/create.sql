create table author (
    id serial primary key,
    "name" varchar(100)
);

create table composition (
    id serial primary key,
    "name" varchar(100),
    author_id int references author(id)
);

create table music_lover (
    id serial primary key,
    "name" varchar(100)
);

create table music_lovers_favourite_compositions (
    id serial primary key,
    music_lover_id int references music_lover(id),
    composition_id int references composition(id)
);
