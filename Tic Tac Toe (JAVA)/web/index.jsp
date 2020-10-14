<%--
  Created by IntelliJ IDEA.
  User: Daniel
  Date: 06-May-20
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>


<%@ page import="joc.SessionCounter" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tic Tac Toe - Gabor Daniel</title>
    <link rel="stylesheet" type="text/css" href="mystyle.css">
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