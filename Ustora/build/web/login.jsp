<%-- 
    Document   : login
    Created on : Feb 17, 2023, 9:10:37 AM
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
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
        <div class="center">
            <h1>Login</h1>
            <h3 class="error">${requestScope.error}</h3>
            <form action="login" method="post">
                <div class="txt_field">
                    <input type="text" name="user" required>
                    <span></span>
                    <label>Username</label>
                </div>
                <div class="txt_field">
                    <input type="password" name="pass" required>
                    <span></span>
                    <label>Password</label>
                </div>
                <div class="pass">Forgot Password?</div>
                <input type="submit" value="Login">
                <div class="signup_link">
                    Not a member? <a href="register">Signup</a>
                </div>
            </form>
        </div>
    </body>
</html>
