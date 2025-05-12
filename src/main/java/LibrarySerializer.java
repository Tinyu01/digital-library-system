import java.io.*;
import java.util.List;

/**
 * Handles the serialization and deserialization of library data.
 */
public class LibrarySerializer {
    /**
     * Saves the library books list to a file.
     *
     * @param books List of books to save
     * @param fileName Path to the file
     * @return true if saved successfully
     */
    public boolean saveLibrary(List<Book> books, String fileName) {
        File file = new File(fileName);
        try {
            // Create parent directories if they don't exist
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(books);
                return true;
            }
        } catch (IOException e) {
            System.err.println("Error saving library: " + e.getMessage());
            return false;
        }
    }

    /**
     * Loads the library books list from a file.
     *
     * @param fileName Path to the file
     * @return List of books, or null if file doesn't exist or is empty
     */
    public List<Book> loadLibrary(String fileName) {
        File file = new File(fileName);
        if (!file.exists() || file.length() == 0) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            @SuppressWarnings("unchecked")
            List<Book> loadedBooks = (List<Book>) ois.readObject();
            return loadedBooks;
        } catch (EOFException e) {
            System.err.println("File is empty: " + fileName);
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading library: " + e.getMessage());
            return null;
        }
    }
}