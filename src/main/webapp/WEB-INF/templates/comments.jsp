<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"  %>
<c:forEach var="comment" items="${requestScope.film.comments}">
    <c:set var="comment" value="${comment}" scope="request"/>
    <jsp:include page="comment.jsp"/>
</c:forEach>