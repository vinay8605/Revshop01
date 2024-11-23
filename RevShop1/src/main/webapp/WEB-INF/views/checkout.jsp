<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.math.RoundingMode" %>
<html>
<head>
    <title>Checkout</title>
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
        .row {
            display: flex;
            justify-content: space-between;
        }
        .col {
            width: 48%;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-control {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .checkout-button {
            background-color: #2980b9;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            width: 100%;
            margin-top: 20px;
        }
        .checkout-button:hover {
            background-color: #3498db;
        }
        .order-summary {
            background-color: #f9f9f9;
            padding: 10px;
            border-radius: 5px;
        }
        .order-summary table {
            width: 100%;
        }
        .order-summary table td {
            padding: 5px;
        }
    </style>
</head>
<body>
    <h2>Checkout</h2>
    <div class="container">
		<form action="${pageContext.request.contextPath}/checkout/submit" method="post">
		    <div class="row">
		        <!-- Billing Information -->
		        <div class="col">
		            <p class="text-center fs-4">Billing Address</p>
		            <hr>
		            <div class="form-group">
		                <label>First Name</label>
		                <input type="text" name="firstName" required class="form-control">
		            </div>
		            <div class="form-group">
		                <label>Last Name</label>
		                <input type="text" name="lastName" required class="form-control">
		            </div>
		            <div class="form-group">
		                <label>Email</label>
		                <input type="email" name="email" required class="form-control">
		            </div>
		            <div class="form-group">
		                <label>Mobile Number</label>
		                <input type="text" name="phone" required class="form-control">
		            </div>
		            <div class="form-group">
		                <label>Address</label>
		                <input type="text" name="address" required class="form-control">
		            </div>
		            <div class="form-group">
		                <label>City</label>
		                <input type="text" name="city" required class="form-control">
		            </div>
		            <div class="form-group">
		                <label>State</label>
		                <input type="text" name="state" required class="form-control">
		            </div>
		            <div class="form-group">
		                <label>Pincode</label>
		                <input type="number" name="pincode" required class="form-control">
		            </div>
		        </div>

		        <!-- Payment & Order Summary -->
		        <div class="col">
		            <p class="text-center fs-4">Payment Type</p>
		            <hr>
		            <div class="form-group">
		                <label>Select Payment Type</label>
		                <select required name="paymentType" class="form-control">
		                    <option value="">--select--</option>
		                    <option value="COD">Cash On Delivery</option>
		                    <option value="ONLINE">Online Payment</option>
		                </select>
		            </div>

		            <div class="order-summary">
		                <table>
		                    <tr>
		                        <td>Total Price</td>
		                        <td>:</td>
		                        <td>&#8377; <%=  request.getParameter("totalPrice") != null ?  request.getParameter("totalPrice") : "0" %></td>
		                    </tr>
		                    <tr>
		                        <td>Delivery Fee</td>
		                        <td>:</td>
		                        <td>&#8377; 250</td>
		                    </tr>
		                    <tr>
		                        <td>Tax</td>
		                        <td>:</td>
		                        <td>&#8377; 100</td>
		                    </tr>
		                    <tr>
		                        <td class="border-top">Total Price</td>
		                        <td>:</td>
								<td>
								     <%
								        BigDecimal deliveryFee = new BigDecimal("250");
								        BigDecimal tax = new BigDecimal("100");
								        String totalPriceStr = request.getParameter("totalPrice");
								        BigDecimal totalPrice = (totalPriceStr != null && !totalPriceStr.isEmpty())
								             ? new BigDecimal(totalPriceStr)
								             : BigDecimal.ZERO;
								        BigDecimal finalTotal = totalPrice.add(deliveryFee).add(tax);
								        finalTotal = finalTotal.setScale(2, RoundingMode.HALF_UP);
										session.setAttribute("finalTotal", finalTotal);
								      %>
						         &#8377; <%= finalTotal %>
								</td>
		                    </tr>
		                </table>
		            </div>

		            <input 
		                type="submit" 
		                value="Place Order" 
		                class="checkout-button" 
		            >
		        </div>
		    </div>
		</form>

    </div>
</body>
</html>
