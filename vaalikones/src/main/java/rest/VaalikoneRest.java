package rest;

import javax.ws.rs.GET;

import javax.ws.rs.POST;
 
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
import com.google.api.client.util.Data;
 
import persist.Ehdokkaat;
import persist.Kysymykset;
 
@Path("/vaalikonejpaservice")
public class VaalikoneRest {
 
    @POST
    @Path("/lisaaehdokas")
    @Consumes(MediaType.APPLICATION_JSON)
    public void lisaaehdokas(Ehdokkaat e) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikones");
        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }
    
    @POST
    @Path("/muokkaaehdokas")
    @Consumes(MediaType.APPLICATION_JSON)
    public void muokkaaehdokas(Ehdokkaat e) throws Exception {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikones");
        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(e);
        em.getTransaction().commit();
    }
    
    @POST
    @Path("/poistaehdokas/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public String poistaehdokas(@PathParam("param") int id) {
    	
    	String info = "EhdokasID " + id + " on nyt poistettu.";
    	
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikones");
        EntityManager em=emf.createEntityManager();
        Ehdokkaat e=em.find(Ehdokkaat.class, id);
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
        return info;
    }
    
    @POST
    @Path("/poistakysymys/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String poistakysymys(@PathParam("param") int kysymysId) {
    	
    	String info = "KysymysID " + kysymysId + " on nyt poistettu.";
    	
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikones");
        EntityManager em=emf.createEntityManager();
        Kysymykset e=em.find(Kysymykset.class, kysymysId);
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
        return info;
    }
    
    @GET
    @Path("/etsiehdokkaat")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Ehdokkaat> etsiehdokkaat() {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikones");
        EntityManager em=emf.createEntityManager();
        Query q=em.createQuery("select a from Ehdokkaat a");
        return (List<Ehdokkaat>)(q.getResultList());
    }
    
    @POST
    @Path("/lisaakysymys")
    @Consumes(MediaType.APPLICATION_JSON)
    public void lisaakysymys(Kysymykset e) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikones");
        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }
    
    @POST
    @Path("/muokkaakysymys")
    @Consumes(MediaType.APPLICATION_JSON)
    public void muokkaakysymys(Kysymykset e) throws Exception {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikones");
        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(e);
        em.getTransaction().commit();
    }
    
    @GET
    @Path("/etsikysymykset")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Kysymykset> etsikysymykset() {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikones");
        EntityManager em=emf.createEntityManager();
        Query q=em.createQuery("select a from Kysymykset a");
        return (List<Kysymykset>)(q.getResultList());
    }
}