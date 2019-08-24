package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

public class Handler {
	private static HashMap<String,Usuario> usuarios = new HashMap<String,Usuario>();
	private static HashMap<String,Defecto> listasDefecto = new HashMap<String,Defecto>();
	private static HashMap<String,Categoria> categorias = new HashMap<String,Categoria>();
	
	public static Usuario findUsuario(String nick) {
		Usuario user = usuarios.get(nick);			//se fija en el map
		if(user == null) {
			EntityManager em = Conexion.getEm();
			user = em.find(Usuario.class, nick);	//se fija en DB
		}
		return user;
		
	}
	
  	
	public static Usuario findUsuarioEM(String email) {
		Usuario usuario = null;		
		for(String nickname : usuarios.keySet()) {
			usuario = usuarios.get(nickname);
			if(email == usuario.getEmail()) {
				return usuario;
			}
		}
		return usuario;
	}
	
	public static boolean addUsuario(Usuario user) {
		Usuario aux = findUsuario(user.getNickname());
		if(aux == null) {
			Conexion.beginTransaction();
			Conexion.persist(user);
			Conexion.commit();
			/*EntityManager em = Conexion.getEm();
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();*/
			usuarios.put(user.getNickname(),user);
			return true;
		}else{
			return false;
		}
	}
	
	public static Defecto findListaDefecto(String nombre) {
		Defecto lst = listasDefecto.get(nombre);
		if(lst == null) {
			EntityManager em = Conexion.getEm();
			return em.find(Defecto.class, nombre);
		}else {
			return lst;
		}
		
	}
	
	public static boolean addListaDefecto(Defecto lst) {
		Defecto aux = findListaDefecto(lst.getNombre());
		if(aux == null) {
			Conexion.beginTransaction();
			Conexion.persist(lst);
			Conexion.commit();
			/*EntityManager em = Conexion.getEm();
			em.getTransaction().begin();
			em.persist(lst);
			em.getTransaction().commit();*/
			listasDefecto.put(lst.getNombre(),lst);
			return true;
		}else{
			return false;
		}
	}
	
	public static Categoria findCategoria(String nombre) {
		Categoria lst = categorias.get(nombre);
		if(lst == null) {
			EntityManager em = Conexion.open();
			return em.find(Categoria.class, nombre);
		}else {
			return lst;
		}
		
	}
	
	public static boolean addCategoria(Categoria c) {
		Categoria aux = findCategoria(c.getNombre());
		if(aux == null) {
			Conexion.beginTransaction();
			Conexion.persist(c);
			Conexion.commit();
			/*EntityManager em = Conexion.getEm();
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();*/
			categorias.put(c.getNombre(),c);
			return true;
		}else{
			return false;
		}
	}
	
	public static ArrayList<String> listarUsuarios(){
		EntityManager em = Conexion.open();
		List users = new ArrayList();		
		users = em.createQuery("SELECT u.nickname FROM Usuario u").getResultList();
		ArrayList<String> names = new ArrayList<String>();
		for(Object name: users) {
			names.add((String)name);
		}
		return names;
	}
	

}
