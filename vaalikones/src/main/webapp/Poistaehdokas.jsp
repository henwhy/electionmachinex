<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,vaalikone.Vaalikone,persist.*"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

<!DOCTYPE html>
<html>
<head>
<title>Poista ehdokas tietokannasta?</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">



<title>Insert title here</title>
</head>
<body>

	<div class="ehdokasdel">
	<form action="./Poistaehdokas" method="post">
	<table border="2">
   <tr>
        <td>Ehdokas ID</td>
        <td>Sukunimi</td>
        <td>Etunimi</td>
   </tr>
   <%
   try
   {
       Class.forName("com.mysql.jdbc.Driver");
       String url="jdbc:mysql://localhost:3306/vaalikone";
       String username="root";
       String password="";
       String query="select * from ehdokkaat";
       Connection conn=DriverManager.getConnection(url, username, password);
       Statement stmt=conn.createStatement();
       ResultSet rs=stmt.executeQuery(query);
       while(rs.next())
       {
   %>
           <tr><td><%=rs.getInt("ehdokas_id") %></td>
           <td><%=rs.getString("sukunimi") %></td>
           <td><%=rs.getString("etunimi") %></td></tr>
   <%
       }
   %>
   </table>
   <%
        rs.close();
        stmt.close();
        conn.close();
   }
   catch(Exception e)
   {
        e.printStackTrace();
   }
   %>
	
	<p>Poistettavan henkilön ehdokas-ID?</p>
	<input type="text" name="ehdokas_ID"/>
	 <br/><br/><br/> 
     <input type="submit"/> 
	</form>
	</div>
</body>
</html>