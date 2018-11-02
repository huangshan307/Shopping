<%-- 
    Document   : reject
    Created on : Oct 31, 2018, 3:59:10 PM
    Author     : Huangshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Reject</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
    </head>
    <body>
        <div class="container">
            <h1 class="red-text title">Reject</h1>
            <p>
                All your cart will be removed. Are you sure? 
            </p>
            <form action="orderProduct" method="POST">
                <div class="w-100">
                    <button name="btnAction" value="OK" class="btn-primary">OK</button>
                    <button name="btnAction" value="Cancel">Cancel</button>
                </div>
            </form>
        </div>
    </body>
</html>
