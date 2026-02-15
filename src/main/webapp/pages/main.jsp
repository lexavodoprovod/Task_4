<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 04.01.2026
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
Welcome to app ${user_login}
<br/>
<form action="logout.do" method="post">
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" name="out" value="Logout">
</form>
</body>
</html>
