package com.vallabha.ecommerce.service;

import com.vallabha.ecommerce.model.Product;
import java.util.List;

public interface ProductService {

    Product addProduct(Long categoryId, Product product);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(Long categoryId);
    
    Product updateProduct(Long productId, Product product);
    
    String deleteProduct(Long productId);
}