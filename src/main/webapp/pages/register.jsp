<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 09.01.2026
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <form action="register.do">
        <input type="hidden" name="command" value="add_user"/>
        Name: <input type="text" name="userName" value=""/>
        <br/>
        Login: <input type="text" name ="login" value=""/>
        <br/>
        Password: <input type="password" name="pass" value=""/>
        <br/>
        <input type="submit" name="regUse" value="Register user"/>
    </form>
</body>
</html>
