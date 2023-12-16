package com.library.tijoLibrary.repositories;


<<<<<<< HEAD
=======
import com.library.tijoLibrary.models.Book;
>>>>>>> origin/develop
import com.library.tijoLibrary.models.BookStatuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookStatusesRepository extends JpaRepository<BookStatuses, Long> {

<<<<<<< HEAD
    Optional<BookStatuses> findByBookIdAndUserId(Long bookId, Long userId);
=======
    Optional<BookStatuses> findByBook_IdAndUser_Id(Long bookId, Long userId);

    Optional<BookStatuses> findByBook_Id(Long bookId);
>>>>>>> origin/develop
}
