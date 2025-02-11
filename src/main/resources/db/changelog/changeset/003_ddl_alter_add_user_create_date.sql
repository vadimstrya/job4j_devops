--liquibase formatted sql
--changeset vtimofeev:add_user_create_date

ALTER TABLE users
  ADD COLUMN create_date TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL;

--rollback ALTER TABLE users DROP COLUMN create_date;
