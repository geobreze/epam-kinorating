<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
    <meta charset="UTF-8"/>
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'/>
    <title>Kinorating</title>
</head>
<body>
<jsp:include page="templates/header.jsp"/>
<div class="wrapper">
    <div class="pane-wrapper">
        <jsp:include page="templates/left_panel.jsp"/>
        <div class="film-wrapper content-panel">
            <c:forEach var="film" items="${requestScope.films}">
                <div class="film-panel pane">
                    <c:set var="film" value="${film}" scope="request"/>
                    <jsp:include page="templates/film_preview.jsp">
                        <jsp:param name="isMoreNeeded" value="true"/>
                    </jsp:include>
                </div>
            </c:forEach>
            <div class="pane paging-wrapper">
                <div class="paging">
                    <jsp:include page="templates/pages.jsp" />
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>