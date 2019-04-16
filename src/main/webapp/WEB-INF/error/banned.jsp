<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
    <meta charset="UTF-8" />
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' />
    <title>Banned</title>
</head>
<body>
<jsp:include page="../templates/header.jsp"/>
<div class="wrapper">
    <div class="log-in-wrapper pane-wrapper">
        <div class="error-wrapper pane service">
            Sorry, but you were banned
            <form action="${pageContext.request.contextPath}/" method="POST">
                <input type="hidden" name="command" value="logout" />
                <button class="service">Log out</button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../templates/footer.jsp"/>
</body>
</html>