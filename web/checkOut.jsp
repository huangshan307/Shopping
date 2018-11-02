<%-- 
    Document   : checkOut
    Created on : Oct 30, 2018, 3:34:43 PM
    Author     : Huangshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Check out</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
    </head>
    <body>
        <div class="container">
            <h1 class="title">Finishing Your Order</h1>
            <div class="welcome-name">
                <p>Welcome, <span>${sessionScope.USER.username}</span>! (<a href="logout">Logout</a>)</p>
            </div>
            <div class="w-100 view-cart">
                <a href="search.jsp">Search</a>
            </div>
            <c:set var="cartObj" value="${sessionScope.CART}"/>
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
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>
                            <form action="delete" method="GET">
                                <c:forEach var="row" items="${cartObj.list}" varStatus="counter">
                                    <c:set var="shoes" value=""/>
                                    <c:set var="quantity" value="${0}"/>
                                    <c:set var="price" value="${0}"/>
                                    <c:forEach var="field" items="${row.value}">

                                        <c:set var="item" value="${field.value}"/>
                                        <c:set var="shoes" value="${item.shoes}"/>
                                        <c:set var="quantity" value="${quantity + item.quantity}"/>
                                        <c:set var="price" value="${price + shoes.price}"/>

                                    </c:forEach>
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${shoes.description}</td>
                                        <td>${quantity}</td>
                                        <td>${shoes.price}</td>
                                        <td>${price}</td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="4">Total</td>
                                    <td>${cartObj.totalPrice}</td>

                                </tr>
                            </form>
                            </tbody>
                        </table>
                    </div>

                    <h2 class="title">Customer Information </h2>
                    <table class="w-100 bg">
                        <form action="confirmCheckOut" method="POST">
                            <tbody>
                                <tr>
                                    <td>OrderID:</td>
                                    <td>(auto generate)</td>
                                    <td>Date:</td>
                                    <td>(Current Date)</td>
                                </tr>
                                <tr>
                                    <td>Customer:</td>
                                    <td>(Full name of customer)</td>
                                    <td>Phone: </td>
                                    <td>(Phone number)</td>
                                </tr>
                                <tr>
                                    <td class="red-text">Address*:</td>
                                    <td colspan="3" class="input-group">
                                        <input type="text" name="address" value="${param.address}" class="input-text"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="red-text">Receiver*:</td>
                                    <td class="input-group">
                                        <input type="text" name="receiver" value="${param.receiver}" class="input-text" />
                                    </td>
                                    <td class="red-text">Receiver's Phone*:</td>
                                    <td class="input-group">
                                        <input type="text" name="receiverPhone" value="${param.receiverPhone}" class="input-text"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4">
                                        <button class="btn-primary" name="btnAction" value="Confirm">Confirm</button>
                                        <button name="btnAction" value="Back">Back</button>
                                        <button>Exit</button>
                                    </td>
                                </tr>
                            </tbody>
                        </form>
                    </table>
                </c:if>
            </c:if>
            <c:if test="${empty cartObj.list}">
                <h2 class="title red-text">No items in your cart!</h2>
            </c:if>
        </div>
    </body>
</html>
