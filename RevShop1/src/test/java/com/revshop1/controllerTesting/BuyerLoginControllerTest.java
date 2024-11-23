package com.revshop1.controllerTesting;
import com.revshop1.controller.BuyerLoginController;
import com.revshop1.model.Buyer;
import com.revshop1.service.BuyerService;
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

class BuyerLoginControllerTest {

    @InjectMocks
    private BuyerLoginController buyerLoginController;

    @Mock
    private BuyerService buyerService;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testLoginBuyerSuccess() {
        Buyer buyer = new Buyer();
        buyer.setBuyerId(1); // Set a buyer ID for testing

        // Mock the service to return a buyer when valid credentials are provided
        when(buyerService.findByEmailAndPassword("test@example.com", "password123")).thenReturn(buyer);

        String viewName = buyerLoginController.loginBuyer("test@example.com", "password123", model, session, null);

        // Verify session attribute is set
        verify(session).setAttribute("buyerId", buyer.getBuyerId());
        assertEquals("redirect:/buyer/dashboard", viewName); // Check the redirect view
    }

    @Test
    void testLoginBuyerInvalidCredentials() {
        // Mock the service to return null for invalid credentials
        when(buyerService.findByEmailAndPassword("invalid@example.com", "wrongpassword")).thenReturn(null);

        String viewName = buyerLoginController.loginBuyer("invalid@example.com", "wrongpassword", model, session, null);

        // Verify no session attribute is set
        verify(session, never()).setAttribute(any(String.class), any());
        verify(model).addAttribute("error", "Invalid email or password");
        assertEquals("login", viewName); // Check that it returns to the login page
    }
}
