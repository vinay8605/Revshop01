package com.revshop1.controllerTesting;
import com.revshop1.controller.WishlistController;
import com.revshop1.model.Product;
import com.revshop1.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class WishlistControllerTest {

    @Mock
    private WishlistService wishlistService;

    @Mock
    private HttpSession session;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Mock
    private Model model;

    @InjectMocks
    private WishlistController wishlistController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddToWishlistWhenLoggedIn() {
        Integer buyerId = 1;
        int productId = 101;

        when(session.getAttribute("buyerId")).thenReturn(buyerId);

        String viewName = wishlistController.addToWishlist(productId, session, redirectAttributes);

        verify(wishlistService).addToWishlist(buyerId, productId);
        verify(redirectAttributes).addFlashAttribute("message", "Product added to wishlist!");
        assertEquals("redirect:/buyer/dashboard", viewName);
    }

    @Test
    void testAddToWishlistWhenNotLoggedIn() {
        when(session.getAttribute("buyerId")).thenReturn(null);

        String viewName = wishlistController.addToWishlist(101, session, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute("error", "You need to log in to add items to your wishlist.");
        assertEquals("redirect:/login", viewName);
    }

    @Test
    void testViewWishlistWhenNotLoggedIn() {
        when(session.getAttribute("buyerId")).thenReturn(null);

        String viewName = wishlistController.viewWishlist(model, session);

        verify(model).addAttribute("error", "You need to be logged in to view your wishlist.");
        assertEquals("wishlist", viewName);
    }
}
