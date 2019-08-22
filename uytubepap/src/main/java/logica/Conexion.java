package logica;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexion {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("uytubepap");
	private static EntityManager em = emf.createEntityManager();	// = Persistence.createEntityManagerFactory("uytubepap").createEntityManager();
	
	public static EntityManager getEm() {
		return em;
	}	
}