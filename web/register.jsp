<%-- 
    Document   : register
    Created on : Nov 3, 2018, 6:50:50 PM
    Author     : Huangshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}"/>
        <c:if test="${not empty user}">
            <c:redirect url="search.jsp"/>
        </c:if>
        <c:set var="errors" value="${requestScope.ERRORS}"/>
        <div class="container">
            <h1 class="title">Create new account</h1>
            <form action="register" method="POST">
                <table cellspacing="10" class="w-100">
                    <tr>
                        <td class="input-group" colspan="3">
                            <label for="username">Username</label>
                            <input type="text" name="username" value="${param.username}" id="username" class="input-text" autofocus/>
                            <small class="red-text">${errors.username}</small>
                            <small class="red-text">${errors.usernameExist}</small>
                        </td>
                    </tr>
                    <tr>
                        <td class="input-group" colspan="3">
                            <label for="password">Password</label>
                            <input type="password" name="password" value="${param.password}" id="password" class="input-text"/>
                            <small class="red-text">${errors.password}</small>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="input-group">
                            <label for="lastName">Last Name</label>
                            <input type="text" name="lastName" value="${param.lastName}" id="lastName" class="input-text">
                            <small class="red-text">${errors.lastName}</small>
                        </td>
                        <td class="input-group">
                            <label for="middleName">Middle Name</label>
                            <input type="text" name="middleName" value="${param.middleName}" id="middleName" class="input-text">
                            <small class="red-text">${errors.middleName}</small>
                        </td>
                        <td class="input-group">
                            <label for="firstName">First Name</label>
                            <input type="text" name="firstName" value="${param.firstName}" id="firstName" class="input-text">
                            <small class="red-text">${errors.firstName}</small>
                        </td>
                    </tr>
                    <tr>
                        <td class="input-group" colspan="3">
                            <label for="address">Address</label>
                            <input type="text" name="address" value="${param.address}" id="address" class="input-text"/>
                            <small class="red-text">${errors.address}</small>
                        </td>
                    </tr>
                    <tr>
                        <td class="input-group" colspan="3">
                            <label for="phone">Phone</label>
                            <input type="text" name="phone" value="${param.phone}" id="phone" class="input-text"/>
                            <small class="red-text">${errors.phone}</small>
                        </td>
                    </tr>
                    <tr>
                        <td class="input-group" colspan="3">
                            <button type="submit" name="btnAction" value="Register" class="btn-primary">Register</button>
                            <button type="reset">Reset</button>
                        </td>
                    </tr>
                </table>
            </form>
            <p>Have a account? <a href="login.html">Login</a></p>
        </div>
    </body>
</html>
