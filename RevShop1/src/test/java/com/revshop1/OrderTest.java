package com.revshop1;


import com.revshop1.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        // Initialize an Order object before each test
        order = new Order();
    }

    @Test
    void testSetAndGetOrderId() {
        order.setOrderId(1);
        assertEquals(1, order.getOrderId());
    }

    @Test
    void testSetAndGetBuyerId() {
        order.setBuyerId(100);
        assertEquals(100, order.getBuyerId());
    }

    @Test
    void testSetAndGetPaymentId() {
        order.setPaymentId("PAY12345");
        assertEquals("PAY12345", order.getPaymentId());
    }

    @Test
    void testSetAndGetName() {
        order.setFirstName("John Doe");
        assertEquals("John Doe", order.getFirstName());
    }

    @Test
    void testSetAndGetEmail() {
        order.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", order.getEmail());
    }

    @Test
    void testSetAndGetPhone() {
        order.setPhone("1234567890");
        assertEquals("1234567890", order.getPhone());
    }

    @Test
    void testSetAndGetAddress() {
        order.setAddress("123 Main St");
        assertEquals("123 Main St", order.getAddress());
    }

    @Test
    void testSetAndGetTotalPrice() {
        BigDecimal price = BigDecimal.valueOf(99.99);
        order.setTotalPrice(price);
        assertEquals(price, order.getTotalPrice());
    }

    @Test
    void testSetAndGetCreatedAt() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        order.setCreatedAt(timestamp);
        assertEquals(timestamp, order.getCreatedAt());
    }



}
