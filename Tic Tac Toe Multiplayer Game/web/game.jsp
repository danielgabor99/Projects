<%--
  Created by IntelliJ IDEA.
  User: Daniel
  Date: 07-May-20
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@page import="joc.GameBean.GameState" %>
<%@page import="joc.Cell" %>
<%@page import="joc.Line" %>

<jsp:useBean id="gameBean" scope="application" class="joc.GameBean"/>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <META HTTP-EQUIV="Refresh" CONTENT="1;">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tic Tac Toe</title>
</head>
<body>
<h1>Tic Tac Toe</h1>
<table border="1">
    <c:forEach var="line" items="${gameBean.gridLines}">
        <tr>
            <c:forEach var="cell" items="${gameBean.getGridStatus(line)}">
                <td>
                    <c:choose>
                        <c:when test="${cell.state == 'X'}">
                            <img src="img/state_x.png" alt="X"/>
                        </c:when>
                        <c:when test="${cell.state == 'O'}">
                            <img src="img/state_o.png" alt="O"/>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${winner == null}">
                                <a href="gameServlet?joc.Line=${cell.line}&Col=${cell.col}">
                            </c:if>
                            <img src="img/state_null.png" alt="null"/>
                            <c:if test="${winner == null}">
                                </a>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
<c:if test="${winner != null}">
    <h1>${winner} the game !</h1>
    <form action="index.jsp" method="post">
        <input type="submit" name="Replay" value="Logout"><br/>
    </form>
</c:if>
</body>
</html>