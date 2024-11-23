<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RevShop - Home</title>
    
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    
    <style>
      
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            font-family: 'Arial', sans-serif;
            background-color: #f5f5f5;
        }

        
        header {
            background-color: rgba(255, 255, 255, 0.9);
            color: #34495e;
            padding: 20px;
            text-align: center;
            font-size: 2.5rem;
            font-weight: bold;
            text-transform: uppercase;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        
        .login-link {
            display: inline-flex;
            align-items: center;
            text-decoration: none;
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 16px;
            transition: background-color 0.3s ease;
            margin-left: 10px;
        }

        .login-link:hover {
            background-color: #0056b3;
        }

       
        .main-content {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: flex-start; 
            padding: 0;
            position: relative; 
        }

        .carousel {
            width: 100%;
            height: 500px;
            margin-top: -1px; 
            background-color: white;
            border-radius: 15px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            overflow: hidden;
            position: relative;
        }

        /
        .carousel-images {
            display: flex;
            animation: slide 12s infinite;
            position: relative; 
        }

        
        .carousel img {
            width: 100%;
            height: 100%;
            object-fit: contain; 
            flex: 0 0 100%; 
        }

        
        @keyframes slide {
            0%, 33% {
                transform: translateX(0);
            }
            34%, 66% {
                transform: translateX(-100%);
            }
            67%, 100% {
                transform: translateX(-200%);
            }
        }

        
        footer {
            background-color: rgba(255, 255, 255, 0.9);
            color: #34495e;
            text-align: center;
            padding: 15px;
            box-shadow: 0 -2px 10px rgba(0, 0, 0, 0.1);
            position: absolute; 
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>


    <header>
        RevShop
        <a href="/buyer/login" class="login-link">
            <i class="fa fa-user"></i> Login
        </a>
    </header>

    
    <div class="main-content">
        <div class="carousel">
            <div class="carousel-images">
                <img src="https://img.freepik.com/free-psd/new-smartphone-16-pro-social-media-cover-design-template_47987-25428.jpg?t=st=1730092985~exp=1730096585~hmac=2145862fe07fd2166c45903459eed23e3a97a470bea63a349e8e27165bd13749&w=1380" alt="Slide 1">
                <img src="https://img.freepik.com/free-vector/dark-style-realistic-smart-watch-horizontal-banner-with-advertising-site-vector-illustration_1284-30193.jpg?t=st=1730093685~exp=1730097285~hmac=57568c9b6bfe100149e1bd0b161a8bbd73284075e9ec23dc8da330565f9428f9&w=1060" alt="Slide 2">
                <img src="https://static.vecteezy.com/system/resources/thumbnails/008/174/590/small_2x/fashion-advertising-web-banner-illustration-vector.jpg" alt="Slide 3">
            </div>
        </div>
    </div>

    
    <footer>
        <p>&copy; 2024 RevShop. All rights reserved.</p>
    </footer>

    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</body>
</html>
