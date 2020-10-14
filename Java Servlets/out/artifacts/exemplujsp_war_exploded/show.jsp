<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Daniel
  Date: 22-Jun-20
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>something</title>
</head>
<body>
<table border="2">
    <tr>
        <td>user id</td>
        <td>name</td>
        <td>description</td>
        <td>value</td>
    </tr>
    <%
        try
        {
            Connection con = webapp.DatabaseConnection.initializeDatabase();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM channels where subscribers like ?");

            pst.setString(1, "%"+(String) request.getAttribute("userid")+"%");
            ResultSet rs = pst.executeQuery();
    while(rs.next())
            {
               // if(rs.getInt("value")>10){
                if(true){

    %>
    <tr style="color:red">
        <td><%=rs.getInt(1) %></td>
    </tr>
    <%
            }else
                {
                %>
                        <tr>
                            <td><%=rs.getInt(1) %></td>

                        </tr>
                <%
            }
        }
    %>
</table>
<%
        rs.close();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }

%>

<form action="getall.jsp" method="post">
    <div style="color: #FF0000;">${errorMessage}</div>
    Username: <input type="text" name="user2"/> <br/>
    <input type="submit" value="Login"/>
</form>
</body>
</html>
