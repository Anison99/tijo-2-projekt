package com.library.tijoLibrary.repositories;

import com.library.tijoLibrary.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);
    List<Book> findByAuthor(String author);

    List<Book> findByCategoryId(Long categoryId);

    Optional<Book> findByTitle(String title);
    @Query("SELECT b.id FROM Book b WHERE b.title = :title")
    Long findBookIdByTitle(@Param("title") String title);
}
