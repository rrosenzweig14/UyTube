package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import datatypes.DtCanal;
import datatypes.DtLista;
import datatypes.DtUsuario;
import datatypes.DtVideo;

public class Handler {
	private static HashMap<String,Usuario> usuarios = new HashMap<String,Usuario>();
	private static ArrayList<String> listasDefecto = new ArrayList<String>();
	private static HashMap<String,Categoria> categorias = new HashMap<String,Categoria>();
	
	public static Usuario findUsuario(String nick) {
		Usuario user = usuarios.get(nick);  //se fija en el map
		if(user == null) {
			EntityManager em = Conexion.getEm();
			user = em.find(Usuario.class, nick);	//se fija en DB
			if (user != null) usuarios.put(nick, user);
		}
		return user;
	}
	
  	public static boolean bajaUsuario(String nick) {
		System.out.println(nick+"@@@@@@@@@@@@@@@@@@@@@@@@@");
		boolean res = false;
		EntityManager em = Conexion.getEm();
		Usuario us = em.find(Usuario.class, nick);
		if(us != null) {
			usuarios.remove(nick,us);
			UsuarioEliminado ue = us.crearEliminado();			
			EntityTransaction et = em.getTransaction();
			et.begin();
			us = em.find(Usuario.class, nick);
			em.remove(us);
			em.flush();
			et.commit();
			us= null;
			res = true;	
			Conexion.beginTransaction();
			Conexion.persist(ue);
			Conexion.commit();
		}
		return res;	
  	}
	
	public static Usuario findUsuarioEM(String email) {
		Usuario usuario = null;	
		for (Usuario user : usuarios.values()){		
			if(email == user.getEmail()) {
				usuario = user;
			}
		}
		if(usuario == null) {
			String query = "SELECT u FROM Usuario u WHERE baja <> null and u.email = '" + email + "'";
			//Conexion.open();
			try {
				usuario = (Usuario) Conexion.getEm().createQuery(query).getResultList().get(0);
			} catch (Exception e) {
				// TODO: handle exception
			}			
		}
		return usuario;
	}
	
	public static void addUsuario(String nickname, String email,String password, String nombre, String apellido, Date fechaNac, String img, DtCanal canal) {
		Conexion.beginTransaction();
		Usuario usuario = new Usuario(nickname, email,password , nombre, apellido, fechaNac, img, canal);
		Canal x = usuario.getCanal();
		x.setUsuario(usuario);
		Iterator<String> i = listasDefecto.iterator();
		while(i.hasNext())
			x.agregarListaDefecto(i.next());					
		Conexion.persist(usuario);
		Conexion.commit();
		usuarios.put(nickname, usuario);
}


	public static Defecto findListaDefecto(String nombre) {
		Defecto lst = null;
		if(listasDefecto.contains(nombre)) {
			lst = new Defecto(nombre,false);
		}else{
			EntityManager em = Conexion.getEm();
			lst = em.find(Defecto.class, nombre);
			listasDefecto.add(nombre);
		}
		return lst;
		
	}
	
	public static ArrayList<String> listarVideos(DtUsuario usr){
		Usuario usrl = Handler.findUsuario(usr.getNickname());
		Canal cnl = usrl.getCanal();
		@SuppressWarnings("rawtypes")
		List videos = new ArrayList();
		videos = Conexion.createQuery("SELECT v.nombre FROM Canal_video c AND Video v WHERE c.canal_nickname="+ cnl.getNickname()+ " AND c.Listavideos_id = v.id");
		ArrayList<String> names = new ArrayList<String>();
		for(Object name: videos) {
			names.add((String)name);
		}
		return names;
	}
	
	public static boolean addListaDefecto(String nombre) {
		boolean res = false;
		Iterator<String> i = listasDefecto.iterator();
		while(i.hasNext() && !res)
			if(i.next() == nombre)
					res = true;
		if (!res) listasDefecto.add(nombre);
		return res;
		
	}
	
	public static Categoria findCategoria(String nombre) {
		Categoria lst = categorias.get(nombre);
		if(lst == null) {
			EntityManager em = Conexion.getEm();
			lst = em.find(Categoria.class, nombre);
			categorias.put(nombre, lst);
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
	
	public static List<Usuario> getUsuarioList(){
		@SuppressWarnings("rawtypes")
		List lista = new ArrayList();
		lista = Conexion.createQuery("SELECT u FROM Usuario u");	
		return lista;
	}
	
	
	public static ArrayList<String> listasDefecto(){
		@SuppressWarnings("rawtypes")
		List listas = new ArrayList();
		listas = Conexion.createQuery("SELECT l.nombre FROM Lista l WHERE dtype = 'Defecto'");
		ArrayList<String> nombreListas = new ArrayList<String>();
		for (Object lst : listas) {
			nombreListas.add(lst.toString());
		}
		listasDefecto = nombreListas;
		return nombreListas;
		
	}
	
	public static HashMap<Integer,String> listarVideosPublicos(){
		@SuppressWarnings("rawtypes")
		List aux = new ArrayList();	
		aux = Conexion.createQuery("SELECT v FROM Video v WHERE v.privado = false");	
		HashMap<Integer,String> videos = new HashMap<Integer,String>();
		if(aux != null) {
			for(Object o: aux) {
				Video v = (Video)o;
				videos.put(v.getId(), v.getNombre());			
			}
		}
		return videos;
	}
	
	public static ArrayList<DtVideo> buscarVideosPublicos(String dato){
		@SuppressWarnings("rawtypes")
		List aux = new ArrayList();	
		aux = Conexion.createQuery("SELECT v FROM Video v WHERE v.privado = false AND (LOWER(v.nombre) LIKE LOWER('%"+ dato +"%') OR "
				+ "LOWER(v.descripcion) LIKE LOWER('%"+ dato +"%'))");	
		ArrayList<DtVideo> videos = new ArrayList<DtVideo>();
		if(aux != null) {
			for(Object o: aux) {
				Video v = (Video)o;
				videos.add(v.getDt());			
			}
		}
		return videos;
	}
	
	
	public static HashMap<Integer,String> listarListasPublicas(){
		@SuppressWarnings("rawtypes")
		List aux = new ArrayList();	
		aux = Conexion.createQuery("SELECT l FROM Lista l WHERE l.privado = false");
		HashMap<Integer,String> listas = new HashMap<Integer,String>();
		if(aux != null) {
			for(Object o: aux) {
				Lista l = (Lista)o;
				listas.put(l.getId(), l.getNombre());			
			}
		}
		return listas;
	}
	
	public static ArrayList<DtLista> buscarListasPublicas(String dato){
		@SuppressWarnings("rawtypes")
		List aux = new ArrayList();	
		aux = Conexion.createQuery("SELECT l FROM Lista l WHERE l.privado = false AND LOWER(l.nombre) LIKE LOWER('%" + dato + "%')");	
		ArrayList<DtLista> listas = new ArrayList<DtLista>();
		if(aux != null) {
			for(Object o: aux) {
				Lista l = (Lista)o;
				listas.add(l.getDt());		
			}
		}
		return listas;
	}
	
	public static HashMap<String,String> listarCanalesPublicos(){
		@SuppressWarnings("rawtypes")
		List aux = new ArrayList();	
		aux = Conexion.createQuery("SELECT c FROM Canal c WHERE c.privado = false");	
		HashMap<String,String> canales = new HashMap<String,String>();
		if(aux != null) {
			for(Object o: aux) {
				Canal c = (Canal)o;
				canales.put(c.getNickname(), c.getNombre());			
			}
		}
		return canales;
	}
	
	public static ArrayList<DtCanal> buscarCanalesPublicos(String dato){
		@SuppressWarnings("rawtypes")
		List aux = new ArrayList();	
		aux = Conexion.createQuery("SELECT c FROM Canal c WHERE c.privado = false AND (LOWER(c.nombre) LIKE LOWER('%"+ dato +"%') "
				+ "OR LOWER(c.descripcion) LIKE LOWER('%"+ dato +"%'))" );	
		ArrayList<DtCanal>  canales = new ArrayList<DtCanal>();
		if(aux != null) {
			for(Object o: aux) {
				Canal c = (Canal)o;
				canales.add(c.getDt());		
			}
		}
		return canales;
	}
	
	public static HashMap<Integer,String> listarVideosXXX(String nick){
		Usuario usr = findUsuario(nick);	
		HashMap<Integer,String> videos = new HashMap<Integer,String>();
		if(usr != null) {
			Canal c = usr.getCanal();
			for(Video v: c.getListaVideos().values()) {
				if(v.getPrivado()) {
					videos.put(v.getId(), v.getNombre());
				}
			}
		}
		return videos;		
	}
	
	public static Video findVideo(int id) {		
		EntityManager em = Conexion.getEm();
		Video v = em.find(Video.class, id);
		return v;		
	}
	
	public static Lista findLista(int id) {
		EntityManager em = Conexion.getEm();
		Lista l = em.find(Lista.class, id);
		return l;
	}
	

	public static ArrayList<String> listarEliminados(){
		@SuppressWarnings("rawtypes")
		List users = new ArrayList();		
		users = Conexion.createQuery("SELECT u.nickname FROM UsuarioEliminado u");
		ArrayList<String> names = new ArrayList<String>();
		for(Object name: users) {
			names.add((String)name);
		}
		return names;
	}
	
	public static UsuarioEliminado findEliminado(String nick) {
		UsuarioEliminado user;
		EntityManager em = Conexion.getEm();
		user = em.find(UsuarioEliminado.class, nick);	//se fija en DB
		return user;
	}
	
}
