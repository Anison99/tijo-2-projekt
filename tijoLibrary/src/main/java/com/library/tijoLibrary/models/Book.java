package com.library.tijoLibrary.models;

import com.library.tijoLibrary.services.CategoryService;
import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private boolean isReserved;
    public Book() {
    }

    public Book(String title, String author) {
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


    public int getAverageRating() {
        return 0;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public void setCategoryById(Long categoryId, CategoryService categoryService) {

        if (categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            if (category != null) {
                this.category = category;
            } else {
                throw new IllegalArgumentException("Category with ID " + categoryId + " not found");
            }
        } else {
            throw new IllegalArgumentException("categoryId cannot be null");
        }
    }

}