package logica;

import java.util.HashMap;

import javax.persistence.EntityManager;

public class Handler {
	private static HashMap<String,Usuario> usuarios = new HashMap<String,Usuario>();
	private static HashMap<String,Defecto> listasDefecto = new HashMap<String,Defecto>();
	private static HashMap<String,Categoria> categorias = new HashMap<String,Categoria>();
	
	public Usuario findUsuario(String nick) {
		Usuario user = usuarios.get(nick);			//se fija en el map
		if(user == null) {
			EntityManager em = Conexion.getEm();
			user = em.find(Usuario.class, nick);	//se fija en DB
		}
		return user;
		
	}
	
//  	
//	public Usuario findUsuarioEM(String email) {
//		Usuario usuario = null;
//		String email = usuarios.forEach(getEmail());;
//	}
	
	public boolean addUsuario(Usuario user) {
		Usuario aux = findUsuario(user.getNickname());
		if(aux == null) {
			EntityManager em = Conexion.getEm();
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			usuarios.put(user.getNickname(),user);
			return true;
		}else{
			return false;
		}
	}
	
	public Defecto findListaDefecto(String nombre) {
		Defecto lst = listasDefecto.get(nombre);
		if(lst == null) {
			EntityManager em = Conexion.getEm();
			return em.find(Defecto.class, nombre);
		}else {
			return lst;
		}
		
	}
	
	public boolean addListaDefecto(Defecto lst) {
		Defecto aux = findListaDefecto(lst.getNombre());
		if(aux == null) {
			EntityManager em = Conexion.getEm();
			em.getTransaction().begin();
			em.persist(lst);
			em.getTransaction().commit();
			listasDefecto.put(lst.getNombre(),lst);
			return true;
		}else{
			return false;
		}
	}
	
	public Categoria findCategoria(String nombre) {
		Categoria lst = categorias.get(nombre);
		if(lst == null) {
			EntityManager em = Conexion.getEm();
			return em.find(Categoria.class, nombre);
		}else {
			return lst;
		}
		
	}
	
	public boolean addCategoria(Categoria c) {
		Categoria aux = findCategoria(c.getNombre());
		if(aux == null) {
			EntityManager em = Conexion.getEm();
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
			categorias.put(c.getNombre(),c);
			return true;
		}else{
			return false;
		}
	}
	

}
