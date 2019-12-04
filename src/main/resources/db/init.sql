DROP TABLE meals IF EXISTS;
DROP TABLE menus IF EXISTS;
DROP TABLE restaurants IF EXISTS;

CREATE TABLE restaurants
(
    id   INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(255) not null

);
CREATE UNIQUE INDEX name
    on restaurants (name);


CREATE TABLE menus
(
    id            INTEGER IDENTITY PRIMARY KEY,
    date          DATE    default NOW() NOT NULL,
    votes         integer default 0     not null,
    restaurant_id integer               not null ,
    foreign key (restaurant_id) references restaurants(id) on delete cascade

);

CREATE TABLE meals
(
    id          INTEGER IDENTITY PRIMARY KEY,
    description varchar(255) not null,
    price       integer      not null,
    menu_id     integer      not null,
    foreign key (menu_id) references menus (id) on DELETE cascade

);


