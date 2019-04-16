<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"  %>
<div class="left-menu pane">
    <span class="service">Greetings, ${sessionScope.user.login}</span>
    <ul type="none">
        <c:if test="${sessionScope.user.role == 'ADMIN'}">
            <li><a href="${pageContext.request.contextPath}?command=show_users" class="service">User list</a></li>
            <li><a href="${pageContext.request.contextPath}?command=add_panel" class="service">New film</a></li>
        </c:if>
    </ul>
    <form action="${pageContext.request.contextPath}/" method="post">
        <input type="hidden" name="command" value="logout">
        <button class="service log-out">Log out</button>
    </form>
</div>