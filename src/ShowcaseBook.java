
public class ShowcaseBook extends Book {

    public ShowcaseBook(String isbn, String title, String author, int publishYear, double price) {
        super(isbn, title, author, publishYear, price);
    }

    @Override
    public boolean isAvailableForPurchase() {
        return false; // Showcase books are not for sale
    }

    @Override
    public void processPurchase(int quantity, String email, String address) {
        throw new UnsupportedOperationException("Showcase books are not available for purchase");
    }

    @Override
    public boolean canBeRemoved(int currentYear, int yearsThreshold) {
        return (currentYear - publishYear) > yearsThreshold;
    }

    @Override
    public String toString() {
        return super.toString() + " [SHOWCASE - NOT FOR SALE]";
    }
}