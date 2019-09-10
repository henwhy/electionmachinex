package electionmachine;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Candidate;
import db.DbConnection;
import dbClasses.DbCandidate;

/**
 * Servlet implementation class Muokkaaehdokas
 */
@WebServlet(name = "editCandidate", urlPatterns = "/editCandidate.jsp")
public class editCandidate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // This class can be used to initialize the database connection

        try {

            // Initialize the database
            Connection conn = DbConnection.getConnection();

            Candidate candidate = new Candidate(
					Integer.parseInt(request.getParameter("candidate_id")),
                    request.getParameter("lastname"),
                    request.getParameter("firstname"),
                    request.getParameter("political_party"),
                    request.getParameter("city"),
                    Integer.parseInt(request.getParameter("age")),
                    request.getParameter("why_question"),
                    request.getParameter("improve_question"),
                    request.getParameter("profession")
            );

            DbCandidate.save(candidate, conn);

            conn.close();

            // Get a writer pointer
            // to display the succesful result
            PrintWriter out = response.getWriter();
            out.println("<html><body><b>Successfully edited"
                    + "</b></body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
