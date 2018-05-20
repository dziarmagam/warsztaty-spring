package app.book;

import app.book.exception.BookCreationException;
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
        List<String> missingProperties = checkBookCreation(createBookDto);

        if(!missingProperties.isEmpty()){
            throw new BookCreationException("missing properties " + missingProperties);
        }

        Book book = new Book(count.getAndIncrement(), createBookDto.getIsbn(),
                createBookDto.getTitle(),
                createBookDto.getAuthor(),
                createBookDto.getPublisher(),
                createBookDto.getType());
        bookMap.put(book.getId(), book);
        return book;
    }

    private List<String> checkBookCreation(CreateBookDto createBookDto){
        if (createBookDto == null){
            return Arrays.asList("title", "isbn");
        }
        List<String> list = new ArrayList<>();
        if(createBookDto.getIsbn() == null){
            list.add("isbn");
        }
        if(createBookDto.getTitle() == null){
            list.add("title");
        }
        return list;
    }


}
