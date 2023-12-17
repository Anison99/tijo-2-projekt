package com.library.tijoLibrary;

import com.library.tijoLibrary.models.*;
import com.library.tijoLibrary.repositories.*;
import com.library.tijoLibrary.services.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.dao.DataIntegrityViolationException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

;import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LibraryIntegrationTests {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookStatusesService bookStatusesService;

    @Autowired
    private BookStatusesRepository bookStatusesRepository;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private HistoryRepository historyRepository;

    @Test
    void testWhenUserExists_thenShouldFindUser() {
        String username = "newUser12345678";
        String password = "testPassword1";

        userService.registerUser(username, password);
        User user = userService.loadUserByUsername("newUser12345678");

        assertNotNull(user);
        assertEquals("newUser12345678", user.getUsername());
    }
    @Test
    @Transactional
    void testWhenDeleteUser_thenUserShouldBeRemoved() {
        User user = new User();
        user.setUsername("deleteTestUser");
        user.setPassword("password");

        userRepository.save(user);
        userService.deleteUser(user.getId());

        assertFalse(userRepository.findById(user.getId()).isPresent());
    }

    @Test
    @Transactional
    void testWhenUpdateUsername_thenUsernameShouldBeUpdated() {
        User user = new User();
        user.setUsername("oldUsername");
        user.setPassword("password");

        userRepository.save(user);
        userService.updateUserName("oldUsername", "newUsername");
        Optional<User> updatedUser = userRepository.findByUsername("newUsername");

        assertTrue(updatedUser.isPresent());
        assertEquals("newUsername", updatedUser.get().getUsername());
    }

    @Test
    @Transactional
    void testWhenUpdateUserPassword_thenPasswordShouldBeUpdated() {
        User user = new User();
        user.setUsername("userForPasswordChange");
        user.setPassword("oldPassword");

        userRepository.save(user);
        userService.updateUserPassword("userForPasswordChange", "newPassword");
        Optional<User> updatedUser = userRepository.findByUsername("userForPasswordChange");

        assertTrue(updatedUser.isPresent());
        assertEquals("newPassword", updatedUser.get().getPassword());
    }

    @Test
    @Transactional
    void testWhenUpdateUserEmail_thenEmailShouldBeUpdated() {
        User user = new User();
        user.setUsername("userForEmailChange");
        user.setPassword("password");
        user.setEmail("oldEmail@example.com");

        userRepository.save(user);
        userService.updateUserEmail("userForEmailChange", "newEmail@example.com");
        Optional<User> updatedUser = userRepository.findByUsername("userForEmailChange");

        assertTrue(updatedUser.isPresent());
        assertEquals("newEmail@example.com", updatedUser.get().getEmail());
    }

    @Test
    @Transactional
    void testWhenRegisterUser_thenUserShouldBeAdded() {
        String username = "newUserRegistration";
        String password = "password123456";

        User registeredUser = userService.registerUser(username, password);

        assertNotNull(registeredUser);
        assertEquals(username, registeredUser.getUsername());
        assertEquals(password, registeredUser.getPassword());
    }

    @Test
    @Transactional
    void testWhenGetUserById_thenShouldReturnUser() {
        User user = new User();
        user.setUsername("getUserByIdTest");
        user.setPassword("password");
        userRepository.save(user);

        User foundUser = userService.getUserById(user.getId());

        assertNotNull(foundUser);
        assertEquals("getUserByIdTest", foundUser.getUsername());
    }

    @Test
    @Transactional
    void testWhenAddBook_thenBookShouldBeAdded() {
        String author = "NewAuthor";
        String title = "NewTitle";

        Book book = bookService.addBook(author, title);

        assertNotNull(book);
        assertEquals(author, book.getAuthor());
        assertEquals(title, book.getTitle());
    }

    @Test
    void testWhenGetAllBooks_thenShouldReturnBooks() {
        bookRepository.save(new Book("Author1", "Title1"));
        bookRepository.save(new Book("Author2", "Title2"));

        List<Book> books = bookService.getAllBooks();

        assertNotNull(books);
        assertTrue(books.size() >= 2);
    }

    @Test
    @Transactional
    void testWhenDeleteBook_thenBookShouldBeRemoved() {
        Book book = new Book("DeleteAuthor", "DeleteTitle");

        book = bookRepository.save(book);
        bookService.deleteBook(book.getId());

        assertFalse(bookRepository.existsById(book.getId()));
    }

    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK
    //ANITA I DOMINIK

    @Test
    @Transactional
    void testWhenUpdateBookTitle_thenTitleShouldBeUpdated() {
        Book book = new Book("AuthorForTitleUpdate", "OldTitle");
        book = bookRepository.save(book);

        bookService.updateBookTitle(book.getId(), "NewTitle");

        Book updatedBook = bookRepository.findById(book.getId()).get();
        assertEquals("NewTitle", updatedBook.getTitle());
    }
    @Test
    @Transactional
    void testWhenUpdateBookAuthor_thenAuthorShouldBeUpdated() {
        Book book = new Book("OldAuthor", "TitleForAuthorUpdate");
        book = bookRepository.save(book);

        bookService.updateBookAuthor(book.getId(), "NewAuthor");

        Book updatedBook = bookRepository.findById(book.getId()).get();
        assertEquals("NewAuthor", updatedBook.getAuthor());
    }

    @Test
    @Transactional
    void testWhenAddRating_thenRatingShouldBeAdded() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user = userRepository.save(user);
        Book book = new Book();
        book.setTitle("testBook");
        book.setAuthor("testAuthor");
        book = bookRepository.save(book);

        Rating rating = ratingService.addRating(user.getId(), book.getId(), 5);

        assertNotNull(rating);
        assertEquals(5, rating.getRating());
        assertEquals(user.getId(), rating.getUser().getId());
        assertEquals(book.getId(), rating.getBook().getId());
    }
    @Test
    @Transactional
    void testWhenAddReview_thenShouldAddReview() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user = userRepository.save(user);
        Book book = new Book();
        book.setTitle("testBook");
        book.setAuthor("testAuthor");
        book = bookRepository.save(book);

        Rating rating = ratingService.addReview(user.getId(), book.getId(), "Great book!");

        assertNotNull(rating);
        assertEquals("Great book!", rating.getReview());
    }

    @Test
    @Transactional
    void testWhenDeleteRating_thenRatingShouldBeDeleted() {
        User user = new User();
        user.setUsername("userForRatingDelete");
        user.setPassword("password");
        user = userRepository.save(user);
        Book book = new Book();
        book.setTitle("bookForRatingDelete");
        book.setAuthor("author");
        book = bookRepository.save(book);
        Rating rating = new Rating();
        rating.setUser(user);
        rating.setBook(book);
        rating.setRating(4);
        rating = ratingRepository.save(rating);

        ratingService.deleteRating(rating.getId());

        assertFalse(ratingRepository.existsById(rating.getId()));
    }

    @Test
    @Transactional
    void testWhenDeleteReview_thenShouldRemoveReview() {
        User user = new User();
        user.setUsername("userForRatingDelete");
        user.setPassword("password");
        user = userRepository.save(user);
        Book book = new Book();
        book.setTitle("bookForRatingDelete");
        book.setAuthor("author");
        book = bookRepository.save(book);
        Rating rating = new Rating();
        rating.setUser(user);
        rating.setBook(book);
        rating.setReview("przykladowa recenzja");
        rating = ratingRepository.save(rating);

        ratingService.deleteReview(rating.getId());
        Rating updatedRating = ratingRepository.findById(rating.getId()).get();

        assertNull(updatedRating.getReview());
    }

    @Test
    @Transactional
    void testWhenUpdateRating_thenRatingShouldBeUpdated() {
        User user = new User();
        user.setUsername("userForRatingUpdate");
        user.setPassword("password");
        user = userRepository.save(user);

        Book book = new Book();
        book.setTitle("bookForRatingUpdate");
        book.setAuthor("author");
        book = bookRepository.save(book);

        Rating rating = new Rating();
        rating.setUser(user);
        rating.setBook(book);
        rating.setRating(3);
        rating = ratingRepository.save(rating);

        ratingService.updateRating(rating.getId(), 5);

        Rating updatedRating = ratingRepository.findById(rating.getId()).orElse(null);
        assertNotNull(updatedRating);
        assertEquals(5, updatedRating.getRating());
    }

    @Test
    @Transactional
    void testWhenUpdateReview_thenShouldUpdateReview() {
        User user = new User();
        user.setUsername("userForRatingDelete");
        user.setPassword("password");
        user = userRepository.save(user);
        Book book = new Book();
        book.setTitle("bookForRatingDelete");
        book.setAuthor("author");
        book = bookRepository.save(book);
        Rating rating = new Rating();
        rating.setUser(user);
        rating.setBook(book);
        rating.setReview("przykladowa recenzja");
        rating = ratingRepository.save(rating);

        ratingService.updateReview(rating.getId(), "przykladowa recenzja");
        Rating updatedRating = ratingRepository.findById(rating.getId()).get();

        assertEquals("przykladowa recenzja", updatedRating.getReview());
    }
    @Test
    @Transactional
    void testWhenGetRatingsForUser_thenShouldReturnRatings() {
        User user = new User();
        user.setUsername("testUserForRatings");
        user.setPassword("password");
        user = userRepository.save(user);

        Book book1 = new Book();
        book1.setTitle("testBook1");
        book1.setAuthor("testAuthor1");
        book1 = bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("testBook2");
        book2.setAuthor("testAuthor2");
        book2 = bookRepository.save(book2);

        Rating rating1 = new Rating();
        rating1.setUser(user);
        rating1.setBook(book1);
        rating1.setRating(4);
        ratingRepository.save(rating1);

        Rating rating2 = new Rating();
        rating2.setUser(user);
        rating2.setBook(book2);
        rating2.setRating(5);
        ratingRepository.save(rating2);

        List<Rating> ratings = ratingService.getReviewsMadeByUser(user.getId());

        assertNotNull(ratings);
        assertTrue(ratings.size() >= 2);
        User finalUser = user;
        assertTrue(ratings.stream().allMatch(rating -> rating.getUser().getId().equals(finalUser.getId())));
    }

    // Test dla wyświetlania wszystkich Rating dla podanej książki
    @Test
    @Transactional
    void testWhenGetRatingsForBook_thenShouldReturnRatings() {
        User user1 = new User();
        user1.setUsername("user1ForBookRatings");
        user1.setPassword("password");
        user1 = userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("user2ForBookRatings");
        user2.setPassword("password");
        user2 = userRepository.save(user2);

        Book book = new Book();
        book.setTitle("testBookForRatings");
        book.setAuthor("testAuthorForRatings");
        book = bookRepository.save(book);

        Rating rating1 = new Rating();
        rating1.setUser(user1);
        rating1.setBook(book);
        rating1.setRating(3);
        ratingRepository.save(rating1);

        Rating rating2 = new Rating();
        rating2.setUser(user2);
        rating2.setBook(book);
        rating2.setRating(4);
        ratingRepository.save(rating2);

        List<Rating> ratings = ratingService.getRatingsForBook(book.getId());

        assertNotNull(ratings);
        assertTrue(ratings.size() >= 2);
        Book finalBook = book;
        assertTrue(ratings.stream().allMatch(rating -> rating.getBook().getId().equals(finalBook.getId())));
    }
    @Test
    @Transactional
    void testWhenGetReviewsForBook_thenShouldReturnReviews() {
        User user1 = new User();
        user1.setUsername("user1ForBookReview");
        user1.setPassword("password");
        user1 = userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("user2ForBookReview");
        user2.setPassword("password");
        user2 = userRepository.save(user2);

        Book book = new Book();
        book.setTitle("testBookForRReview");
        book.setAuthor("testAuthorForReview");
        book = bookRepository.save(book);

        Rating review1 = new Rating();
        review1.setUser(user1);
        review1.setBook(book);
        review1.setReview("testReview2");
        ratingRepository.save(review1);

        Rating review2 = new Rating();
        review2.setUser(user2);
        review2.setBook(book);
        review2.setReview("testReview2");
        ratingRepository.save(review2);

        List<Rating> reviews = ratingService.getReviewsForBook(book.getId());

        assertFalse(reviews.isEmpty());
        assertTrue(reviews.stream().allMatch(review -> "testReview2".equals(review.getReview())));
    }

    @Test
    @Transactional
    void testWhenGetReviewsMadeByUser_thenShouldReturnReviews() {
        User user1 = new User();
        user1.setUsername("user1ForBookReview");
        user1.setPassword("password");
        user1 = userRepository.save(user1);

        Book book = new Book();
        book.setTitle("testBookForRReview");
        book.setAuthor("testAuthorForReview");
        book = bookRepository.save(book);

        Rating review1 = new Rating();
        review1.setUser(user1);
        review1.setBook(book);
        review1.setReview("testReview");
        ratingRepository.save(review1);

        Rating review2 = new Rating();
        review2.setUser(user1);
        review2.setBook(book);
        review2.setReview("testReview");
        ratingRepository.save(review2);

        List<Rating> reviews = ratingService.getReviewsMadeByUser(user1.getId());

        assertFalse(reviews.isEmpty());
        assertTrue(reviews.stream().allMatch(review -> "testReview".equals(review.getReview())));
    }

    
    @Test
    @Transactional
    void testWhenMarkBookAsReserved_thenShouldBeReserved() {
        Book book = bookService.addBook("Test Book", "Test Author");
        bookStatusesService.assignStatusToNewBook(book.getId(), false, true);

        bookStatusesService.markBookAsReserved(book.getId());
        BookStatuses bookStatuses = bookStatusesRepository.findByBook_Id(book.getId()).orElse(null);

        assertNotNull(bookStatuses);
        assertTrue(bookStatuses.isReserved());
        assertFalse(bookStatuses.isAvailable());
    }
    @Test
    @Transactional
    void whenMarkBookAsUnavailable_thenShouldBeUnavailable() {
        Book book = bookService.addBook("Test Book", "Test Author");
        bookStatusesService.assignStatusToNewBook(book.getId(), false, true);

        bookStatusesService.markBookAsUnavailable(book.getId());
        BookStatuses bookStatuses = bookStatusesRepository.findByBook_Id(book.getId()).orElse(null);

        assertNotNull(bookStatuses);
        assertFalse(bookStatuses.isAvailable());
    }
    @Test
    @Transactional
    void whenCheckIfBookIsReserved_thenShouldReturnReservationStatus() {
        Book book = bookService.addBook("Test Book", "Test Author");
        bookStatusesService.assignStatusToNewBook(book.getId(), true, false);

        boolean isReserved = bookStatusesService.isBookReserved(book.getId());

        assertTrue(isReserved);
    }
    @Test
    @Transactional
    void whenCheckIfBookIsAvailable_thenShouldReturnAvailabilityStatus() {
        Book book = bookService.addBook("Test Book", "Test Author");
        bookStatusesService.assignStatusToNewBook(book.getId(), false, true);

        boolean isAvailable = bookStatusesService.isBookAvailable(book.getId());

        assertTrue(isAvailable);
    }
    @Test
    @Transactional
    void whenAssignStatusToNewBook_thenStatusShouldBeAssigned() {
        Book book = bookService.addBook("Test Book", "Test Author");

        bookStatusesService.assignStatusToNewBook(book.getId(), false, true);
        BookStatuses bookStatuses = bookStatusesRepository.findByBook_Id(book.getId()).orElse(null);

        assertNotNull(bookStatuses);
        assertFalse(bookStatuses.isReserved());
        assertTrue(bookStatuses.isAvailable());
    }
    @Test
    @Transactional
    void whenRemoveUserFromBook_thenUserShouldBeRemoved() {
        User user = new User();
        user.setUsername("user1ForStatuses");
        user.setPassword("password");
        user = userRepository.save(user);
        Book book = bookRepository.save(new Book("Test Book", "Test Author"));
        bookStatusesService.assignStatusToNewBook(book.getId(), true, false);

        // Przypisanie użytkownika do książki
        BookStatuses bookStatuses = bookStatusesRepository.findByBook_Id(book.getId()).orElse(null);
        assertNotNull(bookStatuses);
        bookStatuses.setUser(user);
        bookStatusesRepository.save(bookStatuses);

        bookStatusesService.removeUserFromBook(book.getId());
        BookStatuses updatedStatuses = bookStatusesRepository.findByBook_Id(book.getId()).orElse(null);

        assertNotNull(updatedStatuses);
        assertNull(updatedStatuses.getUser());
        assertFalse(updatedStatuses.isReserved());
    }
    @Test
    @Transactional
    void testWhenGetUserLoanHistory_thenShouldReturnHistory() {
        User user = new User();
        user.setUsername("user1ForStatuses");
        user.setPassword("password");
        user = userRepository.save(user);
        Book book = bookRepository.save(new Book("Test Book", "Test Author"));

        History historyEntry = new History();
        historyEntry.setUserId(user.getId());
        historyEntry.setBookId(book.getId());
        historyRepository.save(historyEntry);

        List<History> history = historyService.getUserLoanHistory(user.getId());

        assertFalse(history.isEmpty());
        assertEquals(user.getId(), history.get(0).getUserId());
    }

    @Test
    @Transactional
    void testWhenDeleteHistoryEntry_thenEntryShouldBeDeleted() {
        User user = new User();
        user.setUsername("user1ForStatuses");
        user.setPassword("password");
        user = userRepository.save(user);
        Book book = bookRepository.save(new Book("Test Book", "Test Author"));
        History historyEntry = new History();
        historyEntry.setUserId(user.getId());
        historyEntry.setBookId(book.getId());
        historyEntry = historyRepository.save(historyEntry);

        historyService.deleteHistoryEntry(historyEntry.getId());

        assertFalse(historyRepository.existsById(historyEntry.getId()));
    }
}
