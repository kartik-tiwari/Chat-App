<%@page import="spring.chatapp.user.data.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
User currentUser =(User)session.getAttribute("currentUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chat App home</title>
</head>
<body>
<h3>Welcome <%=currentUser.getFirstName() %> <%=currentUser.getLastName() %></h3>
<br>
<a href="profile/<%=currentUser.getUserName()%>">My Profile</a>
<a href="logout">Logout</a>
<br>
<h4>My Conversations</h4>
</body>
</html>