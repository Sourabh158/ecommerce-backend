package com.vallabha.ecommerce.controller;

import com.vallabha.ecommerce.model.Category;
import com.vallabha.ecommerce.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//CategoryController.java

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api") // Base path को सिर्फ /api रखें
public class CategoryController {

 private final CategoryService categoryService;

 public CategoryController(CategoryService categoryService) {
     this.categoryService = categoryService;
 }

 // 1. Get All Categories (PUBLIC - कोई भी देख सकता है)
 @GetMapping("/public/categories") 
 public ResponseEntity<List<Category>> getAllCategories() {
     List<Category> categories = categoryService.getAllCategories();
     return new ResponseEntity<>(categories, HttpStatus.OK);
 }

 // 2. Create Category (ADMIN ONLY)
 @PostMapping("/admin/categories") 
 public ResponseEntity<String> createCategory(@RequestBody Category category) {
     categoryService.createCategory(category);
     return new ResponseEntity<>("Category added successfully!", HttpStatus.CREATED);
 }

 // 3. Update Category (ADMIN ONLY)
 @PutMapping("/admin/categories/{categoryId}") 
 public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId, 
                                                @RequestBody Category category) {
     Category updatedCategory = categoryService.updateCategory(categoryId, category);
     return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
 }

 // 4. Delete Category (ADMIN ONLY)
 @DeleteMapping("/admin/categories/{categoryId}") 
 public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
     String status = categoryService.deleteCategory(categoryId);
     return new ResponseEntity<>(status, HttpStatus.OK);
 }
}