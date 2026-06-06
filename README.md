# Library Management System

A simple Java project for managing books and users in a library.

## Description
This project allows admins to manage members, books, and issued-book records. Members can view, search, and borrow available books. Admins accept book returns by issue ID.

## Features
- Admin menu: add member, add book, remove book, view books, view available books, search books, view issued books, accept returns
- Member menu: view available books, search books, borrow books
- Data stored in memory (no DB)
- Simple CLI interface

## Folder Structure
```
LibraryManagementSystem/
├── src/
│   ├── LibraryManagementSystem.java
│   ├── model/
│   │   ├── Book.java
│   |   ├── IssuedBook.java
│   |   ├── Admin.java
│   |   ├── Role.java
│   |   ├── Member.java
│   │   └── User.java
│   └── service/
│       └── Library.java
├── bin/  # compiled .class files
├── test/
│   └── LibraryServiceTest.java
└── README.md
```

## Prerequisites
- Java JDK 8 or above installed
- VS Code or any IDE

## Installation & Run
1. Clone the repo:
   ```bash
   git clone https://github.com/Srichakra-D/Library_Management_System
   cd Library_Management_System
   ```
2. Compile:
   ```bash
   javac -d bin src/LibraryManagementSystem.java src/model/*.java src/service/*.java
   ```
3. Run:
   ```bash
   java -cp bin LibraryManagementSystem
   ```

## Tests
Compile and run the dependency-free regression tests:
```bash
javac -d test-bin src/model/*.java src/service/*.java test/LibraryServiceTest.java
java -cp test-bin LibraryServiceTest
```

## Usage
- Login as admin with ID `1` and password `admin123`
- Use the admin menu to add members and books
- Members can log in after an admin creates their account

## Contribute
Feel free to fork and make PRs.

## Author
- Srichakra Devarakonda
