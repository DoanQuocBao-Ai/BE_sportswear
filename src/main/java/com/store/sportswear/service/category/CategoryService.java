package com.store.sportswear.service.category;

import com.store.sportswear.entity.Category;

import java.util.List;

public interface CategoryService {
    Category add(Category category);
    List<Category> getAll();
    List<Category> getByCategoryName(String categoryName);
}
