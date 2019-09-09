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
/**
 * Servlet implementation class Muokkaaehdokas
 */
@WebServlet(name = "editCandidate", urlPatterns = "/editCandidate.jsp")
public class editCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editCandidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// This class can be used to initialize the database connection 
		
				try { 
					  
		            // Initialize the database 
		            //Connection con = OldConnection.initializeDatabase();
		            Connection con = DbConnection.getConnection();

		            // Create a SQL query to insert data into demo table 
		            // demo table consists of two columns, so two '?' is used 
		            PreparedStatement st = con 
		            		.prepareStatement("insert into ehdokkaat (ehdokas_id, sukunimi,etunimi,puolue,kotipaikkakunta,ika,miksi_eduskuntaan,mita_asioita_haluat_edistaa,ammatti) values (?, ?, ?, ?, ?, ?, ?, ?, ?) on duplicate key update "
		            				+ "ehdokas_id = values(ehdokas_id), sukunimi = values(sukunimi), etunimi = values(etunimi), puolue = values(puolue), kotipaikkakunta = values(kotipaikkakunta), ika = values(ika), miksi_eduskuntaan = values(miksi_eduskuntaan), mita_asioita_haluat_edistaa = values(mita_asioita_haluat_edistaa), ammatti = values(ammatti)");
		            // For the first parameter, 
		            // get the data using request object 
		            // sets the data to st pointer 
		            st.setString(1, request.getParameter("candidate_id"));
		            st.setString(2, request.getParameter("lastname"));
		            st.setString(3, request.getParameter("firstname")); 
		            st.setString(4, request.getParameter("political_party")); 
		            st.setString(5, request.getParameter("city")); 
		            st.setString(6, request.getParameter("age"));
		            st.setString(7, request.getParameter("why_question")); 
		            st.setString(8, request.getParameter("improve_question")); 
		            st.setString(9, request.getParameter("profession")); 
		  
		            // Execute the insert command using executeUpdate() 
		            // to make changes in database 
		            st.executeUpdate();  
		            // Close all the connections 
		            st.close(); 
		            con.close(); 
		  
		            // Get a writer pointer  
		            // to display the succesful result 
		           PrintWriter out = response.getWriter(); 
		            out.println("<html><body><b>Successfully edited"
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
				// This class can be used to initialize the database connection 
				
						
			}
}
