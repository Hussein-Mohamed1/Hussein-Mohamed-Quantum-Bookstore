
import java.util.List;

public class QuantumBookstoreFullTest {

    public static void main(String[] args) {
        System.out.println("Starting comprehensive bookstore tests...\n");

        QuantumBookstore bookstore = new QuantumBookstore(2024);

        // Test 1: Adding books
        testAddingBooks(bookstore);

        // Test 2: Display inventory
        testDisplayInventory(bookstore);

        // Test 3: Buying paper books
        testBuyingPaperBooks(bookstore);

        // Test 4: Buying EBooks
        testBuyingEBooks(bookstore);

        // Test 5: Attempting to buy showcase books
        testBuyingShowcaseBooks(bookstore);

        // Test 6: Removing outdated books
        testRemovingOutdatedBooks(bookstore);

        // Test 7: Error handling
        testErrorHandling(bookstore);

        // Test 8: Edge cases
        testEdgeCases(bookstore);

        System.out.println("\nAll tests completed!");
    }

    private static void testAddingBooks(QuantumBookstore bookstore) {
        System.out.println("========== TEST 1: Adding Books ==========");

        try {
            // Add paper books
            bookstore.addBook(new PaperBook("PB001", "The Java Complete Guide", "John Doe", 2020, 45.99, 10));
            bookstore.addBook(new PaperBook("PB002", "Data Structures and Algorithms", "Jane Smith", 2019, 55.50, 5));
            bookstore.addBook(new PaperBook("PB003", "Old Programming Book", "Bob Johnson", 2010, 25.00, 3));

            // Add EBooks
            bookstore.addBook(new EBook("EB001", "Modern Web Development", "Alice Brown", 2023, 29.99, "PDF"));
            bookstore.addBook(new EBook("EB002", "Machine Learning Basics", "Charlie Davis", 2022, 39.99, "EPUB"));
            bookstore.addBook(new EBook("EB003", "Legacy System Design", "David Wilson", 2008, 19.99, "PDF"));

            // Add showcase books
            bookstore.addBook(new ShowcaseBook("SB001", "Future of AI", "Dr. Sarah Connor", 2024, 0.00));
            bookstore.addBook(new ShowcaseBook("SB002", "Quantum Computing Preview", "Prof. Einstein", 2023, 0.00));

            System.out.println("All books added successfully!");

        } catch (BookstoreException e) {
            System.out.println("Error adding books: " + e.getMessage());
        }

        System.out.println();
    }

    private static void testDisplayInventory(QuantumBookstore bookstore) {
        System.out.println("========== TEST 2: Display Inventory ==========");
        bookstore.displayInventory();
        System.out.println();
    }

    private static void testBuyingPaperBooks(QuantumBookstore bookstore) {
        System.out.println("========== TEST 3: Buying Paper Books ==========");

        try {
            // Buy a paper book
            double amount = bookstore.buyBook("PB001", 2, "customer@email.com", "123 Main St, City, State");
            System.out.println("Payment amount: $" + String.format("%.2f", amount));

            // Buy another paper book
            amount = bookstore.buyBook("PB002", 1, "another@email.com", "456 Oak Ave, Town, State");
            System.out.println("Payment amount: $" + String.format("%.2f", amount));

        } catch (BookstoreException e) {
            System.out.println("Error buying paper books: " + e.getMessage());
        }

        System.out.println();
    }

    private static void testBuyingEBooks(QuantumBookstore bookstore) {
        System.out.println("========== TEST 4: Buying EBooks ==========");

        try {
            // Buy an EBook
            double amount = bookstore.buyBook("EB001", 1, "reader@email.com", "Not needed for EBook");
            System.out.println("Payment amount: $" + String.format("%.2f", amount));

            // Buy multiple copies of an EBook
            amount = bookstore.buyBook("EB002", 3, "bulk@email.com", "");
            System.out.println("Payment amount: $" + String.format("%.2f", amount));

        } catch (BookstoreException e) {
            System.out.println("Error buying EBooks: " + e.getMessage());
        }

        System.out.println();
    }

    private static void testBuyingShowcaseBooks(QuantumBookstore bookstore) {
        System.out.println("========== TEST 5: Buying Showcase Books ==========");

        try {
            // Attempt to buy a showcase book (should fail)
            bookstore.buyBook("SB001", 1, "customer@email.com", "123 Main St");
        } catch (BookstoreException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        System.out.println();
    }

    private static void testRemovingOutdatedBooks(QuantumBookstore bookstore) {
        System.out.println("========== TEST 6: Removing Outdated Books ==========");

        System.out.println("Inventory before removal:");
        bookstore.displayInventory();

        // Remove books older than 10 years
        List<Book> removedBooks = bookstore.removeOutdatedBooks(10);

        System.out.println("Removed books:");
        for (Book book : removedBooks) {
            System.out.println(book.toString());
        }

        System.out.println("Inventory after removal:");
        bookstore.displayInventory();

        System.out.println();
    }

    private static void testErrorHandling(QuantumBookstore bookstore) {
        System.out.println("========== TEST 7: Error Handling ==========");

        // Test buying non-existent book
        try {
            bookstore.buyBook("NONEXISTENT", 1, "test@email.com", "123 Main St");
        } catch (BookstoreException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        // Test buying with insufficient stock
        try {
            bookstore.buyBook("PB002", 100, "test@email.com", "123 Main St");
        } catch (BookstoreException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        // Test buying EBook without email
        try {
            bookstore.buyBook("EB001", 1, "", "123 Main St");
        } catch (BookstoreException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        // Test buying paper book without address
        try {
            bookstore.buyBook("PB001", 1, "test@email.com", "");
        } catch (BookstoreException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        // Test adding duplicate book
        try {
            bookstore.addBook(new PaperBook("PB001", "Duplicate Book", "Test Author", 2024, 10.00, 1));
        } catch (BookstoreException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        System.out.println();
    }

    private static void testEdgeCases(QuantumBookstore bookstore) {
        System.out.println("========== TEST 8: Edge Cases ==========");

        try {
            // Add a book with unusual but valid data
            bookstore.addBook(new PaperBook("EDGE001", "Book with $pecial Ch@racters!", "Author-Name", 2024, 0.01, 1));

            // Buy the book
            double amount = bookstore.buyBook("EDGE001", 1, "test@domain.co.uk", "123 Street, City, Country");
            System.out.println("Successfully bought edge case book for $" + String.format("%.2f", amount));

            // Try to buy the same book again (should fail due to stock)
            try {
                bookstore.buyBook("EDGE001", 1, "test@domain.co.uk", "123 Street, City, Country");
            } catch (BookstoreException e) {
                System.out.println("Expected out of stock error: " + e.getMessage());
            }

        } catch (BookstoreException e) {
            System.out.println("Error in edge cases: " + e.getMessage());
        }

        System.out.println();
    }
}