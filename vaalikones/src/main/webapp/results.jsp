<%-- 
    Document   : tulokset
    Created on : 14-Apr-2015, 18:26:35
    Author     : Jonne
--%>

<%@page import="persist.Kysymykset"%>
<%@page import="persist.Vastaukset"%>
<%@page import="java.util.List"%>
<%@page import="persist.Ehdokkaat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                List<Candidates> bestCandidates = (List<Candidates>) request.getAttribute("bestCandidate");
                List<Integer> usersAnswers = (List<Integer>) request.getAttribute("usersAnswers");
                List<Answers> bestCandidatesAnswers = (List<Answers>) request.getAttribute("bestCandidatesAnswers");
                List<Questions> allQuestions = (List<Questions>) request.getAttribute("allQuestions");
                Double points = (double) (Integer) request.getAttribute("points");
                Double percentages = (double) Math.round(points / (3 * 19) * 100);
                Integer positionnumber = (Integer) request.getAttribute("positionnumber");

                if (positionnumber > 0) {%>
            <a href="Vaalikone?func=haeEhdokas&numero=<%= positionnumber - 1%>">Previous candidate</a>&nbsp; 
            <% }
                if (positionnumber < 18) {%>
            <a href="Vaalikone?func=haeEhdokas&numero=<%= positionnumber + 1%>">Next suggested candidate</a>
            <% }

                for (Candidates thatBestCandidate : bestCandidates) {
            %>

            <h2>Number: <%= thatBestCandidate.getCandidateId()%></h2>
            <h3>For you <%= positionnumber+1 %>. best candidate</h3>
            <h3>Compatibility: <%= percentages%>%</h3>
            <ul>
                <li><b>Name:</b><%= thatBestCandidate.getFirstname()%> <%= thatBestCandidate.getLastname()%></li>
                <li><b>Age:</b><%= thatBestCandidate.getAge()%></li>
                <li><b>City:</b><%= thatBestCandidate.getCity()%></li>
                <li><b>Profession:</b><%= thatBestCandidate.getProfession()%></li>
                <li><b>Political party:</b><%= thatBestCandidate.getPoliticalParty()%></li>
            </ul>
            <h2>Why join the parliament?</h2>
            <p><%= thatBestCandidate.getWhyQuestion()%></p>
            <h2>What to improve?</h2>
            <p><%= thatBestCandidate.getImproveQuestion()%></p>

            <% }

                for (int i = 0; i < bestCandidatesAnswers.size(); i++) {
            %>
            <b>Kysymys <%= i + 1%>: <%= allQuestions.get(i).getQuestion()%></b><br>
            <ul>
                <li>Sinun vastaus: <%= usersAnswers.get(i + 1).toString()%></li>
                <li>Ehdokkaan vastaus: <%= bestCandidatesAnswers.get(i).getAnswer()%></li>
                <li>Ehdokkaan kommentti: <%= bestCandidatesAnswers.get(i).getComment()%></li>
            </ul>


            <%
                }

            %>

        </div>

    </body>
</html>
