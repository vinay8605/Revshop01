package com.revshop1.repository;

import com.revshop1.model.Cart;
import com.revshop1.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    
    @Query("SELECT c FROM Cart c JOIN FETCH c.product p WHERE c.buyerId = :buyerId")
    List<Cart> findCartItemsWithProductDetails(int buyerId);

    Cart findByProductAndBuyerId(Product product, int buyerId);

	void deleteByBuyerId(int buyerId);

	List<Cart> findByBuyerId(int buyerId);
    

}
