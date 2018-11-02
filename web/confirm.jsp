<%-- 
    Document   : confirm
    Created on : Oct 31, 2018, 3:31:43 PM
    Author     : Huangshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Confirm</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
    </head>
    <body>
        <div class="container">
            <h1>Confirm</h1>
            <div class="welcome-name">
                <p>Welcome, <span>${sessionScope.USER.username}</span>! (<a href="logout">Logout</a>)</p>
            </div>
            <div class="w-100 view-cart">
                <a href="search.jsp">Search</a>
            </div
            <h2 class="title">Your Information</h2>
            <form action="confirmCheckOut" method="POST">
                <table class="w-100 bg">
                    <tbody>
                        <tr>
                            <td>Customer ID</td>
                            <td></td>
                            <td>OrderID</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Customer</td>
                            <td></td>
                            <td>Phone</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Address</td>
                            <td colspan="3">
                                ${param.address}
                                <input type="hidden" name="address" value="${param.address}" />
                            </td>
                        </tr>
                        <tr>
                            <td>Receiver</td>
                            <td>
                                ${param.receiver}
                                <input type="hidden" name="receiver" value="${param.receiver}" />
                            </td>
                            <td>Receiver's Phone</td>
                            <td>
                                ${param.receiverPhone}
                                <input type="hidden" name="receiverPhone" value="${param.receiverPhone}" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <button name="btnAction" value="OK" class="btn-primary">OK</button>
                                <button name="btnAction" value="Cancel">Cancel</button>
                            </td>
                        </tr>
                    </tbody>
                </table>

            </form>
        </div>
    </body>
</html>
