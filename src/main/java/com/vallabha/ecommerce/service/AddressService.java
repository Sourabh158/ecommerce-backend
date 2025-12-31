package com.vallabha.ecommerce.service;

import com.vallabha.ecommerce.model.Address;
import java.util.List;

public interface AddressService {
    // Address create karne ke liye
    Address createAddress(Address address, String username);

    // User ke saare address dekhne ke liye
    List<Address> getAddressesByUser(String username);

    // Specific address ID se address nikalne ke liye
    Address getAddressById(Long addressId);
    
    // Address delete karne ke liye
    void deleteAddress(Long addressId);
}