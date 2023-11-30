<%-- 
    Document   : add
    Created on : Feb 6, 2023, 9:01:50 PM
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
        <h1>Add new Student</h1>
        <h3 style="color:red">${requestScope.error}</h3>
        <form action="add" method="post" enctype="multipart/form-data">
            enter ID: <input type="text" name="id"/><br/>
            enter name: <input type="text" name="name"/><br/>
            enter image: <input type="file" name="file" /><br/>
            <input type="submit" value="ADD NEW"/>
        </form>
    </body>
</html>
