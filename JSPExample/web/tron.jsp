<%-- 
    Document   : tron
    Created on : Jan 11, 2023, 9:00:42 PM
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
        <h1>Cach 2</h1>
        <form action="tinh" method="post">
            enter radius: <input type="text" name="r" /><br/>
            <input type="submit" value="submit"/>
        </form>
        
        <%
            if(request.getAttribute("dt")!=null){
                String s = (String)request.getAttribute("dt");
        %>
        <h2>Dien tich: <%= s%></h2>
        <%
            }
        %>
    </body>
</html>
