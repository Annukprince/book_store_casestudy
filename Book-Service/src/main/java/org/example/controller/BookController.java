package org.example.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.domain.Book;
import org.example.dto.BookDto;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id)
    {
        Book book=bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody @Valid BookDto bookDto)
    {
        Book book=new Book();
        book.setName(bookDto.name());
        book.setAuthorName(bookDto.authorName());
        book.setStock(bookDto.stock());
        book.setPrice(bookDto.price());
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable @NotNull Long id, @RequestBody @Valid BookDto bookDto){
        Book book = new Book();
        book.setId(id);
        book.setName(bookDto.name());
        book.setAuthorName(bookDto.authorName());
        book.setStock(bookDto.stock());
        book.setPrice(bookDto.price());
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable @NotNull Long id)
    {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/getquantity/{id}")
    public ResponseEntity<Integer> getQuantity(@PathVariable long id) {
        int quantity = bookService.returnQuantity(id);
        return ResponseEntity.ok(quantity);
    }

}
