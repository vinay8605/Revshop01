<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>Forgot Password</title>
	<style>
		/* General Styles */
		body {
		    font-family: Arial, sans-serif;
		    background-color: #f3f3f3;
		    color: #333;
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    height: 100vh;
		    margin: 0;
		}

		/* Form Container */
		.form-container {
		    background-color: white;
		    padding: 40px 30px;
		    border-radius: 8px;
		    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
		    width: 100%;
		    max-width: 400px;
		    text-align: center;
		    box-sizing: border-box;
		}

		h2 {
		    font-size: 1.8em;
		    color: #2c3e50;
		    margin-bottom: 20px;
		}

		/* Form */
		form {
		    display: flex;
		    flex-direction: column;
		    gap: 15px;
		}

		/* Label and Input */
		label {
		    font-size: 1em;
		    color: #34495e;
		    text-align: left;
		}

		input[type="email"] {
		    width: 100%;
		    padding: 12px;
		    font-size: 1em;
		    color: #333;
		    border: 1px solid #ddd;
		    border-radius: 5px;
		    box-sizing: border-box;
		}

		/* Button */
		button {
		    padding: 12px;
		    font-size: 1em;
		    font-weight: bold;
		    color: white;
		    background-color: #2980b9;
		    border: none;
		    border-radius: 5px;
		    cursor: pointer;
		    transition: background-color 0.3s ease;
		}

		button:hover {
		    background-color: #3498db;
		}

		/* Success and Error Messages */
		.error, .success {
		    font-size: 1em;
		    margin-top: 10px;
		}

		.error {
		    color: red;
		}

		.success {
		    color: green;
		}


	</style>
</head>
<body>
	<div class="form-container">
    <h2>Forgot Password</h2>
    <form action="/buyer/send-otp" method="post">
        <label for="email">Enter your email:</label>
        <input type="email" id="email" name="email" required>
        <button type="submit">Send OTP</button>

        <c:if test="${not empty error}">
            <p style="color:red;">${error}</p>
        </c:if>
        
         <c:if test="${not empty success}">
            <div style="color: green;">${success}</div>
        </c:if>
    </form>
	</div>
</body>
</html>