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

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    private void simulatePassageOfTime(int days) {
       
        LocalDate currentDate = LocalDate.now();
        LocalDate simulatedDate = currentDate.plusDays(days);
        SystemDate.setCurrentDate(simulatedDate);
    }

    @Test
    public void testAddingBook() {
        Book newBook = new Book("Title", "Author", "ISBN");
        bookService.addBook(newBook);
        assertTrue(bookService.getBooks().contains(newBook));
    }

    @Test
    public void testBookReservation() {
        User user = new User("username");
        Book book = new Book("Title", "Author", "ISBN");
        bookService.addBook(book);
        reservationService.reserveBook(book, user);
        assertEquals("reserved", book.getStatus());
        assertEquals(user, book.getReservedBy());
    }

    @Test
    public void testRemovingBook() {
        Book book = new Book("Title", "Author", "ISBN");
        bookService.addBook(book);
        bookService.removeBook(book);
        assertFalse(bookService.getBooks().contains(book));
    }

    @Test
    public void testExtendingReservation() {
        Book book = new Book("Title", "Author", "ISBN");
        bookService.addBook(book);
        User user = new User("username");
        reservationService.reserveBook(book, user);

        reservationService.extendReservation(book, user, 7);

        short expectedExtendedDate = 0;
        assertEquals(expectedExtendedDate, reservationService.getReturnDate(book, user));

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

        assertTrue(bookService.checkAvailability(availableBook));
        assertFalse(bookService.checkAvailability(reservedBook));
    }

    @Test
    public void testLoanHistory() {
        User user = new User("username");
        Book book = new Book("Title", "Author", "ISBN");
        bookService.addBook(book);
        reservationService.reserveBook(book, user);

        List<Reservation> loanHistory = reservationService.getLoanHistory(user);

        assertTrue(loanHistory.stream().anyMatch(reservation ->
                reservation.getBook().equals(book) && reservation.getUser().equals(user)));
    }

    @Test
    public void testAccountRegistrationAndLogin() {
        String username = "newUser";
        String password = "password";

        userService.registerUser(username, password);

        assertTrue(userService.isUserRegistered(username));
        User loggedInUser = userService.login(username, password);

        Assertions.assertNotNull(loggedInUser);
        assertEquals(username, loggedInUser.getUsername());
    }

    @Test
    public void testUserLoginDetails() {
        String username = "existingUser";
        String password = "password123";

        userService.addUser(username, password);

        User loggedInUser = userService.login(username, password);
        Assertions.assertNotNull(loggedInUser);
        assertEquals(username, loggedInUser.getUsername());
    }

    @Test
    public void testChangingReservationStatus() {
        User user = new User("username");
        Book book = new Book("Title", "Author", "ISBN");
        bookService.addBook(book);

        reservationService.reserveBook(book, user);
        assertEquals("reserved", book.getStatus());
        reservationService.borrowBook(book, user);
        assertEquals("borrowed", book.getStatus());
        reservationService.returnBook(book, user);
        assertEquals("reserved", book.getStatus());
    }

    // ------------------------------------------------------
    @Test
    public void testEmailNotificationOnBookDueDate() {
         User user = new User("user");
         Book book = new Book("1234567890", "Test Book", "Author");
         user.borrowBook(book);

         simulatePassageOfTime(5);
         assertTrue(user.hasReceivedEmailNotification());
         String expectedNotification = "Przypominamy o zbliżającym się terminie zwrotu książki: Test Book.";
         assertEquals(expectedNotification, user.getLastEmailNotification());
    }

    @Test
    public void testMessageNotificationOnAccountChanges() {
        User user = new User("user");
        user.updateName("Anna Kowalska");

        assertTrue(user.hasReceivedMessageNotification());
        String expectedNotification = "Twoje konto zostało zaktualizowane. Nowe imię: Anna Kowalska.";
        assertEquals(expectedNotification, user.getLastMessageNotification());
    }

    @Test
    public void testWaitingListAdditionForPopularBook() {
         Book popularBook = new Book("9876543210", "Popular Book", "Famous Author");
         User user1 = new User("user");
         User user2 = new User("user");
 
         popularBook.addToWaitingList(user1);
         popularBook.addToWaitingList(user2);
 
         assertTrue(popularBook.isInWaitingList(user1));
         assertTrue(popularBook.isInWaitingList(user2));
    }

    @Test
    public void testNotificationToFirstInWaitingListOnBookReturn() {
        Book returnedBook = new Book("5678901234", "Returned Book", "Some Author");
        User user1 = new User("user");
        User user2 = new User("user");

        returnedBook.addToWaitingList(user1);
        returnedBook.addToWaitingList(user2);

        returnedBook.returnBook();

        assertTrue(user1.hasReceivedNotification());
        assertEquals("Twoja zarezerwowana książka jest teraz dostępna: Returned Book.", user1.getLastNotification());

        assertFalse(user2.hasReceivedNotification());
        assertNull(user2.getLastNotification());
    }

    @Test
    public void testUserCanSubmitRating() {
        Book ratedBook = new Book("9876543210", "Rated Book", "Another Author");
        User user = new User("user");

        user.borrowBook(ratedBook);

        user.rateBook(ratedBook, 4);

        assertEquals(4, ratedBook.getAverageRating(), 0.001);
    }

    @Test
    public void testReviewsImpactBookSelection() {
        Book book1 = new Book("1111111111", "Book 1", "Author 1");
        Book book2 = new Book("2222222222", "Book 2", "Author 2");
        User user = new User("user");

        user.addReview(book1, "Great book!", 5);
        user.addReview(book2, "Not so good.", 2);

        assertEquals(5, book1.getAverageRating(), 0.001);
        assertEquals(2, book2.getAverageRating(), 0.001);

        Book recommendedBook = user.getRecommendedBook();
        assertEquals(book1, recommendedBook);
    }

    @Test
    public void testUserActivityHistoryWithBorrowedBooks() {
        User user = new User("user");
        Book book1 = new Book("1111111111", "Book 1", "Author 1");
        Book book2 = new Book("2222222222", "Book 2", "Author 2");

        user.borrowBook(book1);
        user.borrowBook(book2);

        String activityHistory = user.getActivityHistory();
        String expectedHistory = "Wypożyczono książkę: Book 1\nWypożyczono książkę: Book 2";
        assertEquals(expectedHistory, activityHistory);
    }

    @Test
    public void testUserActivityHistoryLateReturns() {
        User user = new User("user");
        Book overdueBook = new Book("3333333333", "Overdue Book", "Author 3");

        user.borrowBook(overdueBook);

        simulatePassageOfTime(10);
        overdueBook.returnBook();

        String activityHistory = user.getActivityHistory();
        String expectedHistory = "Wypożyczono książkę: Overdue Book\nZwrócono książkę z opóźnieniem: Overdue Book";
        assertEquals(expectedHistory, activityHistory);
    }

    @Test
    public void testUserActivityHistoryReservationFrequency() {
         User user = new User("user");
         Book reservedBook = new Book("4444444444", "Reserved Book", "Author 4");
 
         reservedBook.addToWaitingList(user);
 
         String activityHistory = user.getActivityHistory();
         String expectedHistory = "Dodano do listy oczekujących: Reserved Book";
         assertEquals(expectedHistory, activityHistory);
    }

    @Test
    public void testAutomaticReturnReminder() {
        User user = new User("user");
        Book borrowedBook = new Book("5555555555", "Borrowed Book", "Author 5");

        user.borrowBook(borrowedBook);

        simulatePassageOfTime(7);

        assertTrue(user.hasReceivedNotification());
        assertEquals("Przypomnienie: Termin zwrotu książki Approaching Deadline Book zbliża się.", user.getLastNotification());
    }

    @Test
    public void testBookCategorization() {
        // todo: rozwiń implementacje
    }
    @Test
    public void testEmailNotificationService() {
        // todo: rozwiń implementacje
    }

    @Test
    public void testMessageNotificationService() {
        // todo: rozwiń implementacje
    }
    @Test
    public void testAddingUserToWaitingList() {
        // todo: rozwiń implementacje
    }

    @Test
    public void testNotificationToFirstUserInWaitingList() {
        // todo: rozwiń implementacje
    }
    @Test
    public void testSubmittingRating() {
        // todo: rozwiń implementacje
    }

    @Test
    public void testImpactOfReviews() {
        // todo: rozwiń implementacje
    }
    @Test
    public void testUserActivityHistory() {
        // todo: rozwiń implementacje
    }

    @Test
    public void testUserActivityMetrics() {
        // todo: rozwiń implementacje
    }
}