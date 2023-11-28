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
        User user = new User("user");
        Book borrowedBook = new Book("1234567890", "Integrated Book", "Author Z");

        user.borrowBook(borrowedBook);

        assertTrue(user.isEmailNotificationSent());
    }

    @Test
    public void testMessageNotificationIntegration() {
        User user = new User("user");
        Book reservedBook = new Book("9876543210", "Reserved Book", "Author W");

        user.reserveBook(reservedBook);

        assertTrue(user.isMessageNotificationSent());
    }
    @Test
    public void testWaitingListIntegration() {
        User user1 = new User("user");
        User user2 = new User("user2");
        Book popularBook = new Book("111122223333", "Popular Book", "Author X");

        popularBook.addToWaitingList(user1);
        popularBook.addToWaitingList(user2);

        assertEquals(user1, popularBook.getFirstInWaitingList());
        assertTrue(user1.isNotificationReceived());
    }

    @Test
    public void testNotificationOnWaitingListIntegration() {
        User user1 = new User("User 1");
        User user2 = new User("User 2");
        Book highDemandBook = new Book("333344445555", "High Demand Book", "Author Y");

        highDemandBook.addToWaitingList(user1);
        highDemandBook.addToWaitingList(user2);

        highDemandBook.returnBook();

        assertTrue(user1.isNotificationReceived());

    }
    @Test
    public void testRatingIntegration() {
        User user = new User("User");
        Book ratedBook = new Book("444455556666", "Rated Book", "Author Z");

        user.borrowBook(ratedBook);

        user.rateBook(ratedBook, 4);

        assertEquals(4, ratedBook.getAverageRating(), 0.01);
    }

    @Test
    public void testReviewsImpactIntegration() {
        User user1 = new User("User 1");
        User user2 = new User("User 2");
        Book reviewedBook = new Book("777788889999", "Reviewed Book", "Author X");

        user1.borrowBook(reviewedBook);
        user1.addReview(reviewedBook, "Great book!", 5);

        user2.borrowBook(reviewedBook);

        assertTrue(user2.isBookSelectedBasedOnReview(reviewedBook));
    }

    @Test
    public void testUserActivityHistoryIntegration() {
        User user = new User("User");
        Book borrowedBook = new Book("1234567890", "Integrated Book", "Author Z");

        user.borrowBook(borrowedBook);

        assertTrue(user.isBookInActivityHistory(borrowedBook));
    }

    @Test
    public void testUserActivityMetricsIntegration() {
        User user = new User("User");
        Book borrowedBook1 = new Book("111122223333", "Book 1", "Author X");
        Book borrowedBook2 = new Book("444455556666", "Book 2", "Author Y");

        user.borrowBook(borrowedBook1);
        user.borrowBook(borrowedBook2);

        assertEquals(2, user.getNumberOfBooksBorrowed());
    }

    @Test
    public void testReturnReminderIntegration() {
        User user = new User("User");
        Book borrowedBook = new Book("777788889999", "Reminder Book", "Author W");

        user.borrowBook(borrowedBook);

        simulatePassageOfTime(14);

        assertTrue(user.isReturnReminderSent());
    }

    private void simulatePassageOfTime(int i) {
        
    }

    @Test
    public void testBookCategorizationIntegration() {
        Book categorizedBook = new Book("888899990000", "Categorized Book", "Author Z");
        Category category = new Category("Thriller");

        Library.addCategory(category);

        categorizedBook.addCategory(category);

        assertTrue(category.containsBook(categorizedBook));
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