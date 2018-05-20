package app.book.web;

import app.book.Book;
import app.book.BookService;
import app.book.CreateBookDto;
import app.book.UpdateBookDto;
import app.book.exception.MissingPropertyException;
import app.book.exception.NoBookFound;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
@CrossOrigin
class BookController {

    private final Gson gson;
    private final BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
        this.gson = new Gson();
    }

    @PutMapping("/{id}")
    ResponseEntity update(@RequestBody String body, @PathVariable Long id) {
        UpdateBookDto updateBookDto = gson.fromJson(body, UpdateBookDto.class);
        try {
            bookService.update(id, updateBookDto);
            return ResponseEntity.ok().build();
        } catch (NoBookFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @ExceptionHandler({MissingPropertyException.class})
    ResponseEntity handleException(MissingPropertyException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }


    @GetMapping
    List<BookDto> findAll() {
        List<Book> books = bookService.findAll();
        List<BookDto> bookDtos = mapToDto(books);
        return bookDtos;
    }

    @GetMapping("/{id}")
    ResponseEntity find(@PathVariable Long id) {
        Book book = bookService.find(id);
        if (book != null) {
            return ResponseEntity.ok(gson.toJson(mapToDto(book)));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    ResponseEntity create(@RequestBody String json) {
        CreateBookDto createBookDto = gson.fromJson(json, CreateBookDto.class);
        try {
            Book book = bookService.createBook(createBookDto);
            return ResponseEntity.ok(book.getId());
        } catch (MissingPropertyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private List<BookDto> mapToDto(List<Book> books) {
        List<BookDto> list = new ArrayList<>();
        for (Book book : books) {
            BookDto dto = mapToDto(book);
            list.add(dto);
        }
        return list;
    }

    private BookDto mapToDto(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setPublisher(book.getPublisher());
        dto.setTitle(book.getTitle());
        dto.setType(book.getType());
        return dto;
    }

}
