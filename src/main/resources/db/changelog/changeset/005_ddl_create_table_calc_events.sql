--liquibase formatted sql
--changeset vadimstrya:create_table_calc_events
CREATE TABLE calc_events
(
  id          serial primary key                                                                    not null,
  user_id     integer references users (id)                                                         not null,
  type        varchar(32) check (type in ('ADDITION', 'SUBTRACTION', 'MULTIPLICATION', 'DIVISION')) not null,
  first_arg   real,
  second_arg  real,
  result      real,
  create_date timestamp without time zone default now()                                             not null
);
