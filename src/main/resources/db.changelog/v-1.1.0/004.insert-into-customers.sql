--liquibase formatted sql

--changeset afoninav:1
INSERT INTO customers
(name)
VALUES
('Vasilij'),
('Ivan'),
('Oslan');