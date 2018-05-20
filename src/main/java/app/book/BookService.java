package app.book;

import app.book.exception.MissingPropertyException;
import app.book.exception.NoBookFound;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BookService {
    private final Map<Long, Book> bookMap;
    private static AtomicInteger count = new AtomicInteger(1);

    public BookService() {
        this.bookMap = new HashMap<>();
    }

    public List<Book> findAll() {
        return new ArrayList<Book>(bookMap.values());
    }

    public Book find(Long id) {
        return bookMap.get(id);
    }

    public Book createBook(CreateBookDto createBookDto) {
        List<String> missingProperties = checkBookCreation(createBookDto);
        validateProperties(missingProperties);

        Book book = new Book(count.getAndIncrement(), createBookDto.getIsbn(),
                createBookDto.getTitle(),
                createBookDto.getAuthor(),
                createBookDto.getPublisher(),
                createBookDto.getType());
        bookMap.put(book.getId(), book);
        return book;
    }

    private void validateProperties(List<String> missingProperties) {
        if (!missingProperties.isEmpty()) {
            throw new MissingPropertyException("missing properties " + missingProperties);
        }
    }

    private List<String> checkBookCreation(CreateBookDto createBookDto) {
        if (createBookDto == null) {
            return Arrays.asList("title", "isbn");
        }
        List<String> list = new ArrayList<>();
        if (createBookDto.getIsbn() == null) {
            list.add("isbn");
        }
        if (createBookDto.getTitle() == null) {
            list.add("title");
        }
        return list;
    }


    public void update(Long id, UpdateBookDto updateBookDto) {
        List<String> missingProperties = checkBookUpdate(updateBookDto);
        validateProperties(missingProperties);
        Book book = find(id);
        if (book != null) {
            Book updatedBook = new Book(book.getId(), book.getIsbn(),
                    updateBookDto.getTitle(),
                    updateBookDto.getAuthor(),
                    book.getPublisher(),
                    book.getType());
            bookMap.put(book.getId(), updatedBook);
        } else {
            throw new NoBookFound(id);
        }
    }

    private List<String> checkBookUpdate(UpdateBookDto updateBookDto) {
        if (updateBookDto == null) {
            return Arrays.asList("title", "author");
        }
        List<String> list = new ArrayList<>();
        if (updateBookDto.getAuthor() == null) {
            list.add("author");
        }
        if (updateBookDto.getTitle() == null) {
            list.add("title");
        }
        return list;
    }


}
