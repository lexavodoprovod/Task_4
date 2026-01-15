<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<br/>
<form action = "controller">
    <input type="hidden" name="command" value="login"/>
    Login: <input type="text" name ="authenticate_login" value=""/>
    <br/>
    Password: <input type="password" name="authenticate_pass" value=""/>
    <br/>
    <input type="submit" name="sub" value="Push"/>

    <br/>
</form>
<form action = "controller">
    <input type="hidden" name="command" value="go_to_registration"/>
    <br/>
    <input type="submit" name="reg" value="Register"/>
</form>
${login_msg}
</body>
</html>