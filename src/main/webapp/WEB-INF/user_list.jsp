<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                    <th class="service"><fmt:message key="user.list.table.header.banaction"/></th>
                    <th class="service"><fmt:message key="user.list.table.header.status"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${requestScope.users}">
                    <tr>
                        <td>
                                <c:out value="${user.login}" />
                        </td>
                        <td>
                            <fmt:message key="user.role.${fn:toLowerCase(user.role)}" />
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/" method="POST">
                                <input type="hidden" name="command" value="update_user">
                                <input type="hidden" name="id" value="${user.id}">
                                <input type="hidden" name="ban" value="${user.ban ? 'off' : 'on'}">
                                <button class="service"><fmt:message key="user.action.${user.ban ? 'unban' : 'ban'}"/></button>
                            </form>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/" method="POST">
                                <input type="hidden" name="command" value="update_user">
                                <input type="hidden" name="id" value="${user.id}">
                                <select name="status" class="service submit-on-change">
                                    <c:forEach var="status" items="${requestScope.statuses}">
                                        <option class="service" ${user.status eq status ? "selected" : ""} value="${status}"><fmt:message key="user.status.${fn:toLowerCase(status)}" /></option>
                                    </c:forEach>
                                </select>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/submit_on_change.js"></script>
</body>
</html>