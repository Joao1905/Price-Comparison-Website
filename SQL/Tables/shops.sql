CREATE TABLE IF NOT EXISTS shops (
            shop_id int AUTO_INCREMENT,
            name varchar(20) unique,
            website varchar(64) unique,
            primary key (shop_id)
);