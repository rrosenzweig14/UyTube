package logica;

import java.io.Console;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import interfaces.IControlador;

public class Sistema {
	
	
	public static void main(String[] args) {
		
		
//		IControlador ctrl = new Controlador();
		
		/* Se utilizará para el CASO DE USO Alta categoria */
//		boolean cancel = false;
//		boolean success = false;
//		while (!cancel && !success)  
//			success = ctrl.altaCategoria("Accion");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("uytubepap");
		EntityManager em = emf.createEntityManager();
		
		Date fechaNac1 = new Date();
		Usuario prueba = new Usuario("jp","juan","perez", "jperez@uymail.com.uy", fechaNac1, "es_url");
		
		Date fechaNac2 = new Date();
		Usuario prueba2 = new Usuario("RR","rodrigo","rosenzweig","rrozenweig@uymail.com",fechaNac2, "es_url");	
		
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
