<%-- 
    Document   : disable
    Created on : Mar 8, 2022, 7:48:43 AM
    Author     : datnlm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Disable</h1>
        <form action="DeleteController" method="POST">
            ID: <input type="text" name="txtID"/><br>
            <input type="submit" name="Disable"/>
        </form>
    </body>
</html>
