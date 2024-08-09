create table if not exists users_table
(
    user_id    varchar(20) primary key,
    user_login      varchar(20),
    user_name varchar(40),
    user_surname  varchar(40)
);

insert into users_table (user_id, user_login, user_name, user_surname)
values ('mysql1_user_id_1', 'sheva7', 'Andrey', 'Shevchenko'),
       ('mysql1_user_id_2', 'paolo_maldini', 'Paolo', 'Maldini'),
       ('mysql1_user_id_3', 'pirlo21', 'Andrea', 'Pirlo');