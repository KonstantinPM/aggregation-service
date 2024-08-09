create table if not exists users (
    user_id     varchar(20) primary key,
    login       varchar(20),
    first_name  varchar(40),
    last_name   varchar(40)
);

insert into users (user_id, login, first_name, last_name) values
    ('pg1_user_id_1', 'cr7', 'Cristiano', 'Ronaldo'),
    ('pg1_user_id_2', 'leo10', 'Lionel', 'Messi');