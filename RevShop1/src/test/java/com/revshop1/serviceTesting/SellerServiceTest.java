package com.revshop1.serviceTesting;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revshop1.model.Seller;
import com.revshop1.repository.SellerRepository;
import com.revshop1.service.MailService;
import com.revshop1.service.SellerService;

public class SellerServiceTest {

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private MailService mailService;

    @InjectMocks
    private SellerService sellerService;

    private Seller seller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        seller = new Seller();
        seller.setEmail("test@example.com");
        seller.setPassword("password");
        seller.setOwnerFirstName("John");
        seller.setOwnerLastName("Doe");
        seller.setBusinessName("John's Store");
    }

    @Test
    public void testAuthenticateSeller_Success() {
        when(sellerRepository.findByEmail("test@example.com")).thenReturn(Optional.of(seller));

        Seller authenticatedSeller = sellerService.authenticateSeller("test@example.com", "password");
        assertNotNull(authenticatedSeller);
        assertEquals("test@example.com", authenticatedSeller.getEmail());
    }

    @Test
    public void testAuthenticateSeller_Failure() {
        when(sellerRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        Seller authenticatedSeller = sellerService.authenticateSeller("test@example.com", "wrongpassword");
        assertNull(authenticatedSeller);
    }

    @Test
    public void testRegisterSeller() {
        when(sellerRepository.save(seller)).thenReturn(seller);

        Seller registeredSeller = sellerService.registerSeller(seller);
        assertNotNull(registeredSeller);
        assertEquals("test@example.com", registeredSeller.getEmail());

        verify(mailService, times(1)).sendRegistrationEmail(
            eq("test@example.com"), eq("Welcome to RevShop!"), anyString()
        );
    }

    @Test
    public void testSendOtpToEmail() {
        when(sellerRepository.existsByEmail("test@example.com")).thenReturn(true);

        boolean otpSent = sellerService.sendOtpToEmail("test@example.com");
        assertTrue(otpSent);
        verify(mailService, times(1)).sendPasswordResetEmail(eq("test@example.com"), anyString());
    }

    @Test
    public void testSendOtpToEmail_EmailNotExist() {
        when(sellerRepository.existsByEmail("unknown@example.com")).thenReturn(false);

        boolean otpSent = sellerService.sendOtpToEmail("unknown@example.com");
        assertFalse(otpSent);
    }

    @Test
    public void testUpdatePassword() {
        when(sellerRepository.findByEmail("test@example.com")).thenReturn(Optional.of(seller));

        sellerService.updatePassword("test@example.com", "newPassword");

        verify(sellerRepository, times(1)).save(any(Seller.class));
        verify(mailService, times(1)).sendEmail(eq("test@example.com"), eq("Your Password Has Been Successfully Updated!"), anyString());
    }

    // Add more tests for other methods if needed.
}