package com.revshop1.serviceTesting;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.revshop1.model.Cart;
import com.revshop1.model.Product;
import com.revshop1.repository.CartRepository;
import com.revshop1.repository.ProductRepository;
import com.revshop1.service.CartService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartService cartService;

    private Cart testCart;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product();
        testProduct.setProductId(1);
        testProduct.setPrice(new BigDecimal("100.00"));

        testCart = new Cart();
        testCart.setCartId(1);
        testCart.setProduct(testProduct);
        testCart.setQuantity(2);
    }

    @Test
    void testGetCartItemsByBuyerId() {
        when(cartRepository.findCartItemsWithProductDetails(1)).thenReturn(Arrays.asList(testCart));

        List<Cart> cartItems = cartService.getCartItemsByBuyerId(1);

        assertEquals(1, cartItems.size());
        verify(cartRepository, times(1)).findCartItemsWithProductDetails(1);
    }

    @Test
    void testAddCartItem() {
        when(cartRepository.save(testCart)).thenReturn(testCart);

        Cart savedCart = cartService.addCartItem(testCart);

        assertNotNull(savedCart);
        verify(cartRepository, times(1)).save(testCart);
    }

    @Test
    void testRemoveCartItem() {
        doNothing().when(cartRepository).deleteById(1);

        cartService.removeCartItem(1);

        verify(cartRepository, times(1)).deleteById(1);
    }

    @Test
    void testGetCartItemByProductIdAndBuyerId() {
        when(cartRepository.findByProductAndBuyerId(testProduct, 1)).thenReturn(testCart);

        Cart foundCart = cartService.getCartItemByProductIdAndBuyerId(testProduct, 1);

        assertNotNull(foundCart);
        verify(cartRepository, times(1)).findByProductAndBuyerId(testProduct, 1);
    }

    @Test
    void testUpdateCartItem() {
        cartService.updateCartItem(testCart);

        verify(cartRepository, times(1)).save(testCart);
    }

    @Test
    void testCalculateTotalPrice() {
        when(cartRepository.findCartItemsWithProductDetails(1)).thenReturn(Arrays.asList(testCart));

        BigDecimal totalPrice = cartService.calculateTotalPrice(1);

        assertEquals(new BigDecimal("200.00"), totalPrice);
    }

    @Test
    void testClearCart() {
        doNothing().when(cartRepository).deleteByBuyerId(1);

        cartService.clearCart(1);

        verify(cartRepository, times(1)).deleteByBuyerId(1);
    }

    @Test
    void testGetProductsInCart() {
        when(cartRepository.findByBuyerId(1)).thenReturn(Arrays.asList(testCart));
        when(productRepository.findById(1)).thenReturn(Optional.of(testProduct));

        List<Product> products = cartService.getProductsInCart(1);

        assertEquals(1, products.size());
        assertEquals(testProduct, products.get(0));
    }
}