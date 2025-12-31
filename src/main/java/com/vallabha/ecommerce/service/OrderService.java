package com.vallabha.ecommerce.service;

import java.util.List;

import com.vallabha.ecommerce.model.Order;

public interface OrderService {
    
    // ✅ NEW: 'razorpayPaymentId' add kiya
    Order placeOrder(String username, Long addressId, String paymentMethod, String razorpayPaymentId);

    List<Order> getMyOrders(String username);
}