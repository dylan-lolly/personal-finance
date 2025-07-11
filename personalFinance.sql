-- database personalFinance
BEGIN TRANSACTION;

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS bank;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS time_span;
DROP TABLE IF EXISTS account_type;
DROP TABLE IF EXISTS payment_type;
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS planned_expense;
DROP TABLE IF EXISTS recurring_payment;

-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************

-- users (name is pluralized because 'user' is a SQL keyword)
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	name varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

-- bank
CREATE TABLE bank (
	bank_id SERIAL,
	name varchar(70) NOT NULL UNIQUE,
	nickname varchar(200) NULL,
	user_id int NOT NULL,
	CONSTRAINT PK_bank PRIMARY KEY (bank_id),
    CONSTRAINT FK_bank_users FOREIGN KEY (user_id) REFERENCES (user_id)
);

-- account
CREATE TABLE account (
	account_id SERIAL,
	account_number varchar(25) NULL UNIQUE,
	routing_number varchar(12) NULL,
	name varchar (200) NOT NULL,
	due_balance decimal(20,2) NOT NULL,
	total_balance decimal (20,2) NULL,
    apr decimal (10,2) NULL,
    apy decimal (10,2) NULL,
    payment_due_date DATE NULL,
    account_type_id int NOT NULL,
    bank_id int NULL,
	user_id int NOT NULL,
	CONSTRAINT PK_product PRIMARY KEY (product_id),
    CONSTRAINT FK_account_users FOREIGN KEY (user_id) REFERENCES (user_id),
    CONSTRAINT FK_account_bank FOREIGN KEY (bank_id) REFERENCES (bank_id),
    CONSTRAINT FK_account_account_type FOREIGN KEY (account_type_id) REFERENCES (account_type_id)
);
-- For credit cards derive available balance from total - due

-- category
CREATE TABLE category (
	category_id SERIAL,
	name varchar(50) NOT NULL,
    essential boolean NOT NULL,
    user_id int NOT NULL,
	CONSTRAINT PK_category PRIMARY KEY (category_id),
	CONSTRAINT FK_category_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- time_span
CREATE TABLE time_span (
	time_span_id SERIAL,
	name varchar (50) NOT NULL,
	multiple decimal (5,3) NOT NULL,
	CONSTRAINT PK_time_span PRIMARY KEY (time_span_id)
);

-- account_type
CREATE TABLE account_type (
	account_type_id SERIAL,
	name varchar (50) NOT NULL,
	CONSTRAINT PK_account_type PRIMARY KEY (account_type_id)
);

-- payment_type
CREATE TABLE payment_type (
	payment_type_id SERIAL,
	name varchar (50) NOT NULL,
	CONSTRAINT PK_payment_type PRIMARY KEY (payment_type_id)
);

-- transactions (name is pluralized because 'transaction' is a SQL keyword)
CREATE TABLE transactions (
	transactions_id SERIAL,
	transaction_date DATE NOT NULL,
	amount decimal (10,2) NOT NULL,
	vendor varchar (70) NULL,
	transaction_desc varchar (500) NULL,
	account_id int NOT NULL,
	category_id int NOT NULL,
	user_id int NOT NULL,
	CONSTRAINT PK_transactions PRIMARY KEY (transactions_id),
	CONSTRAINT FK_transactions_account FOREIGN KEY (account_id) REFERENCES (account_id),
	CONSTRAINT FK_transactions_category FOREIGN KEY (category_id) REFERENCES (category_id),
	CONSTRAINT FK_transactions_user FOREIGN KEY (user_id) REFERENCES (user_id)
);

-- planned_expense
CREATE TABLE planned_expenses (
	planned_expense_id SERIAL,
	frequency int NOT NULL,
	time_span_id int NOT NULL,
	single_amount decimal (10,2) NOT NULL,
	category_id int NOT NULL,
	active boolean NOT NULL,
	expense_desc varchar (500) NULL,
	user_id int NOT NULL
	CONSTRAINT PK_planned_expense PRIMARY KEY (planned_expense_id),
	CONSTRAINT FK_planned_expense_time_span FOREIGN KEY (time_span_id) REFERENCES (time_span_id),
	CONSTRAINT FK_planned_expense_category FOREIGN KEY (category_id) REFERENCES (category_id),
	CONSTRAINT FK_planned_expense_user FOREIGN KEY (user_id) REFERENCES (user_id)
);
-- Derive a 'per_month_frequency decimal (10,2)' collumn when collecting 'frequency' and 'time-span' by mulitplying the frequency by the time-span multiple.
-- This will be used to find the 'monthly_amount decimal (10,2)' 

-- recurring_payment
CREATE TABLE recurring_payment (
	recurring_payment_id SERIAL,
	day_of_month_due int NULL,
	account_id int NOT NULL,
	amount decimal (10,2) NOT NULL,
	category_id int NOT NULL,
	payment_type_id int NOT NULL,
	active boolean NOT NULL,
	payment_desc varchar (500) NULL,
	link varchar (500) NULL,
	user_id int NOT NULL
	CONSTRAINT PK_recurring_payment PRIMARY KEY (recurring_payment_id),
	CONSTRAINT FK_reccuring_payment_account FOREIGN KEY (account_id) REFERENCES (account_id),
	CONSTRAINT FK_reccuring_payment_category FOREIGN KEY (category_id) REFERENCES (category_id),
	CONSTRAINT FK_reccuring_payment_payment_type FOREIGN KEY (recurring_payment_id) REFERENCES (recurring_payment_id),
	CONSTRAINT FK_reccuring_payment_user FOREIGN KEY (user_id) REFERENCES (user_id)
);

-- *************************************************************************************************
-- Insert some sample starting data
-- *************************************************************************************************

-- Users
-- Password for all users is password
INSERT INTO users (username,password_hash,role, name, address, city, state_code, zip) VALUES 
    ('user', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER',  'Jack O''Lantern'),
    ('admin','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_ADMIN', 'Jill O''Lantern');

-- Banks
INSERT INTO bank (name, nickname, user_id) VALUES 

-- Accounts
INSERT INTO account (account_number, routing_number, name, due_balance, total_balance, apr, apy, payment_due_date, account_type_id, bank_id, user_id) VALUES
    ();

-- Categories
INSERT INTO category (name, essential, user_id) VALUES 
	();
	
-- Time Span
INSERT INTO time_span (name, multiple) VALUES
	('week', 4),
	('month', 1),
	('year', 0.083);

-- Account Types
INSERT INTO account_type (name) VALUES
	('checking'),
	('saving'),
	('credit');

-- Payment Types
INSERT INTO payment_type (name) VALUES
	('bill'),
	('subscription');

-- Transactions
INSERT INTO transactions (transaction_date, amount, account_id, category_id, vendor, transaction_desc, user_id) VALUES
	();

-- Planned Expenses
INSERT INTO planned_expense (per_month_frequency, frequency, time_span_id, single_amount, monthly_amount, category_id, active, expense_desc, user_id) VALUES
	();

-- Recurring Payments
INSERT INTO recurring_payment (day_of_month_due, account_id, amount, category_id, payment_type_id, active, payment_desc, link, user_id) VALUES
	();

COMMIT TRANSACTION;