package com.library.tijoLibrary.controllers;

import com.library.tijoLibrary.models.Category;
import com.library.tijoLibrary.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.addCategory(category.getCategoryName());
        if (savedCategory != null) {
            return ResponseEntity.ok(savedCategory);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
