package com.vallabha.ecommerce.repository;

import com.vallabha.ecommerce.model.CartItem;
import com.vallabha.ecommerce.model.Product; // ✅ यह इम्पोर्ट ज़रूरी है
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List; // ✅ यह इम्पोर्ट भी जोड़ें

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = ?1 AND ci.product.id = ?2")
    CartItem findCartItemByProductIdAndCartId(Long cartId, Long productId);

    // ✅ बस यह नई लाइन जोड़ें, इसी से ProductServiceImpl का लाल एरर जाएगा
    List<CartItem> findByProduct(Product product);
}