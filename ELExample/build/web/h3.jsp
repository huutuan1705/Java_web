<%-- 
    Document   : h3.jsp
    Created on : Jan 16, 2023, 9:12:25 AM
    Author     : NguyenHuuTuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>VD cho JSTL</h1>
        <c:set var="s" value="${requestScope.st}"/>
        <h2>Hello ${s.name}</h2>
        <img src="${(s.gender?'images/male.jpg':'images/female.jpg')}" width="30px" height="30px"/>
        <c:if test="${s.mark>=5}">
            <h3>Pass</h3>
        </c:if>
        <c:if test="${s.mark<5}">
            <h3>Fail</h3>
        </c:if>
        <h1>Danh sach sinh vien</h1>
        <table border="1px" width="40%">
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>GENDER</th>
                <th>MARK</th>
                <th>RANK</th>
            </tr>
            <c:forEach items="${requestScope.data}" var="i">
                <tr>
                    <td>${i.id}</td>
                    <td>${i.name}</td>
                    <td>
                        <img src="${(i.gender?'images/male.jpg':'images/female.jpg')}" width="30px" height="30px"/>
                    </td>
                    <td>${i.mark}</td>
                    <td>
                        <c:choose>
                            <c:when test="${i.mark<5}">
                                weak
                            </c:when>
                                
                            <c:when test="${(i.mark>=5 && i.mark<8)}">
                                average
                            </c:when>
                                
                            <c:when test="${i.mark>=8}">
                                excellent
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
