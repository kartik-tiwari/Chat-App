<%@page import="chatapp.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%
	User currentUser = (User) session.getAttribute("currentUser");
User targetUser = (User) request.getAttribute("targetUser");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/style.css"/>' />

<meta charset="ISO-8859-1">
<title>ChatApp Profile</title>
</head>
<body>
	<p class="head1">${message }</p>
	<p class="head1"><%=targetUser.getFirstName()%>
		<%=targetUser.getLastName()%>'s Profile
	</p>
	<h4 class="head1">
		Username:
		<%=targetUser.getUserName()%>
	</h4>
	<h4 class="head1">
		Date Of Birth :
		<%=targetUser.getDateOfBirth().getDate()%>
		/
		<%=targetUser.getDateOfBirth().getMonth()%>
		/
		<%=targetUser.getDateOfBirth().getYear()%>
	</h4>
	<h4 class="head1">
		Gender :
		<%=targetUser.getGender()%>
	</h4>
	<%
		if (currentUser.getUserName().equals((targetUser).getUserName())) {
	%>
	<a class="head1" href="/Chat%20App/editView">Edit Profile</a>
	<%
		} else {
	%>
	<button class="head1">Chat</button>
	<%
		}
	%>
	<a class="head1" href="/Chat%20App/userHome">Conversations</a>


</body>
</html>