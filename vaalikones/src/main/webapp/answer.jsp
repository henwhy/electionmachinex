<%@ page import="classes.Question" %>
<%@ page import="java.util.List" %><%--
    Document   : vastaus
    Created on : 09-Apr-2015, 12:50:47
    Author     : Bocceli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Amazing Electionmachine</title>

    <link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>

<div id="container">
    <img id="headerimg" src="Logo.png" width="500" height="144" alt=""/>


    <%
        @SuppressWarnings("unchecked")
        List<Question> questions = (List<Question>) request.getAttribute("questions");
        for (Question question : questions) { %>
    <div class="question">
        <%= question.getQuestionId() %> / 19 <br>
        <%= question.getQuestion() %>
    </div>
    <form action="/election-machine" id="answerform">
        <label>1</label><input type="radio" name="answer" value="1"/>
        <label>2</label><input type="radio" name="answer" value="2"/>
        <label>3</label><input type="radio" name="answer" value="3" checked="checked"/>
        <label>4</label><input type="radio" name="answer" value="4"/>
        <label>5</label><input type="radio" name="answer" value="5"/>
        <input type="hidden" name="question" value="<%= question.getQuestionId() %>">
        <input type="submit" id="submitbtn" value="Answer"/>
    </form>
    <div class="kysymys"><small>1=Completely disagree 2=Partially disagree 3=Can't tell, 4=Partially agree 5=Completely
        agree</small></div>
    <%
        }
    %>


</div>

</body>
</html>
