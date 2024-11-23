<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Seller Login</title>
    <style>
		/* Global Styles */
		body {
		    font-family: 'Arial', sans-serif;
		    background-color: #f9f9f9;
		    color: #4a90e2;
		    padding: 20px;
		    margin: 0;
			background-image: url("https://img.freepik.com/free-photo/black-friday-elements-assortment_23-2149074076.jpg?t=st=1730027959~exp=1730031559~hmac=2316ecc427b8eb18cda80f3ba97f100effd3772502411a62cff5126f36b2bcb7&w=1380");
			background-size: cover;
			background-position: center;
		}

		h2 {
		    text-align: center;
		    font-size: 2rem;
		    margin-bottom: 20px;
		    color: #ffffff;
		}
		
		.login-container {
		    max-width: 400px;
		    margin: 0 auto;
		    padding: 20px;
		    background-color: white;
		    border-radius: 8px;
		    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
		}
		.login-container h2 {
		    text-align: center;
		    color: #333;
		}
		.form-field {
		    margin-bottom: 15px;
		}
		.form-field label {
		    display: block;
		    margin-bottom: 5px;
		    color: #333;
		}
		.form-field input {
		    width: 100%;
		    padding: 8px;
		    border: 1px solid #ddd;
		    border-radius: 4px;
		}
		.submit-btn {
		    width: 100%;
		    padding: 10px;
		    background-color: #4a90e2;
		    color: white;
		    border: none;
		    border-radius: 4px;
		    cursor: pointer;
		}
		.submit-btn:hover {
		    background-color: #357ABD;
		}

		/* Glassmorphic Form Styles */
		.login-container {
		    /*backdrop-filter: blur(2px) saturate(180%);*/
		    background-color: rgba(255, 255, 255, 0.2);
		    padding: 30px;
		    border: 2px solid rgba(255, 255, 255, 0.3);
		    border-radius: 10px;
		    max-width: 400px;
		    margin: 50px auto;
		    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
		    transition: transform 0.3s ease;
		}

		.login-container:hover {
		    transform: scale(1.02);
		}

		.form-group {
		    margin-bottom: 15px;
		}

		label {
		    display: block;
		    margin-bottom: 8px;
		    color: #000000;
		    font-weight: bold;
		}

		input {
		    width: 100%;
		    padding: 10px;
		    margin-bottom: 20px;
		    border: 1px solid rgba(255, 255, 255, 0.3);
		    border-radius: 5px;
		    background-color: rgba(255, 255, 255, 0.1);
		    color: #000;
		    font-size: 1rem;
		}

		input::placeholder {
		    color: rgba(0, 0, 0, 0.7);
		    opacity: 0.7;
		}

		input:focus {
		    outline: none;
		    border-color: #87cefa;
		    box-shadow: 0 0 8px rgba(135, 206, 250, 0.8);
		    transition: box-shadow 0.3s ease;
		}

		/* Password Animation */
		input[type="password"] {
		    position: relative;
		    animation: passwordBounce 1s infinite alternate;
		}

		@keyframes passwordBounce {
		    from {
		        transform: translateY(0px);
		    }
		    to {
		        transform: translateY(-5px);
		    }
		}

		input[type="submit"] {
		    width: 100%;
		    background-color: #87cefa;
		    color: #1e1e1e;
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

		.error-message {
		    color: red;
		    text-align: center;
		    margin-top: 15px;
		}

		/* Responsive Design */
		@media (max-width: 600px) {
		    .login-container {
		        padding: 20px;
		    }

		    h2 {
		        font-size: 1.8rem;
		    }

		    input[type="submit"] {
		        font-size: 1rem;
		    }
		}
		.login-actions {
		    display: flex;
		    justify-content: space-between;
		    margin-top: 15px;
		}

		.login-actions a {
		    color: #000; /* Change to preferred color */
		    text-decoration: none;
		    font-weight: bold;
		    padding: 8px 12px;
		    border-radius: 5px;
		    transition: background-color 0.3s ease;
		}

		.login-actions a:hover {
		    background-color: #f0f8ff; /* Slightly different shade on hover */
		}


    </style>
</head>
<body>
    <div class="login-container">
        <h2>Seller Login</h2>
        <form:form action="/seller/login" modelAttribute="seller" method="post">
            <label>Email:</label>
            <form:input path="email" placeholder="Enter your email" /><br/>
            
            <label>Password:</label>
            <form:input path="password" type="password" placeholder="Enter your password" /><br/>
            
            <input type="submit" value="Login"/>
        </form:form>

        <c:if test="${not empty error}">
            <div style="color: red;">${error}</div>
        </c:if>

		<div class="login-actions">
		           <a href="/seller/register">Register</a>
		           <a href="/seller/forgot-password">Forgot your Password?</a>
		       </div>
    </div>
</body>
</html>
