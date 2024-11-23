package com.revshop1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.revshop1.model.Seller;

import static org.junit.jupiter.api.Assertions.*;

class SellerTest {

    private Seller seller;

    @BeforeEach
    void setUp() {
        seller = new Seller();
    }

    @Test
    void testSetAndGetSellerId() {
        seller.setSellerId(1);
        assertEquals(1, seller.getSellerId(), "Seller ID should be 1");
    }

    @Test
    void testSetAndGetBusinessName() {
        seller.setBusinessName("Tech Shop");
        assertEquals("Tech Shop", seller.getBusinessName(), "Business name should be 'Tech Shop'");
        
        // Edge case: Test setting an empty business name
        seller.setBusinessName("");
        assertEquals("", seller.getBusinessName(), "Business name should be set to empty string");
    }

    @Test
    void testSetAndGetOwnerFirstName() {
        seller.setOwnerFirstName("John");
        assertEquals("John", seller.getOwnerFirstName(), "Owner first name should be 'John'");
        
        // Edge case: Test setting an overly long first name
        String longName = "A".repeat(51); // Assuming the max length is 50
        seller.setOwnerFirstName(longName);
        assertEquals(longName, seller.getOwnerFirstName(), "Owner first name should be set to the long name");
    }

    @Test
    void testSetAndGetOwnerLastName() {
        seller.setOwnerLastName("Doe");
        assertEquals("Doe", seller.getOwnerLastName(), "Owner last name should be 'Doe'");
        
        // Edge case: Test setting a null last name
        seller.setOwnerLastName(null);
        assertNull(seller.getOwnerLastName(), "Owner last name should be null after setting to null");
    }

    @Test
    void testSetAndGetEmail() {
        seller.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", seller.getEmail(), "Email should be 'john.doe@example.com'");
        
        // Edge case: Test setting an invalid email
        seller.setEmail("invalidEmail");
        assertEquals("invalidEmail", seller.getEmail(), "Email should be set to the invalid format");
    }

    @Test
    void testSetAndGetPassword() {
        seller.setPassword("securepassword");
        assertEquals("securepassword", seller.getPassword(), "Password should be 'securepassword'");
        
        // Edge case: Test setting an empty password
        seller.setPassword("");
        assertEquals("", seller.getPassword(), "Password should be set to empty string");
    }
}
