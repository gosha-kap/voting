DELETE
FROM restaurants;
DELETE
FROM meals;
DELETE
FROM menus;


ALTER TABLE restaurants
    ALTER COLUMN ID restart with 1;
ALTER TABLE MENUS
    ALTER COLUMN ID restart with 1;
ALTER TABLE MEALS ALTER COLUMN ID restart with 1;

INSERT INTO RESTAURANTS (name)
VALUES ('Ashan'),
       ('Mishan');

INSERT into MENUS (DATE,RESTAURANT_ID)
values (now(),1),
       (now(),2),
       ('2015-05-29',2);



INSERT INTO meals (DESCRIPTION, PRICE, MENU_ID)
VALUES ('Картошка', 500, 1),
       ('Суп', 1000, 1),
       ('Десерт', 700, 1),
       ('Лапша', 502, 2),
       ('Печень', 1002, 2),
       ('Блины', 702, 2),
       ('Лаваш', 502, 3),
       ('Курица', 1002, 3),
       ('Рис', 702, 3);
