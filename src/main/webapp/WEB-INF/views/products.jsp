<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Products</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Products</h1>
				<p>All available products in our store</p>
			</div>
		</div>
		<a href="<spring:url value="/" />" class="btn btn-default">Home</a>
		<a href="<spring:url value="/products/add"/>" class="btn btn-warning btn-large">Add Product</a> 
	</section>
	
	<section class="container">
		<div class="row">
			<c:forEach items="${products}" var="product">
				<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
					
					<div class="thumbnail">
					<img src="<c:url value="/resource/images/${product.productId}.png"></c:url>" 
					alt="image" style="width:100%">
					
						<div class="caption">
							<h3>${product.name}</h3>
							<p>${product.description}</p>
							<p>$${product.unitPrice}</p>
							<p>Available ${product.unitsInStock} units in stock</p>
							<p>
							<a href=" <spring:url value="/products/product?id=${product.productId}" /> " class=
								"btn btn-primary">
							<span class="glyphicon-info-sign glyphicon"></span> Details</a> 
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
</body>
</html>