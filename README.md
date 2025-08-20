# 📚 Pahana Edu Billing System

The **Pahana Edu Billing System** is a Java-based bookshop management application designed to handle billing, customer management, item management, and reporting for the **Pahana Edu Bookshop**.  
It supports both **console-based** and **web-based** modules, with REST API integration and database connectivity.

---

## 🚀 Features

- **User Authentication**
  - Secure login for system users.
- **Customer Management**
  - Add, update, delete, and view customers.
- **Item Management**
  - Add, update, delete, and view books/items.
- **Billing**
  - Create, update, and delete bills.
  - View bill history.
- **Reporting**
  - Generate reports for sales and customers.
- **Help & Exit**
  - Console-driven help and system exit options.

---

## 🛠️ Tech Stack

- **Java** (Core Java & REST API using JAX-RS)
- **MySQL** (Database: `pahana_bookshop`)
- **DAO & POJO** design pattern
- **Gson** for JSON handling
- **JUnit** for testing (TDD approach)
- **HTML, CSS, JavaScript** for frontend (Web module)
- **Git** for version control

---

## 📂 Project Structure

Pahana_Edu_Billing_System/
│── Pahana_Edu_Backend/ # Java REST API (DAO, POJO, DB connection)
│── Pahana_Edu_Bookshop/ # Java console-based bookshop system
│── Pahana_Edu_Frontend/ # Web frontend (HTML, CSS, JS)
│── README.md # Project documentation


---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository
    ```bash
    git clone https://github.com/Hiruni16/Pahana_Edu_Billing_System.git
    cd Pahana_Edu_Billing_System

---

## Database Setup

Import the database:

CREATE DATABASE pahana_bookshop;
USE pahana_bookshop;


Create required tables (customers, items, bills, etc.).

Update your DB connection details in the backend code (DB_Operation1.java).

3️⃣ Run the Backend (Java REST API)

Open Pahana_Edu_Backend in NetBeans / IntelliJ.

Run the REST server.

---

##Run the Console System

cd Pahana_Edu_Bookshop
javac Main.java
java Main

Run the Frontend (Optional)

Open index.html in a browser.

The frontend communicates with the REST API.

---

##🧪 Testing

JUnit test cases are implemented for:

Customer module

Item module

Billing module

mvn test

Version Control

This project uses Git & GitHub for version control:

Daily commits with version updates.

Branching and merging for new features.

GitHub repository for deployment.

---

##👨‍💻 Author

Chathurya Hiruni
🔗 GitHub Profile
