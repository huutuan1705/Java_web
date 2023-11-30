<%-- 
    Document   : bai4
    Created on : Mar 10, 2023, 12:32:42 PM
    Author     : NguyenHuuTuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>

    <h3 style="color: red">${requestScope.error}</h3>
    <form action="bai4" method="post">
        username: <input type="text" name="user"><br> 
        password: <input type="password" name="pass"><br> 
        <input type="submit" value="Login">
    </form>
</body>
</html>
