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

INSERT INTO customers
(name)
VALUES
('Nikolay'),
('Aleksej'),
('Oleg');

SELECT * FROM customers;

INSERT INTO customers (name) VALUES ('Nikolaj') RETURNING customer_id;

SELECT nextval('customers_customer_id_seq');

SELECT * FROM customers WHERE customer_id = 27;

UPDATE customers SET name = 'John' WHERE customer_id = 27;

DELETE FROM customers WHERE customer_id = 34;

ALTER TABLE customers ADD PRIMARY KEY (customer_id);

-- create sequence
CREATE SEQUENCE user002_customer_id_seq OWNED BY user002.customers.customer_id;

-- set the current value of the sequence to the max value from that column
-- (id column in this scenario)
SELECT SETVAL('user002_customer_id_seq', (select max(customer_id) from user002.customers), true);

-- use sequence for the target column
ALTER TABLE user002.customers ALTER COLUMN customer_id SET DEFAULT nextval('user002_customer_id_seq');