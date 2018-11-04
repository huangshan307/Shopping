<%-- 
    Document   : viewCart
    Created on : Oct 27, 2018, 3:42:04 PM
    Author     : Huangshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>View your cart</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}"/>
        <c:if test="${empty user}">
            <c:redirect url="login.html"/>
        </c:if>
        <div class="container">
            <h1 class="title">View your product</h1>
            <div class="welcome-name">
                <p>Welcome, <span>${user.username}</span>! (<a href="logout">Logout</a>)</p>
            </div>

            <div class="w-100 view-cart">
                <a href="search.jsp">Search</a>
            </div>

            <c:set var="cartObj" value="${sessionScope.CART}" />
            <c:if test="${not empty cartObj}">
                <c:if test="${not empty cartObj.list}">
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
                            <form action="delete" method="GET">
                                <c:forEach var="row" items="${cartObj.list}" varStatus="counter">
                                    <c:forEach var="field" items="${row.value}">
                                        <c:set var="item" value="${field.value}"/>
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${item.shoes.description}</td>
                                            <td>${item.quantity}</td>
                                            <td>${item.sizes.price}</td>
                                            <td>${item.sizes.sizes}</td>
                                            <td>${item.price}</td>
                                            <td><input type="checkbox" name="remove" value="${item.shoes.shoesID}-${item.sizes.id}" /></td>
                                        </tr>
                                    </c:forEach>
                                </c:forEach>
                                <tr>
                                    <td colspan="5">Total</td>
                                    <td>
                                        ${cartObj.totalPrice}
                                    </td>
                                    <td>
                                        <button class="btn-primary">Remove</button>
                                    </td>
                                </tr>
                            </form>
                            </tbody>
                        </table>
                    </div> 
                </c:if>
            </c:if>

            <c:if test="${empty cartObj.list}">
                <h2 class="title red-text">No items in your cart!</h2>
            </c:if>

            <div class="w-100">
                <a href="checkOut.jsp">Check out</a>
            </div>
        </div>
    </body>
</html>
