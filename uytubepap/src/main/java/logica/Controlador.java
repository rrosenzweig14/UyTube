package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

	@Override
	public void valorarVideo(String nick, boolean valor) {
		// TODO Auto-generated method stub
		
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
	public void seguirUsuario(DtUsuario usr1, DtUsuario usr2) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> listarVideos() {			
		ArrayList<String> aux = null;
		if(user1 != null) {
			Canal c = user1.getCanal();
			if(c != null) {
				HashMap<Integer, DtVideo> mapv =  user1.getCanal().getDt().getListaVideos();
				for(DtVideo v: mapv.values()) {
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
			Handler hldr = new Handler();
			Categoria cat = hldr.findCategoria(categoria);
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
		Video vid = user1.getCanal().getListaVideos().get(video);
		Map<Integer, Lista> listasCanal = this.user2.getCanal().getListasReproduccion();
		Lista lst = listasCanal.get(lista.getNombre());
		lst.addVideo(vid);
	}

	@Override
	public boolean crearLista(DtUsuario usuario, String nombre, boolean privada, String categoria) {
		boolean res = true;
		if (this.defecto) 
		{
			HashMap<String,Usuario> usuarios = Handler.getUsuarios();
			boolean flag = false;
			for (Usuario user : usuarios.values()) {
				
				if (!(user.agregarListaDefecto(nombre)))
					res = false;
			}			
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
					cat.añadirLista(lst);
					em.persist(cat);
					
				}			
//				em.flush();
				em.getTransaction().commit();
			}
			else res = false;
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
		Handler hldr = new Handler();
		Categoria cat = hldr.findCategoria(nombre);
		if (cat != null) res = false;
		else {
			cat = new Categoria(nombre);			
			hldr.addCategoria(cat);
		}
		return res;
	}	

	@Override
	public Boolean ingresarUsuario(String nickname, String nombre, String apellido, String email, Date fechaNac, String img) {
		Boolean res = true;
		EntityManager em = Conexion.getEm();
		Handler hldr = new Handler();
		Usuario usuario = hldr.findUsuario(nickname);
		if(usuario == null) {
			usuario = hldr.findUsuarioEM(email);
		}
		if(usuario != null) res = false;
		else {
			usuario = new Usuario(nickname, nombre, apellido, email, fechaNac, img);
			try {
			em.getTransaction().begin();
			em.persist(usuario);
			hldr.addUsuario(usuario);
			em.getTransaction().commit();
			}
			catch (Exception ex) {
				System.out.print(ex.toString());
			}
		}
		
		return res;
	}
	// Descripcion: Si defecto = true, arma lista por defecto
	@Override
	public void ingresarTipoLista(boolean defecto) {
		// Leo el tipo de lista a ingresar, si es defecto = true, sino false
		this.defecto = defecto;		
	}	
	
}	
	
	
	
	