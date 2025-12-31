package com.vallabha.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    
    private String firstName;
    private String lastName;

    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    // Ek user ke paas multiple address ho sakte hain (Home, Office)
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore // Infinite recursion se bachne ke liye
    private User user;
}