<%--suppress HtmlUnknownTarget, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
    <style type="text/css">
        TABLE {
            border-collapse: collapse;
            background-color: #9e9b9b;
        }

        TD, TH {
            padding: 3px;
            border: 1px solid black;
        }

        TH {
            background-color: #808080;
        }

        FORM {
            display: inline-block;
        }
    </style>
</head>
<body>
<table>
    <caption>Users</caption>
    <tbody>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Created</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.createDate}"/></td>
            <td>
                <c:if test="${sessionScope.role == 'admin' || sessionScope.user_id == user.id}">
                    <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                        <input type="hidden" name="id" value="${user.id}"/>
                        <input type="submit" value="Update"/>
                    </form>
                    <form action="${pageContext.servletContext.contextPath}/list" method="post">
                        <input type="hidden" name="id" value="${user.id}"/>
                        <input type="submit" value="Delete"/>
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${role = 'admin'}">
    <form action="${pageContext.servletContext.contextPath}/create" method="get">
        <input type="submit" value="Create">
    </form>
</c:if>
</body>
</html>
