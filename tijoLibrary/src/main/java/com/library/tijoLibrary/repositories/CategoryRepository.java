package com.library.tijoLibrary.repositories;


import com.library.tijoLibrary.models.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository {
    Optional<Category> findById(Long categoryId);
}
