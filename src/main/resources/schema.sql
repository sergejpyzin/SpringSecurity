CREATE TABLE IF NOT EXISTS product (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       product_name VARCHAR(255) NOT NULL,
                                       price DOUBLE NOT NULL
);