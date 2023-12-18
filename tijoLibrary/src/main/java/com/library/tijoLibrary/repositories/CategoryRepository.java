package com.library.tijoLibrary.repositories;


import com.library.tijoLibrary.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
        Optional<Category> findById(Long categoryId);
        Optional<Category> findByCategoryName(String categoryName);
        @Query("SELECT c.id FROM Category c WHERE c.categoryName = :categoryName")
        Long findCategoryIdByCategoryName(@Param("categoryName") String categoryName);
        @Query("Select id FROM Category WHERE categoryName = :categoryName")
        Category findByCategoryName2(String categoryName);
}
