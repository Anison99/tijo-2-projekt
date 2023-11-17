package com.library.tijoLibrary.services;

import com.library.tijoLibrary.models.Book;
import com.library.tijoLibrary.models.Reservation;
import com.library.tijoLibrary.models.User;
import com.library.tijoLibrary.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public ReservationService() {
        this.reservationRepository = reservationRepository;
    }

    public void reserveBook(Book book, User user) {
    }

    public void extendReservation(Book book, User user, int i) {
    }

    public short getReturnDate(Book book, User user) {

        return 0;
    }

    public List<Reservation> getLoanHistory(User user) {
        return null;
    }

    public void borrowBook(Book book, User user) {
    }

    public void returnBook(Book book, User user) {
    }

    // implementacje metod operacji na rezerwacjach
}
