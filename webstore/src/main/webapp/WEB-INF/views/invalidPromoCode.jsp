<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Welcome</title>
</head>
<body>
  <section>
    <div class="jumbotron">
      <div class="container">
        <h1 class="alert alert-danger"> Invalid Promotion Code </h1>        
      </div>
    </div>
  </section>
  
  <section>
    <div class="container">
      <p><a href="<spring:url value="/products" />" class="btn btn-default">
	       <span class="glyphicon-hand-left glyphicon"></span>
	       Back</a>
	  </p>
    </div>
  </section>
</body>
</html>