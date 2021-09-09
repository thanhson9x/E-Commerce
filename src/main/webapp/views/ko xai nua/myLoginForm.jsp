<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style>
.error {
	color: red;
}
</style>
<title>My Login Page</title>
</head>
<body>
	<div>
		<div>
			<h1>My Login Form</h1>
		</div>

		<form:form action="authenticateTheUser" method="post">

			<c:if test="${param.error != null }">
				<span class="error"> Invalid username/password </span>
				<br>
				<br>
			</c:if>

			<div>
				User(*) : <input type="text" name="email" />
				<form:errors path="email" cssClass="error" />
			</div>
			<br>

			<div>
				Password(*) : <input type="password" name="password" />
				<form:errors path="password" cssClass="error" />
			</div>
			<br>

			<div style="margin-top: 20px">
				<input type="submit" value="Login" />
			</div>

		</form:form>
	</div>
</body>
</html>
