package com.example.project.controller;

import com.example.project.model.Book;
import com.example.project.repository.BooksRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BooksRepository bookRepository;

    public BookController(BooksRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping("/add_book")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        bookRepository.save(book);
        return ResponseEntity.ok("Book added successfully");
    }

    @PutMapping("/update_book/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookRepository.findById(id).orElse(null);
        if (updatedBook == null) {
            return ResponseEntity.badRequest().body("Book not found");
        }
        updatedBook.setTitle(book.getTitle());
        bookRepository.save(updatedBook);
        return ResponseEntity.ok("Book updated successfully");
    }

    @DeleteMapping("/delete_book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return ResponseEntity.badRequest().body("Book not found");
        }
        bookRepository.delete(book);
        return ResponseEntity.ok("Book deleted successfully");
    }
}
