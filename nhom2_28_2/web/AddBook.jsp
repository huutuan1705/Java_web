<%-- 
    Document   : AddBook
    Created on : Mar 21, 2023, 9:12:27 AM
    Author     : NguyenHuuTuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Title here</title>
    </head>
    <body>
        <form action="books" method="post">
            Tittle: <input type="text" name="title"><br>
            Author: <input type="text" name="author"><br>
            Category: <input type="text" name="category"><br>
            Approved: <input type="checkbox" name="approved"><br>
            <input type="submit" value="Add Book"><br>
        </form>
    </body>
</html>
