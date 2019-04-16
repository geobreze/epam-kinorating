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
    <title>User list</title>
</head>
<body>
<jsp:include page="templates/header.jsp"/>
<div class="wrapper">
    <div class="pane-wrapper">
        <jsp:include page="templates/left_panel.jsp"/>
        <div class="edit-panel content-panel pane">
            <table class="user-table">
                <caption class="service"><fmt:message key="user.list.table.caption"/></caption>
                <thead>
                <tr>
                    <th class="service"><fmt:message key="user.list.table.header.login"/></th>
                    <th class="service"><fmt:message key="user.list.table.header.role"/></th>
                    <th class="service"><fmt:message key="user.list.table.header.banstatus"/></th>
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
                            <fmt:message key="status.${user.ban ? 'banned' : 'notbanned'}"/>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}?command=show_user&id=${user.id}"><fmt:message
                                    key="user.list.table.userpage"/></a>
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