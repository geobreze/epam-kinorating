<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <meta charset="UTF-8">
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' />
    <title>User list</title>
</head>
<body>
<jsp:include page="templates/header.jsp"/>
<div class="wrapper">
    <div class="pane-wrapper">
        <jsp:include page="templates/left_panel.jsp"/>
        <div class="content-panel pane">
            <div class="pane">
                <div class="login-wrapper">
                    <h2 class="service user-login">${requestScope.user.login}</h2>
                </div>
                <form action="${pageContext.request.contextPath}/" class="user-edit" method="POST">
                    <input type="hidden" name="command" value="update_user">
                    <input type="hidden" name="id" value="${requestScope.user.id}">
                    <span class="service">Role:</span>
                    <select name="role" class="service">
                        <c:forEach var="role" items="${requestScope.roles}">
                            <option ${requestScope.user.role eq role ? "selected" : ""} value="${role}" class="service">${role}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <span class="service">Ban: </span>
                    <input type="checkbox" class="ban-checkbox" name="ban" ${requestScope.user.ban ? "checked" : ""}>
                    <br>
                    <button class="service">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>