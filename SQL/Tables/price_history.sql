CREATE TABLE IF NOT EXISTS price_history (
            price_entry_id int AUTO_INCREMENT,
            date datetime default now(),
            product_id int not null,
            model_id int not null,
            shop_id int not null,
            price real,
            product_url varchar(300),
            page_visits int,
            page_clicks int,
            primary key (price_entry_id),
            foreign key (product_id) references products(product_id),
            foreign key (model_id) references models(model_id),
            foreign key (shop_id) references shops(shop_id)
);