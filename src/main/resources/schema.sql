CREATE SCHEMA IF NOT EXISTS securecapita;

SET NAMES 'UTF8MB4';
SET TIME_ZONE = '+1';

USE securecapita;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name   VARCHAR(50)     NOT NULL,
    last_name    VARCHAR(50)     NOT NULL,
    email        VARCHAR(100)    NOT NULL,
    password     VARCHAR(255) DEFAULT NULL,
    address      VARCHAR(255) DEFAULT NULL,
    phone        VARCHAR(30)  DEFAULT NULL,
    title        VARCHAR(50)  DEFAULT NULL,
    bio          VARCHAR(255) DEFAULT NULL,
    enabled      BOOLEAN      DEFAULT FALSE,
    non_locked   BOOLEAN      DEFAULT TRUE,
    using_mfa    BOOLEAN      DEFAULT FALSE,
    created_date DATETIME     DEFAULT CURRENT_TIMESTAMP,
    image_url    VARCHAR(255) DEFAULT 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.deviantart.com%2Fharleymk%2Fart%2FMy-Joker-Default-Avatar-289867306&psig=AOvVaw0BGZ8I7uNRgzWnNuN2gXN0&ust=1697813213852000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCPj_rp6tgoIDFQAAAAAdAAAAABAE',
    CONSTRAINT UQ_users_email UNIQUE (email)


);

DROP TABLE IF EXISTS roles;

CREATE TABLE roles
(
    id         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(50)     NOT NULL,
    permission VARCHAR(255)    NOT NULL,
    CONSTRAINT UQ_roles_name UNIQUE (name)


);
DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles
(
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id      BIGINT UNSIGNED NOT NULL,
    role_id      BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE RESTRICT ON UPDATE CASCADE,

    CONSTRAINT UQ_users_roles_id UNIQUE (user_id)


)
