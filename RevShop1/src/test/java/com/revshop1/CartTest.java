package com.revshop1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.revshop1.model.Cart;
import com.revshop1.model.Product;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;
    private Product product;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        product = new Product();
        product.setProductId(1);  // Mock product with ID 1 for testing
        product.setName("Sample Product");
    }

    @Test
    void testSetAndGetCartId() {
        cart.setCartId(100);
        assertEquals(100, cart.getCartId(), "Cart ID should be set to 100");
    }

    @Test
    void testSetAndGetBuyerId() {
        cart.setBuyerId(200);
        assertEquals(200, cart.getBuyerId(), "Buyer ID should be set to 200");
    }

    @Test
    void testSetAndGetProduct() {
        cart.setProduct(product);
        assertNotNull(cart.getProduct(), "Product should not be null after setting");
        assertEquals(product, cart.getProduct(), "The product in the cart should match the set product");
        assertEquals(1, cart.getProduct().getProductId(), "Product ID should be 1");
        assertEquals("Sample Product", cart.getProduct().getName(), "Product name should be 'Sample Product'");
    }

    @Test
    void testSetAndGetQuantity() {
        cart.setQuantity(3);
        assertEquals(3, cart.getQuantity(), "Quantity should be set to 3");
        
        // Edge case: Check setting a negative quantity
        cart.setQuantity(-1);
        assertEquals(-1, cart.getQuantity(), "Quantity should be set to -1 even if invalid");
    }

    @Test
    void testSetAndGetAddedAt() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        cart.setAddedAt(timestamp);
        assertEquals(timestamp, cart.getAddedAt(), "Added timestamp should match the set timestamp");
    }

    @Test
    void testSetProductNull() {
        cart.setProduct(null);
        assertNull(cart.getProduct(), "Product should be null after setting to null");
    }
}
