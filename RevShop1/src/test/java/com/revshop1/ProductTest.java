package com.revshop1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revshop1.model.Category;
import com.revshop1.model.Product;
import com.revshop1.model.Seller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;
    private Category category;
    private Seller seller;

    @BeforeEach
    void setUp() {
        product = new Product();
        category = new Category();
        seller = new Seller();

        // Mocking some values
        category.setCategoryId(1); // Assuming there is a method to set ID
        seller.setSellerId(1); // Assuming there is a method to set ID
    }

    @Test
    void testSetAndGetProductId() {
        product.setProductId(1);
        assertEquals(1, product.getProductId(), "Product ID should be 1");
    }

    @Test
    void testSetAndGetName() {
        product.setName("Sample Product");
        assertEquals("Sample Product", product.getName(), "Product name should be 'Sample Product'");
        
        // Edge case: Setting an empty name
        product.setName("");
        assertEquals("", product.getName(), "Product name should be set to empty string");
    }

    @Test
    void testSetAndGetDescription() {
        product.setDescription("This is a sample product description.");
        assertEquals("This is a sample product description.", product.getDescription(), "Product description should match");

        // Edge case: Setting a null description
        product.setDescription(null);
        assertNull(product.getDescription(), "Product description should be null after setting to null");
    }

    @Test
    void testSetAndGetPrice() {
        product.setPrice(BigDecimal.valueOf(100.00));
        assertEquals(BigDecimal.valueOf(100.00), product.getPrice(), "Product price should be 100.00");

        // Edge case: Setting a negative price
        product.setPrice(BigDecimal.valueOf(-50.00));
        assertEquals(BigDecimal.valueOf(-50.00), product.getPrice(), "Product price should be set to -50.00");
    }

    @Test
    void testSetAndGetDiscountedPrice() {
        product.setDiscountedPrice(BigDecimal.valueOf(80.00));
        assertEquals(BigDecimal.valueOf(80.00), product.getDiscountedPrice(), "Product discounted price should be 80.00");

        // Edge case: Setting discounted price greater than regular price
        product.setPrice(BigDecimal.valueOf(100.00));
        product.setDiscountedPrice(BigDecimal.valueOf(110.00));
        assertEquals(BigDecimal.valueOf(110.00), product.getDiscountedPrice(), "Discounted price should be set to 110.00");
    }

    @Test
    void testSetAndGetImageUrl() {
        product.setImageUrl("http://example.com/image.jpg");
        assertEquals("http://example.com/image.jpg", product.getImageUrl(), "Image URL should match");

        // Edge case: Setting an empty image URL
        product.setImageUrl("");
        assertEquals("", product.getImageUrl(), "Image URL should be set to empty string");
    }

    @Test
    void testSetAndGetQuantity() {
        product.setQuantity(10);
        assertEquals(10, product.getQuantity(), "Product quantity should be 10");

        // Edge case: Setting a negative quantity
        product.setQuantity(-5);
        assertEquals(-5, product.getQuantity(), "Product quantity should be set to -5");
    }

    @Test
    void testSetAndGetCategory() {
        product.setCategory(category);
        assertEquals(category, product.getCategory(), "Product category should match the set category");
    }

    @Test
    void testSetAndGetSeller() {
        product.setSeller(seller);
        assertEquals(seller, product.getSeller(), "Product seller should match the set seller");
    }

    @Test
    void testCreatedAtTimestamp() {
        // Simulating the creation of the product
        product.onCreate(); // Assuming you can call this method directly
        LocalDateTime now = LocalDateTime.now();
        assertNotNull(product.getCreatedAt(), "Created At timestamp should not be null");
        assertTrue(product.getCreatedAt().isBefore(now) || product.getCreatedAt().isEqual(now), "Created At timestamp should be before or equal to now");
    }

    @Test
    void testUpdatedAtTimestamp() {
        // Simulating the update of the product
        product.onUpdate(); // Assuming you can call this method directly
        LocalDateTime now = LocalDateTime.now();
        assertNotNull(product.getUpdatedAt(), "Updated At timestamp should not be null");
        assertTrue(product.getUpdatedAt().isBefore(now) || product.getUpdatedAt().isEqual(now), "Updated At timestamp should be before or equal to now");
    }
}
