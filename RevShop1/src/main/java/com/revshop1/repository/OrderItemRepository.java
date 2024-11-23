package com.revshop1.repository;

import com.revshop1.model.Order;
import com.revshop1.model.OrderItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	List<OrderItem> findByOrder(Order order);
}
