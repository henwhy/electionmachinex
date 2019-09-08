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
    <form action="./Uusiehdokas" method="post"> 
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
        <p>Ik‰:</p>  
        <input type="number" name="ika"/> 
        <br/><br/><br/>
        <br/> 
        <p>Miksi eduskuntaan:</p>  
        <input type="text" name="miksi_eduskuntaan"/> 
        <br/><br/><br/>
        <br/> 
        <p>Mit‰ asioita haluat edist‰‰:</p>  
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