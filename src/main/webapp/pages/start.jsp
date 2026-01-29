<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<br/>
<form action = "controller" method="post">
    <input type="hidden" name="command" value="login"/>
    Login: <input type="text" name ="authenticate_login" value=""/>
    <br/>
    Password: <input type="password" name="authenticate_pass" value=""/>
    <br/>
    <input type="submit" name="loginButton" value="Push"/>

    <br/>
</form>
<%--<a href="<%= request.getContextPath() + "/pages/register.jsp" %>" class="btn-back">Register</a>--%>
<br/>
<form action = "controller">
    <input type="hidden" name="command" value="go_to_registration"/>
    <br/>
    <input type="submit" name="reg" value="Sign Up"/>
</form>
${login_msg}
</body>
</html>