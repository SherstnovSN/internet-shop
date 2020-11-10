<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<%@ include file="header.jsp" %>
		<div id="main">
			<div id="content">
				<c:forEach var="product" items="${productList}">
					<div class="product-item">
						<img src="<c:url value="/productImages/${product.image}"/>" alt="product image ${product.id}">
						<div class="product-list">
							<h3>${product.title}</h3>
							<p>${product.price}</p>
							<sec:authorize access="!isAuthenticated()">
								<a href="<c:url value="/login"/>">
									<button>Buy</button>
								</a>
							</sec:authorize>
							<sec:authorize access="isAuthenticated()">
								<c:set var="productId" value="${product.id}"/>
								<button onclick="doAjax(${productId})">Buy</button>
							</sec:authorize>
						</div>
					</div>
				</c:forEach>
			</div>
			<script type="text/javascript">
				function doAjax(productId) {

				    $.ajax({
				        type: 'GET',
				        url: 'buy',
				        dataType: 'json',
				        data: ({
				        	id: productId
				        }),
				        success: function(data) {
				        	$("#totalProducts").text(data);
				        }
				    });
				}
	        </script>
		</div>
		<div id="footer">

		</div>
	</body>
</html>