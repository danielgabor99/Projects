<%--
  Created by IntelliJ IDEA.
  User: Daniel
  Date: 06-May-20
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="gameBean" scope="application" class="joc.GameBean"/>

<%@ page import="joc.SessionCounter" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tic Tac Toe - Karl Woditsch</title>
</head>
<body>
<form action="entryServlet" method="post">
    <div style="color: #FF0000;">${errorMessage}</div>
    <input type="submit" name="User" value="X"><br/>
    <input type="submit" name="User2" value="0">
</form>
<%
    SessionCounter counter = (SessionCounter) session.getAttribute(
            SessionCounter.COUNTER);
%>

Number of users Online: <%= counter.getActiveSessionNumber() - 1 %>
</body>
</html>
