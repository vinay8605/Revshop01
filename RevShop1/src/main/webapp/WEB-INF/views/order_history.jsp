<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.revshop1.model.Order" %>
<%@ page import="com.revshop1.model.OrderItem" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Order History</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        h2 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 20px;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            border-radius: 15px;
            backdrop-filter: blur(10px) saturate(180%);
            background-color: rgba(255, 255, 255, 0.75);
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
        }
        .order-grid {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 20px;
        }
        .order-card {
            border-radius: 10px;
            overflow: hidden;
            background: rgba(255, 255, 255, 0.85);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
            transition: transform 0.2s ease, box-shadow 0.2s ease;
        }
        .order-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }
        .order-header {
            background-color: #3498db;
            color: white;
            font-weight: bold;
            text-align: center;
            padding: 10px;
        }
        .order-details {
            padding: 15px;
            display: flex;
            flex-direction: column;
            gap: 8px;
        }
        .order-date, .order-price {
            font-size: 0.9em;
            color: #2c3e50;
            text-align: left;
        }
        .order-price {
            font-weight: bold;
            color: #e74c3c;
            font-size: 1.1em;
        }
        .order-items {
            padding: 15px;
        }
        .order-item {
            display: flex;
            align-items: center;
            margin-top: 10px;
            border-top: 1px solid #eee;
            padding-top: 10px;
        }
        .product-img {
            width: 50px;
            height: 50px;
            border-radius: 5px;
            margin-right: 10px;
        }
        .btn {
            display: block;
            max-width: 200px;
            margin: 20px auto;
            padding: 10px;
            background-color: #2980b9;
            color: white;
            text-align: center;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            font-weight: bold;
            transition: background-color 0.3s;
        }
        .btn:hover {
            background-color: #3498db;
        }

        @media (max-width: 900px) {
            .order-grid {
                grid-template-columns: repeat(2, 1fr);
            }
        }
        @media (max-width: 600px) {
            .order-grid {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Order History</h2>

        <%
            List<Order> orders = (List<Order>) session.getAttribute("orders");
            if (orders != null && !orders.isEmpty()) {
        %>
            <div class="order-grid">
                <%
                    for (Order order : orders) {
                        List<OrderItem> orderItems = order.getOrderItems();
                %>
                <div class="order-card">
                    <div class="order-header">Order #<%= order.getOrderId() %></div>
                    <div class="order-details">
                        <div class="order-date">Date: <%= order.getCreatedAt() %></div>
                        <div class="order-price">Total: &#8377; <%= order.getTotalPrice() %></div>
                    </div>
                    <div class="order-items">
                        <%
                            for (OrderItem item : orderItems) {
                        %>
                        <div class="order-item">
                            <img src="<%= item.getProduct().getImageUrl() %>" alt="Product Image" class="product-img" />
                            <span><%= item.getProduct().getName() %></span>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        <%
            } else {
        %>
            <p>No orders found.</p>
        <%
            }
        %>
        <a href="<%= request.getContextPath() %>/buyer/dashboard" class="btn">Continue Shopping</a>
    </div>
</body>
</html>
