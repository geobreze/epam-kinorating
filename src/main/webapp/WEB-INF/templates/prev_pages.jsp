<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ltg" uri="localetags" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>

<c:if test="${requestScope.current_page ne 1}">
    <c:set var="page" scope="request" value="1" />
    <jsp:include page="page_button.jsp"/>
</c:if>
<c:choose>
    <c:when test="${requestScope.current_page gt 4}">
        ...
        <c:set var="page" scope="request" value="${requestScope.current_page - 1}" />
        <jsp:include page="page_button.jsp"/>
    </c:when>
    <c:when test="${requestScope.current_page lt 5}">
        <c:forEach var="page" begin="2" end="${requestScope.current_page - 1}">
            <c:set var="page" scope="request" value="${page}" />
            <jsp:include page="page_button.jsp"/>
        </c:forEach>
    </c:when>
</c:choose>