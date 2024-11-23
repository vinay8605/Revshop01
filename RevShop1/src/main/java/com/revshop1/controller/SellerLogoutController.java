package com.revshop1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class SellerLogoutController {

	@GetMapping("/seller/logout")
    public String sellerLogout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/seller/logout-success"; 
    }

    @GetMapping("/seller/logout-success")
    public String sellerLogoutSuccess() {
        return "sellerLogoutSuccess"; 
    }
}