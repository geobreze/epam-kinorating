<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"  %>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="locale"/>

<div class="left-menu pane">
    <span class="service"><fmt:message key="panel.greetings"/>, ${sessionScope.user.login}</span>
    <ul type="none">
        <c:if test="${sessionScope.user.role == 'ADMIN'}">
            <li><a href="${pageContext.request.contextPath}?command=show_users" class="service"><fmt:message key="panel.link.userlist"/></a></li>
            <li><a href="${pageContext.request.contextPath}?command=add_panel" class="service"><fmt:message key="panel.link.newfilm"/></a></li>
        </c:if>
    </ul>
    <form action="${pageContext.request.contextPath}/" method="post">
        <input type="hidden" name="command" value="logout">
        <button class="service log-out"><fmt:message key="button.logout"/></button>
    </form>
</div>