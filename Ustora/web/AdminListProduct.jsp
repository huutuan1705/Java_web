<%-- 
    Document   : index
    Created on : Feb 17, 2023, 8:20:03 AM
    Author     : NguyenHuuTuan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Shop Page- Ustora Demo</title>

        <!-- Google Fonts -->
        <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>

        <!-- Bootstrap -->
        <link rel="stylesheet" href="css/bootstrap.min.css">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="css/font-awesome.min.css">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/owl.carousel.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/responsive.css">
        
        <script type="text/javascript">
            function doDelete(id){
                if(confirm("Are your sure that delete product with id = " +id)){
                    window.location="admindelete?id="+id;
                }
            }
        </script>
    </head>

    <body>
        <!--        Header-->
        <div class="header-area">
            <div class="container">
                <div class="row">
                    <div class="col-md-8">
                        <div class="user-menu">
                            <ul>
                                <li><a href="adminlist"><i class="fa fa-user"></i>${sessionScope.account.username}</a></li>
                                <li><a href="#"><i class="fa fa-heart"></i> Wishlist</a></li>
                                <li><a href="cart.html"><i class="fa fa-user"></i> My Cart</a></li>
                                <li><a href="checkout.html"><i class="fa fa-user"></i> Checkout</a></li>
                                <li><a href="logout"><i class="fa fa-user"></i> Logout</a></li>

                            </ul>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="header-right">
                            <ul class="list-unstyled list-inline">
                                <li class="dropdown dropdown-small">
                                    <a data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle" href="#"><span
                                            class="key">currency :</span><span class="value">USD </span><b
                                            class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">USD</a></li>
                                        <li><a href="#">INR</a></li>
                                        <li><a href="#">GBP</a></li>
                                    </ul>
                                </li>

                                <li class="dropdown dropdown-small">
                                    <a data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle" href="#"><span
                                            class="key">language :</span><span class="value">English </span><b
                                            class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">English</a></li>
                                        <li><a href="#">French</a></li>
                                        <li><a href="#">German</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- End header area -->

        <div class="site-branding-area">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="logo">
                            <h1><a href="./"><img src="img/logo.png"></a></h1>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="shopping-item">
                            <a href="cart.html">Cart - <span class="cart-amunt">$100</span> <i
                                    class="fa fa-shopping-cart"></i> <span class="product-count">5</span></a>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- End site branding area -->

        <div class="product-big-title-area">
            <div class="container" style="margin-left: 5%;">
                <div class="row">
                    <div class="col-md-12">
                        <div class="product-bit-title text-center">
                            <h2>ADMIN ACCOUNT</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!--End site admin-->

        <div class="single-product-area">
            <div class="container">
                <h2 style="text-align: center;">LIST PRODUCT</h2>
                <a href="adminadd" class="btn btn-primary stretched-link">Add new product</a>
                <br/>
                <br/>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">ID</th>
                            <th scope="col">Name</th>
                            <th scope="col">Category</th>
                            <th class="col">Price</th>
                            <th class="col">Availbale quantity</th>
                            <th class="col">Image</th>
                            <th class="col">Action</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${requestScope.listProduct}" var="c">
                            <c:set var="id" value="${c.id}"/>
                            <tr>
                                <td></td>
                                <td>${id}</td>
                                <td>${c.name}</td>
                                <td>${c.type}</td>
                                <td>${c.price}</td>
                                <td>${c.quantity}</td>
                                <td><img src="${c.image}" width="80px" height="80px"/></td>
                                <td>
                                    <a href="adminUpdate?id=${id}">Update</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="#" onclick="doDelete('${id}')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
