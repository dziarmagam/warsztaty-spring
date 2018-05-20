package app.book;

import java.util.Objects;

public class Book {
    private final long id;
    private final String isbn;
    private final String title;
    private final String author;
    private final String publisher;
    private final String type;

    Book(long id, String isbn, String title, String author, String publisher, String type) {
        Objects.requireNonNull(isbn);
        Objects.requireNonNull(title);
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getType() {
        return type;
    }
}
