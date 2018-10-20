<%-- 
    Document   : insertMobile
    Created on : Oct 11, 2018, 1:35:15 PM
    Author     : Huangshan
--%>

<%@page import="sonphse.mobile.MobileInsertError"%>
<%@page import="sonphse.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add new mobile</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="container">
            <h1>Welcome to Staff page</h1>
            <%
                UserDTO user = (UserDTO) session.getAttribute("USER_INFO");
                if (user != null) {
                    if (user.getRole() == 0) {
                        response.sendRedirect("OrderServlet");
                    } // end if user getRole
                    %>
                    <h3>Hello, <span class="green-text"><%= user.getFullName() %></span>! (<a href="OrderServlet?btnAction=Logout">Log out</a>)</h3>
            <%
                } else {
                    response.sendRedirect("OrderServlet");
                } // end if user
            %>
            <h4><a href="OrderServlet?btnAction=Search">Search</a></h4>
            <table border="0" cellspacing="5" cellpadding="5">
                <%
                    MobileInsertError errors = (MobileInsertError) request.getAttribute("ERRORS");
                    if (errors != null) {
                        if (errors.getMobileIdExist() != null) {
                            %>
                                <p class="red-text"><%= errors.getMobileIdExist() %></p>
                            <%
                        }
                    }
                    String msgSuccess = (String) request.getAttribute("MSG_INSERT_SUCCESS");
                    if (msgSuccess != null) {
                        %>
                            <p class="green-text"><%= msgSuccess %></p>
                        <%
                    }
                %>
                <form action="OrderServlet" method="POST">
                    <tbody>
                    <tr>
                        <td>Mobile ID*</td>
                        <td>
                            <input type="text" name="txtMobileId" value="" />
                            <p><small>Max is 10 chars and not empty</small></p>
                            <%
                                if (errors != null) {
                                    if (errors.getMobileIdLength() != null) {
                                        %>
                                            <p class="red-text"><%= errors.getMobileIdLength() %></p>
                                        <%
                                    }
                                }
                            %>
                        </td>
                    </tr>
                    <tr>
                        <td>Description*</td>
                        <td>
                            <input type="text" name="txtDesctiption" value="" />
                            <p><small>Max is 250 chars and not empty</small></p>
                            <%
                                if (errors != null) {
                                    if (errors.getDescriptionLength() != null) {
                                        %>
                                            <p class="red-text"><%= errors.getDescriptionLength() %></p>
                                        <%
                                    }
                                }
                            %>
                        </td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td>
                            <input type="text" name="txtPrice" value="" />
                            <%
                                if (errors != null) {
                                    if (errors.getPriceNumberFormat() != null) {
                                        %>
                                            <p class="red-text"><%= errors.getPriceNumberFormat() %></p>
                                        <%
                                    }
                                }
                            %>
                        </td>
                    </tr>
                    <tr>
                        <td>Mobile Name*</td>
                        <td>
                            <input type="text" name="txtMobileName" value="" />
                            <p><small>Max is 20 and not empty</small></p>
                            <%
                                if (errors != null) {
                                    if (errors.getMobileNameLength() != null) {
                                        %>
                                            <p class="red-text"><%= errors.getMobileNameLength() %></p>
                                        <%
                                    }
                                }
                            %>
                        </td>
                    </tr>
                    <tr>
                        <td>Year Of Production</td>
                        <td>
                            <input type="text" name="txtYear" value="" />
                            <p><small>Year as 1998, 2018, ...</small></p>
                            <%
                                if (errors != null) {
                                    if (errors.getYearNumberFotmat() != null) {
                                        %>
                                            <p class="red-text"><%= errors.getYearNumberFotmat() %></p>
                                        <%
                                    }
                                }
                            %>
                        </td>
                    </tr>
                    <tr>
                        <td>Quantity</td>
                        <td>
                            <input type="text" name="txtQuanlity" value="" />
                            <%
                                if (errors != null) {
                                    if (errors.getQuantityNumberFormat() != null) {
                                        %>
                                            <p class="red-text"><%= errors.getQuantityNumberFormat() %></p>
                                        <%
                                    }
                                }
                            %>
                        </td>
                    </tr>
                    <tr>
                        <td>Not Sale</td>
                        <td>
                            <input type="checkbox" name="txtNotSale" value="NOTSALE" />
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td> <input type="submit" value="Insert" name="btnAction" /> </td>
                    </tr>
                </tbody>
                </form>
            </table>
        </div>
    </body>
</html>
