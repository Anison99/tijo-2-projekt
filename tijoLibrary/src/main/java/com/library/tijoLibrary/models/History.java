package com.library.tijoLibrary.models;


import javax.persistence.*;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
<<<<<<< HEAD
    @Column(name = "history_id", nullable = false)
    private Long history_id;

    @Column (name = "user_id", nullable = false)
    private Long user_id;

    @Column (name = "book_id", nullable = false)
    private Long book_id;

    public Long getHistory_id() {
        return history_id;
    }

    public void setHistory_id(Long history_id) {
        this.history_id = history_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
=======
    @Column(name = "id", nullable = false)
    private Long id;

    @Column (name = "userId", nullable = false)
    private Long userId;

    @Column (name = "bookId", nullable = false)
    private Long bookId;



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
>>>>>>> origin/develop
    }
}
