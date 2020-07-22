<%--suppress HtmlUnknownTarget, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Update</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/edit" method="post">
    <input type="hidden" name="id" value="${user.id}"/>
    Name: <label><input type="text" name="name" value="${user.name}"/></label><br/>
    Login: <label><input type="text" name="login" value="${user.login}"/></label><br/>
    Email: <label><input type="text" name="email" value="${user.email}"/></label><br/>
    <c:if test="${sessionScope.role == 'admin'}">
        Role: <label><select name="role">
        <option value="admin">Admin</option>
        <option value="guest">Guest</option>
    </select></label>
    </c:if>
    <input type="submit" value="Update"/>
</form>
<form action="${pageContext.servletContext.contextPath}/list" method="get">
    <input type="submit" value="List"/>
</form>
</body>
</html>
