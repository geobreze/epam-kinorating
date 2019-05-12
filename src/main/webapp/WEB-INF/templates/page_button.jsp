<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ltg" uri="localetags" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<form action="${pageContext.request.contextPath}">
    <input type="hidden" name="command" value="${requestScope.command}">
    <button value="${requestScope.page}" name="current_page">${requestScope.page}</button>
</form>