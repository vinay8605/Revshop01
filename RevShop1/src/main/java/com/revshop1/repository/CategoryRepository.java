package com.revshop1.repository;

import com.revshop1.model.Cart;
import com.revshop1.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	
}
