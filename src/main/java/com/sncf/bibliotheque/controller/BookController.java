package com.sncf.bibliotheque.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sncf.bibliotheque.entity.Book;
import com.sncf.bibliotheque.repository.BookRepository;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/book")
    public List<Book> index(){
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    public Book show(@PathVariable Long id){
        return bookRepository.findById(id).get();
    }

    @PostMapping("/book/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("title");
        return bookRepository.findByTitleContainingOrAuthorContaining(searchTerm, searchTerm);
    }

    @PostMapping("/book")
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PutMapping("/book/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book){
        // getting book
        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return bookRepository.save(bookToUpdate);
    }

    @DeleteMapping("book/{id}")
    public boolean delete(@PathVariable Long id){
        bookRepository.deleteById(id);
        return true;
    }
}
