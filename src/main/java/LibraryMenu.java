import java.util.Comparator;
import java.util.Scanner;
import java.util.List;

public class LibraryMenu {
    private final Library library;
    private final UserInteractionLogger logger = new UserInteractionLogger();
    private final LibrarySerializer serializer = new LibrarySerializer();

    public LibraryMenu(Library library) {
        this.library = library;
    }

    public void displayMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                printMenu();
                System.out.print("\033[1;34mEnter your choice: \033[0m");
                int choice = safeReadInt(scanner);

                switch (choice) {
                    case 1 -> viewAllBooks();
                    case 2 -> sortBooksByTitle();
                    case 3 -> sortBooksByAuthor();
                    case 4 -> sortBooksByYear();
                    case 5 -> searchBook(scanner);
                    case 6 -> addBook(scanner);
                    case 7 -> updateBook(scanner);
                    case 8 -> deleteBook(scanner);
                    case 9 -> exit();
                    default -> System.out.println("\033[1;31mInvalid choice. Please try again.\033[0m");
                }
            }
        }
    }

    private void printMenu() {
        System.out.println("""
                \033[1;36m
                ╔═══════════════════════════════╗
                ║     Digital Library System    ║
                ╚═══════════════════════════════╝
                \033[0m
                1. View All Books
                2. Sort Books by Title
                3. Sort Books by Author
                4. Sort Books by Year
                5. Search for a Book
                6. Add a Book
                7. Update a Book
                8. Delete a Book
                9. Exit
                """);
    }

    private int safeReadInt(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); // Clear invalid input
            return -1;
        }
    }

    private void viewAllBooks() {
        library.viewAllBooks();
        logger.logViewAllBooks();
    }

    private void sortBooksByTitle() {
        SortUtil.bubbleSort(library.getBooks(), Comparator.comparing(Book::getTitle));
        library.viewAllBooks();
        logger.logSort("Title");
    }

    private void sortBooksByAuthor() {
        SortUtil.insertionSort(library.getBooks(), Comparator.comparing(Book::getAuthor));
        library.viewAllBooks();
        logger.logSort("Author");
    }

    private void sortBooksByYear() {
        SortUtil.quickSort(library.getBooks(), Comparator.comparing(Book::getPublicationYear),
                0, library.getBooks().size() - 1);
        library.viewAllBooks();
        logger.logSort("Publication Year");
    }

    private void searchBook(Scanner scanner) {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();
        Book found = library.searchBookByKeyword(keyword);
        System.out.println(found != null ? found : "\033[1;31mBook not found.\033[0m");
        logger.logSearch(keyword);
    }

    private void addBook(Scanner scanner) {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter publication year: ");
        int year = safeReadInt(scanner);
        scanner.nextLine(); // Consume newline
        Book book = new Book(title, author, year);
        library.addBook(book);
        System.out.println("\033[1;32mBook added successfully!\033[0m");
        logger.logCreate(title);
    }

    private void updateBook(Scanner scanner) {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter title of book to update: ");
        String oldTitle = scanner.nextLine();
        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter new author: ");
        String author = scanner.nextLine();
        System.out.print("Enter new publication year: ");
        int year = safeReadInt(scanner);
        scanner.nextLine(); // Consume newline
        Book updatedBook = new Book(newTitle, author, year);
        if (library.updateBook(oldTitle, updatedBook)) {
            System.out.println("\033[1;32mBook updated successfully!\033[0m");
            logger.logUpdate(oldTitle);
        } else {
            System.out.println("\033[1;31mBook not found.\033[0m");
        }
    }

    private void deleteBook(Scanner scanner) {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter title of book to delete: ");
        String title = scanner.nextLine();
        if (library.deleteBook(title)) {
            System.out.println("\033[1;32mBook deleted successfully!\033[0m");
            logger.logDelete(title);
        } else {
            System.out.println("\033[1;31mBook not found.\033[0m");
        }
    }

    private void exit() {
        serializer.saveLibrary(library.getBooks(), "src/main/resources/data/library.ser");
        System.out.println("\033[1;32mSaving data... Goodbye!\033[0m");
        System.exit(0);
    }
}