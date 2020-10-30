<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Products</title>
		<link href="<c:url value="/images/favicon.ico"/>" rel="icon" type="image/x-icon" />
        <link href="<c:url value="/images/favicon.ico"/>" rel="shortcut icon" type="image/x-icon" />
		<link href="<c:url value="/style/style.css"/>" rel="stylesheet" type="text/css" />
		<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
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
			<a class="addButton" href="<c:url value="/add-new-product"/>">Add</a>
			<div id="content">
				<c:forEach var="product" items="${productList}">
				<div class="product${product.id}">
					<div class="product-item">
						<img src="<c:url value="/productImages/${product.image}"/>" alt="product image ${product.id}">
						<h3>${product.title}</h3>
						<p>${product.price}</p>
						<div class="productAction">
							<a class="editButton" href="<c:url value="/edit-product/${product.id}"/>">Edit</a>
							<a onclick="doAjax('${product.id}')" class="deleteButton">Delete</a>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
			<script type="text/javascript">
				function doAjax(id) {
					$.ajax({
						type: 'GET',
						url: 'delete-product',
						data: ({
							id: id
						}),
						success:
							$(".product" + id).remove()
					});
				}
			</script>
		</div>
		<div id="footer">
			
		</div>
	</body>
</html>