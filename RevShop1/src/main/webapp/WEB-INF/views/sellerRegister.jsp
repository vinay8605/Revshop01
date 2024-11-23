<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seller Registration</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f9f9f9; /* Light Gray */
            color: #4a90e2; /* Light Blue */
            padding: 20px;
            margin: 0;
        }

        h2 {
            text-align: center;
            font-size: 2rem;
            margin-bottom: 20px;
            color: #87cefa; /* Lighter Blue */
        }

        /* Form Styles */
        .register-container {
            background-color: #ffffff; /* White */
            padding: 30px;
            border: 2px solid #87cefa; /* Lighter Blue Border */
            border-radius: 10px;
            max-width: 400px;
            margin: 0 auto;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); /* Subtle shadow */
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #4a90e2; /* Light Blue */
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #87cefa; /* Lighter Blue */
            border-radius: 5px;
            background-color: #f1f1f1; /* Light input background */
            color: #4a90e2; /* Light Blue */
            font-size: 1rem;
        }

        input::placeholder {
            color: #87cefa; /* Lighter Blue for placeholder */
            opacity: 0.7;
        }

        input:focus {
            outline: none;
            border-color: #87cefa; /* Lighter Blue on focus */
            box-shadow: 0 0 8px rgba(135, 206, 250, 0.8); /* Light blue shadow */
        }

        button {
            width: 100%;
            background-color: #87cefa; /* Lighter Blue */
            color: #1e1e1e; /* Dark Gray for button text */
            padding: 12px;
            font-size: 1.2rem;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        button:hover {
            background-color: #6bb3e4; /* Slightly darker blue on hover */
        }

        .success-message {
            color: #27ae60; /* Green for success */
            text-align: center;
            margin-top: 15px;
            font-weight: bold;
            font-size: 1.1em;
        }

        .login-link {
            text-align: center;
            margin-top: 15px;
        }

        .login-link a {
            color: #4a90e2; /* Light Blue */
            text-decoration: none;
            font-weight: bold;
        }

        .login-link a:hover {
            text-decoration: underline;
        }

        /* Responsive Design */
        @media (max-width: 600px) {
            .register-container {
                padding: 20px;
            }

            h2 {
                font-size: 1.8rem;
            }

            button {
                font-size: 1rem;
            }
        }
    </style>
</head>
<body>

<div class="register-container">
    <h2>Seller Registration</h2>
    
    <form:form action="/seller/register" modelAttribute="seller" method="post" class="register-form">
        <div class="form-group">
            <label for="businessName">Business Name:</label>
            <form:input path="businessName" id="businessName" cssClass="form-control" required="true" />
        </div>
        
        <div class="form-group">
            <label for="ownerFirstName">Owner First Name:</label>
            <form:input path="ownerFirstName" id="ownerFirstName" cssClass="form-control" required="true" />
        </div>

        <div class="form-group">
            <label for="ownerLastName">Owner Last Name:</label>
            <form:input path="ownerLastName" id="ownerLastName" cssClass="form-control" required="true" />
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <form:input path="email" id="email" cssClass="form-control" type="email" required="true" />
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <form:password path="password" id="password" cssClass="form-control" required="true" />
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary">Register</button>
        </div>
    </form:form>

    <c:if test="${param.success != null}">
        <p class="success-message">Seller registered successfully!</p>
    </c:if>

    <div class="login-link">
        <p>Already have an account? <a href="/seller/login">Login here</a></p>
    </div>
</div>

</body>
</html>