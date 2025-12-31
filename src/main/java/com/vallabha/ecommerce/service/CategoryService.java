package com.vallabha.ecommerce.service;

import com.vallabha.ecommerce.model.Category;
import java.util.List;

public interface CategoryService {

    // Saari categories ki list lane ke liye
    List<Category> getAllCategories();

    // Nayi category create karne ke liye
    Category createCategory(Category category);

    // Existing category delete karne ke liye
    String deleteCategory(Long categoryId);

    // Existing category update karne ke liye
    Category updateCategory(Long categoryId, Category category);
}