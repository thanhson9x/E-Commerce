<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Access denied</title>
</head>
<body>
	<div style="text-align: center;">
		<div>
			<h1 style="color: red">You are not authorized to access this
				page</h1>
		</div>
		<div>
			User :
			<sec:authentication property="principal.username" />
		</div>
		<div>
			Role :
			<sec:authentication property="principal.authorities" />
		</div>

		<form:form action="${pageContext.request.contextPath}/logout"
			method="post">
			<a href="${pageContext.request.contextPath}/book">Back To Index</a>&nbsp;<input
				type="submit" value="Logout" />
		</form:form>
	</div>
</body>
</html>
