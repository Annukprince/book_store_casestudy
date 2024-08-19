package org.example.service;

import org.example.domain.Book;
import org.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(()->new RuntimeException("book not found with id"));
    }

    public Book createBook(Book book)
    {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id,Book book)
    {
        if(!bookRepository.existsById(id)){
            throw new RuntimeException("book not found");
        }
        book.setId(id);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id)
    {
        if(!bookRepository.existsById(id)){
            throw new RuntimeException("book not found");
        }
        bookRepository.deleteById(id);
    }

    public int returnQuantity(long id){
        Book book = bookRepository.findById(id).orElseThrow(()->new RuntimeException("Book not fond"));
        int quantity = book.getStock();
        return quantity;
    }



}
