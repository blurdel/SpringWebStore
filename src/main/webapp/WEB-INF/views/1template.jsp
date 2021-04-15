<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
				<h1 class="alert alert-danger">No product found</h1>
			</div>
		</div>
	</section>
	
	<section class="container">
		<div class="row">
		
			<div class="col-md-5">
				<img src="<c:url value="/resource/images/${product.productId}.png"></c:url>" 
				alt="image" style="width:100%">
			</div>
		
			<div class="container">
				<h3>${product.name}</h3>
				<p>${product.description}</p>
				<p>
					<strong>Item Code :</strong>
					<span class="label label-warning">${product.productId}</span>
				</p>
				
			</div>
		</div>
	</section>
</body>
</html>