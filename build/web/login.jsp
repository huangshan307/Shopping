<%-- 
    Document   : login.jsp
    Created on : Oct 9, 2018, 9:29:33 PM
    Author     : Huangshan
--%>

<%@page import="sonphse.user.UserDTO"%>
<%@page import="sonphse.user.LoginError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="container">
            <%
                UserDTO user = (UserDTO) session.getAttribute("USER_INFO");
                if (user != null) {
                    String url = "OrderServlet?btnAction=Search";
                    if (user.getRole() == 1 || user.getRole() == 2) {
                        url = "OrderServlet?btnAction=Search";
                    } // end if user getRole
                    response.sendRedirect(url);
                } // end if user
                
                LoginError errors = (LoginError) request.getAttribute("ERRORS");
            %>
            <h1>Login</h1>
            <div>
                <%
                    String orderMsg = (String) request.getAttribute("ORDER_LOGIN");
                    if (orderMsg != null) {
                        %>
                        <p class="red-text"><%= orderMsg %></p>
                        <%
                    }
                %>
                <form action="OrderServlet" method="POST">
                    <p>
                        <lable>Username</lable>
                        <%
                            String lastUsername = "";
                            if (request.getParameter("txtUsername") != null) {
                                lastUsername = request.getParameter("txtUsername");
                            }
                        %>
                        <input type="text" name="txtUsername" value="<%= lastUsername %>" />
                    </p>
                    <p>
                        <label>Password</label>
                        <input type="password" name="txtPassword" value="" />
                        <%
                            if (errors != null) {
                                if (errors.getPasswordNumberFormat() != null) {
                                    %>
                                    <p>
                                        <smal class="red-text"><%= errors.getPasswordNumberFormat() %></smal>
                                    </p>
                        <%
                                } // end if getPasswordNumberFormat
                            } // end if errors
                        %>
                    </p>
                    <p><input type="submit" value="Login" name="btnAction" /></p>
                </form>
                    <%
                        if (errors != null) {
                            if (errors.getUsernameOrPassword() != null) {
                                %>
                                <p class="red-text">
                                    <%= errors.getUsernameOrPassword() %>
                                </p>
                <%
                            } // end if getUernameOrPassword
                        } // end if errors
                    %>
                    <h3> <a href="OrderServlet?btnAction=Search">Go Shopping</a> </h3>
            </div>
        </div>
    </body>
</html>
