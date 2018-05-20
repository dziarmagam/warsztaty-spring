package app.book.exception;

public class NoBookFound extends RuntimeException{

    private final static String MESSAGE_PATTERN = "No book for id %d";
    public final Long id;

    public NoBookFound(Long id) {
        super(String.format(MESSAGE_PATTERN, id));
        this.id = id;
    }
}
