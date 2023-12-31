package com.library.tijoLibrary.services;

import com.library.tijoLibrary.models.Book;
import com.library.tijoLibrary.models.Category;
import com.library.tijoLibrary.repositories.BookRepository;
import com.library.tijoLibrary.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public void assignCategoryToBook(Long bookId, Long categoryId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookId + " not found"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category with ID " + categoryId + " not found"));

        book.setCategory(category);
        bookRepository.save(book);
    }

    public List<Book> findBooksByCategory(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }
    public Category getCategoryOfBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookId + " not found"));
        return book.getCategory();
    }

    public Category addCategory(String categoryName) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        return categoryRepository.save(category);
    }
    public Category getCategoryById(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        } else {
            throw new EntityNotFoundException("Category with ID " + categoryId + " not found");
        }
    }
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}