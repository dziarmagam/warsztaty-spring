package app.book;


public class CreateBookDto {

    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String type;

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
