
import java.util.*;
import java.util.stream.Collectors;

public class QuantumBookstore {
    private Map<String, Book> inventory;
    private int currentYear;

    public QuantumBookstore(int currentYear) {
        this.inventory = new HashMap<>();
        this.currentYear = currentYear;
        System.out.println("Bookstore initialized successfully!");
    }

    public void addBook(Book book) throws BookstoreException {
        if (book == null) {
            throw new BookstoreException("Book cannot be null");
        }

        if (inventory.containsKey(book.getIsbn())) {
            throw new BookstoreException("Book with ISBN " + book.getIsbn() + " already exists");
        }

        inventory.put(book.getIsbn(), book);
        System.out.println("Book added successfully - " + book.getTitle());
    }

    public List<Book> removeOutdatedBooks(int yearsThreshold) {
        System.out.println("Removing books older than " + yearsThreshold + " years...");

        List<Book> outdatedBooks = inventory.values().stream()
                .filter(book -> book.canBeRemoved(currentYear, yearsThreshold))
                .collect(Collectors.toList());

        // Remove outdated books from inventory
        for (Book book : outdatedBooks) {
            inventory.remove(book.getIsbn());
        }

        System.out.println("Removed " + outdatedBooks.size() + " outdated books");
        return outdatedBooks;
    }

    public double buyBook(String isbn, int quantity, String email, String address) throws BookstoreException {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new BookstoreException("ISBN cannot be null or empty");
        }

        if (quantity <= 0) {
            throw new BookstoreException("Quantity must be greater than 0");
        }

        Book book = inventory.get(isbn);
        if (book == null) {
            throw new BookstoreException("Book with ISBN " + isbn + " not found");
        }

        if (!book.isAvailableForPurchase()) {
            throw new BookstoreException("Book with ISBN " + isbn + " is not available for purchase");
        }

        try {
            book.processPurchase(quantity, email, address);
            double totalAmount = book.getPrice() * quantity;

            System.out.println("Purchase successful!");
            System.out.println("Book: " + book.getTitle());
            System.out.println("Quantity: " + quantity);
            System.out.println("Total Amount: $" + String.format("%.2f", totalAmount));

            return totalAmount;
        } catch (Exception e) {
            throw new BookstoreException("Purchase failed: " + e.getMessage(), e);
        }
    }

    public void displayInventory() {
        System.out.println("========== INVENTORY ==========");
        if (inventory.isEmpty()) {
            System.out.println("No books in inventory");
        } else {
            inventory.values().forEach(book ->
                    System.out.println(book.toString())
            );
        }
        System.out.println("============================");
    }

}