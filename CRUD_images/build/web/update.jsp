<%-- 
    Document   : update
    Created on : Feb 7, 2023, 8:49:54 PM
    Author     : NguyenHuuTuan
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
        <h1>Update student</h1>
        <c:set var="c" value="${requestScope.student}"/>
        <form action="update" method="post" enctype="multipart/form-data">
            enter ID: <input type="text" readonly name="id" value="${c.id}"/><br/>
            enter name: <input type="text" name="name" value="${c.name}"/><br/>
            enter image: <input type="file" name="image" value="${c.image}" /><br/>
            <input type="submit" value="UPDATE"/>
        </form>
    </body>
</html>
