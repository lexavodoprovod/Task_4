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
    <form action="controller" method = "post">
        <input type="hidden" name="command" value="add_user"/>
        Name: <input type="text" name="register_userName" value=""/>
        <br/>
        Login: <input type="text" name ="register_login" value=""/>
        <br/>
        Password: <input type="password" name="register_pass" value=""/>
        <br/>
        <input type="submit" name="regUser" value="Register user"/>
        <br/>


    </form>
    <form action="logout.do">
        <input type="hidden" name="command" value="logout"/>
        <input type="submit" name="out" value="Login">
    </form>
    <br/>
    ${register_msg}
</body>
</html>
