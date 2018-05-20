package app.book;

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

    public List<Book> findAll(){
        return new ArrayList<Book>(bookMap.values());
    }

    public Book find(Long id) {
        return bookMap.get(id);
    }

    public Book createBook(CreateBookDto createBookDto){
        Objects.requireNonNull(createBookDto);
        Objects.requireNonNull(createBookDto.getTitle());
        Objects.requireNonNull(createBookDto.getIsbn());
        //
        Book book = new Book(count.getAndIncrement(), createBookDto.getIsbn(),
                createBookDto.getTitle(),
                createBookDto.getAuthor(),
                createBookDto.getPublisher(),
                createBookDto.getType());
        bookMap.put(book.getId(), book);
        return book;
    }


}
