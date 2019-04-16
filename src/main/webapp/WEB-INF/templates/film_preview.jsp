<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"  %>
    <div class="film-info">
        <h2 class="title-wrapper">
            <a href="${pageContext.request.contextPath}?command=show&id=${requestScope.film.id}" class="title service">
                ${requestScope.film.title}
            </a>
        </h2>
        <p class="service">Rating:
            <c:choose>
                <c:when test="${requestScope.film.mark == 0}">
                    N/A
                </c:when>
                <c:otherwise>
                    ${requestScope.film.mark}
                </c:otherwise>
            </c:choose>
        </p>
    </div>
    <p class="description">
        ${requestScope.film.description}
    </p>
    <div class="tools">
        <c:if test="${param.isMoreNeeded}">
            <form action="${pageContext.request.contextPath}/" method="get">
                <input type="hidden" name="command" value="show">
                <input type="hidden" name="id" value="${requestScope.film.id}">
                <button class="service">More</button>
            </form>
        </c:if>
        <c:if test="${sessionScope.user.role == 'ADMIN'}">
            <div class="admin-tools">
                <form action="${pageContext.request.contextPath}/" method="post" class="inline-flex">
                    <input type="hidden" name="command" value="delete_film">
                    <input type="hidden" name="id" value="${requestScope.film.id}">
                    <button class="service">Delete</button>
                </form>
                <form action="${pageContext.request.contextPath}/" method="get" class="inline-flex">
                    <input type="hidden" name="command" value="edit_panel" />
                    <input type="hidden" name="id" value="${requestScope.film.id}">
                    <button class="service">Edit</button>
                </form>
            </div>
        </c:if>
    </div>