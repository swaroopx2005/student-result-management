# Smart Library Management System

A Java-based desktop application developed to simplify library operations using JDBC and ShaktiDB. The system provides an efficient way to maintain book records, manage library members, and handle borrowing and returning of books through an easy-to-use interface.

## Features

- Add, edit, and remove book records
- Register and manage library users
- Borrow and return books
- Search books by title or author
- View borrowing history
- Database-driven record management
- Simple and user-friendly GUI

## Technologies Used

- Java
- Java Swing
- JDBC
- ShaktiDB (PostgreSQL Compatible)
- Linux
- Git & GitHub

## Project Structure

```
Library-Management-System/
│── src/
│── lib/
│── sql/
│── README.md
```

## Database

The project uses ShaktiDB to store and manage data.

### Main Tables

- LibraryBooks
- Users
- BorrowRecords

## Requirements

- Java JDK 17 or above
- ShaktiDB
- JDBC Driver
- Linux or Windows

## Installation

1. Clone the repository.
2. Create the database in ShaktiDB.
3. Import the SQL file from the `sql` folder.
4. Update the database credentials in the source code.
5. Compile and run the project.

## Future Improvements

- Admin authentication
- Fine calculation
- Book reservation
- Dashboard with statistics
- Barcode/QR support
- Email notifications

## Author

Developed by **Swaroop S**

## License

This project is created for educational and learning purposes.
