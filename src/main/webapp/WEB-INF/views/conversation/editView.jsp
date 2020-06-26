<%@page import="chatapp.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	User currentUser = (User) session.getAttribute("currentUser");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/style.css"/>' />
<meta charset="ISO-8859-1">
<title>ChatApp Edit</title>
</head>
<body>
	<p class="head1">${message }</p>
	<h4 class="head1">Update User</h4>

	<form:form action="editUser" method="post" modelAttribute="user">
		<p class="head1">
			User Name :
			<%=currentUser.getUserName()%></p>
		<form:input type="hidden" path="userName" />
		<br>

		<form:input class="input" type="text" path="firstName"
			placeholder="First Name" />
		<br>


		<form:input class="input" type="text" path="lastName"
			placeholder="Last Name" />
		<br>

		<form:input class="input" type="date" path="dateOfBirth"
			placeholder="DOB" />

		<br>
		<form:select class="input" path="gender">
			<form:options items="${genders}" itemValue="gender"
				itemLabel="gender" />
		</form:select>
		<br>
		<form:input class="input" type="hidden" path="password"
			placeholder="Password" />
		<br>
		<input class="submit" type="submit" name="button">
	</form:form>

</body>
</html>