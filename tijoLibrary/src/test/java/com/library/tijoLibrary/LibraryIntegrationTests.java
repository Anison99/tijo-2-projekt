package com.library.tijoLibrary;

import com.library.tijoLibrary.models.Book;
import com.library.tijoLibrary.models.Reservation;
import com.library.tijoLibrary.models.User;
import com.library.tijoLibrary.repositories.BookRepository;
import com.library.tijoLibrary.repositories.ReservationRepository;
import com.library.tijoLibrary.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.List;

;import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LibraryIntegrationTests {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddingBookToDatabase() {
        Book bookToAdd = new Book("Example Title", "Example Author", "Example Isbn");
        Book savedBook = bookRepository.save(bookToAdd);
        assertTrue(bookRepository.findById(savedBook.getId()).isPresent());
    }
    @Test
    public void testRemovingBookFromDatabase() {
        Book bookToRemove = new Book("Example Title", "Example Author", "Example Isbn");
        entityManager.persist(bookToRemove);
        bookRepository.delete(bookToRemove);
        assertFalse(bookRepository.findById(bookToRemove.getId()).isPresent());
    }

    @Test
    public void testUserRegistrationAndLoginWithDatabase() {
        User user = new User("testuser");
        user.setPassword(passwordEncoder.encode("testpassword"));
        userRepository.save(user);
        User savedUser = userRepository.findByUsername("testuser").orElse(null);
        assertNotNull(savedUser);
        assertTrue(passwordEncoder.matches("testpassword", savedUser.getPassword()));
    }

    @Test
    public void testLoanHistoryRetrieval() {
        User user = new User("testuser");
        userRepository.save(user);

        Reservation reservation1 = new Reservation();
        reservation1.setBookTitle("Book 1");
        reservation1.setStartDate(LocalDateTime.now().minusDays(3));
        reservation1.setEndDate(LocalDateTime.now().plusDays(4));
        reservation1.setUser(user);
        reservationRepository.save(reservation1);

        Reservation reservation2 = new Reservation();
        reservation2.setBookTitle("Book 2");
        reservation2.setStartDate(LocalDateTime.now().minusDays(2));
        reservation2.setEndDate(LocalDateTime.now().plusDays(5));
        reservation2.setUser(user);
        reservationRepository.save(reservation2);

        List<Reservation> userReservations = reservationRepository.findByUser(user);

        assertEquals(2, userReservations.size());
        assertTrue(userReservations.contains(reservation1));
        assertTrue(userReservations.contains(reservation2));
    }

    @Test
    public void testCheckingBookAvailabilityWithReservations() {
        Book book = new Book("Book Title", "Book Author", "ISBN");
        bookRepository.save(book);

        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setStartDate(LocalDateTime.now().minusDays(2));
        reservation.setEndDate(LocalDateTime.now().plusDays(5));
        reservationRepository.save(reservation);
        boolean isBookAvailable = ReservationRepository.existsByBookAndEndDateAfter(book, LocalDateTime.now());

        assertFalse(isBookAvailable);
    }

    @Test
    public void testAccountRegistrationWithExistingEmail() {
        User existingUser = new User("existinguser");
        existingUser.setEmail("existing@example.com");
        userRepository.save(existingUser);

        User newUser = new User("newuser");
        newUser.setEmail("existing@example.com");

        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(newUser));
    }

    @Test
    public void testReservingBookAlreadyReserved() {
        Book book = new Book("Book Title", "Book Author", "ISBN");
        bookRepository.save(book);

        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setStartDate(LocalDateTime.now().minusDays(2));
        reservation.setEndDate(LocalDateTime.now().plusDays(5));

        reservationRepository.save(reservation);

        Reservation anotherReservation = new Reservation();
        anotherReservation.setBook(book);
        anotherReservation.setStartDate(LocalDateTime.now().plusDays(1));
        anotherReservation.setEndDate(LocalDateTime.now().plusDays(6));

        assertThrows(Exception.class, () -> reservationRepository.save(anotherReservation));
    }
    @Test
    public void testExtendingReservationAtMaximumPeriod() {
        Book book = new Book("Book Title", "Book Author", "ISBN");
        bookRepository.save(book);

        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setStartDate(LocalDateTime.now());
        reservation.setEndDate(LocalDateTime.now().plusDays(7));
        reservationRepository.save(reservation);

        int maximumExtensions = 3;
        for (int i = 0; i < maximumExtensions; i++) {
            reservation.extendReservation();
        }

        assertThrows(Exception.class, () -> reservation.extendReservation());
    }

    @Test
    public void testCheckingLoanHistoryForMultipleUsers() {
        // Dodanie kilku użytkowników do bazy danych
        User user1 = new User("user1");
        userRepository.save(user1);

        User user2 = new User("user2");
        userRepository.save(user2);

        Book book = new Book("Book Title", "Book Author", "ISBN");
        bookRepository.save(book);

        Reservation reservation1 = new Reservation();
        reservation1.setBook(book);
        reservation1.setStartDate(LocalDateTime.now().minusDays(3));
        reservation1.setEndDate(LocalDateTime.now().plusDays(4));
        reservation1.setUser(user1);
        reservationRepository.save(reservation1);

        Reservation reservation2 = new Reservation();
        reservation2.setBook(book);
        reservation2.setStartDate(LocalDateTime.now().minusDays(2));
        reservation2.setEndDate(LocalDateTime.now().plusDays(5));
        reservation2.setUser(user2);
        reservationRepository.save(reservation2);

        List<Reservation> user1Reservations = reservationRepository.findByUser(user1);
        List<Reservation> user2Reservations = reservationRepository.findByUser(user2);

        assertEquals(1, user1Reservations.size());
        assertEquals(1, user2Reservations.size());
    }
    @Test
    public void testEmailNotificationIntegration() {
        // todo: rozwiń implementacje
    }

    @Test
    public void testMessageNotificationIntegration() {
        // todo: rozwiń implementacje
    }
    @Test
    public void testWaitingListIntegration() {
        // todo: rozwiń implementacje
    }

    @Test
    public void testNotificationOnWaitingListIntegration() {
        // todo: rozwiń implementacje
    }
    @Test
    public void testRatingIntegration() {
        // todo: rozwiń implementacje
    }

    @Test
    public void testReviewsImpactIntegration() {
        // todo: rozwiń implementacje
    }

    @Test
    public void testUserActivityHistoryIntegration() {
        // todo: rozwiń implementacje
    }

    @Test
    public void testUserActivityMetricsIntegration() {
        // todo: rozwiń implementacje
    }

    @Test
    public void testReturnReminderIntegration() {
        // todo: rozwiń implementacje
    }

    @Test
    public void testBookCategorizationIntegration() {
        // todo: rozwiń implementacje
    }

    @Test
    public void testBookRecommendationsIntegration() {
        // todo: rozwiń implementacje
    }

    @Test
    public void testNotificationServiceIntegration() {
        // todo: rozwiń implementacje
    }

    @Test
    public void testWaitingListServiceIntegration() {
        // todo: rozwiń implementacje
    }

    @Test
    public void testRatingServiceIntegration() {
        // todo: rozwiń implementacje
    }

    @Test
    public void testUserActivityServiceIntegration() {
        // todo: rozwiń implementacje
    }
}
