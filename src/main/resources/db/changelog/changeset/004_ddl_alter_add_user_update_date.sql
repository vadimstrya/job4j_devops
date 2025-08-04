--liquibase formatted sql
--changeset vadimstrya:add_user_update_date

alter table users
  add column update_date timestamp without time zone default now();

--rollback ALTER TABLE users DROP COLUMN update_date;
