# Student Management System

A desktop-based Student Management System developed using Java Swing, JDBC, and MySQL to manage student records through a simple graphical user interface.

## Features

- Add new student records
- View all student records in a table
- Update existing student details
- Delete student records
- Search students by name or course
- Input validation for student details
- MySQL database integration using JDBC

## Technologies Used

- **Programming Language:** Java
- **GUI:** Java Swing
- **Database:** MySQL
- **Database Connectivity:** JDBC
- **Build Tool:** Maven
- **IDE:** IntelliJ IDEA
- **Version Control:** Git and GitHub

## Database Setup

1. Install MySQL Server.
2. Create a database named `student_management`.
3. Create the `students` table using the following SQL:

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

Configure the following environment variables in your IDE's run configuration:

- `DB_URL` — `jdbc:mysql://localhost:3306/student_management`
- `DB_USER` — your MySQL username
- `DB_PASSWORD` — your MySQL password

The application uses environment variables to avoid storing database credentials directly in the source code.

## How to Run

1. Clone or download this repository.
2. Open the project in IntelliJ IDEA.
3. Ensure MySQL Server is running and the database is configured.
4. Set the required environment variables.
5. Allow Maven to resolve the project dependencies.
6. Run `StudentManagementUI.java` from the `com.student.ui` package.

## Project Structure

```text
src/main/java/com/student/
├── dao/
│   └── StudentDAO.java
├── database/
│   └── DBConnection.java
├── model/
│   └── Student.java
└── ui/
    └── StudentManagementUI.java
```

## Learning Outcomes

- Developed a desktop application using Java Swing.
- Implemented CRUD operations using JDBC.
- Connected a Java application to a MySQL database.
- Used prepared statements for database operations.
- Practiced project organization with Maven and version control with Git.

## Author

Avishkar
