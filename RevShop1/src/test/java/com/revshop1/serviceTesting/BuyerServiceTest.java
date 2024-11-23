package com.revshop1.serviceTesting;
import com.revshop1.model.Buyer;
import com.revshop1.repository.BuyerRepository;
import com.revshop1.service.BuyerService;
import com.revshop1.service.MailService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuyerServiceTest {

    @Mock
    private BuyerRepository buyerRepository;

    @Mock
    private MailService mailService;

    @InjectMocks
    private BuyerService buyerService; // The class under test

    private Buyer testBuyer;

    @BeforeEach
    void setUp() {
        testBuyer = new Buyer();
        testBuyer.setBuyerId(1);
        testBuyer.setFirstName("John");
        testBuyer.setLastName("Doe");
        testBuyer.setEmail("johndoe@example.com");
        testBuyer.setPassword("password123");
    }

    @Test
    void testSaveBuyer_EmailAlreadyExists() {
        when(buyerRepository.findByEmail(testBuyer.getEmail())).thenReturn(testBuyer);

        String result = buyerService.saveBuyer(testBuyer);

        assertEquals("Email already in use", result);
    }

    @Test
    void testSaveBuyer_Success() {
        when(buyerRepository.findByEmail(testBuyer.getEmail())).thenReturn(null);

        String result = buyerService.saveBuyer(testBuyer);

        assertEquals("Success", result);
        verify(buyerRepository).save(testBuyer);
        verify(mailService).sendRegistrationEmail(eq(testBuyer.getEmail()), anyString(), anyString());
    }

    @Test
    void testGetBuyerById() {
        when(buyerRepository.findById(1)).thenReturn(Optional.of(testBuyer));

        Buyer foundBuyer = buyerService.getBuyerById(1);

        assertNotNull(foundBuyer);
        assertEquals("John", foundBuyer.getFirstName());
    }

    @Test
    void testUpdatePassword_BuyerNotFound() {
        when(buyerRepository.findOptionalByEmail(testBuyer.getEmail())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            buyerService.updatePassword(testBuyer.getEmail(), "newPassword");
        });
    }

    @Test
    void testUpdatePassword_Success() {
        when(buyerRepository.findOptionalByEmail(testBuyer.getEmail())).thenReturn(Optional.of(testBuyer));

        buyerService.updatePassword(testBuyer.getEmail(), "newPassword");

        verify(buyerRepository).save(testBuyer);
        verify(mailService).sendEmail(eq(testBuyer.getEmail()), contains("Your Password Has Been Successfully Updated!"), anyString());
    }
}