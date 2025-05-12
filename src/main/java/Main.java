import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        LibrarySerializer serializer = new LibrarySerializer();

        // Try to load saved library
        List<Book> savedBooks = serializer.loadLibrary("src/main/resources/data/library.ser");
        if (savedBooks != null) {
            library.setBooks(savedBooks);
            System.out.println("\033[1;32mLoaded saved library state\033[0m");
        } else {
            library.loadBooks("src/main/resources/data/books.txt");
            System.out.println("\033[1;32mLoaded initial book data\033[0m");
        }

        // Start menu system
        LibraryMenu menu = new LibraryMenu(library);
        menu.displayMenu();
    }
}