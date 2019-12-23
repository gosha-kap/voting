DROP TABLE voting_history if exists;
DROP TABLE user_roles IF EXISTS;
DROP TABLE users IF EXISTS;
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
    restaurant_id integer               not null,
    foreign key (restaurant_id) references restaurants (id) on delete cascade

);

CREATE TABLE meals
(
    id          INTEGER IDENTITY PRIMARY KEY,
    description varchar(255) not null,
    price       integer      not null,
    menu_id     integer      not null,
    foreign key (menu_id) references menus (id) on DELETE cascade

);

CREATE TABLE users
(
    id       INTEGER IDENTITY PRIMARY KEY,
    login    varchar(255) not null,
    password varchar(255) not null,
    enabled    BOOLEAN DEFAULT TRUE    NOT NULL,
    registered  TIMESTAMP DEFAULT now() NOT NULL
);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    varchar(255) not null ,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE voting_history
(
    user_id        INTEGER                 not null,
    restaurant_val INTEGER           not null,
    date           TIMESTAMP DEFAULT now() NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_val) REFERENCES restaurants (id) ON DELETE CASCADE
);



