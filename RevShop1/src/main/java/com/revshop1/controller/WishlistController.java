package com.revshop1.controller;

import com.revshop1.model.Product;
import com.revshop1.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/wishlist/add")
    public String addToWishlist(@RequestParam("productId") int productId,HttpSession session, RedirectAttributes redirectAttributes) {
        Integer buyerId = (Integer) session.getAttribute("buyerId"); 

        if (buyerId == null) {
            redirectAttributes.addFlashAttribute("error", "You need to log in to add items to your wishlist.");
            return "redirect:/buyer/login"; 
        }

        wishlistService.addToWishlist(buyerId, productId);
        redirectAttributes.addFlashAttribute("message", "Product added to wishlist!");
        return "redirect:/buyer/dashboard"; 
    }


    @GetMapping("/wishlist")
    public String viewWishlist(Model model,HttpSession session) {
    	Integer buyerId = (Integer) session.getAttribute("buyerId"); 

    	if (buyerId != null) {
            List<Product> wishlistProducts = wishlistService.getWishlistByUserId(buyerId);
            model.addAttribute("wishlistProducts", wishlistProducts);
        } else {
            model.addAttribute("error", "You need to be logged in to view your wishlist.");
        }

        return "wishlist";
    }

    
}
