<%-- 
    Document   : search
    Created on : Oct 27, 2018, 12:08:49 PM
    Author     : Huangshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
    </head>
    <body>
        <div class="container">
            <h1 class="title">Search shoes</h1>
            <div class="welcome-name">
                <p>Welcome, <span>${sessionScope.USER.username}</span>! (<a href="logout">Logout</a>)</p>
            </div>
            <div class="search-bar">
                <form action="search">
                    <div class="input-group">
                        <input type="text" name="searchValue" value="${param.searchValue}" class="input-text" placeholder="Enter keywords here..." />
                        <button name="btnAction" value="Search">Search</button>
                    </div>
                </form>
            </div>
            <div class="w-100 view-cart">
                <a href="viewCart.jsp">View your cart</a>
            </div>

            <c:set var="searchValue" value="${param.searchValue}" />
            <c:if test="${not empty searchValue}">
                <c:set var="productList" value="${requestScope.LIST}" />
                <c:if test="${not empty productList}">
                    
                    <div class="search-result">
                        <table class="w-100 bg">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Description</th>
                                    <th>Price</th>
                                    <th>Sizes</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="row" items="${productList}" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${row.key.description}</td>
                                    <td>${row.key.price}</td>
                                    <td>
                                        
                                        <select name="shoeSize">
                                            <c:forEach var="sizes" items="${row.value}">
                                                <option>${sizes.sizes}</option>
                                            </c:forEach>>
                                        </select>
                                    </td>
                                    <td>
                                        <a href="#">Add cart</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div> 
                </c:if>
                <c:if test="${empty productList}">
                    <h2 class="title red-text">No record match!</h2>
                </c:if>
            </c:if>


        </div>
    </body>
</html>
