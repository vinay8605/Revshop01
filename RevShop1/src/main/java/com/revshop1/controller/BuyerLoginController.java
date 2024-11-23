package com.revshop1.controller;

import java.util.Optional;

import com.revshop1.model.Buyer;
import com.revshop1.service.BuyerService;
import com.revshop1.service.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/buyer")
public class BuyerLoginController {

    @Autowired
    private BuyerService buyerService;
    
    @Autowired
    private MailService mailService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("buyer", new Buyer());
        return "login";  
    }

    
    @PostMapping("/login")
    public String loginBuyer(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             Model model,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {  
        Buyer buyer = buyerService.findByEmailAndPassword(email, password);
        
        if (buyer != null) {
            session.setAttribute("buyerId", buyer.getBuyerId());  
            model.addAttribute("buyer", buyer);
            redirectAttributes.addFlashAttribute("message", "Login successful!");
            return "redirect:/buyer/dashboard";  
        } else {
            model.addAttribute("error", "Invalid email or password");
            redirectAttributes.addFlashAttribute("error", "Invalid email or password");

            return "redirect:/buyer/login";  
        }
    }
    @GetMapping("/forgot-password") 
    public String showForgotPasswordPage() {
        return "buyerForgotPassword"; 
    }

    
    @PostMapping("/send-otp") 
    public String sendOtp(@RequestParam("email") String email, HttpSession session, Model model,RedirectAttributes redirectAttributes) {
        session.setAttribute("email", email);
        
        
        Optional<Buyer> buyerOpt = buyerService.getBuyerByEmail(email);

       
        if (buyerOpt.isEmpty()) {
            model.addAttribute("error", "Email does not exist. Please register.");
            return "buyerForgotPassword"; 
        }

        
        boolean isSent = buyerService.sendOtpToEmail(email);
        if (!isSent) {
            model.addAttribute("error", "Failed to send OTP. Please try again.");
            return "buyerForgotPassword"; 
        }

        model.addAttribute("success", "OTP sent to your email.");
        return "buyerVerifyOtp";
    }

    
    @GetMapping("/verify-otp") 
    public String showVerifyOtpPage() {
        return "buyerVerifyOtp"; 
    }

    
    @PostMapping("/verify-otp") 
    public String verifyOtp(@RequestParam("email") String email, @RequestParam("otp") String otp, Model model) {
        boolean isOtpValid = buyerService.verifyOtp(email, otp);
        
        if (isOtpValid) {
            
        	return "redirect:/buyer/reset-password?email=" + email;
        } else {
            model.addAttribute("error", "Invalid OTP. Please try again.");
            return "buyerVerifyOtp"; 
        }
    }

  
    @GetMapping("/reset-password") 
    public String showResetPasswordPage(@RequestParam("email") String email, Model model) {
        model.addAttribute("email", email); 
        return "buyerResetPassword"; 
    }

   
    @PostMapping("/reset-password") 
    public String resetPassword(@RequestParam("newPassword") String newPassword, HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        
        /*if (email == null) {
            model.addAttribute("error", "Session expired. Please try again.");
            return "redirect:/buyer/forgot-password"; /
        }*/

        System.out.println("Email received from session: " + email); 

        try {
            buyerService.updatePassword(email, newPassword);
            model.addAttribute("success", "Password updated successfully.");
            session.removeAttribute("email"); 
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "buyerResetPassword"; 
    }
    
    
    
    @GetMapping("/profile")
    public String viewProfile(HttpSession session, Model model) {
        Integer buyerId = (Integer) session.getAttribute("buyerId");
        if (buyerId == null) {
            return "redirect:/buyer/login";
        }
        Buyer buyer = buyerService.getBuyerById(buyerId);
        model.addAttribute("buyer", buyer);
        return "buyerProfile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute Buyer buyer, HttpSession session, RedirectAttributes redirectAttributes) {
        buyerService.updateBuyer(buyer);
        session.setAttribute("buyerId", buyer.getBuyerId());  

        
        redirectAttributes.addFlashAttribute("success", "Profile updated successfully.");
        return "redirect:/buyer/profile";
    }
}
