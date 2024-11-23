package com.revshop1.repository;

import com.revshop1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> { 
    List<Product> findAllByCategory_CategoryId(int categoryId); 
    
    
    List<Product> findBySeller_SellerId(int sellerId);

	
	List<Product> findAllById(Iterable<Integer> ids);
	
	 Optional<Product> findById(Integer productId);
	 
	
}
