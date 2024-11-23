<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.revshop1.model.Category" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Product</title>
    <style>
        /* Base styling for the page */
		body {
		           font-family: Arial, sans-serif;
		           background-color: #e0e5ec;
		           display: flex;
		           justify-content: center;
		           align-items: center;
		           height: 100vh;
		           margin: 0;
		       }

		       /* Centering container */
		       .add-product-form-container {
		           display: flex;
		           flex-direction: column;
		           align-items: center;
		           justify-content: center;
		           width: 100%;
		       }

		       h1 {
		           font-size: 2em;
		           color: #333;
		           margin-bottom: 20px;
		       }

		       /* Styling for the Add Product Form */
		       form.add-product-form {
		           background: rgba(255, 255, 255, 0.2); /* Glass effect */
		           backdrop-filter: blur(8px);
		           border: 1px solid rgba(255, 255, 255, 0.3);
		           border-radius: 10px;
		           padding: 20px;
		           box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
		           max-width: 500px;
		           width: 90%;
		           transition: transform 0.2s ease;
		       }

		       .add-product-form h2 {
		           text-align: center;
		           font-size: 1.8em;
		           color: #333;
		           margin-bottom: 15px;
		       }

		       /* Label and Input styling */
		       .add-product-form label {
		           display: block;
		           font-weight: bold;
		           color: #555;
		           margin-bottom: 5px;
		       }

		       .add-product-form input[type="text"],
		       .add-product-form input[type="number"],
		       .add-product-form input[type="file"],
		       .add-product-form textarea,
		       .add-product-form select {
		           width: 100%;
		           padding: 12px;
		           margin-bottom: 15px;
		           border-radius: 8px;
		           border: 1px solid rgba(255, 255, 255, 0.6);
		           font-size: 1em;
		           background: rgba(255, 255, 255, 0.1);
		           color: #333;
		           transition: all 0.3s ease;
		           outline: none;
		       }

		       /* Focus effect on inputs */
		       .add-product-form input:focus,
		       .add-product-form textarea:focus,
		       .add-product-form select:focus {
		           background: rgba(255, 255, 255, 0.3);
		           border: 1px solid #4CAF50;
		           box-shadow: 0 0 5px rgba(76, 175, 80, 0.5);
		       }

		       /* Button styling */
		       .add-product-form button[type="submit"] {
		           width: 100%;
		           background-color: #4CAF50;
		           color: white;
		           padding: 12px;
		           border: none;
		           border-radius: 8px;
		           font-size: 1em;
		           cursor: pointer;
		           transition: background-color 0.3s ease, transform 0.3s ease;
		           box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
		       }

		       .add-product-form button[type="submit"]:hover {
		           background-color: #45a049;
		           transform: scale(1.05);
		       }

		       .add-product-form button[type="submit"]:active {
		           transform: scale(0.95);
		       }

		       /* Back to Dashboard link styling */
		       .add-product-form a {
		           display: inline-block;
		           text-align: center;
		           margin-top: 10px;
		           color: #4CAF50;
		           text-decoration: none;
		           transition: color 0.3s ease;
		       }

		       .add-product-form a:hover {
		           color: #45a049;
		       }
    </style>
</head>
<body>
    <div class="add-product-form-container">
        <h1>Add New Product</h1>
        <form class="add-product-form" action="<%= request.getContextPath() %>/seller/addProduct" method="post">
            <h2>Product Information</h2>
            <label for="name">Product Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea>

            <label for="price">Price:</label>
            <input type="number" id="price" name="price" step="0.01" required>

            <label for="discountedPrice">Discounted Price:</label>
            <input type="number" id="discountedPrice" name="discountedPrice" step="0.01">

            <label for="imageUrl">Image URL:</label>
            <input type="text" id="imageUrl" name="imageUrl" required>

            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" required>

            <label for="category">Category:</label>
            <select id="category" name="categoryId" required>
                <% 
                    List<Category> categories = (List<Category>) request.getAttribute("categories");
                    if (categories != null) {
                        for (Category category : categories) {
                %>
                    <option value="<%= category.getCategoryId() %>"><%= category.getName() %></option>
                <% 
                        }
                    } else {
                %>
                    <option value="">No categories available</option>
                <% 
                    } 
                %>
            </select>

            <button type="submit">Add Product</button>
        </form>
        <a href="<%= request.getContextPath() %>/seller/dashboard">Back to Dashboard</a>
    </div>
</body>
</html>
