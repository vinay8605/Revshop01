package com.revshop1.controller;

import com.revshop1.model.Buyer;
import com.revshop1.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    
    @GetMapping("/buyer/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("buyer", new Buyer());  
        return "register";  
    }

    @PostMapping("/buyer/register")
    public String registerBuyer(@ModelAttribute("buyer") Buyer buyer,Model model) {
    	String result = buyerService.saveBuyer(buyer);

        if ("Email already in use".equals(result)) {
            model.addAttribute("error", result);  
            return "register";  
        }

        return "redirect:/buyer/success"; 
    }

    @GetMapping("/buyer/success")
    public String registrationSuccess() {
        return "success";  
    }
}
