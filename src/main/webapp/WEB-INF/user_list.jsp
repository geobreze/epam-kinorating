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
        <div class="edit-panel content-panel pane">
            <table class="user-table">
                <caption class="service">User list</caption>
                <thead>
                <tr>
                    <th class="service">Login</th>
                    <th class="service">Role</th>
                    <th class="service">Ban status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${requestScope.users}">
                    <tr>
                        <td>
                            ${user.login}
                        </td>
                        <td>
                            ${user.role}
                        </td>
                        <td>
                            ${user.ban ? "Banned" : "Not banned"}
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}?command=show_user&id=${user.id}">Go to user page</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>