package com.vallabha.ecommerce.repository;

import com.vallabha.ecommerce.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Custom method ki abhi zaroorat nahi hai
}