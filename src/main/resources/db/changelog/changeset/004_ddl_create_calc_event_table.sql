--liquibase formatted sql
--changeset vtimofeev:create_calc_event_table
create table calc_event
(
  id          serial primary key                        not null,
  user_id     integer references users (id)             not null,
  first_arg   real,
  second_arg  real,
  type        varchar check (type in ('ADDITION', 'SUBTRACTION', 'MULTIPLICATION', 'DIVISION')),
  result      real,
  create_date timestamp without time zone default now() not null
);
