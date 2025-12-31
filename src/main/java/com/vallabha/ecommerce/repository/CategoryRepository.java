package com.vallabha.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vallabha.ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String name);
	
}
