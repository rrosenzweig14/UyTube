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
	
	public static EntityManager open() {
		try {
			emf = Persistence.createEntityManagerFactory("uytubepap");
			em = emf.createEntityManager();
			return em;
		}catch(Exception e){
			return null;
		}
	}
	
	public static boolean beginTransaction() {
		try {
			em.getTransaction().begin();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean persist(Object o) {
		try {
			em.persist(o);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean commit() {
		try {
			em.getTransaction().commit();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean close() {
		try {
			em.close();
			emf.close();
			return true;
		}catch(Exception e){
			return false;
		}		
	}
}