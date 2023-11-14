package com.library.tijoLibrary.repositories;

import com.library.tijoLibrary.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Możesz dodać dodatkowe metody związane z książkami
}
