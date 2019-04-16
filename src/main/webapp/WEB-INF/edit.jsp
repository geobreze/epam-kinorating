<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <meta charset="UTF-8">
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'/>
    <title><fmt:message key="film.edit.title"/></title>
</head>
<body>
<jsp:include page="templates/header.jsp"/>
<div class="wrapper">
    <div class="pane-wrapper">
        <jsp:include page="templates/left_panel.jsp"/>
        <div class="edit-panel content-panel pane">
            <form action="${pageContext.request.contextPath}/" method="post">
                <input type="hidden" name="command" value="${requestScope.forward_command}">
                <input type="hidden" name="id" value="${param.id}">
                <p class="field-description service"><fmt:message key="film.edit.label.title"/></p>
                <input type="text" name="title" class="service"
                       value="${empty requestScope.film ? '' : requestScope.film.title}">
                <p class="field-description service"><fmt:message key="film.edit.label.description"/></p>
                <textarea name="description"
                          class="content-textarea">${empty requestScope.film ? '' : requestScope.film.description}</textarea>
                <br/>
                <button class="service"><fmt:message key="button.submit"/></button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>