package com.vallabha.ecommerce.service.impl;

import com.vallabha.ecommerce.model.Address;
import com.vallabha.ecommerce.model.User;
import com.vallabha.ecommerce.repository.AddressRepository;
import com.vallabha.ecommerce.repository.UserRepository;
import com.vallabha.ecommerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Address createAddress(Address address, String username) {
        // 1. User dhundo
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Address ko User ke sath link karo
        address.setUser(user);

        // 3. Save karo
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAddressesByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return addressRepository.findByUser(user);
    }

    @Override
    public Address getAddressById(Long addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found with ID: " + addressId));
    }

    @Override
    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}