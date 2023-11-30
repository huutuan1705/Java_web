<%-- 
    Document   : list
    Created on : Jan 25, 2023, 8:20:52 PM
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
        <script type="text/javascript">
            function change() {
                document.getElementById("f1").submit();
            }
        </script>
    </head>
    <body>
        <div id='wrapper'>            
            <form id="f1" action="list">
                Dong dien thoai:
                <select name="key">
                    <option value="0">All</option>
                    <c:forEach items="${requestScope.categories}" var="c">
                        <option value="${c.id}">${c.name}</option>
                    </c:forEach>
                </select><br/>
                <input type="text" name="key2" 
                       placeholder="Nhap ten, mieu ta,..."/><br/>
                Tu gia: <input type="number" name="fromprice"/>
                Den gia: <input type="number" name="toprice"/><br/>
                Tu ngay: <input type="date" name="fromdate"/>
                Den ngay: <input type="date" name="todate"/><br/>
                <input type="submit" value="SEARCH"/>
            </form>
            <h3>CAC DONG DIEN THOAI</h3>
            <ul>
                <c:forEach items="${requestScope.products}" var="p">
                    <li>
                        <a href="#">
                            <img src="${p.image}"/>
                            <p>${p.name}</p>
                            <p>Gia goc: <span class="old">${(p.price*3)}</span> VND</p>
                            <p>Sale: ${(p.price)} VND</p>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>
