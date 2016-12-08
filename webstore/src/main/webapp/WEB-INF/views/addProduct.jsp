<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix= "spring" uri="http://www.springframework.org/tags"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Products</title>
</head>
<body>
  <section>
    <div class="jumbotron">
      <a href="<c:url value="/j_spring_security_logout" />" class="btn btn-danger btn-mini pull-right">
      logout</a>	
      <div class="container">
        <h1>Products</h1>
        <p>Add products</p>
      </div>
    </div>
  </section>
  <section class="container">
  	<form:form modelAttribute="newProduct" class="form-horizontal" enctype="multipart/form-data" method="POST">
  	<form:errors path="*" cssClass="alert alert-danger" element="div"/>  	
  		<fieldset>
  			<legend>Add new product</legend>
  			<div class="form-group">
  				<!-- Chapter 4. externalizing messages -->
  				<label class="control-label col-lg-2" for="productId">
  					<spring:message code="addProduct.form.productId.label"/></label>
  				<div class="col-lg-10">
  					<form:input id="productId" path="productId" type="text" class="form:input-large"/>
  					<form:errors path="productId" cssClass="text-danger"/>
  				</div>
  			</div>
  			
  			<div class="form-group">
  				<label class="control-label col-lg-2" for="name">
  					<spring:message code="addProduct.form.name.label"/></label>
  				<div class="col-lg-10">
  					<form:input id="name" type="text" path="name" class="form:input-large"/>
  				</div>
  			</div>
  			
  			<div class="form-group">
  				<label class="control-label col-lg-2" for="unitPrice">Unit Price</label>
  				<div class="col-lg-10">
  					<form:input id="unitPrice" type="text" path="unitPrice" class="form:input-large"/>
  				</div>
  			</div>
  			
  			<div class="form-group">
  				<label class="control-label col-lg-2" for="manufacturer">Manufacturer</label>
  				<div class="col-lg-10">
  					<form:input id="manufacturer" type="text" path="manufacturer" class="form:input-large"/>
  				</div>
  			</div>
  			
  			<div class="form-group">
  				<label class="control-label col-lg-2" for="category">Category</label>
  				<div class="col-lg-10">
  					<form:input id="category" type="text" path="category" class="form:input-large"/>
  				</div>
  			</div>
  			
  			<div class="form-group">
  				<label class="control-label col-lg-2" for="unitsInStock">Units In Stock</label>
  				<div class="col-lg-10">
  					<form:input id="unitsInStock" type="text" path="unitsInStock" class="form:input-large"/>
  				</div>
  			</div>
  			
  			<%-- <div class="form-group">
  				<label class="control-label col-lg-2" for="unitsInOrder">Units In Order</label>
  				<div class="col-lg-10">
  					<form:input id="unitsInOrder" type="text" path="unitsInOrder" class="form:input-large"/>
  				</div>
  			</div>   --%>			
  			
  			<div class="form-group">
  				<label class="control-label col-lg-2" for="description">Description</label>
  				<div class="col-lg-10">
  					<form:textarea id="description" path="description" row="2"/>
  				</div>
  			</div>
  			
  			<%-- <div class="form-group">
  				<label class="control-label col-lg-2" for="discontinued">Discontinued</label>
  				<div class="col-lg-10">
  					<form:checkbox id="discontinued" path="discontinued"/>
  				</div>
  			</div> --%>
  			
  			<div class="form-group">
  				<label class="control-label col-lg-2" for="condition">Condition</label>
  				<div class="col-lg-10">
  					<form:radiobutton path="condition" value="New" />New
  					<form:radiobutton path="condition" value="Old"/>Old
  					<form:radiobutton path="condition" value="Refurbished"/>Refurbished
  				</div>
  			</div>
  			
  			<div class="form-group">
  				<label class="control-label col-lg-2" for="productImage">
<%--   				  <spring:message code="addProduct.form.productImage.label"/> --%>
					Product Image file
  				</label>
  				<div class="col-lg-10">
  					<form:input id="productImage" path="productImage" type="file" class="form:input-large"/>
  				</div>
  			</div>
  			
  			<div class="form-group">
	          <div class="col-lg-offset-2 col-lg-10">
	            <input type="submit" id="btnAdd" class="btn btn-primary" value ="Add"/>
	          </div>
	        </div>
  			
  		</fieldset>
  		
  	</form:form>
  </section>
  
</body>
</html>