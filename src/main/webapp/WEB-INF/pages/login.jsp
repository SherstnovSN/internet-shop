<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Log in</title>
		<link href="<c:url value="/images/favicon.ico"/>" rel="icon" type="image/x-icon" />
        <link href="<c:url value="/images/favicon.ico"/>" rel="shortcut icon" type="image/x-icon" />
        <link href="<c:url value="/style/style.css"/>" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<%@ include file="header.jsp" %>
		<div id="main">
			<c:url value="/j_spring_security_check" var="loginUrl" />
			<form action="${loginUrl}" method="POST">
				<table>
					<tr>
						<td><label>Login:</label></td>
						<td><input type="text" name="j_username" required autofocus></td>
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td><input type="password" name="j_password" required></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Log in"></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>