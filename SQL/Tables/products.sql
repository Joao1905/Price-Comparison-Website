CREATE TABLE IF NOT EXISTS products (
            product_id int AUTO_INCREMENT,
            name varchar(20) unique,
            primary key (product_id)
);