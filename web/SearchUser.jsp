<%-- 
    Document   : SearchUser
    Created on : Oct 8, 2018, 4:48:27 PM
    Author     : Huangshan
--%>

<%@page import="sonphse.mobile.MobileDTO"%>
<%@page import="java.util.List"%>
<%@page import="sonphse.mobile.MobileSearchUserError"%>
<%@page import="sonphse.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Search</title>
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
            String min = "";
            String max = "";
            if (request.getParameter("txtMin") != null) {
                min = request.getParameter("txtMin");
            }
            if (request.getParameter("txtMax") != null) {
                max = request.getParameter("txtMax");
            }
            %>
            <h4> <a href="OrderServlet?btnAction=viewCart">View your cart</a> </h4>
            <%
                String orderSuccess = (String) request.getAttribute("OrderSuccess");
                if (orderSuccess != null) {
                    %>
                    <p class="green-text"><%= orderSuccess %></p>
                    <%
                }
            %>
            <form action="OrderServlet">
                <span>Min </span>
                <input type="text" name="txtMin" value="<%= min %>" />
                <span> Max </span>
                <input type="text" name="txtMax" value="<%= max %>" />
                <input type="submit" value="Search" name="btnAction" />
            </form>
            <div class="red-text">
            <%
                
                MobileSearchUserError errors = (MobileSearchUserError) request.getAttribute("ERRORS");
                if (errors != null) {
                    if (errors.getMinNumberFormat() != null) {
                        %>
                        <p><%= errors.getMinNumberFormat() %></p>
                        <%
                    } // end if minNumberFormat

                    if (errors.getMaxNumberFormat() != null) {
                        %>
                        <p><%= errors.getMaxNumberFormat() %></p>
                        <%
                    } // end if maxNumberFormat
                } // end if errors
            %>
            </div>
            <%
                List<MobileDTO> list = (List<MobileDTO>) request.getAttribute("LIST_SEARCH");
                if (list != null) {
                    if  (list.isEmpty()) {
                    %>
                        <p>Sorry, we could not find device with price in range <%= min %> to <%= max %></p>
                    <%
                    } else {
                    %>
                    
                    <br>
                    <div>
                        <table border="1" cellpadding="10">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Mobile Id</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Mobile Name</th>
                                <th>Year of production</th>
                                <th>Sale</th>
                                <th>Quantity</th>
                                <th>Add to Cart</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                            int count = 0;
                            for (int i = 0; i < list.size(); i++) {
                                MobileDTO row = list.get(i);
                                String addToCartUrl = "OrderServlet"
                                        + "?btnAction=AddToCart"
                                        + "&txtMin=" + min
                                        + "&txtMax=" + max
                                        + "&mobileId=" + row.getMobileId();
                                %>
                                <tr>
                                    <td>
                                        <%= ++count %>
                                    </td>
                                    <td>
                                        <%= row.getMobileId() %>
                                    </td>
                                    <td>
                                        <%= row.getDescription() %>
                                    </td>
                                    <td>
                                        <%= row.getPrice() %>
                                    </td>
                                    <td>
                                        <%= row.getMobileName() %>
                                    </td>
                                    <td>
                                        <%= row.getYearOfProduction() %>
                                    </td>
                                    <td>
                                        <input type="checkbox" name="sale" value="SALE" disabled="disabled" <%
                                            if (!row.isNotSale()) {
                                                    %>
                                                    checked="checked"
                                                    <%
                                            }
                                            %> />
                                    </td>
                                    <td>
                                        <%= row.getQuantity() %>
                                    </td>
                                    <td>
                                        <a href="<%= addToCartUrl %>">Add to cart</a>
                                    </td>
                                </tr>
                                <%
                            } // end for loop
                        %>
                        </tbody>
                    </table>
                    </div>
                    
                    <%
                    } // end if list is empty
                } // end if list
            %>
        </div>
    </body>
</html>
