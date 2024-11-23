package com.revshop1.serviceTesting;

import com.revshop1.exception.OrderNotFoundException;
import com.revshop1.model.Order;
import com.revshop1.model.OrderItem;
import com.revshop1.repository.OrderRepository;
import com.revshop1.repository.OrderItemRepository;
import com.revshop1.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveOrder() {
        Order order = new Order(); // Initialize and set order details if necessary
        when(orderRepository.save(order)).thenReturn(order);

        Order savedOrder = orderService.saveOrder(order);

        assertEquals(order, savedOrder);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testSaveOrderItem() {
        OrderItem orderItem = new OrderItem(); // Initialize and set order item details if necessary

        orderService.saveOrderItem(orderItem);

        verify(orderItemRepository, times(1)).save(orderItem);
    }

    @Test
    public void testGetOrderByIdSuccess() {
        int orderId = 1;
        Order order = new Order();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Order result = orderService.getOrderById(orderId);

        assertEquals(order, result);
    }

    @Test
    public void testGetOrderByIdFailure() {
        int orderId = 1;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(orderId));
    }

    @Test
    public void testGetOrdersWithItemsByBuyerId() {
        int buyerId = 1;
        Order order = new Order(); // Initialize and set order details if necessary
        OrderItem orderItem = new OrderItem(); // Initialize and set order item details if necessary
        List<Order> orders = Arrays.asList(order);
        List<OrderItem> orderItems = Arrays.asList(orderItem);

        when(orderRepository.findByBuyerId(buyerId)).thenReturn(orders);
        when(orderItemRepository.findByOrder(order)).thenReturn(orderItems);

        List<Order> result = orderService.getOrdersWithItemsByBuyerId(buyerId);

        assertEquals(orders, result);
        assertEquals(orderItems, result.get(0).getOrderItems());
    }
}