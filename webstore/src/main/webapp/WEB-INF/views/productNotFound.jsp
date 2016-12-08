<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-5"
    pageEncoding="ISO-8859-5"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Product</title>
</head>
<body>
  <section>
    <div class="jumbotron">
      <div class="container">
        <h1 class="alert alert-danger">There is no product found with Product ID ${invalidProductId}</h1>
      </div>
    </div>
  </section>
  
  <section>
    <div class="container">
      <p>${url}</p>
      <p>${exception}</p>
    </div>
    <div class="container">
      <p><a href="<spring:url value="/products" />" class="btn btn-primary">
      <span class="glyphicon-hand-left glyphicon"></span>Products</a></p>
    </div>    
  </section>  
</body>
</html>