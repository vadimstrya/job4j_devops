--liquibase formatted sql
--changeset vtimofeev:add_columns_to_users
alter table users
  add column first_arg  real,
  add column second_arg real,
  add column result     real
;
