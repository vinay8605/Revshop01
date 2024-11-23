<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Details</title>
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
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
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
        .product-img {
            width: 50px;
            height: 50px;
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
    <h2>Order Details for Order #${order.orderId}</h2>
    <div class="container">
        <table>
            <tr>
                <th>Product Name</th>
                <th>Product Image</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
            </tr>
            
            <!-- Loop through each order item to display product details -->
            <c:forEach var="item" items="${order.orderItems}">
                <tr>
                    <td>${item.product.name}</td>
                    <td><img src="${item.product.imageUrl}" alt="Product Image" class="product-img" /></td>
                    <td>&#8377; ${item.price}</td>
                    <td>${item.quantity}</td>
                    <td>&#8377; ${item.price * item.quantity}</td>
                </tr>
            </c:forEach>
        </table>
        
        <!-- Display total price -->
        <h3>Total Price: &#8377; ${order.totalPrice}</h3>
        
        <a href="${pageContext.request.contextPath}/buyer/dashboard" class="btn">Continue Shopping</a>
    </div>
</body>
</html>
