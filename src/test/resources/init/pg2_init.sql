create table if not exists user_table
(
    ldap_login varchar(20) primary key,
    name       varchar(40),
    surname    varchar(40)
);

insert into user_table (ldap_login, name, surname)
values ('pg2_user_id_1', 'Evgeniy', 'Konoplianka'),
       ('pg2_user_id_2', 'Andrey', 'Yarmolenko');