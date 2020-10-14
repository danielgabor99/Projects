<%--
  Created by IntelliJ IDEA.
  User: Daniel
  Date: 22-Jun-20
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

</head>
<body>
<form action="Login" method="post">
  <div style="color: #FF0000;">${errorMessage}</div>
  Username: <input type="text" name="user"/> <br/>
  Password: <input type="password" name="pass"/> <br/>
  <input type="submit" value="Login"/>
</form>

</body>
</html>
