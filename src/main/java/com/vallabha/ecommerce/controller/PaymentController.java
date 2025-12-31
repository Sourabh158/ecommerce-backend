package com.vallabha.ecommerce.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private RazorpayClient razorpayClient;

    @GetMapping("/create-transaction/{amount}")
    public ResponseEntity<String> createTransaction(@PathVariable Double amount) {
        
        try {
            JSONObject options = new JSONObject();
            // Razorpay paise me deal karta hai (1 Rupee = 100 Paise)
            options.put("amount", (amount * 100)); 
            options.put("currency", "INR");
            options.put("receipt", "txn_123456");

            // Razorpay server ko call lagaya
            Order razorpayOrder = razorpayClient.orders.create(options);
            
            // Wahan se jo Order ID mili (e.g., "order_EKwx...") wo return kar di
            return new ResponseEntity<>(razorpayOrder.toString(), HttpStatus.OK);

        } catch (RazorpayException e) {
            return new ResponseEntity<>("Error creating transaction", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}