package com.library.tijoLibrary.repositories;


import com.library.tijoLibrary.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
        Optional<Category> findById(Long categoryId);
}
