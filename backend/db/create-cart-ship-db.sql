USE test_db;

DROP TABLE IF EXISTS cart_ship;

CREATE TABLE IF NOT EXISTS cart_ship (
    cartId INT NOT NULL,
    address INT(11) NOT NULL
    isDelete BIT DEFAULT 0,
    status INT(1)
);