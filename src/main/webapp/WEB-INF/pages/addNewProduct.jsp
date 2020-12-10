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
		<%@ include file="header.jsp" %>
		<div id="main">
			<c:url value="/add-new-product" var="addProduct"/>
			<form action="${addProduct}" enctype="multipart/form-data" method="POST">
				<table>
					<tr>
						<td><label>Title:</label></td>
						<td><input type="text" name="title" pattern="[a-zA-Z0-9а-яА-Я -]{1,20}" required autofocus></td>
					</tr>
					<tr>
						<td><label>Price:</label></td>
						<td><input type="text" name="price" pattern="[0-9.]{1,20}" required></td>
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