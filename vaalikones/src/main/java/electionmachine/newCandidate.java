package electionmachine;

import javax.persistence.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Candidate;

/**
 * Servlet implementation class newCandidate
 */
@WebServlet(name = "newCandidate", urlPatterns = {"/newCandidate"})
public class newCandidate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */

    public newCandidate() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = Enti.getEm();
        EntityTransaction entitytransaction = em.getTransaction();
        entitytransaction.begin();
        Candidate cand = new Candidate();
        String s = request.getParameter("lastname");
        String e = request.getParameter("firstname");
        String p = request.getParameter("political_party");
        String k = request.getParameter("city");
        int i = Integer.parseInt(request.getParameter("age"));
        String m = request.getParameter("why_question");
        String mi = request.getParameter("improve_question");
        String a = request.getParameter("profession");

        cand.setLastName(s);
        cand.setFirstName(e);
        cand.setPoliticalParty(p);
        cand.setCity(k);
        cand.setAge(i);
        cand.setWhyQuestion(m);
        cand.setImproveQuestion(mi);
        cand.setProfession(a);
        em.persist(cand);
        em.getTransaction().commit();
        response.sendRedirect(request.getContextPath() + "newCandidate.jsp");


//		// This class can be used to initialize the database connection 
//		
//		try { 
//			  
//            // Initialize the database 
//            Connection con = OldConnection.initializeDatabase(); 
//  
//            // Create a SQL query to insert data into demo table 
//            // demo table consists of two columns, so two '?' is used 
//            PreparedStatement st = con 
//                   .prepareStatement("insert into ehdokkaat(sukunimi,etunimi,puolue,kotipaikkakunta,ika,miksi_eduskuntaan,mita_asioita_haluat_edistaa,ammatti) values(?, ?, ?, ?, ?, ?, ?, ?)"); 
//  
//            // For the first parameter, 
//            // get the data using request object 
//            // sets the data to st pointer 
//            //st.setInt(1, Integer.valueOf(request.getParameter("ehdokas_id"))); 
//  
//            // Same for second parameter 
//            st.setString(1, request.getParameter("sukunimi"));
//            st.setString(2, request.getParameter("etunimi")); 
//            st.setString(3, request.getParameter("puolue")); 
//           st.setString(4, request.getParameter("kotipaikkakunta")); 
//            st.setInt(5, Integer.valueOf( request.getParameter("ika"))); 
//           st.setString(6, request.getParameter("miksi_eduskuntaan")); 
//            st.setString(7, request.getParameter("mita_asioita_haluat_edistaa")); 
//            st.setString(8, request.getParameter("ammatti")); 
//  
//            // Execute the insert command using executeUpdate() 
//            // to make changes in database 
//            st.executeUpdate();  
//            // Close all the connections 
//            st.close(); 
//            con.close(); 
//  
//            // Get a writer pointer  
//            // to display the succesful result 
//           PrintWriter out = response.getWriter(); 
//            out.println("<html><body><b>Successfully Inserted"
//                        + "</b></body></html>"); 
//       } 
//        catch (Exception e) { 
//            e.printStackTrace(); 
//        } 
    }
//
//	
//

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
        // This class can be used to initialize the database connection


    }

}
