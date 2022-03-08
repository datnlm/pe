<%-- 
    Document   : search
    Created on : Mar 8, 2022, 7:32:35 AM
    Author     : datnlm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <h1>Search</h1>
        <form action="SearchController" method="POST">
            formPoint <input type="text" name="txtFromPoint"/></br>
            toPoint <input type="text" name="txtToPoint"/></br> 
            <input type="submit" value="Search"/>
        </form>
    <c:if test="${requestScope.LIST_ITEM != null}">
        <table border="1">
            <thead>
                <tr>
                    <th>Number</th>
                    <th>Name</th>
                    <th>Point</th>
                    <th>Type</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="dto" items="${requestScope.LIST_ITEM}" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td>${dto.name}</td>
                    <td>${dto.point}</td>
                    <td>${dto.type}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </c:if>
    </body>
</html>
