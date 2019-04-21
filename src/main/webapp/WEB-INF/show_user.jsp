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
    <title>${requestScope.user.login}</title>
</head>
<body>
<jsp:include page="templates/header.jsp"/>
<div class="wrapper">
    <div class="pane-wrapper">
        <jsp:include page="templates/left_panel.jsp"/>
        <div class="content-panel pane">
            <div class="pane">
                <div class="login-wrapper">
                    <h2 class="service user-login">
                        <c:out value="${requestScope.user.login}" />
                    </h2>
                </div>
                <form action="${pageContext.request.contextPath}/" class="user-edit" method="POST">
                    <input type="hidden" name="command" value="update_user">
                    <input type="hidden" name="id" value="${requestScope.user.id}">
                    <span class="service"><fmt:message key="user.show.label.role"/>:</span>
                    <select name="role" class="service">
                        <c:forEach var="role" items="${requestScope.roles}">
                            <option ${requestScope.user.role eq role ? "selected" : ""} value="${role}"
                                                                                        class="service">${role}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <span class="service"><fmt:message key="user.show.label.ban"/>: </span>
                    <input type="checkbox" class="ban-checkbox" name="ban" ${requestScope.user.ban ? "checked" : ""}>
                    <br>
                    <button class="service"><fmt:message key="button.submit"/></button>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>