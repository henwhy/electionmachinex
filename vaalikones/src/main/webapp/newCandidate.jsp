<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*,vaalikone.Vaalikone,persist.*"%>
<!DOCTYPE html> 
<html> 
<head> 
<title>Insert Data</title> 
  <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css">

<body> 
    <!-- Give Servlet reference to the form as an instances  
    GET and POST services can be according to the problem statement-->
    <div class="ehdokas">
    <form action="./newCandidate)" method="post"> 
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