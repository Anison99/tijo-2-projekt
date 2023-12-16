package com.library.tijoLibrary.services;


import com.library.tijoLibrary.models.Book;
import com.library.tijoLibrary.models.BookStatuses;
import com.library.tijoLibrary.repositories.BookRepository;
import com.library.tijoLibrary.repositories.BookStatusesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class BookStatusesService {

    @Autowired
    private BookStatusesRepository bookStatusesRepository;
    @Autowired
    private BookRepository bookRepository;

    // Zmiana statusu książki na zarezerwowany
    public void markBookAsReserved(Long bookId) {
        BookStatuses bookStatuses = getBookStatuses(bookId);
        bookStatuses.setReserved(true);
        bookStatuses.setAvailable(false);
        bookStatusesRepository.save(bookStatuses);
    }

    // Zmiana statusu książki na niedostępny
    public void markBookAsUnavailable(Long bookId) {
        BookStatuses bookStatuses = getBookStatuses(bookId);
        bookStatuses.setAvailable(false);
        bookStatusesRepository.save(bookStatuses);
    }

    // Sprawdzenie czy książka jest zarezerwowana
    public boolean isBookReserved(Long bookId) {
        return getBookStatuses(bookId).isReserved();
    }

    // Sprawdzenie czy książka jest dostępna
    public boolean isBookAvailable(Long bookId) {
        return getBookStatuses(bookId).isAvailable();
    }

    // Przypisanie statusu nowej książce
    public void assignStatusToNewBook(Long bookId, boolean isReserved, boolean isAvailable) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookId + " not found"));
        BookStatuses bookStatuses = new BookStatuses();
        bookStatuses.setBook(book);
        bookStatuses.setReserved(isReserved);
        bookStatuses.setAvailable(isAvailable);
        bookStatusesRepository.save(bookStatuses);
    }

    // Usunięcie osoby, która ma wypożyczoną książkę
    public void removeUserFromBook(Long bookId) {
        BookStatuses bookStatuses = getBookStatuses(bookId);
        bookStatuses.setUser(null);
        bookStatuses.setReserved(false);
        bookStatusesRepository.save(bookStatuses);
    }

    private BookStatuses getBookStatuses(Long bookId) {
        return bookStatusesRepository.findByBook_Id(bookId)
                .orElseThrow(() -> new EntityNotFoundException("BookStatuses for book ID " + bookId + " not found"));
    }
}
