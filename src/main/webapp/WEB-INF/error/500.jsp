<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title><fmt:message key="error.server.message"/></title>
</head>
<body>
<jsp:include page="../templates/header.jsp"/>
<div class="wrapper">
    <div class="log-in-wrapper pane-wrapper">
        <div class="error-wrapper pane service">
            <fmt:message key="error.server.message"/>
            <form action="${pageContext.request.contextPath}/">
                <button class="service"><fmt:message key="button.index"/></button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../templates/footer.jsp"/>
</body>
</html>