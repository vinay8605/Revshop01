package com.revshop1.service;

import com.revshop1.exception.OrderNotFoundException;
import com.revshop1.model.Order;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revshop1.model.OrderItem;
import com.revshop1.repository.OrderRepository;
import com.revshop1.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public Order saveOrder(Order order) {
        try {
            logger.info("Saving order: {}", order);
            return orderRepository.save(order);
        } catch (Exception e) {
            logger.error("Error saving order: {}", e.getMessage());
            throw e; // Re-throw or handle as needed
        }
    }

    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
    
    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException("Order with ID " + orderId + " not found"));
    }
    
    public List<Order> getOrdersWithItemsByBuyerId(Integer buyerId) {
        // Fetch orders with their order items for the specific buyer
        List<Order> orders = orderRepository.findByBuyerId(buyerId);
        for (Order order : orders) {
            List<OrderItem> orderItems = orderItemRepository.findByOrder(order);
            order.setOrderItems(orderItems); // Set the order items to the order
        }
        return orders;
    }


	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}
}
