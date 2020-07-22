<%--suppress HtmlUnknownTarget, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red;">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/login" method="post">
    Login: <label><input type="text" name="login"/></label><br/>
    Password: <label><input type="password" name="password"/></label><br/>
    <input type="submit" value="Log in">
</form>
</body>
</html>
