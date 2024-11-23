package com.revshop1.controller;

import com.revshop1.model.Product;
import com.revshop1.model.Seller;
import com.revshop1.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seller")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/dashboard")
    public String showProductDashboard(HttpSession session, Model model) {
        Seller seller = (Seller) session.getAttribute("seller");
        if (seller == null) {
            return "redirect:/seller/login"; 
        }

        List<Product> products = productService.getProductsBySeller(seller.getSellerId());
        model.addAttribute("products", products);
        return "productDashboard"; 
    }

   /* @GetMapping("/addProduct")
    public String showAddProductForm(HttpSession session, Model model) {
        Seller seller = (Seller) session.getAttribute("seller");
        if (seller == null) {
            return "redirect:/seller/login"; 
        }

        model.addAttribute("product", new Product());
        return "addProduct"; 
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Product product, HttpSession session) {
        Seller seller = (Seller) session.getAttribute("seller");
        if (seller != null) {
            product.setSeller(seller); 
            productService.saveProduct(product);
        }
        return "redirect:/seller/dashboard"; 
    }*/
}
