package com.revshop1.serviceTesting;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.revshop1.model.Product;
import com.revshop1.model.Wishlist;
import com.revshop1.repository.ProductRepository;
import com.revshop1.repository.WishlistRepository;
import com.revshop1.service.WishlistService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class WishlistServiceTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private WishlistService wishlistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddToWishlist() {
        int buyerId = 1;
        int productId = 123;
        Wishlist wishlist = new Wishlist(buyerId, productId, LocalDateTime.now());

        wishlistService.addToWishlist(buyerId, productId);

        verify(wishlistRepository, times(1)).save(any(Wishlist.class));
    }

}