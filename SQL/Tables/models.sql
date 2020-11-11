CREATE TABLE IF NOT EXISTS models (
	model_id int AUTO_INCREMENT,
	product_id int not null,
	name varchar(100),
	unique_name varchar(40) unique,
	designer varchar(20),
	manufacturer varchar(30),
	image varchar(45),
	primary key (model_id),
	foreign key (product_id) references products(product_id) 
);
