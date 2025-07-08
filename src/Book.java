public abstract class Book {
    protected String isbn;
    protected String title;
    protected String author;
    protected int publishYear;
    protected double price;

    public Book(String isbn, String title, String author, int publishYear, double price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.price = price;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }

    public abstract boolean isAvailableForPurchase();
    public abstract void processPurchase(int quantity, String email, String address);
    public abstract boolean canBeRemoved(int currentYear, int yearsThreshold);

    @Override
    public String toString() {
        return String.format("ISBN: %s, Title: %s, Author: %s, Year: %d, Price: $%.2f",
                isbn, title, author, publishYear, price);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}