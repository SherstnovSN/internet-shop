<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Registration</title>
		<link href="<c:url value="/images/favicon.ico"/>" rel="icon" type="image/x-icon" />
        <link href="<c:url value="/images/favicon.ico"/>" rel="shortcut icon" type="image/x-icon" />
        <link href="<c:url value="/style/style.css"/>" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div id="header">
			<div id="name"><img src="<c:url value="/images/logo.png"/>" width="25" height="30" alt="logo"></div>
			<div id="navigation">
				<a class="headerButton" href="<c:url value="/"/>">Home</a>
				<sec:authorize access="!isAuthenticated()">
					<a class="headerButton" href="<c:url value="/catalog"/>">Catalog</a>
				</sec:authorize>
				<sec:authorize access="hasAuthority('USER')">
					<a class="headerButton" href="<c:url value="/catalog"/>">Catalog</a>
				</sec:authorize>
				<sec:authorize access="hasAuthority('ADMIN')">
					<a class="headerButton" href="<c:url value="/product"/>">Catalog</a>
				</sec:authorize>
				<sec:authorize access="hasAuthority('ADMIN')">
					<a class="headerButton" href="<c:url value="/users"/>">Users</a>
				</sec:authorize>
			</div>
			<div id="user">	
				<sec:authorize access="!isAuthenticated()">
					<a class="headerButton" href="<c:url value="/login"/>">Login</a>
					<a class="headerButton" href="<c:url value="/registration"/>">Registration</a>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					Login: <sec:authentication property="principal.username"/>
					<a class="headerButton" href="<c:url value="/logout"/>">Logout</a>
				</sec:authorize>
			</div>	
		</div>
		<div id="main">
			<c:url value="/registration" var="registration"/>
			<form action="${registration}" method="POST">
				<table>
					<tr>
						<td><label>Login:</label></td>
						<td><input type="text" name="login" pattern="[a-zA-Z0-9_\-]{3,20}" required autofocus> ("a-z", "A-Z", "0-9", "_", "-")</td>
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td><input type="password" name="password" pattern=".{6,12}" required> (6-12 characters)</td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Register"></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>