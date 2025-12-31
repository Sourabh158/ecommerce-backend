package com.vallabha.ecommerce.repository;

import com.vallabha.ecommerce.model.OrderItem;
import com.vallabha.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByProduct(Product product); // प्रोडक्ट से ऑर्डर आइटम ढूंढने के लिए
}