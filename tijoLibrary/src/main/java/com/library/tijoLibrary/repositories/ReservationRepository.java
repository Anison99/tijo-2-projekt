package com.library.tijoLibrary.repositories;

import com.library.tijoLibrary.models.Book;
import com.library.tijoLibrary.models.Reservation;
import com.library.tijoLibrary.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    static boolean existsByBookAndEndDateAfter(Book book, LocalDateTime now) {
        return false;
    }

    List<Reservation> findByUser(User user);
}
