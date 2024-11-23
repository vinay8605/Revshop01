<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.revshop1.model.Category" %>
<%@ page import="com.revshop1.model.Product" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

<html>
<head>
    <title>Buyer Dashboard</title>
    <style>
		{
		           margin: 0;
		           padding: 0;
		           box-sizing: border-box;
		           font-family: 'Arial', sans-serif;
		       }

		       body {
		           background-color: #f3f3f3;
		           color: #333;
		           display: flex;
		           flex-direction: column;
		           min-height: 100vh;
		       }

		       .navbar {
		           background-color: #2c3e50;
		           color: white;
		           display: flex;
		           justify-content: space-between;
		           align-items: center;
		           padding: 15px 20px;
		           box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
		           width: 100%;
		           position: fixed;
		           top: 0;
		           z-index: 1000;
		       }

		       .navbar a {
		           color: #ecf0f1;
		           text-decoration: none;
		           margin: 0 15px;
		           font-weight: bold;
		           position: relative;
		           transition: color 0.3s;
		       }

		       .navbar a:hover {
		           color: #f39c12;
		       }

		       .navbar a::after {
		           content: "";
		           position: absolute;
		           left: 0;
		           bottom: -5px;
		           width: 0%;
		           height: 2px;
		           background-color: #f39c12;
		           transition: width 0.3s;
		       }

		       .navbar a:hover::after {
		           width: 100%;
		       }

		       .navbar .logo {
		           font-size: 1.8em;
		           font-weight: bold;
		       }

		       .main-container {
		           display: flex;
		           margin-top: 60px; 
		           width: 100%;
		       }

		       .sidebar {
		           background-color: #ecf0f1;
		           padding: 20px;
		           width: 200px; 
		           height: calc(100vh - 60px); 
		           position: fixed; 
		           top: 60px; 
		           overflow-y: auto; 
		       }

		       .sidebar h2 {
		           font-size: 1.5em;
		           margin-bottom: 20px;
		       }

		       .sidebar a {
		           display: block;
		           padding: 10px;
		           color: #2c3e50;
		           text-decoration: none;
		           border-radius: 5px;
		           transition: background-color 0.3s;
		       }

		       .sidebar a:hover {
		           background-color: #dcdcdc;
		       }

		       .container {
		           margin-left: 220px; 
		           padding: 20px;
		           width: calc(100% - 220px);
		       }

		       header {
		           display: flex;
		           justify-content: space-between;
		           align-items: center;
		           margin-bottom: 30px;
		       }

		       header h1 {
		           font-size: 2.5em;
		           color: #34495e;
		       }

		       .product-grid {
		           display: grid;
		           grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
		           gap: 20px;
		           margin-top: 20px;
		       }

			   .product-card {
			       display: flex;
			       flex-direction: column;
			       justify-content: space-between;
			       border: 1px solid #ecf0f1;
			       border-radius: 10px;
			       overflow: hidden;
			       background-color: #fff;
			       text-align: center;
			       padding: 20px;
			       transition: transform 0.3s, box-shadow 0.3s;
			   }

			   .product-card h3, .product-card p {
			       margin: 10px 0;
			   }

			   .product-card .price {
			       font-size: 1.4em;
			       color: #e74c3c;
			       font-weight: bold;
			       margin-bottom: 15px;
			   }

			   .product-card .button-container {
			       display: flex;
			       justify-content: center;
			       gap: 10px;
			       align-items: center;
			   }

			   .product-card input[type="number"] {
			       width: 60px;
			       margin-right: 10px;
			       text-align: center;
			   }

			   .button {
			       padding: 10px 15px;
			       background-color: #2980b9;
			       color: white;
			       border: none;
			       border-radius: 5px;
			       cursor: pointer;
			       transition: background-color 0.3s;
			   }

			   .button:hover {
			       background-color: #3498db;
			   }
			   
			   .dropdown {
			   	position: relative;
			   	display: inline-block;
				margin-right:20px;
			   }

			   .profile-icon {
			   	width: 30px; /* Adjust size as needed */
			   	cursor: pointer;
			   }

			   .dropdown-content {
			       display: none;
			       position: absolute;
			       background-color: #000;
				   color:#fff;
			       min-width: 160px;
			       box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
			       z-index: 1;
			       top: 40px; /* Position below the icon */
			       right: 0; /* Align to the right */
			       max-height: 200px; /* Set a max height */
			       overflow-y: auto; /* Add scroll if content overflows */
			   }
			   

			   .show {
			       display: block;
			   }


		       @media (max-width: 768px) {
		           .navbar {
		               flex-direction: column;
		               align-items: flex-start;
		           }

		           .main-container {
		               flex-direction: column;
		           }

		           .sidebar {
		               position: relative;
		               width: 100%;
		               height: auto;
		           }

		           .container {
		               margin-left: 0;
		               width: 100%;
		           }

		           .product-grid {
		               grid-template-columns: 1fr;
		           }
				   
				   .dropdown-content {
				           right: 0; /* Ensure dropdown stays aligned */
				       }
		       }
    </style>
    <script>
        function showAlert(message) {
            alert(message);
        }

        function addToWishlist(productId) {
            const formData = new FormData();
            formData.append("productId", productId);

            fetch("/wishlist/add", {
                method: "POST",
                body: formData
            })
            .then(response => {
                if (response.ok) {
                    showAlert('Product added to wishlist!');
                } else {
                    showAlert('Failed to add to wishlist. Please try again.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                showAlert('An error occurred. Please try again.');
            });
        }

        function loadContent(page) {
            fetch(page)
                .then(response => response.text())
                .then(html => {
                    document.getElementById('content').innerHTML = html;
                })
                .catch(error => console.error('Error loading content:', error));
        }
		
		function toggleDropdown(event) {
		            event.stopPropagation(); // Prevent event from bubbling up to window
		            document.getElementById("profileDropdown").classList.toggle("show");
		        }

		        // Close the dropdown if the user clicks outside of it
		        window.onclick = function(event) {
		            const dropdowns = document.getElementsByClassName("dropdown-content");
		            for (let i = 0; i < dropdowns.length; i++) {
		                const openDropdown = dropdowns[i];
		                if (openDropdown.classList.contains('show')) {
		                    openDropdown.classList.remove('show');
		                }
		            }
		        };
    </script>
</head>
<body>
    <div class="navbar">
        <div class="logo">
            <a href="/">RevShop</a>
        </div>
        <div class="nav-links">
            <a href='/buyer/dashboard'>Home</a>
            <a href="#" onclick="loadContent('/cart')">Cart</a>
            <a href="#" onclick="loadContent('/wishlist')">Wishlist</a>
            <a href="#" onclick="loadContent('/history')">Orders</a>
            <a href="/logout">Logout</a>
			
			<div class="dropdown">
							<a class="nav-links" href="#" onclick="toggleDropdown(event)"> <i
								class="bi bi-person-circle"
								style="font-size: 1.5rem; color: white;"></i>
							</a>
							<div class="dropdown-content" id="profileDropdown">
								<a href="${pageContext.request.contextPath}/buyer/profile">View Profile</a>
							</div>
						</div>
        </div>
    </div>

    <div class="main-container">
        <div class="sidebar">
            <h2>Categories</h2>
            <%
                List<Category> categories = (List<Category>) request.getAttribute("categories");
                if (categories != null) {
                    for (Category category : categories) {
            %>
            <a href='<%= request.getContextPath() %>/buyer/dashboard?category=<%= category.getCategoryId() %>'>
				<%= category.getName() %></a>
            <%
                    }
                }
            %>
        </div>

        <!-- Only the main content inside this container will be dynamically loaded -->
        <div class="container" id="content">
            <header>
                <h1>Products</h1>
            </header>

            <div class="product-grid">
                <%
                    List<Product> products = (List<Product>) request.getAttribute("products");
                    if (products != null) {
                        for (Product product : products) {
                %>
                <div class="product-card">
                    <img src="<%= product.getImageUrl() %>" alt="<%= product.getName() %>" />
                    <h3><%= product.getName() %></h3>
                    <p><%= product.getDescription() %></p>
                    <p class="price">Price: ₹<%= product.getPrice() %></p>
                    <form action="/cart/add" method="post" onsubmit="showAlert('Product added to cart!')">
                        <input type="hidden" name="productId" value="<%= product.getProductId() %>"/>
                        <input type="number" name="quantity" value="1" min="1" style="width: 60px; margin-bottom: 10px;"/>
                        <button type="submit" class="button">Add to Cart</button>
                    </form>
                    <button class="button" onclick="addToWishlist(<%= product.getProductId() %>)">Add to Wishlist</button>
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
    </div>
</body>
</html>



