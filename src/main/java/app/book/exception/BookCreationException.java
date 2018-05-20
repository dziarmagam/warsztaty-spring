package app.book.exception;

public class BookCreationException extends RuntimeException{

    private static final String MESSAGE_PATTERN = "Fail in creating book: %s";

    public BookCreationException(String message) {
        super(String.format(MESSAGE_PATTERN, message));
    }
}
