<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/style.css"/>' />

<title>ChatApp Login</title>
</head>
<body>
	<h3 class="head1">Login User</h3>
	<br>
	<p class="head1">${message }</p>
	<br>
	<form action="loginUser" method="post">
		<input class="input" type="text" name="userName" placeholder="Username"> 
		<br><br>
		<input class="input" type="password" name="password" placeholder="Password">
		<br><br> 
		<input class="submit" type="submit" name="button">
	</form>
	<br>
	<a class="head1" href="/Chat%20App/home">Go to Home</a>
</body>
</html>