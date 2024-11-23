<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.revshop1.model.Product" %>
<%@ page import="com.revshop1.model.Seller" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Seller Dashboard</title>
	<style>
	    /* Global Styles */
	    body {
	        font-family: 'Arial', sans-serif;
	        background-color: #f9f9f9;
	        color: #4a90e2;
	        margin: 0;
	        padding: 0;
	        display: flex;
	    }

	    h1, h2 {
	        text-align: center;
	        color: #87cefa;
	        margin-bottom: 20px;
	    }

	    /* Side Menu Styles */
	    .side-menu {
	        position: fixed;
	        left: 0;
	        top: 0;
	        bottom: 0;
	        width: 250px; /* Full-width for the left side */
	        background-color: black;
	        padding: 20px 0;
	        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	        color: white;
	    }

	    .side-menu h3 {
	        text-align: center;
	        color: white;
	        margin-bottom: 20px;
	    }

	    .side-menu a {
	        display: block;
	        padding: 12px 20px;
	        color: white;
	        text-decoration: none;
	        border-radius: 5px;
	        transition: background-color 0.3s;
	        margin: 10px 0;
	        font-weight: bold;
	    }

	    .side-menu a:hover {
	        background-color: #333333;
	    }

	    .side-menu a.active {
	        background-color: #222222;
	    }

	    /* Main Content Styles */
	    .main-content {
	        margin-left: 250px; /* Aligns with the side menu width */
	        padding: 20px;
	        flex-grow: 1;
	    }

	    /* Grid Container */
	    .product-grid {
	        display: grid;
	        grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
	        gap: 20px;
	        margin-top: 20px;
	    }

	    /* Product Card */
	    .product-card {
	        background-color: #f1f1f1;
	        padding: 15px;
	        border-radius: 8px;
	        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	        text-align: center;
	        overflow: hidden;
	    }

	    /* Image Styling with Crop */
	    .product-card img {
	        width: 100%;
	        height: 300px;
	        object-fit: cover;
	        border-radius: 8px;
	    }

	    /* Product Details */
	    .product-name {
	        font-weight: bold;
	        color: black;
	        margin: 10px 0;
	        font-size: 1.1rem;
	    }

	    .product-description {
	        font-size: 0.85rem;
	        color: #555;
	        margin: 5px 0;
	        line-height: 1.4;
	        max-height: 50px;
	        overflow: hidden;
	        text-overflow: ellipsis;
	        display: -webkit-box;
	        -webkit-line-clamp: 3;
	        -webkit-box-orient: vertical;
	    }

	    .product-price {
	        font-size: 1.1rem;
	        font-weight: bold;
	        color: #2a2a2a;
	        margin-bottom: 15px;
	    }

	    /* Button Styles */
	    .button-container {
	        display: flex;
	        flex-direction: column;
	        gap: 10px;
	    }

	    .button-container a, .button-container button {
	        background-color: #4a90e2;
	        color: #fff;
	        border: none;
	        padding: 10px 0;
	        border-radius: 5px;
	        cursor: pointer;
	        text-decoration: none;
	        font-weight: bold;
	        transition: background-color 0.3s ease, transform 0.2s ease;
	        width: 100%;
	    }

	    .button-container a:hover, .button-container button:hover {
	        background-color: #3a7ec0;
	        transform: scale(1.02);
	    }

	    .button-container button.delete-button {
	        background-color: #e57373;
	    }

	    .button-container button.delete-button:hover {
	        background-color: #d64545;
	    }
	</style>

</head>
<body>
    <div class="side-menu">
        <a href="#" onclick="loadContent('/seller/newProduct')">Add New Product</a>
        <a href="#" onclick="loadContent('/seller/dashboard')">View Products</a>
        <a href="#" onclick="loadContent('/seller/orders')">Orders</a>
        <a href="#" onclick="logoutSeller();">Logout</a>
    </div>

    <div class="main-content">
        <% 
            Seller seller = (Seller) request.getAttribute("seller");
            if (seller != null) { // Check if seller is not null
        %>
            <h1>Welcome to Your Dashboard, <%= seller.getBusinessName() %></h1>
        <% 
            } else { 
        %>
            <h1>Welcome to Your Dashboard</h1> <!-- Fallback if seller is null -->
        <% 
            }
        %>
        
        <div id="dynamic-content">
            <h2>Your Products</h2>
            <div class="product-grid">
                <% 
                    // Fetching the products list from the request attribute
                    List<Product> products = (List<Product>) request.getAttribute("products");
                    if (products != null && !products.isEmpty()) {
                        for (Product product : products) {
                %>
                    <div class="product-card">
                        <img src="<%= product.getImageUrl() %>" alt="Product Image">
                        <div class="product-name"><%= product.getName() %></div>
                        <div class="product-description"><%= product.getDescription() %></div>
                        <div class="product-price">$<%= product.getPrice() %></div>
                        <div class="button-container">
                            <a href="<%= request.getContextPath() %>/seller/editProduct/<%= product.getProductId() %>">Edit</a>
                            <form action="<%= request.getContextPath() %>/seller/deleteProduct" method="post" style="display: inline;" onsubmit="return confirmDelete();">
                                <input type="hidden" name="productId" value="<%= product.getProductId() %>">
                                <button type="submit">Delete</button>
                            </form>
                        </div>
                    </div>
                <%
                        }
                    } else {
                %>
                    <p>No products found.</p>
                <%
                    }
                %>
            </div>
        </div>

        <% 
            String successMessage = (String) request.getAttribute("successMessage"); // Get success message if present
            if (successMessage != null) {
        %>
            <script>
                alert("<%= successMessage %>"); // Show alert with success message
            </script>
        <%
            }
        %>
    </div>

    <script>
        function confirmDelete() {
            return confirm("Are you sure you want to delete this product?");
        }

        function loadContent(contentType) {
            const dynamicContent = document.getElementById('dynamic-content');
            dynamicContent.innerHTML = ''; // Clear existing content
            
            switch(contentType) {
				case '/seller/newProduct':
				            // Fetch the content of the Add Product page via AJAX
				            fetch('<%= request.getContextPath() %>/seller/newProduct')
				                .then(response => {
				                    if (!response.ok) {
				                        throw new Error('Network response was not ok');
				                    }
				                    return response.text(); // Return the text content of the response
				                })
				                .then(html => {
				                    dynamicContent.innerHTML = html; // Inject the fetched HTML into the dynamic content area
				                })
				                .catch(error => {
				                    console.error('There was a problem with the fetch operation:', error);
				                });
				            break;
                case 'viewProducts':
                    // Load products dynamically (if necessary)
                    // Here we are just clearing the grid; the product listing is already shown above.
                    loadProducts();
                    break;
					case '/seller/orders':
					            // Orders page
					            fetch('<%= request.getContextPath() %>/seller/orders')
					                .then(response => response.text())
					                .then(html => dynamicContent.innerHTML = html)
					                .catch(error => console.error('Error loading orders:', error));
					            break;
                case 'profile':
                    dynamicContent.innerHTML = `<h2>Your Profile</h2><p>Profile content goes here...</p>`;
                    break;
                default:
                    loadProducts();
            }
        }

		function loadProducts() {
		    // You can implement AJAX call to fetch products dynamically if needed
		    document.getElementById('dynamic-content').innerHTML = `
		        <h2>Your Products</h2>
		        <div class="product-grid">
		            <% 
		                List<Product> products1 = (List<Product>) request.getAttribute("products");
		                if (products1 != null && !products1.isEmpty()) {
		                    for (Product product : products1) {
		            %>
		                <div class="product-card">
		                    <img src="<%= product.getImageUrl() %>" alt="Product Image">
		                    <div class="product-name"><%= product.getName() %></div>
		                    <div class="product-description"><%= product.getDescription() %></div>
		                    <div class="product-price">$<%= product.getPrice() %></div>
		                    <div class="button-container">
		                        <a href="<%= request.getContextPath() %>/seller/editProduct/<%= product.getProductId() %>">Edit</a>
		                        <form action="<%= request.getContextPath() %>/seller/deleteProduct" method="post" style="display: inline;" onsubmit="return confirmDelete();">
		                            <input type="hidden" name="productId" value="<%= product.getProductId() %>">
		                            <button type="submit">Delete</button>
		                        </form>
		                    </div>
		                </div>
		            <%
		                    }
		                } else {
		            %>
		                <p>No products found.</p>
		            <%
		                }
		            %>
		        </div>
		    `;
		}
		
		function logoutSeller() {
		        if (confirm("Are you sure you want to log out?")) {
		            window.location.href = "<%= request.getContextPath() %>/seller/logout";
		        }
		    }

    </script>
</body>
</html>
