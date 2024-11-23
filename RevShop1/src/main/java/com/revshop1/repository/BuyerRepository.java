package com.revshop1.repository;


import com.revshop1.model.Buyer;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
    Buyer findByEmail(String email);  

	Buyer findByEmailAndPassword(String email, String password);

	Buyer findByfirstName(String first_name);
	
	

	// Method to check if a buyer exists by email
	boolean existsByEmail(String email);

	    // Optional method to find a buyer by email, returns Optional
	Optional<Buyer> findOptionalByEmail(String email);
	
	
	
	
}
