-- DROP DATABASE IF EXISTS test_db;   
-- CREATE DATABASE IF NOT EXISTS test_db;   
USE test_db;

DROP TABLE IF EXISTS cart;

CREATE TABLE IF NOT EXISTS cart (
    cartId INT PRIMARY KEY auto_increment,
    quantity INT(11) NOT NULL,
    userId INT UNIQUE NOT NULL
);
