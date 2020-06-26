<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/style.css"/>' />

<meta charset="ISO-8859-1">
<title>ChatApp Register Form</title>
</head>
<body>
	<p class="head1">User registration form</p>
	
	<p class="head1">${message }</p>
	<br>
	<form:form action="register" method="post" modelAttribute="user">

		<form:input class="input" type="text" path="userName"
			placeholder="Username" />
		<br>

		<form:input class="input" type="text" path="firstName"
			placeholder="First Name" />
		<br>


		<form:input class="input" type="text" path="lastName"
			placeholder="Last Name" />
		<br>

		<form:input class="input" type="date" path="dateOfBirth" placeholder="DOB"/>

		<br>
		<form:select class="input" path="gender">
			<form:options items="${genders}" itemValue="gender"
				itemLabel="gender" />
		</form:select>
		<br>
		<form:input class="input" type="text" path="password" placeholder="Password" />
		<br>
		<input class="submit" type="submit" name="button">
	</form:form>
	<br>
	<a class="head1" href="/Chat%20App/home">Go to Home</a>
</body>
</html>