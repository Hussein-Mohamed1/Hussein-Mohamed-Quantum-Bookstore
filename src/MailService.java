
public class MailService {

    public static void sendEBook(EBook ebook, int quantity, String email) {

        System.out.println("Mail service called for EBook: " + ebook.getTitle());
        System.out.println("Quantity: " + quantity + ", Email: " + email);
        simulateEmailSending(ebook, quantity, email);
    }

    private static void simulateEmailSending(EBook ebook, int quantity, String email) {
        System.out.println("[EMAIL] Preparing email...");
        System.out.println("[EMAIL] EBook: " + ebook.getTitle() + " (ISBN: " + ebook.getIsbn() + ")");
        System.out.println("[EMAIL] File Type: " + ebook.getFileType());
        System.out.println("[EMAIL] Quantity: " + quantity);
        System.out.println("[EMAIL] Recipient: " + email);
        System.out.println("[EMAIL] Email sent successfully with download links!");
    }
}