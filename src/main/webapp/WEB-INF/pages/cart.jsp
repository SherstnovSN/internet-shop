<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Cart</title>
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
			<c:if test = "${cart.totalProducts != 0}">
				<h3>Cart</h3>
				<table>
					<tr>
						<th>Title</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Cost</th>
						<th>Action</th>
					</tr>
					<c:forEach var="products" items="${cart.products}">
						<tr>
							<td>${products.product.title}</td>
							<td align="center">${products.product.price}</td>
							<td align="center">${products.quantity}</td>
							<td align="center">${products.product.price * products.quantity}</td>
							<td align="center"><a class="imgButton" href="<c:url value="/increase-product-quantity/${products.product.id}"/>"><img src="<c:url value="/images/plus.png"/>" width="13" height="13" alt="Cart"></a>
								<a class="imgButton" href="<c:url value="/reduce-product-quantity/${products.product.id}"/>"><img src="<c:url value="/images/minus.png"/>" width="13" height="13" alt="Cart"></a> 
								<a class="deleteButton" href="<c:url value="/delete-product/${products.product.id}"/>">Delete</a></td>
						</tr>
					</c:forEach>
					<tr>
						<td></td>
						<td></td>
						<td align="center">${cart.totalProducts}</td>
						<td align="center">${cart.totalCost}</td>
						<td align="center"><a class="deleteButton" href="<c:url value="/clear"/>">Clear</a></td>
					</tr>
				</table>
			</c:if>
			<c:if test = "${cart.totalProducts == 0}">
				<div id="emptyCart">
					<h2>The cart is empty.</h2>
					<p>Go to the <a class="catalogButton" href="<c:url value="/catalog"/>">catalog</a> to buy products.</p>
				</div>
			</c:if>
		</div>
		<div id="footer">
			
		</div>
	</body>
</html>