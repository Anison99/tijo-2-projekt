package com.library.tijoLibrary.services;

import com.library.tijoLibrary.models.Book;
import com.library.tijoLibrary.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookService {
    @Autowired
     private BookRepository bookRepository;

    public BookService() {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book newBook) {
    }

    public Collection<Object> getBooks() {
        return null;
    }

    public void removeBook(Book book) {
    }

    public boolean checkAvailability(Book availableBook) {
        return false;
    }

    // implementacje metod operacji na książkach
}
