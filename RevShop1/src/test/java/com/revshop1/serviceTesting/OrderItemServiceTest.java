package com.revshop1.serviceTesting;

import com.revshop1.model.Order;
import com.revshop1.model.OrderItem;
import com.revshop1.repository.OrderItemRepository;
import com.revshop1.service.OrderItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OrderItemServiceTest {

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderItemService orderItemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOrderItemsByOrder() {
        Order order = new Order(); // Set order details if needed
        OrderItem item1 = new OrderItem(); // Initialize and set item1 details
        OrderItem item2 = new OrderItem(); // Initialize and set item2 details

        List<OrderItem> expectedItems = Arrays.asList(item1, item2);

        when(orderItemRepository.findByOrder(order)).thenReturn(expectedItems);

        List<OrderItem> actualItems = orderItemService.getOrderItemsByOrder(order);

        assertEquals(expectedItems, actualItems);
    }
}