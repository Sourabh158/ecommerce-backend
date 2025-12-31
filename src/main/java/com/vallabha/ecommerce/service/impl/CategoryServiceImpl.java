package com.vallabha.ecommerce.service.impl;

import com.vallabha.ecommerce.model.Category;
import com.vallabha.ecommerce.repository.CategoryRepository;
import com.vallabha.ecommerce.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    // ✅ Constructor Injection (Best Practice)
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional // Data save karte waqt safety ke liye
    public Category createCategory(Category category) {
        // Validation: Check agar naam pehle se exist karta hai
        Category existing = categoryRepository.findByName(category.getName());
        if (existing != null) {
            throw new RuntimeException("Category already exists with name: " + category.getName());
        }
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));
        
        categoryRepository.delete(category);
        return "Category with ID " + categoryId + " deleted successfully";
    }

    @Override
    @Transactional
    public Category updateCategory(Long categoryId, Category category) {
        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));

        // Sirf naya data set kar rahe hain
        savedCategory.setName(category.getName());
        savedCategory.setDescription(category.getDescription());
        
        return categoryRepository.save(savedCategory);
    }
}