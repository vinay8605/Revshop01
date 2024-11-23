<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <title>Buyer Login</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Hind&family=Poppins:ital,wght@0,200;0,300;0,400;0,500;0,600;1,200&display=swap');
        
        * {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            background: url('https://img.freepik.com/free-photo/shopping-cart-filled-with-coins-copy-space-background_23-2148305919.jpg?t=st=1729676882~exp=1729680482~hmac=225ff07e20d2fbf5483b2e3cbe47cbfd2a13c39ee74d0a1cb1f4b9a164427c91&w=996') no-repeat center center fixed;
            background-size: cover;
        }
        
        .box {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        
        .container {
            width: 350px;
            display: flex;
            flex-direction: column;
            padding: 15px;
            background-color: rgba(0, 0, 0, 0.3); /* Transparent background */
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.5);
        }
        
        span {
            color: #fff;
            font-size: small;
            display: flex;
            justify-content: center;
            padding: 10px 0;
        }
        
        header {
            color: #fff;
            font-size: 30px;
            display: flex;
            justify-content: center;
            padding: 10px 0;
        }
        
        .input-box {
            position: relative;
            margin: 15px 0;
        }
        
        .input-box .input {
            height: 45px;
            width: 100%;
            border: none;
            border-radius: 30px;
            color: #fff;
            font-size: 15px;
            padding-left: 45px;
            background: rgba(255, 255, 255, 0.3); /* Semi-transparent background for inputs */
            outline: none;
        }
        
        i {
            position: absolute;
            top: 12px;
            left: 15px;
            color: #fff;
        }
        
        .submit {
            border: none;
            border-radius: 30px;
            font-size: 15px;
            height: 45px;
            outline: none;
            width: 100%;
            color: #000;
            background: rgba(255, 255, 255, 0.7);
            cursor: pointer;
            transition: 0.3s;
        }
        
        .submit:hover {
            box-shadow: rgba(110, 104, 104, 0.2) 0 4px 8px;
            background: rgba(255, 255, 255, 0.9); /* Slightly brighter on hover */
        }
        
        p {
            text-align: center;
            margin-top: 10px;
            color: #fff;
        }
        
        label a {
            text-decoration: none;
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="box">
        <div class="container">
            <div class="top">
                <span>Have an account?</span>
                <header>Login Now</header>
            </div>
            <form action="${pageContext.request.contextPath}/buyer/login" method="post">
                <div class="input-box">
                    <input type="email" id="email" name="email" class="input" required placeholder="Email">
                    <i class="bx bx-user"></i>
                </div>
                <div class="input-box">
                    <input type="password" id="password" name="password" class="input" required placeholder="Password">
                    <i class="bx bx-lock-alt"></i>
                </div>
                <div class="input-box">
                    <input type="submit" class="submit" value="Login">
                </div>
            </form>
            
            <p>
                Don't have an account? <a href="${pageContext.request.contextPath}/buyer/register">Register here</a>
            </p>
            <!-- Display error if login fails -->
            <c:if test="${not empty error}">
                <p style="color: red;">${error}</p>
            </c:if>
			
			<p>Forgot your password? <a href="/buyer/forgot-password">Reset here</a></p>
        </div>
    </div>
</body>
</html>
