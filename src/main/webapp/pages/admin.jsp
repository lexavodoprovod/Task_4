<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 28.01.2026
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>

    Hello admin : ${user_login}
<br/>
    <form action = "${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="show_users"/>
        <input type="submit" value="Show all users"/>
    </form>
    <br/>
    <form action="logout.do" method="post">
        <input type="hidden" name="command" value="logout"/>
        <input type="submit" name="out" value="Logout">
    </form>


</body>
</html>
