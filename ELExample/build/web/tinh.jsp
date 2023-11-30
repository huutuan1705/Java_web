<%-- 
    Document   : tinh
    Created on : Jan 16, 2023, 8:26:15 AM
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
        <h1>VD cho EL</h1>
        <form>
            Nhap so 1:<input type="text" name="n1" value="${param.n1}"/><br/>
            Nhap so 2:<input type="text" name="n2" value="${param.n2}"/><br/>
            Nhap so 3:<input type="text" name="n3" value="${param.n3}"/><br/>
            <input type="submit" value="tinh toan"/>
        </form>
        <h2>Tong: ${(param.n1+param.n2+param.n3)}</h2>
        <h2>Trung binh: ${(param.n1+param.n2+param.n3)/3}</h2>
        <h2>Dien tich duong tron n1: ${(param.n1*param.n1*Math.PI)}</h2>
        
        <h4>Du lieu lay tu server
            <br/>
            Hello ${requestScope.name}
        </h4>
        <h2 style="color: chocolate">Thong tin${requestScope.st}
            
        </h2>
    </body>
</html>
