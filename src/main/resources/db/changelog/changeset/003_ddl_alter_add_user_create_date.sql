--liquibase formatted sql
--changeset vadimstrya:add_user_create_date

alter table users
  add column create_date timestamp without time zone default now();

--rollback ALTER TABLE users DROP COLUMN create_date;
