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
```
🏦 Welcome to Java ATM (DB Version)
Enter your Account Number: 1001

--- ATM MENU ---
1️⃣ Deposit
2️⃣ Withdraw
3️⃣ Check Balance
4️⃣ Transfer Amount
5️⃣ Exit
Choose option: 1
Enter amount to deposit: 2000
✅ Deposited Rs.2000 successfully!
Mallidi Sai Suresh Reddy
B.Tech (Information Technology), Pragati Engineering College
GitHub: github.com/SureshReddy36

##🏁 Future Improvements

Add transaction history table

Add login PIN authentication

Develop GUI version using Java Swing
