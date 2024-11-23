package com.revshop1.controller;

import com.revshop1.model.Category;
import com.revshop1.model.Product;
import com.revshop1.model.Seller;
import com.revshop1.service.ProductService; 
import com.revshop1.service.SellerService; 
import com.revshop1.service.CategoryService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerDashboardController {

    @Autowired
    private ProductService productService; 

    @Autowired
    private SellerService sellerService; 
    
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/dashboard")
    public String showDashboard(@SessionAttribute(name = "sellerId", required = false) Integer sellerId, Model model) {
        if (sellerId == null) {
            return "redirect:/seller/login"; 
        }

        Seller seller = sellerService.findSellerById(sellerId);
        if (seller == null) {
            model.addAttribute("error", "Seller not found.");
            return "error"; 
        }

        model.addAttribute("seller", seller); 

       
        List<Product> products = productService.findBySellerId(sellerId);
        model.addAttribute("products", products);

        return "sellerDashboard"; 
    }
    
    @GetMapping("/newProduct")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "addProduct"; 
    }


    @PostMapping("/addProduct")
    public String addProduct(@SessionAttribute(name = "sellerId") Integer sellerId, Product product, @RequestParam("categoryId") int categoryId) {
        if (sellerId == null) {
            return "redirect:/seller/login";
        }

        Seller seller = sellerService.findSellerById(sellerId);
        if (seller == null) {
            return "error";
        }

        product.setSeller(seller);

        Category category = categoryService.getCategoryById(categoryId);
        product.setCategory(category); 

        productService.saveProduct(product);

        return "redirect:/seller/dashboard";
    }


    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam int productId, @SessionAttribute(name = "sellerId") Integer sellerId, Model model) {
       
        productService.deleteProduct(productId); 
        model.addAttribute("successMessage", "Product deleted successfully."); 

        
        Seller seller = sellerService.findSellerById(sellerId); 
        if (seller != null) {
            model.addAttribute("seller", seller); 

            
            List<Product> products = productService.findBySellerId(sellerId);
            model.addAttribute("products", products); 
        } else {
            model.addAttribute("error", "Seller not found."); 
        }

        return "sellerDashboard"; 
    }


  
    @GetMapping("/editProduct/{productId}")
    public String showEditProductForm(@PathVariable int productId, Model model) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return "error"; 
        }
        model.addAttribute("product", product); 
        return "editProduct"; 
    }

    
    @PostMapping("/updateProduct")
    public String updateProduct(@SessionAttribute(name = "sellerId") Integer sellerId, Product product) {
        if (sellerId == null) {
            return "redirect:/seller/login"; 
        }

        Seller seller = sellerService.findSellerById(sellerId);
        if (seller != null) {
            product.setSeller(seller); 
        }

        productService.updateProduct(product); 
        return "redirect:/seller/dashboard"; 
    }

}
