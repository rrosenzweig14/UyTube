package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import datatypes.DtCanal;
import datatypes.DtComentario;
import datatypes.DtLista;
import datatypes.DtUsuario;
import datatypes.DtVideo;
import interfaces.IControlador;

public class Controlador implements IControlador{

	private Usuario user1;
	private Usuario user2;
	private boolean defecto;
	private Video video;
	
	@Override
	public void valorarVideo(String nick, boolean valor) {
		EntityManager em = Conexion.getEm();	
		Handler hldr = new Handler();
		Usuario_Video usrvid = new Usuario_Video();
		usrvid.setLeGusta(valor);
		usrvid.setNombreVideo(video);
		usrvid.setNombreUsuario(user1);
		em.getTransaction().begin();
		em.persist(usrvid);
		em.getTransaction().commit();
		
		video.addValoraciones(usrvid);
		
	}

	@Override
	public ArrayList<DtVideo> videoEnLista(DtLista lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void seleccionarCategoria(String cat) {
		// TODO Auto-generated method stub
	}

	@Override
	public DtComentario seleccionarComentario(DtComentario comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void seguirUsuario() {
		if((user1 != null) && (user2 != null)) {
			Conexion.beginTransaction();
			if(user1.getSeguidos() == null) {
				System.out.println("************************seguidos");
				user1.setSeguidos(new HashMap<String,Usuario>());
			}
			user1.añadirSeguido(user2);
			for(Usuario u: user1.getSeguidores().values()) {
				System.out.println("************************"+u.getNickname());
			}
			if(user2.getSeguidores() == null) {
				System.out.println("************************seguidores");
				user2.setSeguidores(new HashMap<String,Usuario>());
			}
			user2.añadirSeguidor(user1);
			for(Usuario u: user2.getSeguidos().values()) {
				System.out.println("************************"+u.getNickname());
			}		
			Conexion.persist(user1);
			Conexion.persist(user2);
			Conexion.commit();
		}
		
	}

	@Override
	public void quitarVideo(DtVideo video) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<DtComentario> mostrarComentario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarUsuarioCanal(DtUsuario usr, DtCanal canal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ingresarComentario(DtComentario comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DtVideo seleccionarVideo(String video) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DtUsuario seleccionarUsuario(String usuario) {
		Usuario aux = Handler.findUsuario(usuario);
		if(user1 == null) {
			user1 = aux;
		}else {
			user2 = aux;
		}
		return aux.getDtUsuario();
	}

	@Override
	public ArrayList<String> listarVideos() {			
		ArrayList<String> aux = null;
		if(user1 != null) {
			Canal c = user1.getCanal();
			if(c != null) {
				HashMap<Integer, DtVideo> mapv =  user1.getCanal().getDt().getListaVideos();
				for(DtVideo v: mapv.values()) {
					if(aux != null)
					aux.add(v.getNombre());
				}
			}
		}
		return aux;
	}

	@Override
	public ArrayList<String> listarUsuarios() {
		return Handler.listarUsuarios();
	}

	@Override
	public Boolean ingresarVideo(String nombre, Integer duracion, String url, String descripcion, Date fechaPub, String categoria) {
		try {
			Canal canal = this.user1.getCanal();
			EntityManager em = Conexion.getEm();
			Categoria cat = Handler.findCategoria(categoria);
			Video video = new Video(nombre,true, url, fechaPub, descripcion, duracion, cat);// FALTA CONTEMPLAR SI ES PRIVADO O NO EL VIDEO
			em.getTransaction().begin();
			em.persist(video);
			canal.ingresarVideo(video);
			if (categoria != null) {
				cat.añadirVideo(video);
			}
			em.getTransaction().commit();
			return true;
		}catch (Exception e){
			return false;
		}
	}

	@Override
	public void editarVideo(DtVideo video) {
		// TODO Auto-generated method stub
	}

	@Override
	public void agregarVideo(String video, DtLista lista) {
		//TODO Falta agregar persistencia
			//EntityManager em = Conexion.getEm();
		//Video vid = user1.getCanal().getListaVideos().get(video);
		//Map<Integer, Lista> listasCanal = this.user2.getCanal().getListasReproduccion();
		//Lista lst = listasCanal.get(lista.getNombre());
		//lst.addVideo(vid);
	}

	@Override
	public boolean crearLista(DtUsuario usuario, String nombre, boolean privada, String categoria) {
		boolean res = true;
		if (this.defecto) 
		{
			EntityManager em = Conexion.getEm();
			em.getTransaction().begin();	
			List<Usuario> usuarios = Handler.getUsuarioList();			
			//boolean flag = false;			
			Iterator<Usuario> it = usuarios.iterator();
			while (it.hasNext()) {
				Usuario usr = it.next();
				
				if (!(usr.agregarListaDefecto(nombre)))
					res = false;
				else em.merge(usr.getCanal());
				
			}
			em.getTransaction().commit();					
		}
		else 
		{
			EntityManager em = Conexion.getEm();
			em.getTransaction().begin();			
			Categoria cat = null;
			Usuario user = Handler.findUsuario(usuario.getNickname());
			if (categoria != null) 
			{
				cat = Handler.findCategoria(categoria);				
			}
			em.persist(user);			
			Lista lst = user.agregarListaPart(nombre, privada, cat);			
			if (lst != null) 
			{
				
				if (cat != null) 
				{	
					em.flush();					
					em.merge(cat);					
				}			
				
			}
			else res = false;
			em.getTransaction().commit();
		}
		return res;
		
		
	}

	@Override
	public Map<ArrayList<DtVideo>, ArrayList<DtLista>> listarXCat(String categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public  Map<DtUsuario, DtCanal> listarDatosUsuario(String nick) {
		Map<DtUsuario, DtCanal> datos= new HashMap<DtUsuario, DtCanal>();
		user1 = Handler.findUsuario(nick);
		if(user1 != null) {
			DtUsuario dtu = user1.getDtUsuario();
			Canal aux = user1.getCanal();
			if(aux != null) {
				DtCanal dtc = aux.getDt();
				datos.put(dtu,dtc);
			}else {
				datos.put(dtu,null);
			}
		}
		return datos;
	}

	@Override
	public Boolean altaCategoria(String nombre) {		
		Boolean res = true;
		Categoria cat = Handler.findCategoria(nombre);
		if (cat != null) res = false;
		else {
			cat = new Categoria(nombre);			
			Handler.addCategoria(cat);
		}
		return res;
	}	

	@Override
	public Boolean ingresarUsuario(String nickname, String email, String nombre, String apellido, Date fechaNac, String img, String canal) {
		Boolean res = true;
		res = Handler.addUsuario(nickname, email, nombre, apellido, fechaNac, img, canal);
		
		return res;
	}
	
	// Descripcion: Si defecto = true, arma lista por defecto
	@Override
	public void ingresarTipoLista(boolean defecto) {
		// Leo el tipo de lista a ingresar, si es defecto = true, sino false
		this.defecto = defecto;		
	}	
	
	public void finCasoUso() {
		user1 = null;
		user2 = null;
	}
}	
	
	
	
	