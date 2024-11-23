<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buyer Registration</title>
     <!-- Use the correct path for CSS -->
    <style>
        /* Add any additional CSS styles here */
		body {
		            font-family: 'Arial', sans-serif;
		            background-color: #f9f9f9; /* Light background */
		            color: #4a90e2; /* Light blue text for contrast */
		            margin: 0;
		            padding: 40px;
		            background: url('/images/your-image.jpg') no-repeat center center fixed;
		            background-size: cover;
		        }

		        h2 {
		            text-align: center;
		            font-size: 2.5rem;
		            margin-bottom: 30px;
		            color: #87cefa; /* Lighter blue */
		        }

		        /* Form Styles */
		        form {
		            background-color: #ffffff; /* White background for the form */
		            padding: 30px;
		            border: 2px solid #87cefa; /* Lighter blue border */
		            border-radius: 10px;
		            max-width: 500px;
		            margin: 0 auto;
		            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
		            transition: box-shadow 0.3s ease;
		        }

		        form:hover {
		            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.2);
		        }

		        label {
		            display: block;
		            margin-bottom: 8px;
		            font-weight: bold;
		            color: #4a90e2; /* Light blue */
		        }

		        input {
		            width: 100%;
		            padding: 12px;
		            margin-bottom: 20px;
		            border: 1px solid #87cefa; /* Lighter blue border */
		            border-radius: 8px;
		            background-color: #f3f3f3; /* Light gray background for inputs */
		            color: #4a90e2; /* Light blue text for inputs */
		            font-size: 1rem;
		            transition: all 0.3s ease;
		        }

		        input::placeholder {
		            color: #87cefa; /* Placeholder color */
		        }

		        input:focus {
		            outline: none;
		            border-color: #87cefa; /* Lighter blue border on focus */
		            box-shadow: 0 0 10px rgba(135, 206, 250, 0.5);
		        }

		        button {
		            width: 100%;
		            background-color: #87cefa; /* Lighter blue button */
		            color: #ffffff; /* White text */
		            padding: 12px;
		            font-size: 1.2rem;
		            font-weight: bold;
		            border: none;
		            border-radius: 8px;
		            cursor: pointer;
		            transition: background-color 0.3s ease, transform 0.3s ease;
		        }

		        button:hover {
		            background-color: #4a90e2; /* Slightly darker blue on hover */
		            transform: scale(1.05);
		        }

		        /* Responsive Design */
		        @media (max-width: 600px) {
		            form {
		                padding: 20px;
		            }

		            h2 {
		                font-size: 2rem;
		            }

		            button {
		                font-size: 1rem;
		            }
		        }
    </style>
</head>
<body>
    <h2>Buyer Registration</h2>

    <form action="${pageContext.request.contextPath}/buyer/register" method="post">
		
		<% 
		            // Check if an error message exists and display it
		            String error = (String) request.getAttribute("error");
		            if (error != null) {
		        %>
		            <div style="color:red;"><%= error %></div> <!-- Display the error message -->
		        <% 
		            }
		        %>
		
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" required>

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Register</button>
    </form>
</body>
</html>
