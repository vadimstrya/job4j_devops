--liquibase formatted sql
--changeset vadimstrya:create_users_table
CREATE TABLE users
(
  id       SERIAL PRIMARY KEY,
  username VARCHAR(2000)
);
