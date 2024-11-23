package com.revshop1.controller;

import com.revshop1.model.Order;
import com.revshop1.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Controller
public class CheckoutController {

   /* @Autowired
    private OrderService orderService;

    @GetMapping("/checkout")
    public String showCheckoutPage(HttpServletRequest request, Model model) {
        
        HttpSession session = request.getSession();
        BigDecimal totalPrice = (BigDecimal) session.getAttribute("totalPrice");
        model.addAttribute("totalPrice", totalPrice);
        return "checkout"; 
    }

    @PostMapping("/checkout/submit")
    protected void checkout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        int buyerId = (int) session.getAttribute("buyerId"); 
        String paymentId = "PAYMENT123"; 

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        BigDecimal totalPrice = new BigDecimal(request.getParameter("totalPrice"));
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());

        Order order = new Order();
        order.setBuyerId(buyerId);
        order.setPaymentId(paymentId);
        order.setFirstName(name);
        order.setEmail(email);
        order.setPhone(phone);
        order.setAddress(address);
        order.setTotalPrice(totalPrice);
        order.setCreatedAt(createdAt);

        orderService.saveOrder(order);

        response.sendRedirect("/order/confirmation");
    }*/
}
