package com.library.tijoLibrary;

import com.library.tijoLibrary.models.Book;
import com.library.tijoLibrary.models.Category;
import com.library.tijoLibrary.models.User;
import com.library.tijoLibrary.repositories.BookRepository;
import com.library.tijoLibrary.repositories.UserRepository;
import com.library.tijoLibrary.services.BookService;
import com.library.tijoLibrary.services.UserService;
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
    void whenDeleteBook_thenBookShouldBeRemoved() {
        Book book = new Book("DeleteAuthor", "DeleteTitle");

        book = bookRepository.save(book);
        bookService.deleteBook(book.getId());

        assertFalse(bookRepository.existsById(book.getId()));
    }
}
