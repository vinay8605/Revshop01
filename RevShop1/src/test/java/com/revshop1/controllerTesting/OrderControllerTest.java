package com.revshop1.controllerTesting;

import com.revshop1.controller.OrderController;
import com.revshop1.model.Order;
import com.revshop1.model.OrderItem;
import com.revshop1.model.Product;
import com.revshop1.service.OrderService;
import com.revshop1.service.CartService;
import com.revshop1.service.MailService;
import com.revshop1.service.OrderItemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private OrderItemService orderItemService;

    @Mock
    private CartService cartService;

    @Mock
    private MailService mailService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowOrderConfirmationPage() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("orderId")).thenReturn(1);
        
        Order order = new Order();
        order.setOrderId(1);
        when(orderService.getOrderById(1)).thenReturn(order);

        List<OrderItem> orderItems = new ArrayList<>();
        when(orderItemService.getOrderItemsByOrder(order)).thenReturn(orderItems);

        String viewName = orderController.showOrderConfirmationPage(request, model);

        verify(model).addAttribute("order", order);
        verify(model).addAttribute("orderItems", orderItems);
        verify(session).removeAttribute("orderId");
        assertEquals("order_success", viewName);
    }

    @Test
    void testShowCheckoutPage() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("totalPrice")).thenReturn(BigDecimal.valueOf(100.00));

        String viewName = orderController.showCheckoutPage(request, model);

        verify(model).addAttribute("totalPrice", BigDecimal.valueOf(100.00));
        assertEquals("checkout", viewName);
    }

//    @Test
//    void testCheckoutWithValidBuyerId() throws IOException {
//        when(request.getSession()).thenReturn(session);
//        when(session.getAttribute("buyerId")).thenReturn(1);
//        when(session.getAttribute("finalTotal")).thenReturn(BigDecimal.valueOf(150.00));
//        when(request.getParameter("firstName")).thenReturn("John");
//        when(request.getParameter("lastName")).thenReturn("Doe");
//        when(request.getParameter("email")).thenReturn("john.doe@example.com");
//        when(request.getParameter("paymentType")).thenReturn("CREDIT");
//
//        List<Product> cartProducts = new ArrayList<>();
//        Product product = new Product();
//        product.setPrice(BigDecimal.valueOf(50.00));
//        cartProducts.add(product);
//
//        when(cartService.getProductsInCart(1)).thenReturn(cartProducts);
//
//        Order savedOrder = new Order();
//        savedOrder.setOrderId(123);
//        when(orderService.saveOrder(any(Order.class))).thenReturn(savedOrder);
//
//        orderController.checkout(request, response);
//
//        verify(orderService).saveOrder(any(Order.class));
//        verify(cartService).clearCart(1);
//        verify(mailService).sendOrderConfirmationEmail("john.doe@example.com", 123);
//        verify(response).sendRedirect(request.getContextPath() + "/order/confirmation");
//    }

    @Test
    void testCheckoutWithoutBuyerId() throws IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("buyerId")).thenReturn(null);

        orderController.checkout(request, response);

        verify(response).sendRedirect("/error");
    }

    @Test
    void testShowOrderHistoryWithLoggedInBuyer() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("buyerId")).thenReturn(1);

        List<Order> orders = new ArrayList<>();
        when(orderService.getOrdersWithItemsByBuyerId(1)).thenReturn(orders);

        String viewName = orderController.showOrderHistory(request, model);

        verify(session).setAttribute("orders", orders);
        verify(model).addAttribute("orders", orders);
        assertEquals("order_history", viewName);
    }

    @Test
    void testShowOrderHistoryWithoutLoggedInBuyer() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("buyerId")).thenReturn(null);

        String viewName = orderController.showOrderHistory(request, model);

        assertEquals("redirect:/login", viewName);
    }

    @Test
    void testShowOrderDetailsWithValidOrder() {
        Order order = new Order();
        order.setOrderId(1);
        when(orderService.getOrderById(1)).thenReturn(order);

        String viewName = orderController.showOrderDetails(1, model);

        verify(model).addAttribute("order", order);
        assertEquals("order_details", viewName);
    }

    @Test
    void testShowOrderDetailsWithInvalidOrder() {
        when(orderService.getOrderById(1)).thenReturn(null);

        String viewName = orderController.showOrderDetails(1, model);

        assertEquals("error", viewName);
    }
}
