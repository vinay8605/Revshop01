package com.revshop1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.revshop1.model.Buyer;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BuyerTest {

    private Buyer buyer;

    @BeforeEach
    void setUp() {
        buyer = new Buyer();
    }

    @Test
    void testSetAndGetBuyerId() {
        buyer.setBuyerId(1);
        assertEquals(1, buyer.getBuyerId());
    }

    @Test
    void testSetAndGetFirstName() {
        buyer.setFirstName("John");
        assertEquals("John", buyer.getFirstName());
    }

    @Test
    void testSetAndGetLastName() {
        buyer.setLastName("Doe");
        assertEquals("Doe", buyer.getLastName());
    }

    @Test
    void testSetAndGetEmail() {
        buyer.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", buyer.getEmail());
    }

    @Test
    void testSetAndGetPassword() {
        buyer.setPassword("password123");
        assertEquals("password123", buyer.getPassword());
    }

    @Test
    void testDefaultCreatedAt() {
        assertNotNull(buyer.getCreatedAt());  // By default, should be set
    }

    @Test
    void testSetAndGetCreatedAt() {
        LocalDateTime now = LocalDateTime.now();
        buyer.setCreatedAt(now);
        assertEquals(now, buyer.getCreatedAt());
    }

    @Test
    void testDefaultUpdatedAt() {
        assertNotNull(buyer.getUpdatedAt());  // By default, should be set
    }

    @Test
    void testSetAndGetUpdatedAt() {
        LocalDateTime now = LocalDateTime.now();
        buyer.setUpdatedAt(now);
        assertEquals(now, buyer.getUpdatedAt());
    }

}
