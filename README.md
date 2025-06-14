# Library Management System

A simple Java project for managing Books and Users in a library.

## Descrption
This project allows admin and users to borow and return books. Admin can add, remove books, view all issues. Users can search and isue books.

## Feautures
- Admin memu: add book, remove book, view book, accept return, add member
- User menue: view, search and borrow books
- Data stored in memory (no DB)
- Clean and simpe CLI interface

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
├── bin/  ← compiled .class files
└── README.md
```

## Prerequisites
- Java JDK 8 or above installd
- VS Code or any IDE

## Instalation & Run
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

## Usage
- Login as **admin** (username: admin, Id: 1, password: admin123)
- Use the menues to manage books
- Login as **user** (signup first)

## Contribute
Feel free to fork and make PRs.

## Author
- Srichakra Devarakonda
