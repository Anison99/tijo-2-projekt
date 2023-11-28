package com.library.tijoLibrary.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    public User(String username) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User orElse(Object o) {
        return null;
    }

    public void setEmail(String mail) {
    }

    public void borrowBook(Book borrowedBook) {
    }

    public boolean isEmailNotificationSent() {
        return false;
    }

    public void reserveBook(Book reservedBook) {
    }

    public boolean isMessageNotificationSent() {
        return false;
    }

    public boolean isNotificationReceived() {
        return false;
    }

    public void rateBook(Book ratedBook, int i) {
    }

    public void addReview(Book reviewedBook, String s, int i) {
    }

    public boolean isBookSelectedBasedOnReview(Book reviewedBook) {
        return false;
    }

    public boolean isBookInActivityHistory(Book borrowedBook) {
        return false;
    }

    public int getNumberOfBooksBorrowed() {
        return 0;
    }

    public boolean isReturnReminderSent() {
        return false;
    }
}