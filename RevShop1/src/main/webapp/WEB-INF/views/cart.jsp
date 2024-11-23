<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.revshop1.model.Cart" %>
<%@ page import="com.revshop1.model.Product" %>
<%@ page import="java.math.BigDecimal" %>
<html>
<head>
    <title>Your Cart</title>
    <style>
		* {
		            margin: 0;
		            padding: 0;
		            box-sizing: border-box;
		            font-family: 'Arial', sans-serif;
		        }

		        body {
		            background-color: #f3f4f6;
		            color: #333;
		            padding: 20px;
		        }
				
				.navbar {
				    background-color: #2c3e50;
				    color: white;
				    display: flex;
				    justify-content: space-between;
				    align-items: center;
				    padding: 15px 20px;
				    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
				}

				.navbar a {
				    color: #ecf0f1;
				    text-decoration: none;
				    margin: 0 15px;
				    font-weight: bold;
				    position: relative;
				    transition: color 0.3s;
				}

				.navbar a:hover {
				    color: #f39c12;
				}

				.navbar a::after {
				    content: "";
				    position: absolute;
				    left: 0;
				    bottom: -5px;
				    width: 0%;
				    height: 2px;
				    background-color: #f39c12;
				    transition: width 0.3s;
				}

				.navbar a:hover::after {
				    width: 100%;
				}

				.navbar .logo {
				    font-size: 1.8em;
				    font-weight: bold;
				}

				.navbar .nav-links {
				    display: flex;
				}

		        h2 {
		            text-align: center;
		            margin-bottom: 20px;
		            color: #2c3e50;
		            font-size: 2em;
		        }

		        /* Cart Container */
		        .cart-container {
		            display: grid;
		            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
		            gap: 20px;
		            margin: 0 auto;
		            max-width: 1200px;
		        }

		        /* Cart Item Styles */
		        .cart-item {
		            background-color: white;
		            border-radius: 10px;
		            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
		            overflow: hidden;
		            transition: transform 0.3s, box-shadow 0.3s;
		            text-align: center;
		            padding: 15px;
		            animation: fadeIn 0.5s ease-in-out;
		        }

		        .cart-item:hover {
		            transform: scale(1.05);
		            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
		        }

		        .cart-item img {
		            max-width: 100%;
		            height: 200px;
		            object-fit: cover;
		            margin-bottom: 10px;
		            border-radius: 8px;
		        }

		        .cart-item h3 {
		            font-size: 1.6em;
		            color: #34495e;
		            margin-bottom: 5px;
		        }

		        .cart-item p {
		            font-size: 1em;
		            color: #7f8c8d;
		            margin-bottom: 10px;
		        }

		        /* Remove Button */
		        .remove-button {
		            background-color: #e74c3c;
		            color: white;
		            border: none;
		            padding: 10px 15px;
		            cursor: pointer;
		            border-radius: 5px;
		            transition: background-color 0.3s ease;
		        }

		        .remove-button:hover {
		            background-color: #c0392b;
		        }

		        /* Total Price Section */
		        .total-price {
		            font-size: 1.8em;
		            font-weight: bold;
		            text-align: center;
		            margin-top: 20px;
		            color: #2ecc71;
		        }

		        /* Checkout Button */
		        .checkout-button {
		            display: block;
		            width: 100%;
		            max-width: 300px;
		            margin: 20px auto;
		            padding: 15px;
		            background-color: #2980b9;
		            color: white;
		            border: none;
		            border-radius: 5px;
		            font-size: 1.2em;
		            cursor: pointer;
		            transition: background-color 0.3s ease, transform 0.2s;
		            text-align: center;
		            text-decoration: none;
		        }

		        .checkout-button:hover {
		            background-color: #3498db;
		            transform: scale(1.05);
		        }

		        /* Animation */
		        @keyframes fadeIn {
		            from {
		                opacity: 0;
		                transform: translateY(10px);
		            }
		            to {
		                opacity: 1;
		                transform: translateY(0);
		            }
		        }

		        /* Empty Cart Message */
		        .empty-cart {
		            grid-column: span 4;
		            text-align: center;
		            font-size: 1.5em;
		            color: #95a5a6;
		            padding: 40px 0;
		        }

		        /* Responsive Design */
		        @media (max-width: 768px) {
		            .cart-container {
		                grid-template-columns: 1fr;
		            }

		            .checkout-button {
		                width: 90%;
		            }
		        }
    </style>
</head>
<body>

    
    <div class="cart-container">
        <%
            List<Cart> cartItems = (List<Cart>) request.getAttribute("cartItems");
            BigDecimal totalPrice = BigDecimal.ZERO;

            // Check if the cartItems are not empty
            if (cartItems != null && !cartItems.isEmpty()) {

                // Store the cart in the session
                session.setAttribute("cart", cartItems);

                for (Cart item : cartItems) {
                    Product product = item.getProduct();
                    BigDecimal itemTotal = product.getPrice().multiply(new BigDecimal(item.getQuantity()));
                    totalPrice = totalPrice.add(itemTotal);
        %>
		<div class="cart-item">
		    <h3><%= product.getName() %></h3>
		    <img src="<%= product.getImageUrl() %>" alt="<%= product.getName() %>" />
		    <p>Price: ₹<%= product.getPrice() %></p>
		    <p>Quantity: <%= item.getQuantity() %></p>
		    <form action="/cart/remove/<%= item.getCartId() %>" method="post">
		        <input type="submit" value="Remove" class="remove-button"/>
		    </form>
		</div>
        <%
                }
        %>
        </div>
        <div class="total-price">
            Total Price: ₹<%= totalPrice %>
        </div>
		
		<form action="${pageContext.request.contextPath}/cart/checkout" method="get">
		    <input type="hidden" name="totalPrice" value="<%= totalPrice %>" />
		    <input type="submit" value="Proceed to Checkout" class="checkout-button"/>
		</form>

       <!-- <form action="/checkout" method="post">  Ensure this points to the correct endpoint 
            <input type="submit" value="Proceed to Checkout" class="checkout-button"/>
        </form>-->
        <%
            } else {
        %>
        <div class="empty-cart" >
            <p>Your cart is empty.</p>
        </div>
        <%
            }
        %>
    </div>
</body>
</html>
