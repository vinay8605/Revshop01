package com.revshop1.repository;

import com.revshop1.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Optional<Seller> findByEmail(String email);
    Seller findByEmailAndPassword(String email, String password);
    
    

    boolean existsByEmail(String email); 
    
    
	
}
