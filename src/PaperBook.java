
public class PaperBook extends Book {
    private int stock;

    public PaperBook(String isbn, String title, String author, int publishYear, double price, int stock) {
        super(isbn, title, author, publishYear, price);
        this.stock = stock;
    }

    public void reduceStock(int quantity) {
        if (quantity > stock) {
            throw new IllegalArgumentException("Insufficient stock. Available: " + stock + ", Requested: " + quantity);
        }
        stock -= quantity;
    }

    @Override
    public boolean isAvailableForPurchase() {
        return stock > 0;
    }

    @Override
    public void processPurchase(int quantity, String email, String address) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        if (!isAvailableForPurchase()) {
            throw new IllegalStateException("Paper book is out of stock");
        }

        if (quantity > stock) {
            throw new IllegalArgumentException("Insufficient stock. Available: " + stock + ", Requested: " + quantity);
        }

        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address is required for paper book shipping");
        }

        reduceStock(quantity);

        ShippingService.ship(this, quantity, address);

        System.out.println("Paper book '" + title + "' (Quantity: " + quantity + ") will be shipped to: " + address);
    }

    @Override
    public boolean canBeRemoved(int currentYear, int yearsThreshold) {
        return (currentYear - publishYear) > yearsThreshold;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Stock: %d", stock);
    }
}