<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Add product</title>
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
			<c:url value="/add-new-product" var="addProduct"/>
			<form action="${addProduct}" enctype="multipart/form-data" method="POST">
				<table>
					<tr>
						<td><label>Title:</label></td>
						<td><input type="text" name="title" required autofocus></td>
					</tr>
					<tr>
						<td><label>Price:</label></td>
						<td><input type="text" name="price" required></td>
					</tr>
					<tr>
						<td><label>Image:</label></td>
						<td><input type="file" name="image" required></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Add product"></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>