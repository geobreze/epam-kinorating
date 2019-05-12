<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ltg" uri="localetags" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>

<jsp:include page="prev_pages.jsp" />
<form action="${pageContext.request.contextPath}">
    <input type="hidden" name="command" value="${requestScope.command}">
    <button class="current-page" value="${requestScope.current_page}" name="current_page">${requestScope.current_page}</button>
</form>
<jsp:include page="next_pages.jsp" />