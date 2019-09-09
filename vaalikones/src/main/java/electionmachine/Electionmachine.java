 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electionmachine;

import java.io.IOException;
import static java.lang.Integer.parseInt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.utils.SystemProperty;

import persist.Ehdokkaat;
import persist.Kysymykset;
import persist.Vastaukset;

/**
 * Electionmachine-servlet, vastaa vaalikoneen varsinaisesta toiminnallisuudesta
 *
 * @author Jonne
 */
public class Electionmachine extends HttpServlet {

	
    //hae java logger-instanssi
    private final static Logger logger = Logger.getLogger(Log.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int question_id;

        // hae http-sessio ja luo uusi jos vanhaa ei ole vielä olemassa
        HttpSession session = request.getSession(true);

        //hae käyttäjä-olio http-sessiosta
        User usr = (User) session.getAttribute("usrobj");

        //jos käyttäjä-oliota ei löydy sessiosta, luodaan sinne sellainen
        if (usr == null) {
            usr = new User();
            logger.log(Level.FINE, "User-object created.");
            session.setAttribute("usrobj", usr);
        }
        EntityManagerFactory emf=null;
        EntityManager em = null;
        try {
  	      emf=Persistence.createEntityManagerFactory("electionmachines");
  	      em = emf.createEntityManager();
        }
        catch(Exception e) {
          	response.getWriter().println("EMF+EM does not compute.");
          	
          	e.printStackTrace(response.getWriter());
          	
          	return;
        }
        
        //hae url-parametri func joka määrittää toiminnon mitä halutaan tehdä.
        //func=haeEhdokas: hae tietyn ehdokkaan tiedot ja vertaile niitä käyttäjän vastauksiin
        //Jos ei määritelty, esitetään kysymyksiä.
        String strFunc = request.getParameter("func");

        if (strFunc == null) {

            //hae parametrinä tuotu edellisen kysymyksen nro
            String strQuestion_id = request.getParameter("question");

            //hae parametrina tuotu edellisen kysymyksen vastaus
            String strAnswer = request.getParameter("answer");

            // Jos kysymyksen numero (kysId) on asetettu, haetaan tuo kysymys
            // muuten haetaan kysnro 1
            if (strQuestion_id == null) {
                question_id = 1;
            } else {
                question_id = parseInt(strQuestion_id);
                //jos vastaus on asetettu, tallenna se session käyttäjä-olioon
                if (strAnswer != null) {
                    usr.addAnswer(question_id, parseInt(strAnswer));
                }

                //määritä seuraavaksi haettava kysymys
                question_id++;
            }

            //jos kysymyksiä on vielä jäljellä, hae seuraava
            if (question_id < 20) {
                try {
                    //Hae haluttu kysymys tietokannasta
                    Query q = em.createQuery(
                            "SELECT k FROM Questions k WHERE k.questionId=?1");
                    q.setParameter(1, question_id);
                    //Lue haluttu kysymys listaan
                    List<Questions> questionList = q.getResultList();
                    request.setAttribute("questions", questionList);
                    request.getRequestDispatcher("/answer.jsp")
                            .forward(request, response);

                } finally {
                    // Sulje tietokantayhteys
                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    em.close();
                }

                //jos kysymykset loppuvat, lasketaan tulos!
            } else {

                //Tyhjennetään piste-array jotta pisteet eivät tuplaannu mahdollisen refreshin tapahtuessa
                for (int i = 0; i < 20; i++) {
                    usr.points.set(i, new Tuple<>(0, 0));
                }

                //Hae lista ehdokkaista
                Query qE = em.createQuery(
                        "SELECT e.candidateId FROM Candidates e"
                );
                List<Integer> candidateList = qE.getResultList();

                //iteroi ehdokaslista läpi
                for (int i = 1; i < candidateList.size(); i++) {

                    //Hae lista ehdokkaiden vastauksista
                    Query qV = em.createQuery(
                            "SELECT v FROM Answers v WHERE v.answersPk.candidateId=?1");
                    qV.setParameter(1, i);
                    List<Answers> answerList = qV.getResultList();

                    //iteroi vastauslista läpi
                    for (Answers eAnswer : answerList) {
                        int points;

                        //hae käyttäjän ehdokaskohtaiset pisteet
                        points = usr.getPoints(i);

                        //laske oman ja ehdokkaan vastauksen perusteella pisteet 
                        points += countPoints(usr.getAnswer(i), eAnswer.getAnswer());

                        logger.log(Level.INFO, "eID: {0} / k: {1} / kV: {2} / eV: {3} / p: {4}", new Object[]{i, eAnswer.getAnswers().getQuestionId(), usr.getAnswer(i), eAnswer.getAnswer(), points});
                        usr.addPoints(i, points);
                    }

                }

                //siirrytään hakemaan paras ehdokas
                strFunc = "searchForCandidate";
            }

        }

        //jos func-arvo on haeEhdokas, haetaan haluttu henkilö käyttäjälle sopivimmista ehdokkaista
        if ("searchForCandidate".equals(strFunc)) {
            //luetaan url-parametristä "top-listan järjestysnumero". Jos ei määritelty, haetaan PARAS vaihtoehto.
            String strNumber = request.getParameter("number");
            Integer Number = 0;
            if (strNumber != null) {
                number = Integer.parseInt(strNumber);
            }

            //Lue käyttäjälle sopivimmat ehdokkaat väliaikaiseen Tuple-listaan.
            List<Tuple<Integer, Integer>> tpl = usr.searchForBestCandidate();

            //hae määritetyn ehdokkaan tiedot
            Query q = em.createQuery(
                    "SELECT e FROM candidates e WHERE e.candidateId=?1");
            q.setParameter(1, tpl.get(number).candidateId);
            List<Candidates> bestCandidate = q.getResultList();

            //hae ko. ehdokkaan vastaukset
            q = em.createQuery(
                    "SELECT v FROM answers v WHERE v.answerPk.candidateId=?1");
            q.setParameter(1, tpl.get(number).candidateId);
            List<Answers> bestCandidateAnswers = q.getResultList();

            //hae kaikki kysymykset
            q = em.createQuery(
                    "SELECT k FROM questions k");
            List<Questions> allQuestions = q.getResultList();
            
            //ohjaa tiedot tulosten esityssivulle
            request.setAttribute("allQuestions", allQuestions);
            request.setAttribute("usersAnswers", usr.getAnswerList());
            request.setAttribute("BestCandidateAnswers", bestCandidateAnswers);
            request.setAttribute("bestCandidate", bestCandidate);
            request.setAttribute("points", tpl.get(number).points);
            request.setAttribute("number", number);
            request.getRequestDispatcher("/results.jsp")
                    .forward(request, response);

            // Sulje tietokantayhteys
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();

        }

    }

    private Integer countPoints(Integer kAnswer, Integer eAnswer) {
        int points = 0;
        if (kAnswer - eAnswer == 0) {
        	points = 3;
        }
        if (kAnswer - eAnswer == 1 || kAnswer - eAnswer == -1) {
        	points = 2;
        }
        if (kAnswer - eAnswer == 2 || kAnswer - eAnswer == -2 || kAnswer - eAnswer == 3 || kAnswer - eAnswer == -3) {
        	points = 1;
        }
        
        //if (kVastaus - eVastaus == 4 || kVastaus - eVastaus == -4) pisteet = 0;
        return points;

    }

    //<editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
