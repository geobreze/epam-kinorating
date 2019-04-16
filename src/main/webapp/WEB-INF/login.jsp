<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <meta charset="UTF-8" />
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' />
    <title>Login</title>
</head>
<body>
<jsp:include page="templates/header.jsp"/>
<div class="wrapper">
    <div class="log-in-wrapper pane-wrapper">
        <div class="log-in-pane pane">
            <form action="${pageContext.request.contextPath}/" method="post">
                <input type="hidden" value="login" name="command" />
                <p class="service">Login</p>
                <input type="text" name="login" value="${param.login}" />
                <p class="service">Password</p>
                <input type="password" name="password"/>
                <br />
                <c:if test="${not empty param.error}">
                    <div class="error service">
                            ${param.error}
                    </div>
                </c:if>
                <button class="service">Log in</button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>