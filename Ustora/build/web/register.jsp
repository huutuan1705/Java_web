<%-- 
    Document   : register
    Created on : Feb 17, 2023, 3:15:58 PM
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
        <link rel="stylesheet" href="css/register.css">
    </head>
    <body>
        <div class="container">
            <div class="title">Registration</div>
            <h3 style="color: red; font-size: 100">${requestScope.errorUsernameExist}</h3>
            <h3 style="color: red; font-size: 100" >${requestScope.errorConfirmPassword}</h3>
            <form action="register" method="post">
                <div class="user-details">
                    <div class="input-box">
                        <span class="details">Full name</span>
                        <input type="text" name="name" placeholder="Enter your name" required>
                    </div>

                    <div class="input-box">
                        <span class="details">Username</span>
                        <input type="text" name="user" placeholder="Enter your Username" required>
                    </div>

                    <div class="input-box">
                        <span class="details">Email</span>
                        <input type="text" name="email" placeholder="Enter your Email" required>
                    </div>

                    <div class="input-box">
                        <span class="details">Phone number</span>
                        <input type="text" name="phone" placeholder="Enter your Phone number" required>
                    </div>

                    <div class="input-box">
                        <span class="details">Password</span>
                        <input type="text" name="pass" placeholder="Enter your Password" required>
                    </div>

                    <div class="input-box">
                        <span class="details">Confirm Password</span>
                        <input type="text" name="conPass" placeholder="Confirm your Password" required>
                    </div>
                </div>

                <div class="gender-details">
                    <input type="radio" name="gender" value="male" id="dot-1">
                    <input type="radio" name="gender" value="female" id="dot-2">
                    <span class="gender-title">Gender</span>
                    <div class="category">
                        <label for="dot-1">
                            <span class="dot one"></span>
                            <span class="gender">Male</span>
                        </label>

                        <label for="dot-2">
                            <span class="dot two"></span>
                            <span class="gender">Female</span>
                        </label>
                    </div>
                </div>

                <div class="button">
                    <input type="submit" value="Register">
                </div>
            </form>
        </div>
    </body>
</html>
