<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Product</title>
    <style>
        /* Global Styles */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f9f9f9; /* Light background */
            color: #333; /* Dark text */
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #4a90e2; /* Light Blue */
            margin-bottom: 20px;
        }

        /* Form Styles */
        form {
            background-color: #ffffff; /* White background for the form */
            padding: 30px;
            border: 2px solid #4a90e2; /* Light Blue */
            border-radius: 10px;
            max-width: 400px;
            margin: 0 auto;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #333; /* Dark text for labels */
            font-weight: bold;
        }

        input, textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #4a90e2; /* Light Blue */
            border-radius: 5px;
            background-color: #f0f8ff; /* Light Blue background */
            color: #333; /* Dark text */
            font-size: 1rem;
        }

        input::placeholder, textarea::placeholder {
            color: #4a90e2; 
            opacity: 0.7;
        }

        input:focus, textarea:focus {
            outline: none;
            border-color: #4a90e2; 
            box-shadow: 0 0 8px rgba(74, 144, 226, 0.5);
        }

        button {
            width: 100%;
            background-color: #4a90e2; /* Light Blue */
            color: #ffffff; /* White text */
            padding: 12px;
            font-size: 1.2rem;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        button:hover {
            background-color: #357ab8; /* Darker Blue on hover */
        }

        a {
            color: #4a90e2; /* Light Blue */
            text-decoration: none;
            font-weight: bold;
            display: inline-block;
            margin-top: 20px;
            text-align: center;
        }

        a:hover {
            text-decoration: underline;
        }

        @media (max-width: 600px) {
            form {
                padding: 20px;
            }

            h1 {
                font-size: 1.8rem;
            }

            button {
                font-size: 1rem;
            }
        }
    </style>
</head>
<body>
    <h1>Edit Product</h1>
    <form action="<%= request.getContextPath() %>/seller/updateProduct" method="post">
        <input type="hidden" name="productId" value="${product.productId}"> <!-- Hidden input for product ID -->
        
        <label for="name">Product Name:</label>
        <input type="text" id="name" name="name" value="${product.name}" required>

        <label for="description">Description:</label>
        <textarea id="description" name="description" required>${product.description}</textarea>

        <label for="price">Price:</label>
        <input type="number" id="price" name="price" step="0.01" value="${product.price}" required>

        <label for="discountedPrice">Discounted Price:</label>
        <input type="number" id="discountedPrice" name="discountedPrice" step="0.01" value="${product.discountedPrice}">

        <label for="imageUrl">Image URL:</label>
        <input type="text" id="imageUrl" name="imageUrl" value="${product.imageUrl}" required>

        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" value="${product.quantity}" required>

        <button type="submit">Update Product</button>
    </form>
    <a href="<%= request.getContextPath() %>/seller/dashboard">Back to Dashboard</a>
</body>
</html>
