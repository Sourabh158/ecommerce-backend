package com.vallabha.ecommerce.service.impl;

import com.vallabha.ecommerce.model.Cart;
import com.vallabha.ecommerce.model.CartItem;
import com.vallabha.ecommerce.model.Product;
import com.vallabha.ecommerce.model.User;
import com.vallabha.ecommerce.repository.CartRepository;
import com.vallabha.ecommerce.repository.ProductRepository;
import com.vallabha.ecommerce.repository.UserRepository;
import com.vallabha.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public CartItem addProductToCart(String username, Long productId, Integer quantity) { // Variable name change kiya (email -> username)
    	

     // ✅ Ye sahi hai
        User user = userRepository.findByUsername(username) 
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        
        // 2. Product ko dhundo
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        // 3. User ka Cart check karo
        Cart cart = cartRepository.findByUser(user);
        
        // Agar Cart nahi hai, to naya banao
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }

        // 4. Check karo item pehle se cart me hai ya nahi
        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst();

        CartItem cartItem;

        if (existingItem.isPresent()) {
            cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setProductPrice(product.getPrice()); 
            cartItem.setDiscount(0.0);
            cart.getCartItems().add(cartItem);
        }
        
        // 5. Save karo
        calculateTotalPrice(cart);

        return cartItem;
    }
    
 // ... (addProductToCart method ke baad ye paste karein) ...

    @Override
    public Cart getCart(String username) {
        // 1. User ko dhundo
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        // 2. User ka Cart database se nikalo
        Cart cart = cartRepository.findByUser(user);

        // 3. Agar Cart nahi hai, to exception do ya empty cart return karo
        if (cart == null) {
            throw new RuntimeException("Cart not found for user: " + username);
            // Option: Aap chaho to yahan naya cart create karke return kar sakte ho
        }
        
       
        calculateTotalPrice(cart);
        return cart;
    }
    
    @Override
    public CartItem updateProductQuantityInCart(String username, Long productId, Integer newQuantity) {
        // 1. User aur Cart dhundo
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user);

        // 2. Item dhundo
        CartItem item = cart.getCartItems().stream()
                .filter(i -> i.getProduct().getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        // 3. Quantity update karo
        item.setQuantity(newQuantity);
        
        // 4. Total Price Recalculate karo aur Save karo
        calculateTotalPrice(cart); 
        
        // Note: cartRepository.save(cart) calculateTotalPrice me call ho raha hai
        
        return item;
    }

    @Override
    public void deleteProductFromCart(String username, Long productId) {
        // 1. User aur Cart dhundo
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user);

        // 2. List me se item remove karo (Java Stream ka removeIf use kiya)
        boolean removed = cart.getCartItems().removeIf(item -> item.getProduct().getProductId().equals(productId));

        if (!removed) {
            throw new RuntimeException("Product not found in cart");
        }

        // 3. Total Recalculate aur Save
        calculateTotalPrice(cart);
    }
 // ✅ Helper method to calculate total price
    private void calculateTotalPrice(Cart cart) {
        double total = 0.0;
        for (CartItem item : cart.getCartItems()) {
            total += (item.getProductPrice() * item.getQuantity());
        }
        cart.setTotalPrice(total);
        cartRepository.save(cart); // Database me save kar do
    }
} // End of Class
