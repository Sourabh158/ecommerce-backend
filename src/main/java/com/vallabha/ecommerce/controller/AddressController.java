package com.vallabha.ecommerce.controller;

import com.vallabha.ecommerce.model.Address;
import com.vallabha.ecommerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // ✅ CREATE Address: POST Request
    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address, Principal principal) {
        String username = principal.getName();
        Address savedAddress = addressService.createAddress(address, username);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

    // ✅ GET All Addresses: GET Request
    @GetMapping
    public ResponseEntity<List<Address>> getUserAddresses(Principal principal) {
        String username = principal.getName();
        List<Address> addresses = addressService.getAddressesByUser(username);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // ✅ DELETE Address
    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>("Address deleted successfully", HttpStatus.OK);
    }
}