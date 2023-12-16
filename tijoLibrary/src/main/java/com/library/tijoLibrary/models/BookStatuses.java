package com.library.tijoLibrary.models;

import javax.persistence.*;

@Entity
public class BookStatuses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Column(name = "isReserved", nullable = false)
    private boolean isReserved;

    @Column(name = "isAvailable",nullable = false)
    private boolean isAvailable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
=======
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "isReserved", nullable = false)
    private boolean isReserved;

    @Column(name = "isAvailable", nullable = false)
    private boolean isAvailable;

    @Column(name = "status", nullable = false)
    private Boolean status;
>>>>>>> origin/develop

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

<<<<<<< HEAD
=======
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

>>>>>>> origin/develop
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
<<<<<<< HEAD
=======

    public Long getBookId() {
        return book != null ? book.getId() : null;
    }

    public Long getUserId() {
        return user != null ? user.getId() : null;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    // ... pozostałe metody
>>>>>>> origin/develop
}
