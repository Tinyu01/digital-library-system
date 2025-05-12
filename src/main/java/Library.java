import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Manages a collection of books and provides operations for searching and
 * manipulating the collection.
 */
public class Library {
    private List<Book> books = new ArrayList<>();

    /**
     * Loads books from a CSV file.
     *
     * @param fileName Path to the CSV file
     */
    public void loadBooks(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        String title = parts[0].trim();
                        String author = parts[1].trim();
                        int year = Integer.parseInt(parts[2].trim());
                        books.add(new Book(title, author, year));
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing year in line: " + line);
                    }
                }
            }
            System.out.println("Successfully loaded " + books.size() + " books.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Displays all books in the collection.
     */
    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }

        System.out.println("\n===== LIBRARY CATALOG =====");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
        System.out.println("==========================");
    }

    /**
     * Searches for books by keyword in title, author, or publication year.
     *
     * @param keyword The search term
     * @return List of books matching the keyword
     */
    public List<Book> searchBooksByKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return new ArrayList<>();
        }

        String lowerKeyword = keyword.toLowerCase();
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(lowerKeyword) ||
                        book.getAuthor().toLowerCase().contains(lowerKeyword) ||
                        String.valueOf(book.getPublicationYear()).contains(lowerKeyword))
                .collect(Collectors.toList());
    }

    /**
     * Searches for a single book by exact keyword in title or author.
     *
     * @param keyword The search term
     * @return The first book matching the keyword, or null if not found
     */
    public Book searchBookByKeyword(String keyword) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(keyword) ||
                        book.getAuthor().equalsIgnoreCase(keyword))
                .findFirst()
                .orElse(null);
    }

    /**
     * Adds a new book to the library.
     *
     * @param book The book to add
     * @return true if added successfully
     */
    public boolean addBook(Book book) {
        if (book == null) {
            return false;
        }
        return books.add(book);
    }

    /**
     * Updates an existing book.
     *
     * @param id          The ID of the book to update
     * @param updatedBook The updated book information
     * @return true if updated successfully
     */
    public boolean updateBook(String id, Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                books.get(i).setTitle(updatedBook.getTitle());
                books.get(i).setAuthor(updatedBook.getAuthor());
                books.get(i).setPublicationYear(updatedBook.getPublicationYear());
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes a book by ID.
     *
     * @param id The ID of the book to delete
     * @return true if deleted successfully
     */
    public boolean deleteBook(String id) {
        return books.removeIf(book -> book.getId().equals(id));
    }

    /**
     * Gets a book by index.
     *
     * @param index The index of the book
     * @return The book at the specified index, or null if index is invalid
     */
    public Book getBookByIndex(int index) {
        if (index < 0 || index >= books.size()) {
            return null;
        }
        return books.get(index);
    }

    /**
     * Gets a book by ID.
     *
     * @param id The ID of the book
     * @return Optional containing the book if found
     */
    public Optional<Book> getBookById(String id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    // Getters and setters
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}