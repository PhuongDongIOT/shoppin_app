USE test_db;

DROP TABLE IF EXISTS cart_item;

CREATE TABLE IF NOT EXISTS cart_item (
    cartItemId INT NOT NULL,
    cartId INT NOT NULL,
    quantity INT(11) NOT NULL
    isDelete BIT DEFAULT 0
);