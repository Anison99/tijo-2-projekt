package com.library.tijoLibrary.services;

import com.library.tijoLibrary.models.Reservation;
import com.library.tijoLibrary.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // implementacje metod operacji na rezerwacjach
}
