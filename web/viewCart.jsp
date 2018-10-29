<%-- 
    Document   : viewCart
    Created on : Oct 27, 2018, 3:42:04 PM
    Author     : Huangshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>View your cart</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
    </head>
    <body>
        <div class="container">
            <h1 class="title">View your product</h1>
            <div class="welcome-name">
                <p>Welcome, <span>Name</span>! (Logout)</p>
            </div>
            
            <div class="w-100 view-cart">
                <a href="search.jsp">Search</a>
            </div>
            <div class="search-result">
                <table class="w-100 bg">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Description</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Sizes</th>
                            <th>Total</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>This is a description</td>
                            <td>3</td>
                            <td>100</td>
                            <td>38</td>
                            <td>300</td>
                            <td>
                                <input type="checkbox" name="remove" value="REMOVE" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="5">Total</td>
                            <td>300</td>
                            <td><button class="btn-primary">Remove</button></td>
                        </tr>
                    </tbody>
                </table>

            </div>            
            
            <div class="w-100">
                <a href="search.jsp">Check out</a>
            </div>
        </div>
    </body>
</html>
