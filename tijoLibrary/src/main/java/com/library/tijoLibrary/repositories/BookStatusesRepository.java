package com.library.tijoLibrary.repositories;


import com.library.tijoLibrary.models.Book;
import com.library.tijoLibrary.models.BookStatuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookStatusesRepository extends JpaRepository<BookStatuses, Long> {

    Optional<BookStatuses> findByBookIdAndUserId(Long bookId, Long userId);

    Optional<BookStatuses> findByBookId(Long bookId);
}
