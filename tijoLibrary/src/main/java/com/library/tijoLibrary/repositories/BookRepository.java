package com.library.tijoLibrary.repositories;

import com.library.tijoLibrary.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);
    List<Book> findByAuthor(String author);

    List<Book> findByCategoryId(Long categoryId);
}
