<%@ page import="com.hololeenko.task_4.model.entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 28.01.2026
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
    <h2>All users</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Login</th>
                <th>Role</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<User> users = (List<User>) request.getAttribute("user_list");
                if(users != null){
                    for(User user : users){
            %>
            <tr>
                <td><%=user.getId()%></td>
                <td><%=user.getName()%></td>
                <td><%=user.getLogin()%></td>
                <td><%=user.getRole().name()%></td>
            </tr>
            <%
                    }
                }else{
            %>
            <tr><td colspan="4">There are no users</td> </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
