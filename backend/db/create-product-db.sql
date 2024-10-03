-- DROP DATABASE IF EXISTS test_db;   
-- CREATE DATABASE IF NOT EXISTS test_db;   
USE test_db;

DROP TABLE IF EXISTS product;

CREATE TABLE IF NOT EXISTS product (
    id INT PRIMARY KEY auto_increment,
    title VARCHAR(25) UNIQUE NOT NULL,
    price INT(11) NOT NULL,
    description VARCHAR(50) NOT NULL,
    imageUrl VARCHAR(50) NOT NULL,
    userId INT UNIQUE NOT NULL
);
