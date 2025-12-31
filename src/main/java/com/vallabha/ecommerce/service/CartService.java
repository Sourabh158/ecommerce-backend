package com.vallabha.ecommerce.service;

import com.vallabha.ecommerce.model.Cart;
import com.vallabha.ecommerce.model.CartItem;

public interface CartService {
    CartItem addProductToCart(String username, Long productId, Integer quantity);
    Cart getCart(String username);

    // ✅ NEW: Quantity update karne ke liye
    CartItem updateProductQuantityInCart(String username, Long productId, Integer newQuantity);

    // ✅ NEW: Item delete karne ke liye
    void deleteProductFromCart(String username, Long productId);
}