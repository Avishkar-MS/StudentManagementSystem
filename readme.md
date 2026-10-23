# Student Management System

A desktop-based Student Management System developed using Java Swing, JDBC, and MySQL to manage student records efficiently.

## Features

- Add new student records
- View all student records
- Update existing student information
- Delete student records with confirmation
- Search students by name or course
- Refresh the student list
- Validate student names, ages, emails, and course names
- Store records persistently in MySQL

## Technologies Used

- Java
- Java Swing
- JDBC
- MySQL
- Maven
- IntelliJ IDEA

## Prerequisites

- Java JDK
- MySQL Server
- IntelliJ IDEA or another Java IDE
- Maven

## Database Setup

Create the database and table using the following SQL commands:

```sql
CREATE DATABASE student_management;

USE student_management;

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    email VARCHAR(100) UNIQUE,
    course VARCHAR(100) NOT NULL
);
```

## Configuration

Configure these environment variables in your IDE or runtime environment:

- `DB_URL` — defaults to `jdbc:mysql://localhost:3306/student_management`
- `DB_USER` — defaults to `root`
- `DB_PASSWORD` — your MySQL password

Do not commit passwords or other sensitive credentials to GitHub.

## Running the Application

1. Clone or download the repository.
2. Open the project in IntelliJ IDEA as a Maven project.
3. Configure the database and environment variables.
4. Ensure MySQL Server is running.
5. Run `com.student.ui.StudentManagementUI`.

## Project Structure

```text
src/main/java/com/student/
├── database/
│   └── DBConnection.java
├── dao/
│   └── StudentDAO.java
├── model/
│   └── Student.java
└── ui/
    └── StudentManagementUI.java
```

## Learning Outcomes

- Java object-oriented programming
- Building desktop interfaces with Swing
- Connecting Java applications to MySQL using JDBC
- Implementing CRUD operations
- Input validation and exception handling
- Managing dependencies with Maven
