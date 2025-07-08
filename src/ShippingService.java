
public class ShippingService {

    public static void ship(PaperBook book, int quantity, String address) {
        System.out.println("Shipping service called for book: " + book.getTitle());
        System.out.println("Quantity: " + quantity + ", Address: " + address);
        simulateShipping(book, quantity, address);
    }

    private static void simulateShipping(PaperBook book, int quantity, String address) {
        System.out.println("[SHIPPING] Processing shipment...");
        System.out.println("[SHIPPING] Book: " + book.getTitle() + " (ISBN: " + book.getIsbn() + ")");
        System.out.println("[SHIPPING] Quantity: " + quantity);
        System.out.println("[SHIPPING] Destination: " + address);
        System.out.println("[SHIPPING] Shipment scheduled successfully!");
    }
}