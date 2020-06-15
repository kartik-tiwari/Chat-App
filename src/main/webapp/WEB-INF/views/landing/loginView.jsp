<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ChatApp Login</title>
</head>
<body>
<p>${message }</p>
<br>
<form action="loginUser" method="post">
<label>User Name : </label>
<input type="text" name="userName"><br>
<label>Password :</label>

<input type="text" name="password"><br>

<input type="submit" name="button">
</form>
<br>
<a href="home">Go to Home</a>
</body>
</html>