CREATE DATABASE atm_db;

USE atm_db;

CREATE TABLE accounts (
    account_no VARCHAR(10) PRIMARY KEY,
    holder_name VARCHAR(50),
    balance DOUBLE
);

INSERT INTO accounts VALUES
('1001', 'Suresh', 10000),
('1002', 'Ravi', 8000),
('1003', 'Priya', 12000); -- for testing
