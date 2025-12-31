package com.vallabha.ecommerce.repository;

import com.vallabha.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Spring Data JPA automatically query banayega based on Category ID
    List<Product> findByCategory_Id(Long categoryId);
}