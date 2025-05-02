# 🛍️ Order Management System

![Java](https://img.shields.io/badge/Java-17+-blue)
![MySQL](https://img.shields.io/badge/Database-MySQL-orange)
![License](https://img.shields.io/github/license/your-username/order-management-system)
![Status](https://img.shields.io/badge/Status-Active-success)

A simple **console-based Java application** that allows customers to register, log in, browse products, place orders, and manage inventory. Built using **JDBC**, **MySQL**, and follows a clean, modular code architecture.

---

## 📚 Table of Contents

- [✅ Features](#-features)
- [🛠 Tech Stack](#-tech-stack)
- [📁 Project Structure](#-project-structure)
- [🚀 Getting Started](#-getting-started)
- [💡 Usage](#-usage)
- [🧱 Database Schema](#-database-schema-erd)
- [👨‍💻 Author](#-author)

---

## ✅ Features

- 👤 Customer Registration & Login
- 🛒 Place Orders with Product Selection
- 📦 View Customer Order History
- 🔄 Update Product Stock (Admin-like Functionality)
- ✅ Input Validation & Logging
- 🧩 Follows MVC-inspired Clean Architecture

---

## 🛠 Tech Stack

| Technology  | Description                 |
|-------------|-----------------------------|
| **Java 17+**     | Core Language            |
| **MySQL**        | Relational Database      |
| **JDBC**         | Database Connectivity    |
| **Lombok**       | Reduce Boilerplate Code  |
| **SLF4J**        | Logging Framework        |
| **Maven**        | Project Build Tool       |

---

## 📁 Project Structure

```plaintext
src/
└── org.example.Task_86cynjbhb/
    ├── controller/      # CLI Menus & Logic Control
    ├── service/         # JDBC Business Logic
    ├── model/           # POJOs (Customers, Orders, Products)
    ├── utility/         # Input, Print, Validation, DB Queries
    ├── config/          # Database Connection Setup
    ├── enums/           # Constants
    └── OrderManagementSystem.java   # Main Entry Point
```
---

## 🚀 Getting Started

### 1️⃣ Prerequisites
- Java 17+
- MySQL Server
- Maven

## 2️⃣ Clone Repository
```bash
git clone https://github.com/your-username/order-management-system.git
cd order-management-system
```

## 3️⃣ Database Setup
```bash
CREATE DATABASE order_db;

USE order_db;

CREATE TABLE customers (
    cust_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100),
    email VARCHAR(100) UNIQUE
);

CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100),
    price DOUBLE,
    stock INT
);

CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    cust_id INT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cust_id) REFERENCES customers(cust_id)
);

CREATE TABLE order_items (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);
```


## 4️⃣ Update DB Connection
```bash
String url = "jdbc:mysql://localhost:3306/order_db";
String username = "root";
String password = "your_password";
```

---

## 💡 Usage
```bash
mvn clean compile
java -cp target/classes org.example.Order_Management_System.OrderManagementSystem
```

---

## 🧱 Database Schema (ERD)
CUSTOMERS ─────┐
               │
               ▼
             ORDERS ─────► ORDER_ITEMS ◄───── PRODUCTS

---

## 🧑‍💻 Author
GitHub: @SoniSujal
LinkedIn: https://www.linkedin.com/in/sujal-soni-240661240/



