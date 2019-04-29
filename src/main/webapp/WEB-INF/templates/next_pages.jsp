<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ltg" uri="localetags" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>

<c:choose>
    <c:when test="${requestScope.pages - requestScope.current_page gt 3}">
        <c:set var="page" scope="request" value="${requestScope.current_page + 1}" />
        <jsp:include page="page_button.jsp"/>
        ...
    </c:when>
    <c:when test="${requestScope.pages - requestScope.current_page lt 4}">
        <c:forEach var="page" begin="${requestScope.current_page + 1}" end="${requestScope.pages - 1}">
            <c:set var="page" scope="request" value="${page}" />
            <jsp:include page="page_button.jsp"/>
        </c:forEach>
    </c:when>
</c:choose>
<c:if test="${requestScope.pages ne requestScope.current_page}">
    <c:set var="page" scope="request" value="${requestScope.pages}" />
    <jsp:include page="page_button.jsp"/>
</c:if>