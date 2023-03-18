CREATE ROLE user002 WITH nosuperuser login PASSWORD 'qwerty';
CREATE DATABASE testdb OWNER user002;
CREATE SCHEMA user002 AUTHORIZATION user002;
GRANT ALL ON ALL TABLES IN SCHEMA user002 to user002;
GRANT USAGE ON ALL TABLES IN SCHEMA user002 to user002;
SHOW search_path;
SET search_path TO "$user",public;

DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
	customer_id serial PRIMARY KEY,
	name varchar(255)
);