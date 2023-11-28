package com.library.tijoLibrary;

import com.library.tijoLibrary.models.Book;
import com.library.tijoLibrary.models.Reservation;
import com.library.tijoLibrary.models.User;
import com.library.tijoLibrary.services.BookService;
import com.library.tijoLibrary.services.ReservationService;
import com.library.tijoLibrary.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LibraryUnitTests {

    private BookService bookService;
    private UserService userService;
    private ReservationService reservationService;

    @BeforeEach
    public void setUp() {
        bookService = new BookService();
        userService = new UserService();
        reservationService = new ReservationService();
    }

    @Test
    public void testAddingBook() {
        Book newBook = new Book("Title", "Author", "ISBN");
        bookService.addBook(newBook);
        Assertions.assertTrue(bookService.getBooks().contains(newBook));
    }

    @Test
    public void testBookReservation() {
        User user = new User("username");
        Book book = new Book("Title", "Author", "ISBN");
        bookService.addBook(book);
        reservationService.reserveBook(book, user);
        Assertions.assertEquals("reserved", book.getStatus());
        Assertions.assertEquals(user, book.getReservedBy());
    }

    @Test
    public void testRemovingBook() {
        Book book = new Book("Title", "Author", "ISBN");
        bookService.addBook(book);
        bookService.removeBook(book);
        Assertions.assertFalse(bookService.getBooks().contains(book));
    }

    @Test
    public void testExtendingReservation() {
        Book book = new Book("Title", "Author", "ISBN");
        bookService.addBook(book);
        User user = new User("username");
        reservationService.reserveBook(book, user);

        reservationService.extendReservation(book, user, 7);

        short expectedExtendedDate = 0;
        Assertions.assertEquals(expectedExtendedDate, reservationService.getReturnDate(book, user));

        // todo: Zdefiniuj oczekiwaną datę przedłużenia książki na podstawie implementacji. Może to być bieżąca data oddania + 7 dni
        // todo: Data przedłużenia nie może być przekroczona
    }

    @Test
    public void testCheckingBookStatus() {
        Book availableBook = new Book("Available Title", "Author", "ISBN");
        bookService.addBook(availableBook);
        Book reservedBook = new Book("Reserved Title", "Author", "ISBN");
        bookService.addBook(reservedBook);
        User user = new User("username");
        reservationService.reserveBook(reservedBook, user);

        Assertions.assertTrue(bookService.checkAvailability(availableBook));
        Assertions.assertFalse(bookService.checkAvailability(reservedBook));
    }

    @Test
    public void testLoanHistory() {
        User user = new User("username");
        Book book = new Book("Title", "Author", "ISBN");
        bookService.addBook(book);
        reservationService.reserveBook(book, user);

        List<Reservation> loanHistory = reservationService.getLoanHistory(user);

        Assertions.assertTrue(loanHistory.stream().anyMatch(reservation ->
                reservation.getBook().equals(book) && reservation.getUser().equals(user)));
    }

    @Test
    public void testAccountRegistrationAndLogin() {
        String username = "newUser";
        String password = "password";

        userService.registerUser(username, password);

        Assertions.assertTrue(userService.isUserRegistered(username));
        User loggedInUser = userService.login(username, password);

        Assertions.assertNotNull(loggedInUser);
        Assertions.assertEquals(username, loggedInUser.getUsername());
    }

    @Test
    public void testUserLoginDetails() {
        String username = "existingUser";
        String password = "password123";

        userService.addUser(username, password);

        User loggedInUser = userService.login(username, password);
        Assertions.assertNotNull(loggedInUser);
        Assertions.assertEquals(username, loggedInUser.getUsername());
    }

    @Test
    public void testChangingReservationStatus() {
        User user = new User("username");
        Book book = new Book("Title", "Author", "ISBN");
        bookService.addBook(book);

        reservationService.reserveBook(book, user);
        Assertions.assertEquals("reserved", book.getStatus());
        reservationService.borrowBook(book, user);
        Assertions.assertEquals("borrowed", book.getStatus());
        reservationService.returnBook(book, user);
        Assertions.assertEquals("reserved", book.getStatus());
    }

    @Test
    public void testEmailNotificationOnBookDueDate() {
        // Test implementation
    }

    @Test
    public void testMessageNotificationOnAccountChanges() {
        // Test implementation
    }

    @Test
    public void testWaitingListAdditionForPopularBook() {
        // Test implementation
    }

    @Test
    public void testNotificationToFirstInWaitingListOnBookReturn() {
        // Test implementation
    }

    @Test
    public void testUserCanSubmitRating() {
        // Test implementation
    }

    @Test
    public void testReviewsImpactBookSelection() {
        // Test implementation
    }

    @Test
    public void testUserActivityHistoryWithBorrowedBooks() {
        // Test implementation
    }

    @Test
    public void testUserActivityHistoryLateReturns() {
        // Test implementation
    }

    @Test
    public void testUserActivityHistoryReservationFrequency() {
        // Test implementation
    }

    @Test
    public void testAutomaticReturnReminder() {
        // Test implementation
    }

    @Test
    public void testBookCategorization() {
        // Test implementation
    }


}

