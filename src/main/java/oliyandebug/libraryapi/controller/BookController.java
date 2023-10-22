package oliyandebug.libraryapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import oliyandebug.libraryapi.domain.model.Book;
import oliyandebug.libraryapi.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public record BookController (BookService bookService) {

    @GetMapping("/")
    @Operation(summary = "List all books in the library")
    public ResponseEntity<List<Book>> findAll(){
        var books = bookService.findAll();
        return ResponseEntity.ok(books);
    }
    @PostMapping("/")
    @Operation(summary = "Add book to library")
    public Book add(@RequestBody Book book){
        return bookService.create(book);
    }


    /*
     TODO book delete
     TODO book update
    */

}
