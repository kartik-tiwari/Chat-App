<%@page import="chatapp.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%
	User currentUser = (User) session.getAttribute("currentUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ChatApp home</title>
</head>
<body>
	<p>${message }</p>
	<h3>
		Welcome
		<%=currentUser.getFirstName()%>
		<%=currentUser.getLastName()%></h3>
	<br>
	<a href="profile/<%=currentUser.getUserName()%>">My Profile</a>
	<a href="logout">Logout</a>
	<br>
	<h4>My Conversations</h4>
</body>
</html>