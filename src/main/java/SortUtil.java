import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Utility class providing different sorting algorithms for Book collections.
 */
public class SortUtil {
    /**
     * Bubble sort implementation for books.
     *
     * @param books List of books to sort
     * @param comparator Comparator to determine ordering
     */
    public static void bubbleSort(List<Book> books, Comparator<Book> comparator) {
        int n = books.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(books.get(j), books.get(j + 1)) > 0) {
                    Collections.swap(books, j, j + 1);
                }
            }
        }
    }

    /**
     * Insertion sort implementation for books.
     *
     * @param books List of books to sort
     * @param comparator Comparator to determine ordering
     */
    public static void insertionSort(List<Book> books, Comparator<Book> comparator) {
        for (int i = 1; i < books.size(); i++) {
            Book key = books.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(books.get(j), key) > 0) {
                books.set(j + 1, books.get(j));
                j = j - 1;
            }
            books.set(j + 1, key);
        }
    }

    /**
     * Quick sort implementation for books.
     *
     * @param books List of books to sort
     * @param comparator Comparator to determine ordering
     * @param low Starting index
     * @param high Ending index
     */
    public static void quickSort(List<Book> books, Comparator<Book> comparator, int low, int high) {
        if (low < high) {
            int pi = partition(books, comparator, low, high);
            quickSort(books, comparator, low, pi - 1);
            quickSort(books, comparator, pi + 1, high);
        }
    }

    /**
     * Helper method for quickSort that partitions the list.
     */
    private static int partition(List<Book> books, Comparator<Book> comparator, int low, int high) {
        Book pivot = books.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(books.get(j), pivot) <= 0) {
                i++;
                Collections.swap(books, i, j);
            }
        }
        Collections.swap(books, i + 1, high);
        return i + 1;
    }
    
    /**
     * Modern Java-style sort using streams.
     *
     * @param books List of books to sort
     * @param comparator Comparator to determine ordering
     * @return A new sorted list of books
     */
    public static List<Book> modernSort(List<Book> books, Comparator<Book> comparator) {
        return books.stream()
                .sorted(comparator)
                .toList();
    }
}