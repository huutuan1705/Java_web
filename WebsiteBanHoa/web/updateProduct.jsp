<%-- 
    Document   : updateProduct
    Created on : May 10, 2023, 9:29:15 PM
    Author     : huutuan
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
        <h1>Update Product</h1>
        <c:set var="c" value="${requestScope.product}"/>
        <form action="updateProduct" method="post" enctype="multipart/form-data">
            ID: <input type="text" readonly name="id" value="${c.id}"/><br/>
            Name: <input type="text" name="name" value="${c.name}"/><br/>
            Image: <input type="file" name="image" value="${c.image}" /><br/>
            Price: <input type="text" name="price" value="${c.price}"/><br/>
            Quantity: <input type="text" name="quantity" value="${c.quantity}"/><br/>
            Description: <input type="text" name="description" value="${c.description}"/><br/>
            <input type="submit" value="UPDATE"/>
        </form>
    </body>
</html>
