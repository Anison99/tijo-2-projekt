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

    public void borrowBook(Book book) {
    }

    public boolean hasReceivedEmailNotification() {
        return false;
    }

    public String getLastEmailNotification() {
        return null;
    }

    public void updateName(String annaKowalska) {
    }

    public boolean hasReceivedMessageNotification() {
        return false;
    }

    public String getLastMessageNotification() {
        return null;
    }

    public boolean hasReceivedNotification() {
        return false;
    }

    public String getLastNotification() {
        return null;
    }

    public void rateBook(Book ratedBook, int i) {
    }

    public void addReview(Book book1, String s, int i) {
    }

    public Book getRecommendedBook() {
        return null;
    }

    public String getActivityHistory() {
        return null;
    }
}