<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logout Success</title>
    <style>
		/* General Styles */
		body {
		    font-family: Arial, sans-serif;
		    background-color: #f3f3f3;
		    color: #333;
		    margin: 0;
		    padding: 0;
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    height: 100vh;
		}

		/* Centered Container */
		div {
		    text-align: center;
		    margin-top: 100px;
		}

		/* Heading Styles */
		h1 {
		    font-size: 2.5em;
		    color: #2c3e50;
		    margin-bottom: 10px;
		}

		p {
		    font-size: 1.2em;
		    color: #7f8c8d;
		    margin-bottom: 20px;
		}

		/* Button Styles */
		button {
		    padding: 12px 20px;
		    font-size: 16px;
		    background-color: #2980b9;
		    color: white;
		    border: none;
		    border-radius: 5px;
		    cursor: pointer;
		    font-weight: bold;
		    transition: background-color 0.3s ease;
		}

		button:hover {
		    background-color: #3498db;
		}

	</style>
    
    <script>
    window.onload = function () {
        if (window.history && window.history.pushState) {
            window.history.pushState(null, null, window.location.href);
            window.onpopstate = function () {
                window.history.pushState(null, null, window.location.href);
            };
        }
    };
</script>
    
</head>
<body>
    <div style="text-align: center; margin-top: 100px;">
        <h1>You have successfully logged out!</h1>
        <p>Thank you for visiting our site.</p>
        <a href="${pageContext.request.contextPath}/seller/login">
            <button style="padding: 10px 20px; font-size: 16px;">Login Again</button>
        </a>
    </div>
</body>
</html>