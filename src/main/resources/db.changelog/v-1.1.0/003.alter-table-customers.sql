--liquibase formatted sql

--changeset afoninav:1
ALTER TABLE customers ADD PRIMARY KEY (customer_id);


CREATE SEQUENCE user002_customer_id_seq OWNED BY user002.customers.customer_id;


SELECT SETVAL('user002_customer_id_seq', (select max(customer_id) from user002.customers), true);


ALTER TABLE user002.customers ALTER COLUMN customer_id SET DEFAULT nextval('user002_customer_id_seq');