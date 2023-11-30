<%-- 
    Document   : menu
    Created on : Jan 12, 2023, 3:24:17 PM
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
        <ul class="menu">
            <li><a href="index.html"> <img src="images/home_icon.png" alt="home" /> 
                </a></li>
            <li> <a href="#"> TIN TỨC </a> </li>
            <li> <a href="#"> TRẢI NGHIỆM </a> </li>
            <li><a href="#"> LIÊN HỆ </a></li>
            <li> THẺ ATM 
                <select class="ATM">
                    <option> AGRIBANK </option>
                    <option> VIETINBANK </option>
                    <option> TECHCOMBANK </option>
                </select>
            </li>
        </ul>
    </body>
</html>
