<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"  %>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="locale"/>

<footer>
    <p class="service">
        <fmt:message key="footer.info.copyright" />
    </p>
</footer>