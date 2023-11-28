package com.library.tijoLibrary.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private boolean isReserved;

    public Book(String title, String author, String isbn) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public String getStatus() {
        return null;
    }

    public User getReservedBy() {
        return null;
    }

    public void addToWaitingList(User user1) {
    }

    public boolean isInWaitingList(User user1) {
        return false;
    }

    public void returnBook() {
    }

    public int getAverageRating() {
        return 0;
    }
}