CREATE TABLE IF NOT EXISTS user (
            user_id int AUTO_INCREMENT,
            login varchar(20) unique,
            passwd varchar(64),
            email varchar(40),
            last_login datetime not null default now(),
            primary key (user_id)
);