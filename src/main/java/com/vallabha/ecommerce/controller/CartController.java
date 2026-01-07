package com.vallabha.ecommerce.controller;

import com.vallabha.ecommerce.model.Cart;
import com.vallabha.ecommerce.model.CartItem;
import com.vallabha.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // POST Request (Jo pehle banaya tha)
    @PostMapping("/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartItem> addProductToCart(
            @PathVariable Long productId,
            @PathVariable Integer quantity,
            Principal principal) {
        
        String username = principal.getName();
        CartItem item = cartService.addProductToCart(username, productId, quantity);
        return new ResponseEntity<CartItem>(item, HttpStatus.CREATED);
    }

    // ✅ NEW: GET Request (Cart dekhne ke liye)
    @GetMapping
    public ResponseEntity<Cart> getCart(Principal principal) {
        
        // 1. Username nikalo
        String username = principal.getName();
        
        // 2. Service se Cart mango
        Cart cart = cartService.getCart(username);
        
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }
    
 // ✅ UPDATE Quantity: PUT Request
    @PutMapping("/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartItem> updateCartProduct(
            @PathVariable Long productId,
            @PathVariable Integer quantity,
            Principal principal) {
        
        CartItem item = cartService.updateProductQuantityInCart(principal.getName(), productId, quantity);
        return new ResponseEntity<CartItem>(item, HttpStatus.OK);
    }

    // ✅ DELETE Item: DELETE Request
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteCartProduct(
            @PathVariable Long productId,
            Principal principal) {
        
        cartService.deleteProductFromCart(principal.getName(), productId);
        return new ResponseEntity<String>("Product removed from cart", HttpStatus.OK);
    }
}