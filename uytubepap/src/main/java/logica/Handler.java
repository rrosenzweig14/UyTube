package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

public class Handler {
	private static HashMap<String,Usuario> usuarios = new HashMap<String,Usuario>();
	private static HashMap<String,Defecto> listasDefecto = new HashMap<String,Defecto>();
	private static HashMap<String,Categoria> categorias = new HashMap<String,Categoria>();
	
	public static Usuario findUsuario(String nick) {
		Usuario user = usuarios.get(nick);  //se fija en el map
		System.out.println("al entrar en findUSUARIO");
		if(user == null) {
			EntityManager em = Conexion.getEm();
			user = em.find(Usuario.class, nick);	//se fija en DB
			System.out.println("cuando user null findUsuario");
		}
		return user;
	}
	
  	
	public static Usuario findUsuarioEM(String email) {
		Usuario usuario = null;	
		for (Usuario user : usuarios.values()){		
			if(email == user.getEmail()) {
				usuario = user;
			}
		}
		if(usuario == null) {
			@SuppressWarnings("rawtypes")
			List usuarios = new ArrayList();
			String query = "SELECT u FROM usuario u WHERE u.email = '" + email + "'";
			usuarios = Conexion.createQuery(query);
			if(usuarios != null)
				if(!usuarios.isEmpty())
				usuario = (Usuario) usuarios.get(0);

		}
		if(usuario == null) {
			System.out.println("Usuario null en FindEM");
		}
		return usuario;
	}
	
	public static boolean addUsuario(String nickname, String email, String nombre, String apellido, Date fechaNac, String img, String canal) {
		Usuario aux = findUsuario(nickname);
		if(aux == null)
			aux = findUsuarioEM(email);
		if(aux == null) {
			Conexion.beginTransaction();
			Usuario usuario = new Usuario(nickname, email, nombre, apellido, fechaNac, img, canal);
			Canal x = usuario.getCanal();
			x.setUsuario(usuario);
			x.setNickname(nickname);
			Conexion.persist(usuario);
			Conexion.commit();
			/*EntityManager em = Conexion.getEm();
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();*/
			usuarios.put(nickname, usuario);
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
			lst = em.find(Categoria.class, nombre);
			em.close();
			return lst;
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
		@SuppressWarnings("rawtypes")
		List users = new ArrayList();		
		users = Conexion.createQuery("SELECT u.nickname FROM Usuario u");
		ArrayList<String> names = new ArrayList<String>();
		for(Object name: users) {
			names.add((String)name);
		}
		return names;
	}
	
	public static ArrayList<String> listarCategorias(){		
		@SuppressWarnings("rawtypes")
		List categorias = new ArrayList();
		categorias = Conexion.createQuery("SELECT c.nombre FROM Categoria c");
		ArrayList<String> nombresCategoria = new ArrayList<String>();
		for (Object nombre : categorias) {
			nombresCategoria.add(nombre.toString());
		}
		return nombresCategoria;
	}
	
	public static HashMap<String,Usuario> getUsuarios(){
		return usuarios;
	}
	
	public static ArrayList<String> listasDefecto(){
		@SuppressWarnings("rawtypes")
		List listas = new ArrayList();
		listas = Conexion.createQuery("SELECT l.nombre FROM Lista l WHERE dtype = 'Defecto'");
		ArrayList<String> nombreListas = new ArrayList<String>();
		for (Object lst : listas) {
			nombreListas.add(lst.toString());
		}
		return nombreListas;
	}
	

}
