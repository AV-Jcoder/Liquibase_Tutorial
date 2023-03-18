--liquibase formatted sql

--changeset afoninav:1
CREATE TABLE customers (
	customer_id integer,
	name varchar(255)
);