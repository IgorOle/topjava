DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);


INSERT INTO meals (id, date_time, description, calories, user_id) VALUES
    (100002, '2018-09-04 09:14:00.000000', 'завтрак', 500, 100000),
    (100003, '2018-09-04 12:15:00.000000', 'обед', 1000, 100000),
    (100004, '2018-09-04 18:15:00.000000', 'ужин', 1500, 100000),
    (100005, '2018-09-05 08:15:00.000000', 'завтрак1', 400, 100001),
    (100006, '2018-09-05 12:16:00.000000', 'обед1', 800, 100001);

ALTER SEQUENCE global_seq RESTART WITH 100009;