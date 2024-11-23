package com.revshop1.controller;


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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class OrderController {

	
	
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private CartService cartService;
    
    @Autowired
    private MailService mailService;
    
    @GetMapping("/order/confirmation")
    public String showOrderConfirmationPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Integer orderId = (Integer) session.getAttribute("orderId");
        Order order = orderService.getOrderById(orderId);
        
        if (order == null) {
            return "error"; 
        }

        
        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrder(order);
        model.addAttribute("order", order);
        model.addAttribute("orderItems", orderItems);
        
        session.setAttribute("orderItems", orderItems);

        session.removeAttribute("orderId");

        return "order_success"; 
    }
    
    @GetMapping("/checkout")
    public String showCheckoutPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        BigDecimal totalPrice = (BigDecimal) session.getAttribute("totalPrice");
        model.addAttribute("totalPrice", totalPrice); 
        return "checkout"; 
    }

    @PostMapping("/checkout/submit")
    public void checkout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Integer buyerId = (Integer) session.getAttribute("buyerId");

        if (buyerId == null) {
            response.sendRedirect("/error"); 
            return;
        }

        
        BigDecimal deliveryFee = new BigDecimal("5.00"); 
        BigDecimal tax = new BigDecimal("2.00");

        
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String pincode = request.getParameter("pincode");
        String paymentType = request.getParameter("paymentType");

       
        BigDecimal totalOrderPrice = (BigDecimal) session.getAttribute("finalTotal");

        
        if (totalOrderPrice == null) {
            totalOrderPrice = BigDecimal.ZERO; 
        }

        Timestamp createdAt = new Timestamp(System.currentTimeMillis());

        
        Order order = new Order();
        order.setBuyerId(buyerId);
        order.setPaymentId("PAYMENT123"); 
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setEmail(email);
        order.setPhone(phone);
        order.setAddress(address);
        order.setCity(city);
        order.setState(state);
        order.setPincode(pincode);
        order.setPaymentType(Order.PaymentType.valueOf(paymentType));
        order.setTotalPrice(totalOrderPrice.add(deliveryFee).add(tax)); 
        order.setDeliveryFee(deliveryFee); 
        order.setTax(tax); 
        order.setCreatedAt(createdAt);

        
        Order savedOrder = orderService.saveOrder(order);
        
        session.setAttribute("orderId", order.getOrderId());
        
        
        request.setAttribute("orderId", savedOrder.getOrderId()); 
        request.setAttribute("totalAmount", savedOrder.getTotalPrice()); 
        request.setAttribute("paymentMethod", paymentType);
        request.setAttribute("deliveryAddress", address + ", " + city + ", " + state + ", " + pincode); 

        
        List<Product> cartProducts = cartService.getProductsInCart(buyerId);
        for (Product product : cartProducts) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder); 
            orderItem.setProduct(product);
            orderItem.setQuantity((Integer) session.getAttribute("cartQuantity"));
            orderItem.setPrice(product.getPrice());
            orderService.saveOrderItem(orderItem);
        }

        
        cartService.clearCart(buyerId);
        
        mailService.sendOrderConfirmationEmail(email, savedOrder.getOrderId());

        
        response.sendRedirect(request.getContextPath() + "/order/confirmation");
    }


    @GetMapping("/history")
    public String showOrderHistory(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Integer buyerId = (Integer) session.getAttribute("buyerId");

        if (buyerId == null) {
            return "redirect:/buyer/login";
        }

        List<Order> orders = orderService.getOrdersWithItemsByBuyerId(buyerId);
        
        session.setAttribute("orders", orders);

        model.addAttribute("orders", orders);

        return "order_history";
    }
    
    @GetMapping("/order/details/{orderId}")
    public String showOrderDetails(@PathVariable("orderId") Integer orderId, Model model) {
        Order order = orderService.getOrderById(orderId);  
        if (order == null) {
            return "error";  
        }
        model.addAttribute("order", order);  
        return "order_details";  
    }
    
    @GetMapping("/seller/orders")
    public String getAllOrders(HttpSession session, Model model) {
        List<Order> orders = orderService.getAllOrders();
        session.setAttribute("orders", orders); 
        model.addAttribute("orders", orders); 
        return "sellerOrders"; 
    }



}
