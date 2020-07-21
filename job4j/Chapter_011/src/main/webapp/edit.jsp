<%--suppress HtmlUnknownTarget --%>
<%@ page import="models.User" %>
<%@ page import="logic.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<%User user = ValidateService.INSTANCE.findById(Integer.parseInt(request.getParameter("id")));%>
<form action="<%=request.getContextPath()%>/edit" method="post">
    <input type="hidden" name="id" value="<%=String.valueOf(user.getId())%>"/>
    Name: <label><input type="text" name="name" value="<%=user.getName()%>"/></label><br/>
    Login: <label><input type="text" name="login" value="<%=user.getLogin()%>"/></label><br/>
    Email: <label><input type="text" name="email" value="<%=user.getEmail()%>"/></label><br/>
    <input type="submit" value="Update"/>
</form>
<form action="<%=request.getContextPath()%>/list" method="get">
    <input type="submit" value="List"/>
</form>
</body>
</html>
