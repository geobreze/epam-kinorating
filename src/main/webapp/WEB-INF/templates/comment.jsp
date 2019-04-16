<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"  %>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="locale"/>

<div id="comment${requestScope.comment.id}" class="comment">
    <div class="comment-header">
        <span class="service">${requestScope.comment.author.login}</span>
        <c:if test="${sessionScope.user.role == 'ADMIN'}">
            <form action="${pageContext.request.contextPath}/" method="POST">
                <input type="hidden" name="command" value="delete_comment" />
                <input type="hidden" name="film_id" value="${requestScope.comment.filmId}">
                <input type="hidden" name="id" value="${requestScope.comment.id}">
                <button class="service delete-comment"><fmt:message key="button.delete" /></button>
            </form>
        </c:if>
    </div>
    <p>
        ${requestScope.comment.text}
    </p>
</div>