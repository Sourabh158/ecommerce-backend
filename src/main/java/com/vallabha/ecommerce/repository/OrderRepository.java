package com.vallabha.ecommerce.repository;

import com.vallabha.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    // User ke saare orders dekhne ke liye (Email se dhundenge)
    List<Order> findAllByEmail(String emailId);
}