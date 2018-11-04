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
        <c:set var="user" value="${sessionScope.USER}"/>
        <c:if test="${empty user}">
            <c:redirect url="login.html"/>
        </c:if>
        <div class="container">
            <h1 class="title">Finishing Your Order</h1>
            <div class="welcome-name">
                <p>Welcome, <span>${user.username}</span>! (<a href="logout">Logout</a>)</p>
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
                                    <th>Product</th>
                                    <th>Quantity</th>
                                    <th>Size</th>
                                    <th>Price</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>
                            <form action="delete" method="GET">
                                <c:forEach var="row" items="${cartObj.list}" varStatus="counter">


                                    <c:forEach var="field" items="${row.value}">

                                        <c:set var="item" value="${field.value}"/>
                                        <c:set var="shoes" value="${item.shoes}"/>
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${shoes.description}</td>
                                            <td>${item.quantity}</td>
                                            <td>${item.sizes.sizes}</td>
                                            <td>${item.sizes.price}</td>
                                            <td>${item.price}</td>
                                        </tr>

                                    </c:forEach>

                                </c:forEach>
                                <tr>
                                    <td colspan="5"><b>Total</b></td>
                                    <td>${cartObj.totalPrice}</td>

                                </tr>
                            </form>
                            </tbody>
                        </table>
                    </div>

                    <c:set var="errorItems" value="${sessionScope.ErrorQuantityItem}" />
                    <c:if test="${not empty errorItems}">
                        <c:forEach var="row" items="${errorItems}">

                            <c:set var="item" value="${row.key}"/>
                            <c:set var="quantity" value="${row.value}"/>

                            <div class="red-text">${item.shoes.description}, size: ${item.sizes.sizes} - not enough quantity only have ${quantity}</div>
                            
                        </c:forEach>
                    </c:if>
                            <br />

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
                                    <td>${user.firstName} ${user.lastName} ${user.middleName}</td>
                                    <td>Phone: </td>
                                    <td>(Phone number)</td>
                                </tr>
                                <tr>
                                    <td class="red-text">Address*:</td>
                                    <td colspan="3" class="input-group">
                                        <input type="text" name="address" value="${param.address}" class="input-text" required="required"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="red-text">Receiver*:</td>
                                    <td class="input-group">
                                        <input type="text" name="receiver" value="${param.receiver}" class="input-text" required="required"/>
                                    </td>
                                    <td class="red-text">Receiver's Phone*:</td>
                                    <td class="input-group">
                                        <input type="text" name="receiverPhone" value="${param.receiverPhone}" class="input-text" required="required"/>
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
