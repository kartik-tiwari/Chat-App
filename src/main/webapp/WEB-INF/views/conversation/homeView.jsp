<%@page import="chatapp.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%
	User currentUser = (User) session.getAttribute("currentUser");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/style.css"/>' />

<meta charset="ISO-8859-1">
<title>ChatApp home</title>
</head>
<body>
	<p class="head1">${message }</p>
	<h3 class="head1">
		Welcome
		<%=currentUser.getFirstName()%>
		<%=currentUser.getLastName()%></h3>
	<br>
	<a class="head1" href="/Chat%20App/profile/<%=currentUser.getUserName()%>">My Profile</a>
	<a class="head1" href="/Chat%20App/logout">Logout</a>
	<br>
	<h4 class="head1">My Conversations</h4>
</body>
</html>