package com.revshop1.service;

import com.revshop1.model.Cart;
import com.revshop1.model.Product;
import com.revshop1.repository.CartRepository;
import com.revshop1.repository.ProductRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private ProductRepository productRepository;

    
    public List<Cart> getCartItemsByBuyerId(int buyerId) {
        return cartRepository.findCartItemsWithProductDetails(buyerId);
    }

    
    public Cart addCartItem(Cart cart) {
        return cartRepository.save(cart);
    }

    
    public void removeCartItem(int cartId) {
        cartRepository.deleteById(cartId);
    }

    
    public Cart getCartItemByProductIdAndBuyerId(Product product, int buyerId) {
        return cartRepository.findByProductAndBuyerId(product, buyerId);
    }

    
    public void updateCartItem(Cart cart) {
        cartRepository.save(cart); 
    }
    
    public BigDecimal calculateTotalPrice(int buyerId) {
        List<Cart> cartItems = getCartItemsByBuyerId(buyerId); 
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Cart cartItem : cartItems) {
            Product product = cartItem.getProduct(); 
            
            if (product != null) { 
                
                totalPrice = totalPrice.add(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            } else {
                
                System.out.println("Product not found for cart item ID: " + cartItem.getCartId());
            }
        }

        return totalPrice;
    }


    @Transactional
	public void clearCart(int buyerId) {
		cartRepository.deleteByBuyerId(buyerId);
	}

	public List<Product> getProductsInCart(int buyerId) {
		 List<Cart> cartItems = cartRepository.findByBuyerId(buyerId);

	        // Initialize an empty list to hold the products
	        List<Product> productsInCart = new ArrayList<>();

	        // Iterate through the cart items and retrieve the product
	        for (Cart cartItem : cartItems) {
	            // Get the product ID from the cart item's product object
	            int productId = cartItem.getProduct().getProductId();
	            // Fetch the product using the repository
	            Product product = productRepository.findById(productId).orElse(null); // Handle if product is not found
	            if (product != null) {
	                productsInCart.add(product); // Add the product to the list
	            }
	        }

	        return productsInCart;
    }

   
}
