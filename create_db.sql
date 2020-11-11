CREATE DATABASE internet_shop;
USE internet_shop;


CREATE TABLE product
(
id INT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(20) NOT NULL,
price DOUBLE NOT NULL,
image VARCHAR(50) NOT NULL
);


CREATE TABLE user
(
login VARCHAR(20) PRIMARY KEY,
password VARCHAR(100) NOT NULL,
role VARCHAR(10) DEFAULT 'USER',
cart_id INT
);
INSERT INTO  user (login, password, role)
VALUES ('admin', '$2y$12$dGy5TaOApsFDuLhoJLbzzOLVa9L5F.TDEY3J477da4vhq/F.B5DPe', 'ADMIN');


CREATE TABLE cart
(
id INT PRIMARY KEY AUTO_INCREMENT,
total_products INT DEFAULT NULL,
total_cost DOUBLE DEFAULT NULL
);


CREATE TABLE cart_product
(
cart_id INT,
product_id INT,
quantity INT
);