package com.vallabha.ecommerce.controller;

import com.vallabha.ecommerce.model.Address;
import com.vallabha.ecommerce.model.Order;
import com.vallabha.ecommerce.model.User;
import com.vallabha.ecommerce.repository.AddressRepository;
import com.vallabha.ecommerce.repository.UserRepository;
import com.vallabha.ecommerce.service.OrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrderDirectly(@RequestBody OrderFormRequest request, Principal principal) {
        String username = principal.getName();
        try {
            // 1. User find logic
            User user = userRepository.findByUsername(username)
                    .orElseGet(() -> userRepository.findByEmail(username).orElse(null));

            if(user == null) {
                return new ResponseEntity<>("User not found: " + username, HttpStatus.BAD_REQUEST);
            }

            // 2. Address Map & Save
            Address address = new Address();
            String fullName = request.getFullName();
            if (fullName != null && !fullName.isEmpty()) {
                String[] nameParts = fullName.trim().split(" ", 2);
                address.setFirstName(nameParts[0]);
                address.setLastName(nameParts.length > 1 ? nameParts[1] : "");
            }
            address.setStreetAddress(request.getStreet()); 
            address.setCity(request.getCity());
            address.setState(request.getState());
            address.setZipCode(request.getPincode());
            address.setCountry("India");
            address.setUser(user);
            
            Address savedAddress = addressRepository.save(address);

            // 3. Order Placement
            Order order = orderService.placeOrder(username, savedAddress.getAddressId(), request.getPaymentMethod(), null);

            // 4. Detailed Response for Razorpay
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("orderId", order.getOrderId());
            response.put("totalAmount", order.getTotalAmount());
            
            // Note: If this method gives error, add 'razorpayOrderId' field in your Order Model
            try {
                response.put("razorpayOrderId", order.getRazorpayOrderId()); 
            } catch (Exception e) {
                response.put("razorpayOrderId", null);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Order Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @Data
    public static class OrderFormRequest {
        private String fullName;
        private String email;
        private String street; 
        private String city;
        private String state;
        private String pincode; 
        private String paymentMethod;
    }
    
 // OrderController.java में इसे जोड़ें
    @GetMapping("/user")
    public ResponseEntity<?> getMyOrders(Principal principal) {
        try {
            String username = principal.getName();
            List<Order> orders = orderService.getMyOrders(username);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching orders: " + e.getMessage(), 
                                         HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}