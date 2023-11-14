package com.library.tijoLibrary.repositories;

import com.library.tijoLibrary.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Możesz dodać dodatkowe metody związane z rezerwacjami
}
