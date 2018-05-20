package app.book.web;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
class BookController {

    private final Gson gson;

    BookController() {
        this.gson = new Gson();
    }

    @GetMapping
    String test(){
        return "test";
    }


    @PostMapping
    String create(@RequestBody String json){
        BookDto bookDto = gson.fromJson(json, BookDto.class);
        System.out.println(bookDto);
        return gson.toJson(bookDto);
    }

}
