<%-- 
    Document   : register
    Created on : Jan 27, 2023, 8:38:55 AM
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
        <h1>Register form</h1>
        <h3 style="color: red">${requestScope.error1}</h3>
        <h3 style="color: red">${requestScope.error2}</h3>
        <form action="register" method="post">
            Username: <input type="text" name="user"/><br/>
            Password: <input type="password" name="pass"/><br/>
            Confirm password: <input type="password" name="passCon"/><br/>
            Email: <input type="text" name="email"/><br/>
            <input type="submit" name="REGISTER"/>
        </form>
    </body>
</html>
