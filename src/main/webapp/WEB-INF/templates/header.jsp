<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<header>
    <div class="logo-wrapper">
        <h1><a href="${pageContext.request.contextPath}/" class="logo service">Kinorating</a></h1>
    </div>
    <div class="lang-buttons-wrapper">
        <form action="${pageContext.request.contextPath}/" method="POST">
            <input type="hidden" name="command" value="change_language"/>
            <button class="lang-change service" value="EN" name="language">EN</button>
            <button class="lang-change service" value="RU" name="language">RU</button>
        </form>
    </div>
</header>