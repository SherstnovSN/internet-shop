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
        <sec:authorize access="hasAnyAuthority('ADMIN', 'MODERATOR')">
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
        <sec:authorize access="hasAuthority('USER')">
            <a class="imgButton" href="<c:url value="/cart"/>"><img src="<c:url value="/images/cart.png"/>" width="25" height="15" alt="Cart"></a>
            <div id="totalProducts">
                <c:if test="${cart.totalProducts != 0}">
                    ${cart.totalProducts}
                </c:if>
            </div>
        </sec:authorize>
    </div>
</div>