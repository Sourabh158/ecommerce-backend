package com.vallabha.ecommerce.repository;

import com.vallabha.ecommerce.model.Address;
import com.vallabha.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    
    // User ke saare saved addresses nikalne ke liye
    List<Address> findByUser(User user);
}