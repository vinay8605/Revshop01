<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password</title>
    <style>
        /* Your existing styles */
		/* Global Styles */
		body {
		    font-family: 'Arial', sans-serif;
		    background-color: #f9f9f9; /* Light Gray */
		    color: #4a90e2; /* Light Blue */
		    margin: 0;
		    padding: 20px;
		    text-align: center; /* Center align content */
		}

		.login-container {
		    background-color: #ffffff; /* White */
		    padding: 30px;
		    border: 1px solid #87cefa; /* Lighter Blue */
		    border-radius: 10px;
		    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
		    display: inline-block; /* Center form container */
		    max-width: 400px; /* Max width for the container */
		    margin: auto; /* Center horizontally */
		}

		h2 {
		    color: #87cefa; /* Lighter Blue */
		    margin-bottom: 20px;
		}

		/* Form Styles */
		label {
		    display: block;
		    margin-bottom: 8px;
		    color: #4a90e2; /* Light Blue */
		    font-weight: bold;
		}

		input[type="email"] {
		    width: 100%;
		    padding: 10px;
		    margin-bottom: 20px;
		    border: 1px solid #87cefa; /* Lighter Blue */
		    border-radius: 5px;
		    background-color: #f1f1f1; /* Light input background */
		    color: #1e1e1e; /* Dark Gray */
		    font-size: 1rem;
		}

		input[type="submit"] {
		    width: 100%;
		    background-color: #87cefa; /* Lighter Blue */
		    color: #1e1e1e; /* Dark Gray */
		    padding: 12px;
		    font-size: 1.2rem;
		    font-weight: bold;
		    border: none;
		    border-radius: 5px;
		    cursor: pointer;
		    transition: background-color 0.3s ease, transform 0.2s ease;
		}

		input[type="submit"]:hover {
		    background-color: #6bb3e4; 
		}

		
		.success-message {
		    color: green;
		    margin-top: 15px;
		}

		.error-message {
		    color: red;
		    margin-top: 15px;
		}

    </style>
</head>
<body>
    <div class="login-container">
        <h2>Forgot Password</h2>
        <form action="/seller/send-otp" method="post">
            <label>Email:</label>
            <input type="email" name="email" required/><br/>
            <input type="submit" value="Send OTP"/>
        </form>

        <c:if test="${not empty error}">
            <div style="color: red;">${error}</div>
        </c:if>

        <c:if test="${not empty success}">
            <div style="color: green;">${success}</div>
        </c:if>
    </div>
</body>
</html>