package com.sncf.bibliotheque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sncf.bibliotheque.entity.Book;

  
@Repository
public interface BookRepository extends JpaRepository<Book, Long>  {

    // custom query to search to book by title or author
    List<Book> findByTitleContainingOrAuthorContaining(String text, String textAgain);
    
}