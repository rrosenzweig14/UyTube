package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
	private Comentario comentarioSeleccionado;
	private Lista lista;
	private Canal canal;
	private Categoria categoria1;

	@Override
	public void valorarVideo(String nick, boolean valor) {
        user2 = Handler.findUsuario(nick);
        Usuario_Video usrvid = new Usuario_Video();
        usrvid.setLeGusta(valor);
        usrvid.setNombreVideo(video);
        usrvid.setNombreUsuario(user2);
        Conexion.beginTransaction();
        Conexion.persist(usrvid);
        Conexion.commit();  		
	}

	@Override
	public ArrayList<DtVideo> videosEnLista(DtLista lst) {
		ArrayList<DtVideo> res = null;
		lista = canal.getListasReproduccion().get(lst.getNombre());
		Map<String,Video> videos = lista.getVideos();		
		for (Video video : videos.values()) {
			res.add(video.getDt());
		}		
		return res;
	}

	@Override
	public void seleccionarCategoria(String cat) {
		categoria1 =  Handler.findCategoria(cat);
		if(categoria1 == null) {
			System.out.println("La categoria no existe.");			
		}
	}
	
	// precondicion: video != null y el comentario existe
	@Override
	public void seleccionarComentario(DtComentario comment) {	
		comentarioSeleccionado = video.findComentario(comment);	
	}

	@Override
	// USER 1 SIGUE A USER 2
	public void seguirUsuario() {
		if((user1 != null) && (user2 != null)) {
			System.out.println("USER 1 = " + user1.getNickname() + " USER 2 = " + user2.getNickname());
			Conexion.beginTransaction();
			//Con solo una operacion asignamos el seguidor y el seguido.
			user2.añadirSeguidor(user1);		
			
			Conexion.persist(user2);
			Conexion.persist(user1);
			Conexion.commit();
		}
		
	}

	@Override
	public void quitarVideo(DtVideo video) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarUsuarioCanal(DtUsuario usr, DtCanal canal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ingresarComentario(DtComentario comentario) {		
		Conexion.beginTransaction();
		
		if (comentarioSeleccionado == null) {			
			Comentario comment = video.ingresarComentario(comentario,user1);			
			Conexion.persist(video);			
		}
		else {			
			video.ingresarRespuesta(comentario,user1,comentarioSeleccionado);			
			Conexion.persist(video);			
		}			
		Conexion.commit();
	}
	
	//Precondicion usuario != null
	@Override
	public DtVideo seleccionarVideo(String nombreVideo) {
		Video aux = user1.getCanal().findVideo(nombreVideo);
		if(video == null) {
			video = aux;
		}
		return aux.getDt();		
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
			//Se agarra el canal del usuario
		Canal canal = this.user1.getCanal();
			//Se busca o crea la categoria
		Categoria cat = Handler.findCategoria(categoria);
			//Se comienza la persistencia
		EntityManager em = Conexion.getEm();
		em.getTransaction().begin();
		if (cat != null) 
		{
			
		}
		
		else {
			//Tiene que ser una categoria existente
//			System. out. println("Esa categoria no existe en el sistema\n");
//			return false
			cat = new Categoria(categoria);
			em.persist(cat);
		}
			//Se crea el video
		Video video = new Video(nombre,true, url, fechaPub, descripcion, duracion, cat);// FALTA CONTEMPLAR SI ES PRIVADO O NO EL VIDEO
		canal.ingresarVideo(video);
		em.persist(canal);
		em.persist(video);
		em.getTransaction().commit();
		this.finCasoUso();
		return true;
	}

	@Override
	public void editarVideo(DtVideo video) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean agregarVideo(String video, DtLista lista) {
		EntityManager em = Conexion.getEm();
		em.getTransaction().begin();
		Video vid = user1.getCanal().getListaVideos().get(video);
		Map<String, Lista> listasCanal = this.user2.getCanal().getListasReproduccion();
		Lista lst = listasCanal.get(lista.getNombre());
		lst.addVideo(vid);
		em.persist(lst);
		em.getTransaction().commit();
		this.finCasoUso();
		return true;
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
			Handler.addListaDefecto(nombre);
			if(!usuarios.isEmpty())
			while (it.hasNext()) {
				Usuario usr = it.next();
				
				if (!(usr.agregarListaDefecto(nombre)))
					res = false;
				else em.merge(usr.getCanal());
				em.getTransaction().commit();					
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
	public DtLista seleccionarLista(String lst) {
		Map<String, Lista> aux = user1.getCanal().getListasReproduccion();
		Lista lstaux = aux.get(lst);
		if(lista == null) {
			lista = lstaux;
		}
		return lstaux.getDt();
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
	//Precondicion video != null
	public Set<DtComentario> mostrarComentarios()
	{
		List<Comentario> comentarios = video.getComentarios();
		Set<DtComentario> listadoRes = new HashSet<DtComentario>();
		for (Comentario comment : comentarios) {
			DtComentario c = comment.getDt();
			listadoRes.add(c);			
		}
		return listadoRes;
		
	}
	
	public ArrayList<DtLista> listarListasReproduccion(DtUsuario usuario){
		ArrayList<DtLista> res = null;
		Usuario userSelec = Handler.findUsuario(usuario.getNickname());
		Canal canal = userSelec.getCanal();
		Map<String,Lista> listas = canal.getListasReproduccion();
		for(Lista lst : listas.values()) {
			res.add(lst.getDt());			
		}	
		return res;
	}
	
	public DtVideo consultarVideo(String nombreVideo) {
		DtVideo res = null;
		if (lista == null) {
			Video videoSelec = canal.getListaVideos().get(nombreVideo);
			if (videoSelec != null) res = videoSelec.getDt();
		}
		else {
			Video videoSelec = lista.getVideos().get(nombreVideo);
			if (videoSelec != null) res = videoSelec.getDt();
		}		
		return res;
	}
	
	
}	
	
	
	
	