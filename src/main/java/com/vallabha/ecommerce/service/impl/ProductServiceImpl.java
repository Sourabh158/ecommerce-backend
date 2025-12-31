package com.vallabha.ecommerce.service.impl;

import com.vallabha.ecommerce.model.Category;
import com.vallabha.ecommerce.model.OrderItem;
import com.vallabha.ecommerce.model.Product;
import com.vallabha.ecommerce.model.CartItem; // ✅ Import add karein
import com.vallabha.ecommerce.repository.CategoryRepository;
import com.vallabha.ecommerce.repository.OrderItemRepository;
import com.vallabha.ecommerce.repository.ProductRepository;
import com.vallabha.ecommerce.repository.CartItemRepository; // ✅ Import add karein
import com.vallabha.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired; // ✅ Required for Injection
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // ✅ Safe delete ke liye zaroori

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired // ✅ Isse inject karein taaki cart references check ho sakein
    private CartItemRepository cartItemRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product addProduct(Long categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));

        product.setCategory(category);

        // ✅ Image path ko clean rakhein
        if (product.getImage() == null || product.getImage().isEmpty()) {
            product.setImage("default.png");
        } else if (!product.getImage().startsWith("images/")) {
            // Agar user sirf filename dale, to 'images/' prefix khud jod dein (Optional logic)
            // product.setImage("images/" + product.getImage()); 
        }

        double price = product.getPrice();
        double discount = product.getDiscount();
        double specialPrice = price - ((discount * 0.01) * price);
        product.setSpecialPrice(specialPrice);

        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));
        
        return productRepository.findByCategory_Id(categoryId);
    }
    
    @Override
    public Product updateProduct(Long productId, Product product) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
        
        existingProduct.setProductName(product.getProductName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDiscount(product.getDiscount());
        existingProduct.setQuantity(product.getQuantity());
        
        double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01) * product.getPrice());
        existingProduct.setSpecialPrice(specialPrice);
        
        if(product.getImage() != null) {
            existingProduct.setImage(product.getImage());
        }

        return productRepository.save(existingProduct);
    }

    @Override
    @Transactional
    public String deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
        
        // 1. Cart Items से डिलीट करें
        List<CartItem> cartItems = cartItemRepository.findByProduct(product);
        if (!cartItems.isEmpty()) {
            cartItemRepository.deleteAll(cartItems);
        }

        // 2. Order Items से डिलीट करें (यही एरर दे रहा था)
        List<OrderItem> orderItems = orderItemRepository.findByProduct(product);
        if (!orderItems.isEmpty()) {
            orderItemRepository.deleteAll(orderItems);
        }
        
        // 3. अब प्रोडक्ट डिलीट करें
        productRepository.delete(product);
        
        return "Product Deleted Successfully";
    }
}