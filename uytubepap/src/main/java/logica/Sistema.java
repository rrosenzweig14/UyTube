package logica;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Sistema {
	
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("uytubepap");
		EntityManager em = emf.createEntityManager();
		
		Usuario prueba = new Usuario("juan","perez");
		
		Usuario prueba2 = new Usuario("rodrigo","rosenzweig");	
		
		em.getTransaction().begin();
		em.persist(prueba);
		em.persist(prueba2);
		
		prueba.añadirSeguidor(prueba2);
		prueba2.añadirSeguido(prueba);
		
		
		
		
		Map<String,Usuario> test = prueba.getSeguidores();
		Map<String,Usuario> test1 = prueba.getSeguidos();
		Map<String,Usuario> test2 = prueba2.getSeguidores();
		Map<String,Usuario> test3 = prueba2.getSeguidos();
		
		
		
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		System.out.println(test.size());
		System.out.println(test1.size());
		System.out.println(test2.size());
		System.out.println(test3.size());
		
		
		
	}

}
