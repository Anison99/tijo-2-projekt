package com.library.tijoLibrary;

import com.library.tijoLibrary.models.*;
import com.library.tijoLibrary.repositories.*;
import com.library.tijoLibrary.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.mockito.Mockito.when;

public class LibraryUnitTests {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookStatusesRepository bookStatusesRepository;

    @InjectMocks
    private BookStatusesService bookStatusesService;
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;
    @Mock
    private HistoryRepository historyRepository;

    @InjectMocks
    private HistoryService historyService;
    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingService ratingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //########################################################################################################################
    //########################################################################################################################
    //###########################################//test użytkownika ##########################################################
    //########################################################################################################################
    //########################################################################################################################
    //########################################################################################################################
    @Test
    void testRegisterUser() {
        String username = "newUser1234";
        String password = "password1123456";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User result = userService.registerUser(username, password);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertEquals(password, result.getPassword());
    }
    @Test
    void testRegisterUserWithEmptyUsername() {
        String username = "";
        String password = "password12345";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User result = userService.registerUser(username, password);

        assertNull(result);
    }
    @Test
    void testRegisterUserWithNullUsername() {
        String username = null;
        String password = "password12345";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User result = userService.registerUser(username, password);

        assertNull(result);
    }
    @Test
    void testRegisterUserWithShortUsername() {
        String username = "1234567";
        String password = "password12345";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User result = userService.registerUser(username, password);

        assertNull(result);
    }
    @Test
    void testRegisterUserWithNullPassword() {
        String username = "newUser12345678";
        String password = null;

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User result = userService.registerUser(username, password);

        assertNull(result);
    }
    @Test
    void testRegisterUserWithEmptyPassword() {
        String username = "newUser12345678";
        String password = "";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User result = userService.registerUser(username, password);

        assertNull(result);
    }
    @Test
    void testRegisterUserWithShortPassword() {
        String username = "newUser12345678";
        String password = "pswd1";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User result = userService.registerUser(username, password);

        assertNull(result);
    }
    @Test
    void testRegisterUserWithPasswordThatDontIncludeNumber() {
        String username = "newUser12345678";
        String password = "password";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User result = userService.registerUser(username, password);

        assertNull(result);
    }
    @Test
    void testRegisterUserWithPasswordThatDontIncludeCharacter() {
        String username = "newUser12345678";
        String password = "12345678";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User result = userService.registerUser(username, password);

        assertNull(result);
    }
    @Test
    void testGetUserByLoginDetails() {
        String username = "existingUser";
        String password = "password123";

        User existingUser = new User();
        existingUser.setUsername(username);
        existingUser.setPassword(password);

        when(userRepository.findByUsernameAndPassword(username, password)).thenReturn(Optional.of(existingUser));

        Optional<User> result = userService.getUserByLoginDetails(username, password);

        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
        assertEquals(password, result.get().getPassword());
    }
    @Test
    void testUserLoginWrongDetails() {
        String username = "newUser12345678";
        String password = "testPassword1";
        String wrongPassword = "testwrongPassword1";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        when(userRepository.findByUsernameAndPassword(username, wrongPassword)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByLoginDetails(username, password);

        assertFalse(result.isPresent());
    }

    @Test
    void testChangingUsername() {
        // Ustawienie danych testowych
        String originalUsername = "newUser12345678";
        String newPassword = "testPassword1";
        String newUsername = "changedUsername";

        User user = new User();
        user.setUsername(originalUsername);
        user.setPassword(newPassword);

        // Konfiguracja Mockito
        when(userRepository.findByUsername(originalUsername)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        User updatedUser = userService.updateUserName(originalUsername, newUsername);

        assertNotNull(updatedUser);
        assertEquals(newUsername, updatedUser.getUsername());
    }
    @Test
    void testChangingPassword() {
        // Ustawienie danych testowych
        String originalUsername = "newUser12345678";
        String oldPassword = "testPassword1";
        String newPassword = "password";

        User user = new User();
        user.setUsername(originalUsername);
        user.setPassword(newPassword);

        // Konfiguracja Mockito
        when(userRepository.findByUsername(originalUsername)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        User updatedUser = userService.updateUserName(originalUsername, newPassword);

        assertNotNull(updatedUser);
        assertEquals(newPassword, updatedUser.getPassword());
    }

    @Test
    void testAddingNewEmail(){
        String username = "newUser12345678";
        String newPassword = "testPassword1";
        String email = "newemail@email.com";

        User user = new User();
        user.setUsername(username);
        user.setPassword(newPassword);

        // Konfiguracja Mockito
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        User updatedUser = userService.addEmail(username, email);

        assertNotNull(updatedUser);
        assertEquals(email, updatedUser.getEmail());
    }
    @Test
    void testChangingEmail() {
        // Ustawienie danych testowych
        String originalUsername = "newUser12345678";
        String newPassword = "testPassword1";
        String oldemail = "test@test.pl";
        String newemail = "test2@test2.pl";

        User user = new User();
        user.setUsername(originalUsername);
        user.setPassword(newPassword);

        // Konfiguracja Mockito
        when(userRepository.findByUsername(originalUsername)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        User UserwithAddedemail = userService.addEmail(originalUsername, oldemail);
        User updatedUser = userService.updateUserEmail(originalUsername,newemail);
        assertNotNull(updatedUser);
        assertEquals(newemail, updatedUser.getEmail());
    }
    //########################################################################################################################
    //########################################################################################################################
    //####################################################test book ##########################################################
    //########################################################################################################################
    //########################################################################################################################
    //########################################################################################################################

    @Test
    void testAddBookWithCorrectData() {
        String title = "newtestedBook";
        String author = "newtestedAuthor";

        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setReserved(false);

        when(bookRepository.save(any(Book.class))).thenReturn(newBook);

        Book savedBook = bookService.addBook(author, title);

        assertEquals(title, savedBook.getTitle());
        assertEquals(author, savedBook.getAuthor());
        assertFalse(savedBook.isReserved());
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    void testAddBookwithEmptyOrNullAuthor() {
        String title = "newtestedBook";
        String author = "";
        String author2 = null;

        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setReserved(false);

        when(bookRepository.save(any(Book.class))).thenReturn(newBook);

        Book savedBook = bookService.addBook(author, title);
        Book savedBook2 = bookService.addBook(author2, title);

        assertNull(savedBook);
        assertNull(savedBook2);

    }

    @Test
    void testAddBookWithEmptyOrNullTitle() {
        String title = "";
        String title2 = null;
        String author = "newtestedAuthor";

        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setReserved(false);

        when(bookRepository.save(any(Book.class))).thenReturn(newBook);

        Book savedBook = bookService.addBook(author, title);
        Book savedBook2 = bookService.addBook(author, title2);

        assertNull(savedBook);
        assertNull(savedBook2);
    }

    @Test
    public void testDeleteBook() {
        Long bookId = 1L;
        when(bookRepository.existsById(bookId)).thenReturn(true);

        bookService.deleteBook(bookId);

        verify(bookRepository).deleteById(bookId);
    }

    @Test
    public void testCheckBookExists() {
        Long bookId = 1L;
        when(bookRepository.existsById(bookId)).thenReturn(true);

        boolean exists = bookService.checkBookExists(bookId);

        assertTrue(exists);
    }

    @Test
    public void testGetAllBooks() {
        Book book1 = new Book("Title1", "Author1");
        Book book2 = new Book("Title2", "Author2");
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.getAllBooks();

        assertNotNull(books);
        assertEquals(2, books.size());
    }

    @Test
    public void testUpdateBookTitle() {
        Long bookId = 1L;
        String newTitle = "New Title";
        Book book = new Book("Old Title", "Author");
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenAnswer(i -> i.getArguments()[0]);

        bookService.updateBookTitle(bookId, newTitle);

        assertEquals(newTitle, book.getTitle());
    }
    //########################################################################################################################
    //########################################################################################################################
    //################################################test book Statuses #####################################################
    //########################################################################################################################
    //########################################################################################################################
    //########################################################################################################################

    @Test
    public void testMarkBookAsReserved() {
        Long bookId = 1L;
        BookStatuses bookStatuses = new BookStatuses();
        when(bookStatusesRepository.findByBook_Id(bookId)).thenReturn(Optional.of(bookStatuses));

        bookStatusesService.markBookAsReserved(bookId);

        assertTrue(bookStatuses.isReserved());
        assertFalse(bookStatuses.isAvailable());
        verify(bookStatusesRepository).save(bookStatuses);
    }

    @Test
    public void testMarkBookAsUnavailable() {
        Long bookId = 1L;
        BookStatuses bookStatuses = new BookStatuses();
        when(bookStatusesRepository.findByBook_Id(bookId)).thenReturn(Optional.of(bookStatuses));

        bookStatusesService.markBookAsUnavailable(bookId);

        assertFalse(bookStatuses.isAvailable());
        verify(bookStatusesRepository).save(bookStatuses);
    }

    @Test
    public void testIsBookReserved() {
        Long bookId = 1L;
        BookStatuses bookStatuses = new BookStatuses();
        bookStatuses.setReserved(true);
        when(bookStatusesRepository.findByBook_Id(bookId)).thenReturn(Optional.of(bookStatuses));

        boolean isReserved = bookStatusesService.isBookReserved(bookId);

        assertTrue(isReserved);
    }

    @Test
    public void testIsBookAvailable() {
        Long bookId = 1L;
        BookStatuses bookStatuses = new BookStatuses();
        bookStatuses.setAvailable(true);
        when(bookStatusesRepository.findByBook_Id(bookId)).thenReturn(Optional.of(bookStatuses));

        boolean isAvailable = bookStatusesService.isBookAvailable(bookId);

        assertTrue(isAvailable);
    }

    //##################################################################################################
    //##################################################################################################
    //##################################################################################################
    //##########################################CATEGORY################################################
    //##################################################################################################
    //##################################################################################################
    //##################################################################################################
    @Test
    public void assignCategoryToBook_Success() {
        Long bookId = 1L;
        Long categoryId = 1L;
        Book book = new Book();
        Category category = new Category();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        assertDoesNotThrow(() -> categoryService.assignCategoryToBook(bookId, categoryId));
        verify(bookRepository).save(book);
    }

    @Test
    public void getUserLoanHistory_Success() {
        Long userId = 1L;
        History history1 = new History();
        history1.setUserId(userId);
        History history2 = new History();
        history2.setUserId(userId);
        List<History> histories = Arrays.asList(history1, history2);

        when(historyRepository.findByUserId(userId)).thenReturn(histories);

        List<History> result = historyService.getUserLoanHistory(userId);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(historyRepository).findByUserId(userId);
    }

    @Test
    public void deleteHistoryEntry_Success() {
        Long historyId = 1L;
        doNothing().when(historyRepository).deleteById(historyId);

        assertDoesNotThrow(() -> historyService.deleteHistoryEntry(historyId));
        verify(historyRepository).deleteById(historyId);
    }

    @Test
    public void markBookAsReserved_Success() {
        Long bookId = 1L;
        BookStatuses bookStatuses = new BookStatuses();
        when(bookStatusesRepository.findByBook_Id(bookId)).thenReturn(Optional.of(bookStatuses));

        bookStatusesService.markBookAsReserved(bookId);

        verify(bookStatusesRepository).save(bookStatuses);
        assertTrue(bookStatuses.isReserved());
        assertFalse(bookStatuses.isAvailable());
    }

    @Test
    public void markBookAsUnavailable_Success() {
        Long bookId = 1L;
        BookStatuses bookStatuses = new BookStatuses();
        when(bookStatusesRepository.findByBook_Id(bookId)).thenReturn(Optional.of(bookStatuses));

        bookStatusesService.markBookAsUnavailable(bookId);

        verify(bookStatusesRepository).save(bookStatuses);
        assertFalse(bookStatuses.isAvailable());
    }

    @Test
    public void isBookReserved_Success() {
        Long bookId = 1L;
        BookStatuses bookStatuses = new BookStatuses();
        bookStatuses.setReserved(true);
        when(bookStatusesRepository.findByBook_Id(bookId)).thenReturn(Optional.of(bookStatuses));

        boolean result = bookStatusesService.isBookReserved(bookId);

        assertTrue(result);
    }

    @Test
    public void isBookAvailable_Success() {
        Long bookId = 1L;
        BookStatuses bookStatuses = new BookStatuses();
        bookStatuses.setAvailable(true);
        when(bookStatusesRepository.findByBook_Id(bookId)).thenReturn(Optional.of(bookStatuses));

        boolean result = bookStatusesService.isBookAvailable(bookId);

        assertTrue(result);
    }

    @Test
    public void assignStatusToNewBook_Success() {
        Long bookId = 1L;
        Book book = new Book();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        bookStatusesService.assignStatusToNewBook(bookId, true, false);

        verify(bookStatusesRepository).save(any(BookStatuses.class));
    }

    @Test
    public void removeUserFromBook_Success() {
        Long bookId = 1L;
        BookStatuses bookStatuses = new BookStatuses();
        when(bookStatusesRepository.findByBook_Id(bookId)).thenReturn(Optional.of(bookStatuses));

        bookStatusesService.removeUserFromBook(bookId);

        verify(bookStatusesRepository).save(bookStatuses);
        assertNull(bookStatuses.getUser());
        assertFalse(bookStatuses.isReserved());
        assertTrue(bookStatuses.isAvailable());
    }
    @Test
    public void addRating_Success() {
        Long userId = 1L;
        Long bookId = 1L;
        User user = new User();
        Book book = new Book();
        Rating rating = new Rating();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);

        Rating result = ratingService.addRating(userId, bookId, 5);

        assertNotNull(result);
        verify(ratingRepository).save(any(Rating.class));
    }

    @Test
    public void deleteRating_Success() {
        Long ratingId = 1L;
        doNothing().when(ratingRepository).deleteById(ratingId);

        assertDoesNotThrow(() -> ratingService.deleteRating(ratingId));
        verify(ratingRepository).deleteById(ratingId);
    }

    @Test
    public void updateRating_Success() {
        Long ratingId = 1L;
        Rating rating = new Rating();
        when(ratingRepository.findById(ratingId)).thenReturn(Optional.of(rating));

        assertDoesNotThrow(() -> ratingService.updateRating(ratingId, 5));
        verify(ratingRepository).save(rating);
    }

    @Test
    public void getRatingsForBook_Success() {
        Long bookId = 1L;
        when(ratingRepository.findByBookId(bookId)).thenReturn(Arrays.asList(new Rating(), new Rating()));

        List<Rating> result = ratingService.getRatingsForBook(bookId);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ratingRepository).findByBookId(bookId);
    }
    @Test
    public void addReview_Success() {
        Long userId = 1L;
        Long bookId = 1L;
        String review = "Excellent book!";
        User user = new User();
        Book book = new Book();
        Rating rating = new Rating();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);

        Rating result = ratingService.addReview(userId, bookId, review);

        assertNotNull(result);
        verify(ratingRepository).save(any(Rating.class));
    }

    @Test
    public void updateReview_Success() {
        Long ratingId = 1L;
        String newReview = "Updated review";
        Rating rating = new Rating();
        when(ratingRepository.findById(ratingId)).thenReturn(Optional.of(rating));

        assertDoesNotThrow(() -> ratingService.updateReview(ratingId, newReview));
        verify(ratingRepository).save(rating);
    }

    @Test
    public void deleteReview_Success() {
        Long ratingId = 1L;
        Rating rating = new Rating();
        rating.setReview("Initial review");
        when(ratingRepository.findById(ratingId)).thenReturn(Optional.of(rating));

        assertDoesNotThrow(() -> ratingService.deleteReview(ratingId));
        verify(ratingRepository).save(rating);
    }

    @Test
    public void getReviewsForBook_Success() {
        Long bookId = 1L;
        when(ratingRepository.findByBookId(bookId)).thenReturn(Arrays.asList(new Rating(), new Rating()));

        List<Rating> result = ratingService.getReviewsForBook(bookId);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ratingRepository).findByBookId(bookId);
    }

    @Test
    public void getReviewsMadeByUser_Success() {
        Long userId = 1L;
        when(ratingRepository.findByUserId(userId)).thenReturn(Arrays.asList(new Rating(), new Rating()));

        List<Rating> result = ratingService.getReviewsMadeByUser(userId);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ratingRepository).findByUserId(userId);
    }
    
}
