<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.revshop1.model.Buyer" %>

<html>
<head>
    <title>Buyer Profile</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f9f9f9;
        }

        .container {
            width: 50%;
            margin: 50px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        input[type="text"], input[type="email"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .button {
            padding: 10px 20px;
            background-color: #2980b9;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-right: 10px;
        }

        .button:hover {
            background-color: #3498db;
        }

        .actions {
            margin-top: 20px;
        }

        .success-message {
            color: green;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .error-message {
            color: red;
            font-weight: bold;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Buyer Profile</h1>

        <%-- Display success message if available --%>
        <c:if test="${not empty success}">
            <div class="success-message">${success}</div>
        </c:if>

        <%-- Display error message if available --%>
        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <form action="/buyer/profile/update" method="post">
            <input type="hidden" name="buyerId" value="<%= ((Buyer) request.getAttribute("buyer")).getBuyerId() %>"/>

            <label>First Name:</label>
            <input type="text" name="firstName" value="<%= ((Buyer) request.getAttribute("buyer")).getFirstName() %>"/>

            <label>Last Name:</label>
            <input type="text" name="lastName" value="<%= ((Buyer) request.getAttribute("buyer")).getLastName() %>"/>

            <label>Email:</label>
            <input type="email" name="email" value="<%= ((Buyer) request.getAttribute("buyer")).getEmail() %>"/>

            <div class="actions">
                <button type="submit" class="button">Update</button>
                <button type="button" class="button" onclick="window.location.href='/buyer/dashboard'">Back to Dashboard</button>
            </div>
        </form>
    </div>
</body>
</html>