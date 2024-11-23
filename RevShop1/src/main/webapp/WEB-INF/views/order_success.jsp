<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.revshop1.model.Order" %>
<%@ page import="com.revshop1.model.OrderItem" %>

<html>
<head>
    <title>Order Confirmation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f3f4f6;
            padding: 20px;
        }
        h2 {
            text-align: center;
            color: #2c3e50;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .order-details, .order-items {
            margin-top: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table th, table td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }
        table th {
            background-color: #2980b9;
            color: white;
        }
        .btn {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #2980b9;
            color: white;
            text-align: center;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
            text-decoration: none;
        }
        .btn:hover {
            background-color: #3498db;
        }
    </style>
</head>
<body>
    <h2>Order Confirmation</h2>
    <div class="container">
        <p>Your order has been placed successfully! Thank you for shopping with us.</p>
        
        <!-- Order details section -->
        <div class="order-details">
            <h3>Order Details</h3>
            <table>
                <tr>
                    <th>Order ID</th>
                    <td>${order.orderId != null ? order.orderId : 'N/A'}</td>
                </tr>
                <tr>
                    <th>Total Amount</th>
                    <td>&#8377; ${sessionScope.finalTotal != null ? sessionScope.finalTotal : '0'}</td>
                </tr>
                <tr>
                    <th>Payment Method</th>
                    <td>${order.paymentType != null ? order.paymentType : 'N/A'}</td>
                </tr>
                <tr>
                    <th>Delivery Address</th>
                    <td>${order.address != null ? order.address : 'N/A'}</td>
                </tr>
            </table>
        </div>
        
        <!-- Order items section -->
        <div class="order-items">
            <h3>Items Purchased</h3>
            <table>
                <tr>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
                <%
                    List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("orderItems");
                    if (orderItems != null && !orderItems.isEmpty()) {
                        for (OrderItem item : orderItems) {
                %>
                    <tr>
                        <td><%= item.getProduct().getName() %></td>
                        <td><%= item.getQuantity() %></td>
                        <td>&#8377; <%= item.getPrice() %></td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="3">No items found.</td>
                    </tr>
                <%
                    }
                %>
            </table>
        </div>
        
        <a href="${pageContext.request.contextPath}/buyer/dashboard" class="btn">Continue Shopping</a>
    </div>
</body>
</html>
