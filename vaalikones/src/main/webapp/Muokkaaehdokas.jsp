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
	<form action="./Muokkaaehdokas" method="post">
	<table border="2">
   <tr>
        <td>Ehdokas ID</td>
        <td>Sukunimi</td>
        <td>Etunimi</td>
        <td>Puolue</td>
        <td>Kotipaikkakunta</td>
        <td>Ikä</td>
        <td>Miksi eduskuntaan?</td>
        <td>Mitä asioita haluat edistää?</td>
        <td>Ammatti</td>
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
           <td><%=rs.getString("etunimi") %></td>
           <td><%=rs.getString("puolue") %></td>
           <td><%=rs.getString("kotipaikkakunta") %></td>
           <td><%=rs.getString("ika") %></td>
           <td><%=rs.getString("miksi_eduskuntaan") %></td>
           <td><%=rs.getString("mita_asioita_haluat_edistaa") %></td>
           <td><%=rs.getString("ammatti") %></td></tr>
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
	
		<p>Muokattavan henkilön ehdokas-ID?</p>
		<input type="number" name="ehdokas_id"/>
	 	<br/><br/><br/>  
    	<br/> 
        <p>Sukunimi:</p>  
        <input type="text" name="sukunimi"/> 
        <br/><br/><br/>
        <br/> 
        <p>Etunimi:</p>  
        <input type="text" name="etunimi"/> 
        <br/><br/><br/>
        <br/> 
        <p>Puolue:</p>  
        <input type="text" name="puolue"/> 
        <br/><br/><br/>
        <br/> 
        <p>Kotipaikkakunta:</p>  
        <input type="text" name="kotipaikkakunta"/> 
        <br/><br/><br/>
        <br/> 
        <p>Ikä:</p>  
        <input type="number" name="ika"/> 
        <br/><br/><br/>
        <br/> 
        <p>Miksi eduskuntaan:</p>  
        <input type="text" name="miksi_eduskuntaan"/> 
        <br/><br/><br/>
        <br/> 
        <p>Mitä asioita haluat edistää:</p>  
        <input type="text" name="mita_asioita_haluat_edistaa"/> 
        <br/><br/><br/>
        <br/> 
        <p>Ammatti:</p>  
        <input type="text" name="ammatti"/> 
        <br/><br/><br/> 
 		<input type="submit"/>
	</form>
	</div>
</body>
</html>