package oliyandebug.libraryapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import oliyandebug.libraryapi.domain.model.Book;
import oliyandebug.libraryapi.service.exception.BusinessException;
import oliyandebug.libraryapi.service.impl.BookServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public record LibraryController(BookServiceImpl bookService) {

    @GetMapping("/")
    @Operation(summary = "List all books of the library")
    public List<Book> findAll(){
        return bookService.findAll();
    }
    @GetMapping("/borrowed")
    @Operation(summary = "List all books borrowed of the library")
    public ResponseEntity<List<Book>> findAllBorrowed() {
        return ResponseEntity.ok(bookService.findAllBorrowed());
    }

    @GetMapping("/available")
    @Operation(summary = "List all available of the library")
    public ResponseEntity<List<Book>> findAllAvailable() {
        return ResponseEntity.ok(bookService.findAllAvailable());
    }

//    TODO borrowBook returnBook
}
