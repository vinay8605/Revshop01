package com.revshop1.service;

import com.revshop1.model.Product;
import com.revshop1.model.Wishlist;
import com.revshop1.repository.ProductRepository;
import com.revshop1.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addToWishlist(int buyerId, int productId) {
        Wishlist wishlist = new Wishlist(buyerId, productId, LocalDateTime.now());
        wishlistRepository.save(wishlist);
    }

    public List<Product> getWishlistByUserId(int buyerId) {
        List<Wishlist> wishlistEntries = wishlistRepository.findByBuyerId(buyerId);
        List<Integer> productIds = new ArrayList<>();
        
        for (Wishlist entry : wishlistEntries) {
            productIds.add(entry.getProductId());
        }

        return productRepository.findAllById(productIds);
    }
}
