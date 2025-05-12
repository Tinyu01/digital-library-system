import java.io.Serializable;
import java.util.UUID;

/**
 * Represents a book in the digital library system.
 * Implements Serializable to allow for persistence.
 */
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String title;
    private String author;
    private int publicationYear;

    /**
     * Constructor for creating a new book.
     *
     * @param title The title of the book
     * @param author The author of the book
     * @param publicationYear The year the book was published
     */
    public Book(String title, String author, int publicationYear) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    // Getters and setters
    public String getId() { return id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public int getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    @Override
    public String toString() {
        return String.format("Book [ID: %s, Title: %s, Author: %s, Year: %d]", 
                id.substring(0, 8), title, author, publicationYear);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return id.equals(book.id);
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}