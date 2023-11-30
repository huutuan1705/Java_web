<%-- 
    Document   : login
    Created on : Jan 26, 2023, 3:12:07 PM
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
        <h1>Login Form</h1>
        <h3 style="color: red">${requestScope.error}</h3>
        <c:set var="cookie" value="${pageContext.request.cookies}"/>
        <form action="login" method="post">
            enter username:<input type="text" name="user" value="${cookie.cuser.value}"/><br/>
            enter password:<input type="password" name="pass" value="${cookie.cpass.value}"/><br/>
            <input type="checkbox" ${(cookie.cuser.value!=null?'checked':'')} name="rem" value="ON"/>Remember me<br/>
            <input type="submit" value="LOGIN"/>
        </form>
    </body>
</html>
