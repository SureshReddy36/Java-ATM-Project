# 💳 Java ATM System (Database Version)

### 📘 Description
A console-based **ATM Simulation System** built using **Core Java** and **MySQL (JDBC)**.  
This project allows users to perform real-time banking operations such as **deposit**, **withdraw**, **balance check**, and **amount transfer** — all linked to a MySQL database for persistent data storage.

---

## ⚙️ Features
- 🏦 **Account Management** – Each user has a unique account stored in the database  
- 💰 **Deposit / Withdraw** – Update balance in real time  
- 🔍 **Check Balance** – View current account balance instantly  
- 🔁 **Amount Transfer** – Transfer money between accounts (with transaction safety)  
- 💾 **Database Integration** – All operations are stored and retrieved from MySQL  
- 🧱 **JDBC (PreparedStatement)** – Secure and efficient database queries  

---

## 🧱 Tech Stack
- **Language:** Java (JDK 8+)
- **Database:** MySQL
- **Connector:** MySQL Connector/J (JDBC)
- **IDE:** Eclipse / IntelliJ / VS Code

---

## 🗃️ Database Setup
Run these SQL commands in MySQL before starting:

```sql
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
('1003', 'Priya', 12000);
