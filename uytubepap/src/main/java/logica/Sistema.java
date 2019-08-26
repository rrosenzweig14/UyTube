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
		
		
		IControlador ctrl = new Controlador();
		
		/* Se utilizará para el CASO DE USO Alta categoria */
//		boolean cancel = false;
//		boolean success = false;
//		while (!cancel && !success)  
//			success = ctrl.altaCategoria("Accion");
		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("uytubepap");
//		EntityManager em = emf.createEntityManager();
		
		
		
		Date fechaNac1 = new Date();
		if(ctrl.ingresarUsuario("jp","jperezARROBAuymail.com.uy", "juan","perez", fechaNac1, "es_url", "jpChanel"))
			System. out. println("Usuario ingresado con exito\n");
		else 
			System. out. println("El usuario ya existe en el sistema\n");

		Date fechaNac2 = new Date();		
		if(ctrl.ingresarUsuario("RR","rrozenweigARROBAuymail.com","rodrigo","rosenzweig",fechaNac2, "es_url", "RRTV"))
			System. out. println("Usuario ingresado con exito\n");
		else 
			System. out. println("El usuario ya existe en el sistema\n");
		
		if(ctrl.ingresarUsuario("RO","rrozenweigARROBAuymail.com","rodrigo","rosenzwreig",fechaNac2, "es_ur3l", "ROTV"))
			
			System. out. println("Usuario ingresado con exito\n");
		else 
			System. out. println("El usuario ya existe en el sistema\n");
		
		if(ctrl.ingresarUsuario("RG","rrozenweigARROBAuymail.com","rodrigo","rosenzwreig",fechaNac2, "es_ur3l", "RGTV"))
			System. out. println("Usuario ingresado con exito\n");
		else 
			System. out. println("El usuario ya existe en el sistema\n");
		
		ctrl.ingresarVideo("video1", 5, "wwwvideo1", "este es un video de prueba", new Date(), "prueba");
		//ctrl.crearCanal("RG","salame", "todo lo que quiere saber sobre chacinados", false);
				
//		em.getTransaction().begin();
//		em.persist(prueba);
//		em.persist(prueba2);
		
//		
//		prueba.añadirSeguidor(prueba2);
//		prueba2.añadirSeguido(prueba);
		
		
		
		
//		Map<String,Usuario> test = prueba.getSeguidores();
//		Map<String,Usuario> test1 = prueba.getSeguidos();
//		Map<String,Usuario> test2 = prueba2.getSeguidores();
//		Map<String,Usuario> test3 = prueba2.getSeguidos();
//		
//		
//		
//		
//		em.getTransaction().commit();
//		em.close();
//		emf.close();
//		
//		System.out.println(test.size());
//		System.out.println(test1.size());
//		System.out.println(test2.size());
//		System.out.println(test3.size());
		
		
		
	}

}
