package logica;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Sistema {
	
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("uytubepap");
		EntityManager em = emf.createEntityManager();
		
		Usuario prueba = new Usuario("juan","perez");		
		
		em.getTransaction().begin();
		em.persist(prueba);
		
		
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
