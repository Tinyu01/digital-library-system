import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logs user interactions with the library system.
 */
public class UserInteractionLogger {
    private static final String LOG_FILE = "src/resources/data/user_interactions.log";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructor that ensures the log directory exists.
     */
    public UserInteractionLogger() {
        File logFile = new File(LOG_FILE);
        if (!logFile.exists()) {
            File parentDir = logFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
        }
    }

    /**
     * Logs a search action.
     *
     * @param searchTerm The term that was searched for
     */
    public void logSearch(String searchTerm) {
        log("Search action: " + searchTerm);
    }

    /**
     * Logs a sort action.
     *
     * @param sortCriteria The criteria used for sorting
     */
    public void logSort(String sortCriteria) {
        log("Sort action: " + sortCriteria);
    }

    /**
     * Logs a view all books action.
     */
    public void logViewAllBooks() {
        log("View all books action");
    }

    /**
     * Logs an add book action.
     *
     * @param bookTitle The title of the added book
     */
    public void logAddBook(String bookTitle) {
        log("Add book action: " + bookTitle);
    }

    /**
     * Logs an update book action.
     *
     * @param bookId The ID of the updated book
     */
    public void logUpdateBook(String bookId) {
        log("Update book action: Book ID " + bookId);
    }

    /**
     * Logs a delete book action.
     *
     * @param bookId The ID of the deleted book
     */
    public void logDeleteBook(String bookId) {
        log("Delete book action: Book ID " + bookId);
    }

    /**
     * Logs a create book action.
     *
     * @param bookTitle The title of the created book
     */
    public void logCreate(String bookTitle) {
        log("Create book action: " + bookTitle);
    }

    /**
     * Logs an update book action.
     *
     * @param bookTitle The title of the updated book
     */
    public void logUpdate(String bookTitle) {
        log("Update book action: " + bookTitle);
    }

    /**
     * Logs a delete book action.
     *
     * @param bookTitle The title of the deleted book
     */
    public void logDelete(String bookTitle) {
        log("Delete book action: " + bookTitle);
    }

    /**
     * Logs a message with timestamp.
     * Changed from private to public for testing access
     *
     * @param message The message to log
     */
    public void log(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            String timestamp = LocalDateTime.now().format(formatter);
            writer.write(timestamp + " - " + message + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}