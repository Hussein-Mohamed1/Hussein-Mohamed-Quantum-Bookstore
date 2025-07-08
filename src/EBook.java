
public class EBook extends Book {
    private String fileType;

    public EBook(String isbn, String title, String author, int publishYear, double price, String fileType) {
        super(isbn, title, author, publishYear, price);
        this.fileType = fileType;
    }

    public String getFileType() { return fileType; }

    @Override
    public boolean isAvailableForPurchase() {
        return true; // EBooks are always available
    }

    @Override
    public void processPurchase(int quantity, String email, String address) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required for EBook delivery");
        }

        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        MailService.sendEBook(this, quantity, email);

        System.out.println("EBook '" + title + "' (" + fileType + ") (Quantity: " + quantity + ") will be sent to: " + email);
    }

    @Override
    public boolean canBeRemoved(int currentYear, int yearsThreshold) {
        return (currentYear - publishYear) > yearsThreshold;
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", File Type: %s", fileType);
    }
}