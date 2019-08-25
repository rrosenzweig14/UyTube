package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import datatypes.DtCanal;
import datatypes.DtComentario;
import datatypes.DtLista;
import datatypes.DtUsuario;
import datatypes.DtVideo;
import interfaces.IControlador;

public class Controlador implements IControlador{

	private Usuario User1;
	private Usuario User2;
	private boolean defecto;

	@Override
	public void ValorarVideo(String nick, boolean valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<DtVideo> VideoEnLista(DtLista lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SeleccionarCategoria(String cat) {
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
	public ArrayList<DtComentario> MostrarComentario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarUsuarioCanal(DtUsuario usr, DtCanal canal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void IngresarComentario(DtComentario comment) {
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
	public ArrayList<DtVideo> listarVideos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> listarUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean ingresarVideo(String nombre, Integer duracion, String url, String descripcion, Date fechaPub, String categoria) {
		try {
			Canal canal = this.User1.getCanal();
			EntityManager em = Conexion.getEm();
			Handler hldr = new Handler();
			Categoria cat = hldr.findCategoria(categoria);
			Video video = new Video(nombre, false, url, fechaPub, descripcion, duracion, cat);//Se agrego para probar
			em.getTransaction().begin();
			em.persist(video);
			//canal.ingresarVideo(video);
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
		//Video vid = User1.getCanal().getListaVideos().get(video);
		//Map<String, Lista> listasCanal = this.User2.getCanal().getListasReproduccion();
		//Lista lst = listasCanal.get(lista.getNombre());
		//lst.añadirVideo(vid);
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
			Conexion.open();
			em.getTransaction().begin();			
			Categoria cat = null;
			Usuario user = Handler.findUsuario(usuario.getNickname());
			if (categoria != null) 
			{
				cat = Handler.findCategoria(categoria);				
			}						
			Lista lst = user.agregarListaPart(nombre, privada, cat);
				
			if (lst != null) 
			{
				if (cat != null) 
				{					
					cat.añadirLista(lst);
					//em.unwrap(Session.class).update(cat);
//					em.merge(cat);
				}		
				em.unwrap(Session.class).saveOrUpdate(user); 
				
				em.merge(user);				
				em.getTransaction().commit();
			}
			else res = false;
			Conexion.close();
		}
		return res;
		
		
	}

	@Override
	public Map<ArrayList<DtVideo>, ArrayList<DtLista>> listarXCat(String categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<DtUsuario, DtCanal> listarDatosUsuario(String nick) {
		// TODO Auto-generated method stub
		return null;
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
	
	
	
	