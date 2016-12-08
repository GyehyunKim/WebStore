<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js">
</script>
<script src="/resource/js/controllers.js"></script>
<title>Product</title>
</head>
<body>
  <section>
    <div class="jumbotron">
      <div class="container">
        <h1> Product </h1>
      </div>
    </div>
  </section>
  
  <section class="container" ng-app="cartApp">
    <div class="container">
   	  <div class="row">
   	  <div class="col-md-5">   
   	  <img src="<c:url value="/resource/images/${product.productId}.png"></c:url>"
   	        alt="Product image" style="width:100%"/>	
   	  </div>    
	    <div class="col-md-3">
	          <h3>${product.name}</h3>
	          <p>${product.description}</p>
	          <p><strong>Item code: </strong><span class="lable label-warning">${product.productId}</span></p>   	          
	          <p><strong>Manufacturer: </strong>${product.manufacturer}</p>
	          <p><strong>Category: </strong>${product.category}</p>
	          <p><strong>Available units in stock: </strong>${product.unitsInStock}</p>
	          <h4>${product.unitPrice } USD</h4>
	          
	          <p ng-controller="cartCtrl"><a href="#" class="btn btn-warning btn-large" 
	          ng-click="addToCart('${product.productId}')">
	          	<span class="glyphicon-shopping-cart glyphicon"></span>
	          	Order Now</a>
	          	<a href="<spring:url value="/cart" />" class="btn btn-default">
				  <span class="glyphicon-hand-right glyphicon"></span> View Cart
				</a>
	          </p>
	          
	          <p><a href="<spring:url value="/products" />" class="btn btn-default">
	          	<span class="glyphicon-hand-left glyphicon"></span>Back</a>
	          </p>
	    </div>    	         
      </div>
    </div>    
  </section>
</body>
</html>