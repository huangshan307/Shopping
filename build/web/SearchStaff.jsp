<%-- 
    Document   : SearchStaff
    Created on : Oct 8, 2018, 5:42:02 PM
    Author     : Huangshan
--%>

<%@page import="sonphse.mobile.MobileUpdateError"%>
<%@page import="java.util.List"%>
<%@page import="sonphse.mobile.MobileDTO"%>
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
            <h4><a href="OrderServlet?btnAction=InsertPage">Insert new products</a></h4>
            <form action="OrderServlet">
                <p>
                    <select name="type">
                        <option>Id</option>
                        <option>Name</option>
                    </select>
                    <%
                        String lastSearchValue = request.getParameter("txtSearchValue");
                    %>
                    <input type="text" name="txtSearchValue" <%
                        if (lastSearchValue != null) {
                            %>
                            value="<%= lastSearchValue %>"
                        <%
                        }
                        %> placeholder="Enter id or name to search" />
                    <input type="submit" value="Search" name="btnAction" />
                </p>
            </form>
                    
            <%
                MobileUpdateError errors = (MobileUpdateError) request.getAttribute("ERRORS");
                if (errors != null) {
                    if (errors.getDescriptionLength() != null) {
                        %>
                        <p class="red-text"><%= errors.getDescriptionLength() %></p>
                        <%
                    }
                    if (errors.getPriceNumberFormat() != null) {
                        %>
                        <p class="red-text"><%= errors.getPriceNumberFormat() %></p>
                        <%
                    }
                    if (errors.getQuanlityNumberFormat() != null) {
                        %>
                        <p class="red-text"><%= errors.getQuanlityNumberFormat() %></p>
                        <%
                    }
                }
                
                List<MobileDTO> list = (List<MobileDTO>) request.getAttribute("LIST_SEARCH");
                if (list != null) {
                    if (list.isEmpty()) {
                        %>
                        <p>Sorry, we could not find this item </p>
                    <%
                    } else {
                        %>
                        <table border="1" cellpadding="5">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Id</th>
                                    <th>Description</th>
                                    <th>Price</th>
                                    <th>Name</th>
                                    <th>Year of production</th>
                                    <th>Quantity</th>
                                    <th>Sale</th>
                                    <th>Delete</th>
                                    <th>Update</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int count = 0;
                                    for (int i = 0; i < list.size(); i++) {
                                        MobileDTO row = list.get(i);
                                        
                                        String deleteUrl = "OrderServlet"
                                                + "?btnAction=Delete"
                                                + "&type=" + request.getParameter("type")
                                                + "&lastSearchValue=" + lastSearchValue
                                                + "&mobileId=" + row.getMobileId();
                                        %>
                                        <form action="OrderServlet" method="POST">
                                            <tr>
                                            <td>
                                                <%= ++count%>
                                            </td>
                                            <td>
                                                <%= row.getMobileId()%>
                                                <input type="hidden" name="mobileId" value="<%= row.getMobileId()%>" />
                                            </td>
                                            <td>
                                                <input type="text" name="txtDescription" value="<%= row.getDescription()%>" />
                                            </td>
                                            <td>
                                                <input type="text" name="txtPrice" value="<%= row.getPrice()%>" />
                                            </td>
                                            <td>
                                                <%= row.getMobileName()%>
                                            </td>
                                            <td>
                                                <%= row.getYearOfProduction()%>
                                            </td>
                                            <td>
                                                <input type="text" name="txtQuanlity" value="<%= row.getQuantity()%>" />
                                            </td>
                                            <td>
                                                <input type="checkbox" name="notSale" <%
                                                    if (!row.isNotSale()) {
                                                        %>
                                                        checked="checked"
                                                    <%
                                                    } // end if isNotSale
                                                    %> value="SALE" />
                                            </td>
                                            <td>
                                                <a href="<%= deleteUrl %>">Delete</a>
                                            </td>
                                            <td>
                                                <input type="hidden" name="type" value="<%= request.getParameter("type")%>" />
                                                <input type="hidden" name="lastSearchValue" value="<%= lastSearchValue %>" />
                                                <input type="submit" value="Update" name="btnAction" />
                                            </td>
                                        </tr>
                                        </form>
                                <%
                                    } // end for loop
                                %>
                            </tbody>
                        </table>
                        <%
                    } // end if list empty
                } // end if list
            %>
        </div>
    </body>
</html>
