package com.store.sportswear.repository;

import com.store.sportswear.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> getByCategoryName(String categoryName);

    @Query("from Category where categoryName=:categoryName")
    String findByCategoryName(String categoryName);
}
