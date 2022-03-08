<%-- 
    Document   : create
    Created on : Mar 8, 2022, 7:03:40 AM
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
        <h1>Hello World!</h1>
        <form action="CreateController" method="POST">
            ID: <input type="text" name="txtID"/></br>
            Type: <select name="cboType">
                <option value="attack">attack</option>
                <option value="defence">defence</option>
            </select></br>
            Name: <input type="text" name="txtName"/></br>
            Description <input type="text" name="txtDescription"/></br>
            Point: <input type="text" name="txtPoint"/></br>
            Status: <select name="cboStatus">
                <option value="enable">enable</option>
                <option value="disable">disable</option>
            </select></br>
            <input type="submit" value="Create"/>
        </form>
    </body>
</html>
