package com.revshop1.controller;

import com.revshop1.model.Cart;
import com.revshop1.model.Product;
import com.revshop1.service.CartService;
import com.revshop1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getCartItems(HttpSession session, Model model) {
        Integer buyerId = (Integer) session.getAttribute("buyerId");

        if (buyerId == null) {
            return "redirect:/login"; 
        }

        List<Cart> cartItems = cartService.getCartItemsByBuyerId(buyerId);
        model.addAttribute("cartItems", cartItems);
        
        
        if (model.containsAttribute("message")) {
            model.addAttribute("alertMessage", model.getAttribute("message"));
        }

        return "cart";
    }

    @PostMapping("/add")
    public String addCartItem(@RequestParam int productId, @RequestParam int quantity, HttpSession session,RedirectAttributes redirectAttributes) {
        Integer buyerId = (Integer) session.getAttribute("buyerId");

        if (buyerId == null) {
            return "redirect:/buyer/login"; 
        }

        Product product = productService.getProductById(productId);

        if (product == null) {
        	redirectAttributes.addFlashAttribute("message", "Product not found!");
            return "redirect:/cart"; 
        }

        Cart existingCartItem = cartService.getCartItemByProductIdAndBuyerId(product, buyerId);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            cartService.updateCartItem(existingCartItem);
        } else {
            Cart cart = new Cart();
            cart.setBuyerId(buyerId);
            cart.setProduct(product);
            cart.setQuantity(quantity);
            cartService.addCartItem(cart);
            session.setAttribute("cartQuantity", quantity);
        }
        redirectAttributes.addFlashAttribute("message", "Product added to cart!");
        return "redirect:/buyer/dashboard"; 
    }

    @PostMapping("/remove/{cartId}")
    public String removeCartItem(@PathVariable int cartId, RedirectAttributes redirectAttributes) {
        cartService.removeCartItem(cartId);
        redirectAttributes.addFlashAttribute("message", "Item removed from cart successfully!"); 
        return "redirect:/cart"; 
    }

    @GetMapping("/checkout") 
    public String showCheckoutPage(HttpSession session, Model model) {
        Integer buyerId = (Integer) session.getAttribute("buyerId");
        if (buyerId == null) {
            return "redirect:/buyer/login";
        }

        BigDecimal totalPrice = cartService.calculateTotalPrice(buyerId);
        model.addAttribute("totalPrice", totalPrice);
        return "checkout"; 
    }

    @PostMapping("/checkout/submit") 
    public String submitOrder(@RequestParam String name, @RequestParam String email,
                              @RequestParam String phone, @RequestParam String address,
                              @RequestParam BigDecimal totalPrice, RedirectAttributes redirectAttributes) {
        
        redirectAttributes.addFlashAttribute("message", "Order placed successfully!");
        return "redirect:/cart"; 
    }
}
