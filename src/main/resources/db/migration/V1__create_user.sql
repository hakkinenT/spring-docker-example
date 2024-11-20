CREATE TABLE tb_user(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);