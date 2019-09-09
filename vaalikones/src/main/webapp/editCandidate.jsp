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
<title>Edit candidate(maybe)</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">



<title>Insert title here (Nice)</title>
</head>
<body>

	<div class="removeCandidate">
	<form action="./EditCandidate" method="post">
	<table border="2">
   <tr>
        <td>Candidate ID</td>
        <td>Lastname</td>
        <td>Firstname</td>
        <td>Political party</td>
        <td>City</td>
        <td>Age</td>
        <td>Why to the parliament?</td>
        <td>What to improve?</td>
        <td>Profession</td>
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
           <td><%=rs.getString("firstname") %></td>
           <td><%=rs.getString("politicalparty") %></td>
           <td><%=rs.getString("city") %></td>
           <td><%=rs.getString("age") %></td>
           <td><%=rs.getString("whyquestion") %></td>
           <td><%=rs.getString("improvequestion") %></td>
           <td><%=rs.getString("profession") %></td></tr>
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
	
		<p>ID of the candidate to be edited</p>
		<input type="number" name="candidate_id"/>
	 	<br/><br/><br/>  
    	<br/> 
        <p>Lastname:</p>  
        <input type="text" name="lastname"/> 
        <br/><br/><br/>
        <br/> 
        <p>Firstname:</p>  
        <input type="text" name="firstname"/> 
        <br/><br/><br/>
        <br/> 
        <p>Political party:</p>  
        <input type="text" name="politicalparty"/> 
        <br/><br/><br/>
        <br/> 
        <p>City:</p>  
        <input type="text" name="city"/> 
        <br/><br/><br/>
        <br/> 
        <p>Age:</p>  
        <input type="number" name="age"/> 
        <br/><br/><br/>
        <br/> 
        <p>Why join the parliament:</p>  
        <input type="text" name="whyquestion"/> 
        <br/><br/><br/>
        <br/> 
        <p>What to improve:</p>  
        <input type="text" name="improvequestion"/> 
        <br/><br/><br/>
        <br/> 
        <p>Profession:</p>  
        <input type="text" name="profession"/> 
        <br/><br/><br/> 
 		<input type="submit"/>
	</form>
	</div>
</body>
</html>