<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Reset Password</title>
	<style>
		/* Container for centering the form */
		.form-container {
		    max-width: 400px;
		    margin: 0 auto;
		    padding: 20px;
		    background-color: #f9f9f9;
		    border-radius: 8px;
		    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
		    text-align: center;
		}

		/* Heading */
		h2 {
		    color: #333;
		    font-size: 1.5em;
		    margin-bottom: 20px;
		    text-align: center;
		}

		/* Labels */
		label {
		    display: block;
		    font-weight: bold;
		    color: #555;
		    margin-bottom: 8px;
		    text-align: left;
		}

		/* Input Fields */
		input[type="password"] {
		    width: 100%;
		    padding: 10px;
		    margin-bottom: 20px;
		    border: 1px solid #ddd;
		    border-radius: 4px;
		    font-size: 1em;
		}

		/* Submit Button */
		button[type="submit"] {
		    width: 100%;
		    padding: 10px;
		    background-color: #28a745;
		    color: #fff;
		    font-size: 1em;
		    border: none;
		    border-radius: 4px;
		    cursor: pointer;
		    font-weight: bold;
		}

		button[type="submit"]:hover {
		    background-color: #218838;
		}

		/* Success and Error Messages */
		.success {
		    color: #28a745;
		    font-weight: bold;
		    margin-top: 10px;
		}

		.error {
		    color: #d9534f;
		    font-weight: bold;
		    margin-top: 10px;
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
	<div class="form-container">
    <h2>Reset Password</h2>
    
    <c:choose>
        <c:when test="${not empty sessionScope.email}">
            <p>Current Email: ${sessionScope.email}</p> <!-- For Debugging -->
        </c:when>
    </c:choose>
    
    <form action="/buyer/reset-password" method="post">
    <input type="hidden" name="email" value="${email}">

    <label for="newPassword">New Password:</label>
    <input type="password" id="newPassword" name="newPassword" required>

    <button type="submit">Reset Password</button>

     <c:if test="${not empty success}">
        <div style="color: green;">${success}</div>
		<div class="login-link">
			<a href="${pageContext.request.contextPath}/buyer/login">Login Here</a>
		</div>
    </c:if>
    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>
</form>
</div>

</body>
</html>