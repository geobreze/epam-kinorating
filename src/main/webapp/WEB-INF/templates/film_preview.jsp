<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>

<div class="film-info">
    <h2 class="title-wrapper">
        <a href="${pageContext.request.contextPath}?command=show&id=${requestScope.film.id}" class="title service">
            <c:out value="${requestScope.film.title}" />
        </a>
    </h2>
    <p class="service"><fmt:message key="film.rating"/>:
        <c:choose>
            <c:when test="${requestScope.film.mark == 0}">
                <fmt:message key="film.rating.undefined"/>
            </c:when>
            <c:otherwise>
                ${requestScope.film.mark}
            </c:otherwise>
        </c:choose>
    </p>
</div>
<p class="description">
    <c:out value="${requestScope.film.description}" />
</p>
<div class="tools">
    <c:if test="${param.isMoreNeeded}">
        <form action="${pageContext.request.contextPath}/" method="get">
            <input type="hidden" name="command" value="show">
            <input type="hidden" name="id" value="${requestScope.film.id}">
            <button class="service"><fmt:message key="button.more"/></button>
        </form>
    </c:if>
    <c:if test="${sessionScope.user.role == 'ADMIN'}">
        <div class="admin-tools">
            <form action="${pageContext.request.contextPath}/" method="post" class="inline-flex">
                <input type="hidden" name="command" value="delete_film">
                <input type="hidden" name="id" value="${requestScope.film.id}">
                <button class="service"><fmt:message key="button.delete"/></button>
            </form>
            <form action="${pageContext.request.contextPath}/" method="get" class="inline-flex">
                <input type="hidden" name="command" value="edit_panel"/>
                <input type="hidden" name="id" value="${requestScope.film.id}">
                <button class="service"><fmt:message key="button.edit"/></button>
            </form>
        </div>
    </c:if>
</div>