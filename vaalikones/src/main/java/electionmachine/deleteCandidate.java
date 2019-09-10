package electionmachine;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DbConnection;
import dbClasses.DbCandidate;

/**
 * Servlet implementation class deleteCandidate
 */
@WebServlet(name= "deleteCandidate", urlPatterns = "/deleteCandidate.jsp")
public class deleteCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteCandidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try { 
			  
            // Initialize the database 
            //Connection con = OldConnection.initializeDatabase();
            Connection con = DbConnection.getConnection();

            boolean isDeleted = DbCandidate.delete(Integer.parseInt(request.getParameter("candidate_id")), con);

            con.close(); 

            if (isDeleted) {
                // Get a writer pointer
                // to display the succesful result
                PrintWriter out = response.getWriter();
                out.println("<html><body><b>Successfully Deleted"
                        + "</b></body></html>");
            }
            else {
                PrintWriter out = response.getWriter();
                out.println("<html><body><b>Delete unsuccessful"
                        + "</b></body></html>");
            }
       } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
