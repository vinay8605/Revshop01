package com.revshop1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.revshop1.model.Category;
import com.revshop1.model.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category category;
    private Product product;

    @BeforeEach
    void setUp() {
        category = new Category();
        product = new Product();
        product.setProductId(1);  // Mock product with ID 1 for testing
        product.setName("Sample Product");
        product.setCategory(category); // Set category in product
    }

    @Test
    void testSetAndGetCategoryId() {
        category.setCategoryId(100);
        assertEquals(100, category.getCategoryId(), "Category ID should be set to 100");
    }

    @Test
    void testSetAndGetName() {
        category.setName("Electronics");
        assertEquals("Electronics", category.getName(), "Category name should be 'Electronics'");
    }

    @Test
    void testSetAndGetProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        category.setProducts(productList);
        assertNotNull(category.getProducts(), "Product list should not be null");
        assertEquals(1, category.getProducts().size(), "Category should contain exactly one product");
        assertEquals(product, category.getProducts().get(0), "The product in the category should match the added product");
    }

    @Test
    void testSetProductsNull() {
        category.setProducts(null);
        assertNull(category.getProducts(), "Product list should be null after setting it to null");
    }

    @Test
    void testSetNameNull() {
        category.setName(null);
        assertNull(category.getName(), "Category name should be null after setting it to null");
    }
}
