package com.library.tijoLibrary.repositories;


import com.library.tijoLibrary.models.Book;
import com.library.tijoLibrary.models.BookStatuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookStatusesRepository extends JpaRepository<BookStatuses, Long> {

    Optional<BookStatuses> findByBook_IdAndUser_Id(Long bookId, Long userId);

    Optional<BookStatuses> findByBook_Id(Long bookId);

    Optional<BookStatuses> findByUser_Id(Long userId);

    @Query("SELECT bs.id FROM BookStatuses bs WHERE bs.book.id = :bookId AND bs.isReserved = :isReserved")
    Long findBookStatusIdByBookIdAndReservedStatus(@Param("bookId") Long bookId, @Param("isReserved") boolean isReserved);
}
