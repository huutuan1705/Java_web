<%-- 
    Document   : list
    Created on : Jan 24, 2023, 8:58:23 PM
    Author     : NguyenHuuTuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>
        <div id="wrapper">
            <div id="menu_tab">
                <ul class="menu">
                    <li><a href="product?cid=${0}">ALL</a></li>
                    <c:forEach items="${requestScope.data}" var="c">
                        <li><a href="product?cid=${c.id}">${c.name}</a></li>
                    </c:forEach>

                </ul>
            </div>
            <div class="clr"></div>
            <div id="content">
                <c:set var="list" value="${requestScope.products}"/>
                <c:if test="${((list==null) || (list.size()==0))}">
                    <h3>No product</h3>
                </c:if>
                
                <c:if test="${((list!=null) && (list.size()>0))}">
                  
                <table border="1px">
                    <tr>
                        <th>ID</th>
                        <th>NAME</th> 
                        <th>PRICE</th> 
                        <th>IMAGE</th>  
                        <th>CATEGORY</th>
                    </tr>
                    <c:forEach items="${list}" var="p">
                        <tr>
                            <td>${p.id}</td>
                            <td>${p.name}</td>
                            <td>${p.price}</td>
                            <td><img src="${p.image}" width="80px" height="80px"/></td>
                            <td>${p.category.name}</td>
                        </tr>
                    </c:forEach>
                </table>
                </c:if>  
            </div>
        </div>
    </body>
</html>
