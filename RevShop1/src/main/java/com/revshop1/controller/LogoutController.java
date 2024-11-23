package com.revshop1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/logout-success"; 
    }

    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "logout-success"; 
    }
}