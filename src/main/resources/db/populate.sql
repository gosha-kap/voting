DELETE FROM restaurants;
DELETE FROM meals;
DELETE FROM menus;
DELETE FROM user_roles;
DELETE FROM users;


ALTER TABLE restaurants  ALTER COLUMN ID restart with 1;
ALTER TABLE menus ALTER COLUMN ID restart with 1;
ALTER TABLE meals ALTER COLUMN ID restart with 1;
ALTER TABLE users ALTER COLUMN ID restart with 1;


INSERT INTO users (login,password,name,surname,registered) VALUES
('user1','{noop}pass','name1','surname1','2015-05-30 10:00:00'),
('user2','{noop}pass','name2','surname2','2015-05-30 11:00:00'),
('user3','{noop}pass','name3','surname3','2015-05-30 12:00:00'),
('user4','{noop}pass','name4','surname4','2015-05-30 13:00:00'),
('admin','{noop}111','name5','surname5','2015-05-30 14:00:00');

INSERT INTO user_roles (role, user_id) VALUES
('ROLE_USER', 1),
('ROLE_USER', 2),
('ROLE_USER', 3),
('ROLE_USER', 4),
('ROLE_ADMIN', 5),
('ROLE_USER', 5);


INSERT INTO RESTAURANTS (name,timezone)
VALUES ('Ashan','GMT+07:00'),
       ('Mishan','GMT+11:00'),
       ('Ravshan','GMT+10:00'),
       ('Goshan','GMT+09:00');

INSERT into MENUS (DATE, RESTAURANT_ID, votes)
values ('2015-05-29', 1, 4),
       ('2015-05-29', 2, 1),
       ('2015-05-29', 3, 0),
       ('2015-05-29', 4, 0),

       ('2017-08-29', 1, 3),
       ('2017-08-29', 2, 2),
       ('2017-08-29', 3, 0),
       ('2017-08-29', 4, 0),

       ('2018-08-29', 1, 1),
       ('2018-08-29', 2, 1),
       ('2018-08-29', 3, 2),
       ('2018-08-29', 4, 1),

       (now(), 1, 1),
       (now(), 2, 0),
       (now(), 3, 2);


INSERT INTO meals (DESCRIPTION, PRICE, MENU_ID)
VALUES ('Картошка', 500, 1),
       ('Суп', 1000, 1),
       ('Десерт', 700, 1),
       ('Лапша', 502, 2),
       ('Печень', 1002, 2),
       ('Блины', 702, 2),
       ('Лаваш', 502, 3),
       ('Курица', 1002, 3),
       ('Рис', 702, 3),
       ('Булка', 502, 4),
       ('Пирожок', 1002, 4),
       ('Повидло', 702, 4),

       ('Пюрешка', 500, 5),
       ('Яица', 1000, 5),
       ('Кофе', 700, 5),
       ('Суп', 502, 6),
       ('Лаваш', 1002, 6),
       ('Гречка', 702, 6),
       ('Борщ', 502, 7),
       ('Макароны', 1002, 7),
       ('Водка', 702, 7),
       ('Рыба', 502, 8),
       ('Икра', 1002, 8),
       ('Пиво', 702, 8),

       ('Каша', 500, 9),
       ('Маша', 1000, 9),
       ('Грибы', 700, 9),
       ('Тунец', 502, 10),
       ('Мармелад', 1002, 10),
       ('Индейка', 702, 10),
       ('Ягода', 502, 11),
       ('Горбуша', 1002, 11),
       ('Нут', 702, 11),
       ('Бублик', 502, 12),
       ('Сосиски', 1002, 12),
       ('Утка', 702, 12),

       ('Биг-Мак', 500, 13),
       ('Кола', 1000, 13),
       ('Фри', 700, 13),
       ('Суп', 502, 14),
       ('Креветка', 1002, 14),
       ('Пиво', 702, 14),
       ('Лаваш', 502, 15),
       ('Брутуч', 1002, 15),
       ('Пенце', 702, 15);

insert into VOTING_HISTORY(user_id, restaurant_id) values
(3,3),(4,3),(2,1);

