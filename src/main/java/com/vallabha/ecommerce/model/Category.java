package com.vallabha.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // Lombok automatically generates Getters, Setters, toString
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;
}