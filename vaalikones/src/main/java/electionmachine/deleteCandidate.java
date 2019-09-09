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

import db.DbConnection;
import vaalikone.Uusi;

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

            // Create a SQL query to insert data into demo table 
            // demo table consists of two columns, so two '?' is used 
            PreparedStatement st = con 
            .prepareStatement("DELETE FROM candidates WHERE candidate_id =  ?"); 
  
 
            st.setString(1, request.getParameter("candidate_id"));
  
            // Execute the insert command using executeUpdate() 
            // to make changes in database 
            st.executeUpdate();  
            // Close all the connections 
            st.close(); 
            con.close(); 
  
            // Get a writer pointer  
            // to display the succesful result 
           PrintWriter out = response.getWriter(); 
            out.println("<html><body><b>Successfully Deleted"
                        + "</b></body></html>"); 
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
