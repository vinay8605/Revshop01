<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Successful</title>
	<style>
		body {
		            font-family: 'Arial', sans-serif;
		            background-color: #f9f9f9; /* Light background */
		            color: #4a90e2; /* Light blue text */
		            margin: 0;
		            padding: 40px;
		            display: flex;
		            flex-direction: column;
		            align-items: center; /* Center items */
		            justify-content: center; /* Center vertically */
		            height: 100vh; /* Full height */
		        }

		        h2 {
		            font-size: 2.5rem;
		            margin-bottom: 20px;
		            color: #87cefa; /* Lighter blue for the heading */
		        }

		        p {
		            font-size: 1.2rem;
		            text-align: center; /* Center align the text */
		            margin-bottom: 20px;
		        }

		        a {
		            color: #4a90e2; /* Light blue for links */
		            text-decoration: none;
		            font-weight: bold;
		            transition: color 0.3s ease; /* Smooth transition for color */
		        }

		        a:hover {
		            color: #87cefa; /* Lighter blue on hover */
		        }

		        /* Responsive Design */
		        @media (max-width: 600px) {
		            h2 {
		                font-size: 2rem; /* Smaller heading on mobile */
		            }

		            p {
		                font-size: 1rem; /* Smaller paragraph on mobile */
		            }
		        }
	</style>
</head>
<body>
    <h2>Registration Successful!</h2>
    <p>Thank you for registering as a buyer. You can now <a href="${pageContext.request.contextPath}/buyer/login">login</a>.</p>
</body>
</html>
