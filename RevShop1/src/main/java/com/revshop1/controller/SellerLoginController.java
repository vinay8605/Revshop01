package com.revshop1.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revshop1.model.Seller;
import com.revshop1.service.SellerService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SellerLoginController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/seller/login")
    public String showLoginForm(Model model) {
        model.addAttribute("seller", new Seller());
        return "sellerLogin"; 
    }

    @PostMapping("/seller/login")
    public String loginSeller(@ModelAttribute("seller") Seller seller, HttpSession session, Model model) {
        Seller loggedInSeller = sellerService.authenticateSeller(seller.getEmail(), seller.getPassword());

        if (loggedInSeller != null) {
            session.setAttribute("sellerId", loggedInSeller.getSellerId());
            return "redirect:/seller/dashboard"; 
        } else {
            model.addAttribute("error", "Invalid email or password. Please try again.");
            return "sellerLogin"; 
        }
    }

    @GetMapping("/seller/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("seller", new Seller());
        return "sellerRegister"; 
    }

    @PostMapping("/seller/register")
    public String registerSeller(@ModelAttribute("seller") Seller seller, Model model) {
        sellerService.registerSeller(seller);
        model.addAttribute("success", "Registration successful! Please log in.");
        return "redirect:/seller/login"; 
    }

    // Forgot Password Methods

    @GetMapping("/seller/forgot-password")
    public String showForgotPasswordPage() {
        return "forgotPassword";
    }

    @PostMapping("/seller/send-otp")
    public String sendOtp(@RequestParam("email") String email, HttpSession session, Model model) {
        
    	session.setAttribute("email", email);
    	
    	
        Optional<Seller> sellerOpt = sellerService.getSellerByEmail(email); 

    
        if (sellerOpt.isEmpty()) {
            model.addAttribute("error", "Email does not exist. Please register.");
            return "forgotPassword"; 
        }

        
        boolean isSent = sellerService.sendOtpToEmail(email);
        if (!isSent) {
            model.addAttribute("error", "Failed to send OTP. Please try again.");
            return "forgotPassword"; 
        }

        model.addAttribute("success", "OTP sent to your email.");
        return "verifyOtp"; 
    }

    @GetMapping("/seller/verify-otp")
    public String showVerifyOtpPage() {
        return "verifyOtp"; 
    }

    @PostMapping("/seller/verify-otp")
    public String verifyOtp(@RequestParam("email") String email, @RequestParam("otp") String otp, Model model) {
        boolean isOtpValid = sellerService.verifyOtp(email, otp);
        
        if (isOtpValid) {
            
            return "redirect:/seller/reset-password?email=" + email; 
        } else {
            model.addAttribute("error", "Invalid OTP. Please try again.");
            return "verifyOtp"; 
        }
    }

    @GetMapping("/seller/reset-password") 
    public String showResetPasswordPage(@RequestParam("email") String email, Model model) {
        model.addAttribute("email", email); 
        return "resetPassword"; 
    }

    @PostMapping("/seller/reset-password")
    public String resetPassword(@RequestParam("newPassword") String newPassword, HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        
       /* if (email == null) {
            model.addAttribute("error", "Session expired. Please try again.");
            return "redirect:/seller/forgot-password"; // Redirect if email is not in session
        }*/

        System.out.println("Email received from session: " + email); 

        try {
            sellerService.updatePassword(email, newPassword);
            model.addAttribute("success", "Password updated successfully.");
            session.removeAttribute("email");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "resetPassword";
    }

    
}