<%@page import="spring.chatapp.user.data.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	User currentUser = (User) session.getAttribute("currentUser");
User targetUser = (User) request.getAttribute("targetUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chat App Profile</title>
</head>
<body>
	<h3><%=targetUser.getFirstName()%>
		<%=targetUser.getLastName()%>'s Profile
	</h3>
	<h4>
		Username:
		<%=targetUser.getUserName() %>
	</h4>
	<h4>
		Date Of Birth :
		<%=targetUser.getDateOfBirth().getDate()%>
		/
		<%=targetUser.getDateOfBirth().getMonth()%>
		/
		<%=targetUser.getDateOfBirth().getYear()%>
	</h4>
	<h4>
		Gender :
		<%=targetUser.getGender()%>
	</h4>
	<%if(currentUser.equals(targetUser)) {%>
	<button>Edit Profile</button>
	<%} else{ %>
	<button>Chat</button>
	<%} %>
	
</body>
</html>