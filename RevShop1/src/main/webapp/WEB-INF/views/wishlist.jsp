<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.revshop1.model.Product" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Wishlist</title>
    <style>
        body {
            background-color: #f3f3f3;
            color: #333;
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        h1 {
            font-size: 2.5em;
            color: #34495e;
            margin-bottom: 20px;
            text-align: center;
        }

        /* Alert Message */
        .alert {
            padding: 10px;
            background-color: #dff0d8;
            color: #3c763d;
            border: 1px solid #d6e9c6;
            border-radius: 5px;
            margin-bottom: 20px;
            text-align: center;
        }

        /* Wishlist Container */
        .wishlist-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
        }

        /* Grid Styling */
        .wishlist-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr); /* Ensure 3 products per row */
            gap: 20px;
        }

        /* Product Card Styling */
        .product-card {
            background-color: #ffffff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s ease, box-shadow 0.2s ease;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 10px;
            text-align: center;
        }

        .product-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }

        /* Product Image */
        .product-img {
            width: 100%;
            height: 180px;
            object-fit: cover;
            border-radius: 5px;
            margin-bottom: 10px;
        }

        /* Product Details */
        .product-name {
            font-weight: bold;
            color: #2980b9;
            margin: 10px 0 5px;
            font-size: 1.1em;
        }

        .product-description {
            font-size: 0.9em;
            color: #666;
            margin-bottom: 8px;
        }

        .product-price {
            font-weight: bold;
            color: #e74c3c;
            font-size: 1.1em;
            margin-bottom: 15px;
        }

        /* Button Styling */
        .button {
            padding: 8px 15px;
            background-color: #2980b9;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s;
            text-align: center;
        }

        .button:hover {
            background-color: #3498db;
        }

        /* Back to Dashboard Link */
        .back-link {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: #2980b9;
            font-weight: bold;
            text-align: center;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="wishlist-container">
        <h1>Your Wishlist</h1>

        <% 
            String message = (String) request.getAttribute("message");
            if (message != null) { 
        %>
            <div class="alert alert-success"><%= message %></div>
        <% 
            } 
        %>

        <div class="wishlist-grid">
            <%
                List<Product> wishlistProducts = (List<Product>) request.getAttribute("wishlistProducts");
                if (wishlistProducts != null && !wishlistProducts.isEmpty()) {
                    for (Product product : wishlistProducts) {
            %>
            <div class="product-card">
                <img src="<%= product.getImageUrl() %>" alt="<%= product.getName() %>" class="product-img" />
                <div class="product-name"><%= product.getName() %></div>
                <div class="product-description"><%= product.getDescription() %></div>
                <div class="product-price">&#8377; <%= product.getPrice() %></div>
                <form action="/cart/add" method="post">
                    <input type="hidden" name="productId" value="<%= product.getProductId() %>"/>
                    <input type="hidden" name="quantity" value="1"/>
                    <button type="submit" class="button">Add to Cart</button>
                </form>
            </div>
            <%
                    }
                } else {
            %>
                <p style="text-align: center; font-size: 1.1em;">Your wishlist is empty.</p>
            <%
                }
            %>
        </div>

        <div style="text-align: center;">
            <a href="<%= request.getContextPath() %>/buyer/dashboard" class="back-link">Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
