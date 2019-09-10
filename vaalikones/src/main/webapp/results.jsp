<%-- 
    Document   : tulokset
    Created on : 14-Apr-2015, 18:26:35
    Author     : Bocceli
--%>


<%@page import="java.util.List" %>
<%@ page import="classes.Candidate" %>
<%@ page import="classes.Answer" %>
<%@ page import="classes.Question" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Best candidate</title>
    <link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
    <h1>Amazing electionmachine tells you your best candidate:</h1>
    <%
        List<Candidate> bestCandidates = (List<Candidate>) request.getAttribute("bestCandidate");
        List<Integer> usersAnswers = (List<Integer>) request.getAttribute("usersAnswers");
        List<Answer> bestCandidatesAnswers = (List<Answer>) request.getAttribute("bestCandidatesAnswers");
        List<Question> allQuestions = (List<Question>) request.getAttribute("allQuestions");
        Double points = (double) (Integer) request.getAttribute("points");
        Double percentages = (double) Math.round(points / (3 * 19) * 100);
        Integer positionnumber = (Integer) request.getAttribute("positionnumber");

        if (positionnumber > 0) {%>
    <a href="/election-machine?func=searchForCandidate&number=<%= positionnumber - 1%>">Previous candidate</a>&nbsp;
    <% }
        if (positionnumber < 18) {%>
    <a href="/election-machine?func=searchForCandidate&number=<%= positionnumber + 1%>">Next suggested candidate</a>
    <% }

        for (Candidate thatBestCandidate : bestCandidates) {
    %>

    <h2>Number: <%= thatBestCandidate.getCandidateId()%>
    </h2>
    <h3>For you <%= positionnumber + 1 %>. best candidate</h3>
    <h3>Compatibility: <%= percentages%>%</h3>
    <ul>
        <li><b>Name:</b><%= thatBestCandidate.getFirstName()%> <%= thatBestCandidate.getLastName()%>
        </li>
        <li><b>Age:</b><%= thatBestCandidate.getAge()%>
        </li>
        <li><b>City:</b><%= thatBestCandidate.getCity()%>
        </li>
        <li><b>Profession:</b><%= thatBestCandidate.getProfession()%>
        </li>
        <li><b>Political party:</b><%= thatBestCandidate.getPoliticalParty()%>
        </li>
    </ul>
    <h2>Why join the parliament?</h2>
    <p><%= thatBestCandidate.getWhyQuestion()%>
    </p>
    <h2>What to improve?</h2>
    <p><%= thatBestCandidate.getImproveQuestion()%>
    </p>

    <% }

        for (int i = 0; i < bestCandidatesAnswers.size(); i++) {
    %>
    <b>Question <%= i + 1%>: <%= allQuestions.get(i).getQuestion()%>
    </b><br>
    <ul>
        <li>Your answer: <%= usersAnswers.get(i + 1).toString()%>
        </li>
        <li>Candidate answer: <%= bestCandidatesAnswers.get(i).getAnswer()%>
        </li>
        <li>Candidate comment: <%= bestCandidatesAnswers.get(i).getComment()%>
        </li>
    </ul>


    <%
        }

    %>

</div>

</body>
</html>
