package com.revshop1.service;

import com.revshop1.model.Product;
import com.revshop1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

   
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    
    public List<Product> getProductsByCategory(int categoryId) {
        return productRepository.findAllByCategory_CategoryId(categoryId);
    }

    
    public Product getProductById(int productId) {
        return productRepository.findById(productId).orElse(null);
    }

 
    public List<Product> getProductsBySeller(int sellerId) {
        return productRepository.findBySeller_SellerId(sellerId);
    }

    
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    
    public Product updateProduct(Product product) {
        return productRepository.save(product); 
    }

   
    public void deleteProduct(int productId) {
        productRepository.deleteById(productId); 
    }

    
    public List<Product> findBySellerId(int sellerId) {
        return productRepository.findBySeller_SellerId(sellerId);
    }
    
}
