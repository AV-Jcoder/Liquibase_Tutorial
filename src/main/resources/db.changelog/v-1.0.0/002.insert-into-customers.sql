--liquibase formatted sql

--changeset afoninav:1
INSERT INTO customers
(customer_id, name)
VALUES
(1, 'Nikolay'),
(2, 'Aleksej'),
(3, 'Oleg');