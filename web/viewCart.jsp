<%-- 
    Document   : viewCart
    Created on : Oct 12, 2018, 11:03:46 PM
    Author     : Huangshan
--%>

<%@page import="sonphse.order.OrderInsertError"%>
<%@page import="sonphse.cart.Item"%>
<%@page import="sonphse.mobile.MobileDTO"%>
<%@page import="sonphse.cart.Cart"%>
<%@page import="sonphse.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="container">
            <h1>Go Shopping</h1>
            <%
            UserDTO user = (UserDTO) session.getAttribute("USER_INFO");
            if (user != null) {
                %>
                    <h3>Hello, <span class="green-text"><%= user.getFullName() %></span>! (<a href="OrderServlet?btnAction=Logout">Log out</a>)</h3>
                <%
            } else {
                %>
                <h3> <a class="green-text" href="OrderServlet?btnAction=Login">Login now!</a> </h3>
                <%
            }
            %>
            <h4> <a href="OrderServlet?btnAction=Search">Search</a> </h4>
            <form action="OrderServlet">
                <span>Min </span>
                <input type="text" name="txtMin" value="" />
                <span> Max </span>
                <input type="text" name="txtMax" value="" />
                <input type="submit" value="Search" name="btnAction" />
            </form>
                <h2>Your cart</h2>
                <%
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) {
                    if  (!cart.getItems().isEmpty()) {
                        
                        %>
                        <form action="OrderServlet" method="POST">
                            <table border="1" cellpadding="10">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Mobile Name</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Total price</th>
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                        int count = 0;
                                        for (String key : cart.getItems().keySet()) {
                                            Item item = cart.getItems().get(key);
                                            MobileDTO dto = item.getMobile();
                                            String name = dto.getMobileName();
                                            int quantity = item.getQuantity();
                                            float totalPrice = item.getTotalPrice();
                                            float price = dto.getPrice();
                                            %>
                                            <tr>
                                                <td>
                                                    <%= ++count %>
                                                </td>
                                                <td>
                                                    <%= name %>
                                                </td>
                                                <td>
                                                    <%= quantity %>
                                                </td>
                                                <td>
                                                    <%= price %>
                                                </td>
                                                <td>
                                                    <%= totalPrice %>
                                                </td>
                                                <td>
                                                    <input type="checkbox" name="ckRemove" value="<%= dto.getMobileId() %>" />
                                                </td>
                                            </tr>
                                            <%
                                        }
                                %>
                            </tbody>
                        </table>
                            <button class="btn" name="btnAction" value="Remove">Remove</button>
                        </form>
                            <div>
                                <h3>Your payment: <span class="green-text"><%= cart.getTotalFullPrice() %>$</span></h3>
                                <div>
                                    <form action="OrderServlet">
                                        <button class="btn" name="btnAction" value="OrderNow">Order Now</button>
                                    </form>
                                    <%
                                        OrderInsertError errors = (OrderInsertError) request.getAttribute("ERRORS");
                                        if (errors != null) {
                                            if (errors.getQuantityError() != null) {
                                                %>
                                                <p class="red-text"><%= errors.getQuantityError() %></p>
                                                <%
                                            }
                                        }
                                    %>
                                </div>
                            </div>
                        <%
                    } //end if cart empty
                } else {
                %>
                    <p>You do not have any item here.</p>
                <%
                } // end if cart
                %>
        
        </div>
    
    </body>
</html>
