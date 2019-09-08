package vaalikone;
import javax.persistence.*;
public class Enti {

	public static EntityManager getEm() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		
		try {
			emf = Persistence.createEntityManagerFactory("vaalikones");
			em = emf.createEntityManager();
		} catch (Exception e) {
			
		}
		return em;
	}
}
