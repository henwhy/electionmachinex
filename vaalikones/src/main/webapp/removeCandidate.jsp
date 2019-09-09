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
<title>Remove candidate from database?</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">



<title>Insert title here</title>
</head>
<body>

	<div class="candidateremove">
	<form action="./removeCandidate" method="post">
	<table border="2">
   <tr>
        <td>Candidate Id</td>
        <td>Lastname</td>
        <td>Firstname</td>
   </tr>
   <%
   try
   {
       Class.forName("com.mysql.jdbc.Driver");
       String url="jdbc:mysql://localhost:3306/electionmachine";
       String username="root";
       String password="";
       String query="select * from candidates";
       Connection conn=DriverManager.getConnection(url, username, password);
       Statement stmt=conn.createStatement();
       ResultSet rs=stmt.executeQuery(query);
       while(rs.next())
       {
   %>
           <tr><td><%=rs.getInt("candidate_id") %></td>
           <td><%=rs.getString("lastname") %></td>
           <td><%=rs.getString("firstname") %></td></tr>
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
	
	<p>ID of the  person to be removed</p>
	<input type="text" name="candidate_id"/>
	 <br/><br/><br/> 
     <input type="submit"/> 
	</form>
	</div>
</body>
</html>