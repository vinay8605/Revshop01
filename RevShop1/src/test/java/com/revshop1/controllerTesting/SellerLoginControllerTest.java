package com.revshop1.controllerTesting;

import com.revshop1.controller.SellerLoginController;
import com.revshop1.model.Seller;
import com.revshop1.service.SellerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;



import jakarta.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SellerLoginControllerTest {

    @InjectMocks
    private SellerLoginController sellerLoginController;

    @Mock
    private SellerService sellerService;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testLoginSellerSuccess() {
        Seller seller = new Seller();
        seller.setSellerId(1); // Set seller ID for testing
        seller.setEmail("test@example.com");
        seller.setPassword("password123");

        // Mock the service to return a seller when valid credentials are provided
        when(sellerService.authenticateSeller(seller.getEmail(), seller.getPassword())).thenReturn(seller);

        String viewName = sellerLoginController.loginSeller(seller, session, model);

        // Verify session attribute is set
        verify(session).setAttribute("sellerId", seller.getSellerId());
        assertEquals("redirect:/seller/dashboard", viewName); // Check the redirect view
    }

    @Test
    void testLoginSellerInvalidCredentials() {
        Seller seller = new Seller();
        seller.setEmail("invalid@example.com");
        seller.setPassword("wrongpassword");

        // Mock the service to return null for invalid credentials
        when(sellerService.authenticateSeller(seller.getEmail(), seller.getPassword())).thenReturn(null);

        String viewName = sellerLoginController.loginSeller(seller, session, model);

        // Verify no session attribute is set
        verify(session, never()).setAttribute(any(String.class), any());
        verify(model).addAttribute("error", "Invalid email or password. Please try again.");
        assertEquals("sellerLogin", viewName); // Check that it returns to the login page
    }

 

    @Test
    void testRegisterSeller() {
        Seller seller = new Seller();
        seller.setEmail("new@example.com");
        seller.setPassword("newpassword");

        // Call the registerSeller method
        String viewName = sellerLoginController.registerSeller(seller, model);

        // Verify that the registerSeller method was called
        verify(sellerService).registerSeller(seller);
        verify(model).addAttribute("success", "Registration successful! Please log in.");
        assertEquals("redirect:/seller/login", viewName); // Check the redirect view
    }
}
