<%--suppress HtmlUnknownTarget --%>
<%@ page import="models.User" %>
<%@ page import="logic.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
    <%for (User user : ValidateService.INSTANCE.getAll()) {%>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCreateDate().getTime().toString()%></td>
        <td>
            <form action="<%=request.getContextPath()%>/edit" method="get">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" value="Update"/>
            </form>
            <form action="<%=request.getContextPath()%>/list" method="post">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" value="Delete"/>
            </form>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>
<form action="<%=request.getContextPath()%>/create" method="get">
    <input type="submit" value="Create">
</form>
</body>
</html>
