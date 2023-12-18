package com.library.tijoLibrary.services;

import com.library.tijoLibrary.models.Book;
import com.library.tijoLibrary.models.User;
import com.library.tijoLibrary.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // Dodawanie nowej książki
    public Book addBook(String author, String title) {
        if(author != null
                && author != ""
                && title != null
                && title != ""
        )
        {
            Book newBook = new Book();
            newBook.setAuthor(author);
            newBook.setTitle(title);
            newBook.setReserved(false);
            return bookRepository.save(newBook);
        }
        return null;

    }

    public void deleteBook(Long bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
        } else {
            throw new EntityNotFoundException("Book with ID " + bookId + " not found");
        }
    }

    // Sprawdzenie czy książka istnieje
    public boolean checkBookExists(Long bookId) {
        return bookRepository.existsById(bookId);
    }

    // Wyświetlenie wszystkich książek
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Zmiana tytułu książki
    public void updateBookTitle(Long bookId, String newTitle) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookId + " not found"));
        book.setTitle(newTitle);
        bookRepository.save(book);
    }

    // Zmiana autora książki
    public void updateBookAuthor(Long bookId, String newAuthor) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookId + " not found"));
        book.setAuthor(newAuthor);
        bookRepository.save(book);
    }
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookId + " not found"));
    }
    public Book updateBook(Long bookId, Book bookDetails) {
        Book book = getBookById(bookId);
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        return bookRepository.save(book);
    }


}
