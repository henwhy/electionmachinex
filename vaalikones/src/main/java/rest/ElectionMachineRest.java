package rest;

import javax.ws.rs.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.MediaType;

import classes.Candidate;
import classes.Question;

import db.DbConnection;
import dbClasses.DbCandidate;
import dbClasses.DbQuestion;

@Path("/ems") // election-machine-service
public class ElectionMachineRest {

    @POST
    @Path("/add-candidate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Candidate addCandidate(Candidate candidate) throws SQLException {
        Connection conn = DbConnection.getConnection();

        candidate = DbCandidate.save(candidate, conn);

        conn.close();

        return candidate;
    }

    @DELETE
    @Path("/remove-candidate/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean removeCandidate(@PathParam("id") int id) throws SQLException {

        Connection conn = DbConnection.getConnection();

        boolean isDeleted = DbCandidate.delete(id, conn);

        conn.close();

        String info = "EhdokasID " + id + " on nyt poistettu.";

        return isDeleted;
    }

    @POST
    @Path("/edit-candidate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Candidate editCandidate(Candidate candidate) throws SQLException {
        Connection conn = DbConnection.getConnection();

        candidate = DbCandidate.save(candidate, conn);

        conn.close();

        return candidate;
    }

    @DELETE
    @Path("/remove-question/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    public boolean removeQuestion(@PathParam("id") int questionId) throws SQLException {
        Connection conn = DbConnection.getConnection();

        boolean isDeleted = DbQuestion.delete(questionId, conn);

        conn.close();

        return isDeleted;
    }

    @GET
    @Path("/get-candidates")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Candidate> getCandidates() throws SQLException {
        Connection conn = DbConnection.getConnection();

        List<Candidate> arrayList = DbCandidate.getCandidates(conn);

        conn.close();

        return arrayList;
    }

    @POST
    @Path("/add-question")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Question addQuestion(Question question) throws SQLException {
        Connection conn = DbConnection.getConnection();

        question = DbQuestion.save(question, conn);

        conn.close();

        return question;
    }

    @POST
    @Path("/edit-question")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Question editQuestion(Question question) throws Exception {
        Connection conn = DbConnection.getConnection();

        question = DbQuestion.save(question, conn);

        conn.close();

        return question;
    }

    @GET
    @Path("/get-questions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Question> getQuestions() throws SQLException {
        Connection conn = DbConnection.getConnection();

        List<Question> list = DbQuestion.getQuestions(conn);

        conn.close();

        return list;
    }
}