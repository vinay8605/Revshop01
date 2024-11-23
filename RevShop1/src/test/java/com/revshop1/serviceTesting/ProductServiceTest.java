package com.revshop1.serviceTesting;
import com.revshop1.model.Product;
import com.revshop1.repository.ProductRepository;
import com.revshop1.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product(); // Initialize product1 details
        Product product2 = new Product(); // Initialize product2 details

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
    }

    @Test
    public void testGetProductsByCategory() {
        int categoryId = 2;
        Product product = new Product(); // Initialize product details

        when(productRepository.findAllByCategory_CategoryId(categoryId)).thenReturn(Arrays.asList(product));

        List<Product> products = productService.getProductsByCategory(categoryId);

        assertEquals(1, products.size());
    }

    @Test
    public void testGetProductById() {
        int productId = 1;
        Product product = new Product(); // Initialize product details

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(productId);

        assertEquals(product, result);
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product(); // Initialize product details

        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);

        assertEquals(product, savedProduct);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product(); // Initialize product details

        when(productRepository.save(product)).thenReturn(product);

        Product updatedProduct = productService.updateProduct(product);

        assertEquals(product, updatedProduct);
    }

    @Test
    public void testDeleteProduct() {
        int productId = 3;

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    public void testGetProductsBySeller() {
        int sellerId = 1;
        Product product = new Product(); // Initialize product details

        when(productRepository.findBySeller_SellerId(sellerId)).thenReturn(Arrays.asList(product));

        List<Product> products = productService.getProductsBySeller(sellerId);

        assertEquals(1, products.size());
    }
}