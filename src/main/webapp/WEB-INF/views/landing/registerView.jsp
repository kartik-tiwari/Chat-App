<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ChatApp Register Form</title>
</head>
<body>
	<p>${message }</p>
	<br>
	User registration form
	<form:form action="register" method="post">
		<label>User Name : </label>
		<form:input type="text" path="userName" />
		<br>

		<label>First Name : </label>
		<form:input type="text" path="firstName" />
		<br>

		<label>Last Name : </label>
		<form:input type="text" path="lastName" />
		<br>

		<label>Birthday:</label>
		<form:input type="date" path="dateOfBirth" />
		<br>
		<label> Gender</label>
		<br>
		<form:select path="gender">
			<form:options items="${genders}" itemValue="gender"
				itemLabel="gender" />
		</form:select>
		<br>
		<label>Password :</label>
		<form:input type="text" path="password" />
		<br>
		<input type="submit" name="button">
	</form:form>
	<br>
<a href="home">Go to Home</a>
</body>
</html>