<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
	<style>
		/* Global Styles */
		body {
		    font-family: 'Arial', sans-serif;
		    background-color: #f9f9f9; /* Light Gray */
		    color: #4a90e2; /* Light Blue */
		    margin: 0;
		    padding: 20px;
		    text-align: center; /* Center align content */
		}

		h2 {
		    color: #87cefa; /* Lighter Blue */
		    margin-bottom: 20px;
		}

		/* Form Styles */
		form {
		    background-color: #ffffff; /* White */
		    padding: 30px;
		    border: 1px solid #87cefa; /* Lighter Blue */
		    border-radius: 10px;
		    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
		    display: inline-block; /* Align form in the center */
		}

		label {
		    display: block;
		    margin-bottom: 8px;
		    color: #4a90e2; /* Light Blue */
		    font-weight: bold;
		}

		input[type="password"] {
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
		    background-color: #6bb3e4; /* Slightly darker blue */
		}

		.success-message {
		    color: green;
		    margin-top: 15px;
		}

		.error-message {
		    color: red;
		    margin-top: 15px;
		}
		
		/* Login Link */
		.login-link {
		    margin-top: 10px;
		}

		.login-link a {
		    font-size: 0.9em;
		    color: #2980b9;
		    text-decoration: none;
		    font-weight: bold;
		}

		.login-link a:hover {
		    color: #3498db;
		    text-decoration: underline;
		}


	</style>
</head>
<body>
    <h2>Reset Password</h2>

    <c:choose>
        <c:when test="${not empty sessionScope.email}">
            <p>Current Email: ${sessionScope.email}</p> <!-- For Debugging -->
        </c:when>
    </c:choose>

    <form action="/seller/reset-password" method="post">
        <label>New Password:</label>
        <input type="password" name="newPassword" required/><br/>
        <input type="submit" value="Update Password"/>
    </form>

    <c:if test="${not empty success}">
        <div style="color: green;">${success}</div>
		<div class="login-link">
			<a href="${pageContext.request.contextPath}/seller/login">Login Here</a>
		</div>

    </c:if>
    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>
</body>
</html>
