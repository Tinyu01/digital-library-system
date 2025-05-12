# Digital Library Management System

A modern Java application for managing a digital library with support for book management, search, sorting, and persistence.

## Features

- 📚 Book management (add, view, update, delete)
- 🔍 Search functionality by title, author, or publication year
- 🔄 Multiple sorting algorithms (bubble sort, insertion sort, quick sort)
- 💾 Data persistence through serialization
- 📝 User activity logging
- 🖥️ Modern command-line interface

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── Book.java                    - Book entity class
│   │   ├── Library.java                 - Core library functionality
│   │   ├── SortUtil.java                - Sorting algorithms implementation
│   │   ├── LibraryMenu.java             - Interactive menu system
│   │   ├── UserInteractionLogger.java   - Activity logging
│   │   ├── LibrarySerializer.java       - Data persistence
│   │   └── Main.java                    - Application entry point
│   └── resources/
│       └── data/
│           ├── books.txt                - Initial book data
│           ├── library.ser              - Serialized library state
│           └── user_interactions.log    - User activity logs
```

## Installation & Setup

1. Clone this repository
   ```
   git clone https://github.com/yourusername/digital-library-system.git
   ```

2. Navigate to the project directory
   ```
   cd digital-library-system
   ```

3. Compile the project
   ```
   javac -d bin src/main/java/*.java
   ```

4. Run the application
   ```
   java -cp bin Main
   ```

## Usage

The application provides a command-line interface with the following options:

1. **View All Books** - Display all books in the library
2. **Add New Book** - Add a new book to the library
3. **Update Book** - Modify details of an existing book
4. **Delete Book** - Remove a book from the library
5. **Sort Books by Title** - Sort the collection by book title
6. **Sort Books by Author** - Sort the collection by author name
7. **Sort Books by Year** - Sort the collection by publication year
8. **Search for a Book** - Find books by keyword
9. **Exit** - Save and exit the application

## Sample Data

Create a `books.txt` file in the `src/resources/data/` directory with the following format:

```
The Great Gatsby,F. Scott Fitzgerald,1925
To Kill a Mockingbird,Harper Lee,1960
1984,George Orwell,1949
Pride and Prejudice,Jane Austen,1813
The Catcher in the Rye,J.D. Salinger,1951
```

## Fixed Issues

- Fixed access modifier in `UserInteractionLogger.log()` method
- Added proper error handling for directory creation in `LibrarySerializer`
- Implemented proper type handling for serialization/deserialization
- Modernized user interface with CRUD operations

## Future Enhancements

- Graphical user interface
- Database integration
- Advanced search capabilities
- User authentication and permissions
- Cloud synchronization

## License

This project is licensed under the MIT License - see the LICENSE file for details.