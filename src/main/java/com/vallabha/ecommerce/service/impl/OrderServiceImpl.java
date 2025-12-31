package com.vallabha.ecommerce.service.impl;

import com.vallabha.ecommerce.model.*;
import com.vallabha.ecommerce.repository.*;
import com.vallabha.ecommerce.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository; // ✅ NEW

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private PaymentRepository paymentRepository; 

    @Override
    @Transactional
    // ✅ Parameter updated
    public Order placeOrder(String username, Long addressId, String paymentMethod, String razorpayPaymentId) {
        
        // 1. User, Cart, Address fetching logic (SAME AS BEFORE - No Change)
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user);
        if (cart == null || cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        // 2. Order Create (SAME AS BEFORE)
        Order order = new Order();
        order.setEmail(user.getEmail());
        order.setOrderDate(LocalDate.now());
        order.setTotalAmount(cart.getTotalPrice());
        order.setOrderStatus("Order Accepted !");
        order.setAddress(address);

        // 3. ✅ PAYMENT LOGIC UPDATE
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMethod(paymentMethod);
        payment.setAmount(cart.getTotalPrice());
        
        if (paymentMethod.equalsIgnoreCase("COD")) {
            payment.setPaymentStatus("Pending");
        } else if (paymentMethod.equalsIgnoreCase("RAZORPAY")) {
            payment.setPaymentStatus("Success"); // Paisa mil gaya samjho
            payment.setPgPaymentId(razorpayPaymentId); // ✅ Razorpay ki ID save ki
        }
        
        order.setPayment(payment);

        // 4. Order Items & Stock Logic (SAME AS BEFORE)
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrderedProductPrice(cartItem.getProductPrice());
            orderItem.setOrder(order);
            orderItems.add(orderItem);

            // Stock Logic
            Product product = cartItem.getProduct();
            if (product.getQuantity() < cartItem.getQuantity()) {
                throw new RuntimeException("Product " + product.getProductName() + " is OUT OF STOCK!");
            }
            product.setQuantity(product.getQuantity() - cartItem.getQuantity());
            productRepository.save(product);
        }
        order.setOrderItems(orderItems);

        // 5. Save & Clear (SAME AS BEFORE)
        Order savedOrder = orderRepository.save(order);
        paymentRepository.save(payment);

        cart.getCartItems().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);

        return savedOrder;
    }
    @Override
    public List<Order> getMyOrders(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return orderRepository.findAllByEmail(user.getEmail());
    }



}