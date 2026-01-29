<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 04.01.2026
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500</title>
</head>
<body>
    java.lang.Throwable
    Request from: ${pageContext.errorData.requestURI} is failed <br/>
    Servlet name: ${pageContext.errorData.servletName} <br/>
    Status code: ${pageContext.errorData.statusCode}<br/>
    Exception: ${pageContext.exception} <br/>
    <a href="<%= request.getContextPath() %>/" class="btn-back">Вернуться домой</a>
    <br/><br/><br/>
</body>
</html>
