package com.revshop1.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.revshop1.service.ProductService;

import jakarta.servlet.http.HttpSession;

import com.revshop1.service.CategoryService;
import com.revshop1.model.Product;
import com.revshop1.model.Category;

@Controller
public class BuyerDashboardController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/buyer/dashboard")
    public String getDashboard(@RequestParam(value = "category", required = false, defaultValue = "0") Integer categoryId, Model model) {
        
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

       
        List<Product> products;
        if (categoryId == null || categoryId == 0) {
            products = productService.getAllProducts(); 
        } else {
            products = productService.getProductsByCategory(categoryId); 
        }

        model.addAttribute("products", products);

        return "buyerDashboard"; 
    }
    
}
