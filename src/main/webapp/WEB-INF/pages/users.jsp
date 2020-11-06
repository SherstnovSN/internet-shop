<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Users</title>
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
    <h3>Users</h3>
    <table>
        <tr>
            <th>Login</th>
            <th>Role</th>
        </tr>
        <c:forEach var="user" items="${usersList}">
            <tr>
                <td>${user.login}</td>
                <td align="center">${user.role}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
</div>
<div id="footer">

</div>
</body>
</html>