package app.book.exception;

public class MissingPropertyException extends RuntimeException{

    private static final String MESSAGE_PATTERN = "Fail in creating book: %s";

    public MissingPropertyException(String message) {
        super(String.format(MESSAGE_PATTERN, message));
    }
}
