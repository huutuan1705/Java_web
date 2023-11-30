<%-- 
    Document   : welcome
    Created on : Jan 12, 2023, 8:50:43 PM
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
        <%
            if(request.getAttribute("name")!=null){
                String name = (String)request.getAttribute("name");
        %>
        <h1>Hello <%= name %></h1>
        <%
            }
        %>
        <h1>Hello World!</h1>
    </body>
</html>
