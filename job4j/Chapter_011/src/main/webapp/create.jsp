<%--suppress HtmlUnknownTarget --%>
<%@ page import="logic.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/create" method="post">
    <input type="hidden" name="id" value="<%=String.valueOf(ValidateService.INSTANCE.count())%>"/>
    Name: <label><input type="text" name="name"/></label><br/>
    Login: <label><input type="text" name="login"/></label><br/>
    Email: <label><input type="text" name="email"/></label><br/>
    <input type="submit" value="Create"/>
</form>
<form action="<%=request.getContextPath()%>/list" method="get">
    <input type="submit" value="List"/>
</form>
</body>
</html>
