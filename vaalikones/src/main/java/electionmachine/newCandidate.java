package electionmachine;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Candidate;
import db.DbConnection;
import dbClasses.DbCandidate;

/**
 * Servlet implementation class newCandidate
 */
@WebServlet(name = "newCandidate", urlPatterns = {"/newCandidate"})
public class newCandidate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = DbConnection.getConnection();

        Candidate candidate = new Candidate(
                null,
                request.getParameter("lastname"),
                request.getParameter("firstname"),
                request.getParameter("political_party"),
                request.getParameter("city"),
                Integer.parseInt(request.getParameter("age")),
                request.getParameter("why_question"),
                request.getParameter("improve_question"),
                request.getParameter("profession")
        );

        candidate = DbCandidate.save(candidate, conn);

        response.sendRedirect(request.getContextPath() + "newCandidate.jsp");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
        // This class can be used to initialize the database connection
    }
}
