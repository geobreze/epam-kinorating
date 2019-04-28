<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
    <meta charset="UTF-8"/>
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'/>
    <title>${requestScope.film.title}</title>
</head>
<body>
<c:set var="rating" scope="page" value="${empty requestScope.userMark ? 1 : requestScope.userMark.value}"/>
<jsp:include page="templates/header.jsp"/>
<div class="wrapper">
    <div class="pane-wrapper">
        <jsp:include page="templates/left_panel.jsp"/>
        <div class="film-wrapper content-panel">
            <div class="film-panel pane">
                <jsp:include page="templates/film_preview.jsp">
                    <jsp:param name="isMoreNeeded" value="false"/>
                </jsp:include>
                <div class="rating">
                    <p class="service"><fmt:message key="film.show.label.rating"/>:
                        <span class="rating-value service">
                            ${rating}
                        </span>
                    </p>
                    <form action="${pageContext.request.contextPath}/" method="post">
                        <input type="hidden" name="film_id" value="${requestScope.film.id}"/>
                        <c:choose>
                            <c:when test="${empty requestScope.userMark}">
                                <input type="hidden" name="command" value="add_mark"/>
                                <input type="range" min="1" max="10" value="1" class="slider" name="value"/>
                                <br>
                                <button id="vote-button" class="service"><fmt:message
                                        key="film.show.button.vote"/></button>
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="command" value="remove_mark"/>
                                <input type="range" min="1" name="value" max="10" value="${requestScope.userMark.value}"
                                       class="slider disabled-slider disabled" disabled>
                                <br>
                                <button id="remove-mark-button" class="service"><fmt:message
                                        key="film.show.button.unvote"/></button>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </div>
                <form action="${pageContext.request.contextPath}/" method="post" class="new-comment" id="comment-form">
                    <input type="hidden" name="command" value="add_comment"/>
                    <input type="hidden" name="film_id" value="${requestScope.film.id}"/>
                    <p class="service"><fmt:message key="film.show.label.share"/></p>
                    <textarea name="text" class="content-textarea" id="comment-text-input"></textarea>
                    <div class="service error" id="new-comment-error"></div>
                    <div>
                        <button class="service"><fmt:message key="button.submit"/></button>
                    </div>
                </form>
                <div class="comments">
                    <jsp:include page="templates/comments.jsp"/>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/slider.js"></script>
<script src="${pageContext.request.contextPath}/js/edit_validator.js"></script>
<script>
    addCommentValidationListener(
        '<fmt:message key="error.empty" />'
    );
</script>
</body>
</html>