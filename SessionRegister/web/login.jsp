<%-- 
    Document   : login
    Created on : Jan 27, 2023, 8:38:47 AM
    Author     : NguyenHuuTuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login form</h1>
        <form action="login" method="post">
            Enter username: <input type="text" name="user"/><br/>
            Enter password: <input type="password" name="pass"/><br/>
            <input type="submit" value="LOGIN"/>
            <a href="register">REGISTER</a>
        </form>
    </body>
</html>
