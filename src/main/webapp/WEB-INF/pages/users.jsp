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
                    <th>Action</th>
                </tr>
                <c:forEach var="user" items="${usersList}">
                    <c:url value="/edit-role" var="editRole"/>
                    <form action="${editRole}" method="POST">
                        <tr>
                            <td>${user.login}<input type="hidden" name="login" value="${user.login}"></td>
                            <td align="center">${user.role}</td>
                            <td>
                                <c:if test="${!user.role.equals('ADMIN')}">
                                    <select name="role">
                                        <c:forEach var="role" items="${rolesList}">
                                            <c:if test="${!role.equals(user.role) && !role.equals('ADMIN')}">
                                                <option value="${role}">${role}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <input type="submit" value="Edit">
                                </c:if>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </table>
            <br>
        </div>
        <div id="footer">

        </div>
    </body>
</html>