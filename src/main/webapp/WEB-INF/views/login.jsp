<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
				<p>Add Products</p>
			</div>
		</div>
		<a href="<spring:url value="/products" />" class="btn btn-default">Products</a>
	</section>
	
	<div class="container">
		<div class="row">
		<div class="col-md-4 col-md-offset-4">
		<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Please sign in</h3>
		</div>
			<div class="panel-body">
				<c:if test="${not empty error}">
					<div class="alert alert-danger">
						<spring:message code="AbstractUserDetailsAuthenticationProvider.badCredentials"/><br>
					</div>
				</c:if>
				<form action="<c:url value="/j_spring_security_check"></c:url>" method="post">
					<fieldset>
					<div class="form-group">
						<input class="form-control" placeholder="User Name" name="j_username" type="text">
					</div>
					<div class="form-group">
						<input class="form-control" placeholder="Password" name="j_password" type="password" value="">
					</div>
					<input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
					</fieldset>
				</form>
			</div>
		</div>
		</div>
		</div>	
	</div>
</body>
</html>