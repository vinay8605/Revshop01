<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.revshop1.model.Order" %>
<%@ page import="com.revshop1.model.OrderItem" %>
<%@ page import="com.revshop1.model.Product" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.math.BigDecimal" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Orders</title>
    <style>
        /* Basic styling for the orders page */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f9f9f9;
            color: #333;
            margin: 0;
            padding: 20px;
        }

        h1 {
            color: #4a90e2;
            text-align: center;
            margin-bottom: 30px;
        }

        /* Orders Table */
        .orders-table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }

        .orders-table th, .orders-table td {
            padding: 12px 15px;
            border: 1px solid #ddd;
            text-align: left;
        }

        .orders-table th {
            background-color: #4a90e2;
            color: white;
        }

        .orders-table tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        /* Order Details */
        .order-detail {
            font-weight: bold;
            color: #333;
        }

        /* Product image */
        .product-image {
            width: 60px;
            height: 60px;
            object-fit: cover;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <h1>All Orders</h1>

    <% 
        List<Order> orders = (List<Order>) session.getAttribute("orders");
        if (orders != null && !orders.isEmpty()) {
    %>
            <table class="orders-table">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th>Total Price</th>
                        <th>Order Date</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        for (Order order : orders) {
                            // Assuming Order has a method to get its items
                            List<OrderItem> orderItems = order.getOrderItems(); // Get list of OrderItems
                            for (OrderItem orderItem : orderItems) {
                                Product product = orderItem.getProduct(); // Assuming OrderItem has getProduct method
                                // You might also have a way to get the buyer from the order
                    %>
                        <tr>
                            <td><%= order.getOrderId() %></td>
                            <td>
                                <img src="<%= product.getImageUrl() %>" alt="Product Image" class="product-image">
                                <div class="order-detail"><%= product.getName() %></div>
                            </td>
                            
                            <td><%= orderItem.getQuantity() %></td>
                            <td>$<%= orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity())) %></td> <!-- Calculate total price -->
                            <td><%= dateFormat.format(order.getCreatedAt()) %></td>
                        </tr>
                    <% 
                            }
                        } 
                    %>
                </tbody>
            </table>
    <% } else { %>
            <p>No orders found.</p>
    <% } %>
</body>
</html>
