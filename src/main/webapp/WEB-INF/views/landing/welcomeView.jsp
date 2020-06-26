<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/style.css"/>' />
<title>Chat App Welcome</title>
</head>
<body>
	<h3 class ="head1">Welcome to Chat App</h3>
	<p>${message }</p>
	<br>
	<p class="head1"> <a href="/Chat%20App/loginView"> Go to login</a> </p>
	<br>
	<p class="head1"> <a href="/Chat%20App/registerView">Go to register</a></p>
</body>
</body>
</html>