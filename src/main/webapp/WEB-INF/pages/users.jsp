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
        <%@ include file="header.jsp" %>
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