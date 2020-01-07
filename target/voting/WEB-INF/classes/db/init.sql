DROP TABLE voting_history if exists;
DROP TABLE user_roles IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE meals IF EXISTS;
DROP TABLE menus IF EXISTS;
DROP TABLE restaurants IF EXISTS;

CREATE TABLE restaurants
(
    id   INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(255) not null,
    timezone varchar(10) not null

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
    name varchar(255) not null,
    surname varchar(255) not null,
    enabled    BOOLEAN DEFAULT TRUE    NOT NULL,
    registered  TIMESTAMP DEFAULT now() NOT NULL
);

CREATE UNIQUE INDEX login
    on users (login);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    varchar(255) not null ,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE voting_history
(
    user_id        INTEGER           not null,
    restaurant_id INTEGER           not null,
    votedDate     date DEFAULT now() NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX uservote ON voting_history (user_id, votedDate);



