<%-- 
    Document   : home
    Created on : May 10, 2023, 9:05:39 PM
    Author     : huutuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>List product</h1>
        <table border="1px" width="60%">
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>IMAGES</th>
                <th>PRICE</th>
                <th>QUANTITY</th>
                <th>DESCRIPTION</th>
                <th>ACTION</th>
            </tr>
            <c:forEach items="${requestScope.products}" var="c">
                <c:set var="id" value="${c.id}"/>
                <tr>
                    <td>${id}</td>
                    <td>${c.name}</td>
                    <td><img src="${c.image}" width="80px" height="80px"/></td>
                    <td>${c.price}</td>
                    <td>${c.quantity}</td>
                    <td>${c.description}</td>
                    <td>
                        <a href="updateProduct?id=${id}">Update</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="#" onclick="doDelete('${id}')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
