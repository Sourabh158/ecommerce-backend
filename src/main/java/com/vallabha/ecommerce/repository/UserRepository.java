package com.vallabha.ecommerce.repository;

import com.vallabha.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // ✅ Ye method zaroori hai login aur cart ke liye
    Optional<User> findByUsername(String username);

    // ✅ Ye method bhi zaroori hai signup ke liye
    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}