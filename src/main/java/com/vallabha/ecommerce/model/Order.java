package com.vallabha.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String email; // User ki email ID store karenge track karne ke liye

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDate orderDate;

    private Double totalAmount;

    private String orderStatus; // Example: "Pending", "Shipped", "Delivered"
    
    // ✅ NEW: Order ko Address se link karo
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
    
    private String razorpayOrderId;
    
}
