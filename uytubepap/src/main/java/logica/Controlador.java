package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.swing.JTree;

import datatypes.DtCanal;
import datatypes.DtComentario;
import datatypes.DtLista;
import datatypes.DtUsuario;
import datatypes.DtVideo;
import interfaces.IControlador;

public class Controlador implements IControlador {

	//private Boolean existeEmail;
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
		Usuario u = Handler.findUsuario(nick);
		Video v = Handler.findVideo(video.getId());
		List<Usuario_Video> valoraciones = v.getValoraciones();
		Iterator<Usuario_Video> it = valoraciones.iterator();
		boolean encontrado = false;
		Usuario_Video usrvid = null;
		while (!encontrado && it.hasNext()) {
			usrvid = it.next();
			if (usrvid.getNombreUsuario().getNickname().equals(nick))
				encontrado = true;
		}
		if (encontrado)
			usrvid.setLeGusta(valor);
		else {
			usrvid = new Usuario_Video();
			usrvid.setLeGusta(valor);
			usrvid.setNombreVideo(v);
			usrvid.setNombreUsuario(u);
			u.addValoraciones(usrvid);
			v.addValoraciones(usrvid);
		}
		Conexion.beginTransaction();
		Conexion.persist(usrvid);
		Conexion.commit();
	}

	@Override
	public ArrayList<DtVideo> videosEnLista(DtLista lst) {
		ArrayList<DtVideo> res = new ArrayList<DtVideo>();
		if (canal == null) {
			//TODO Agregado para el test, ver si rompe todo
			canal = user1.getCanal();
		}
		lista = canal.getListasReproduccion().get(lst.getNombre());
		Map<String, Video> videos = lista.getVideos();
		for (Video video : videos.values()) {
			res.add(video.getDt());
		}
		return res;
	}
	
	public ArrayList<DtVideo> videosEnListaPublica(DtLista lst){
		ArrayList<DtVideo> res = new ArrayList<DtVideo>();
		Lista lista = Handler.findLista(lst.getId());
		Map<String, Video> videos = lista.getVideos();
		for (Video vid : videos.values()) {
			res.add(vid.getDt());
		}
		return res;
	};

	@Override
	public void seleccionarCategoria(String cat) {
		categoria1 = Handler.findCategoria(cat);
		if (categoria1 == null) {
			System.out.println("La categoria no existe.");
		}
	}

	// precondicion: video != null y el comentario existe
	@Override
	public void seleccionarComentario(DtComentario comment) {
		comentarioSeleccionado = video.findComentario(comment.getId());
	}

	@Override
	// USER 1 SIGUE A USER 2
	public void seguirUsuario() {
		if ((user1 != null) && (user2 != null)) {
			System.out.println("USER 1 = " + user1.getNickname() + " USER 2 = " + user2.getNickname());
			Conexion.beginTransaction();
			// Con solo una operacion asignamos el seguidor y el seguido.
			user2.añadirSeguidor(user1);

			Conexion.persist(user2);
			Conexion.persist(user1);
			Conexion.commit();
		}
	}

	@Override
	// USER 1 SIGUE A USER 2
	public void dejarSeguir() {
		if ((user1 != null) && (user2 != null)) {
			System.out.println("USER 1 = " + user1.getNickname() + " USER 2 = " + user2.getNickname());
			Conexion.beginTransaction();
			// Con solo una operacion asignamos el seguidor y el seguido.
			user2.quitarSeguidor(user1);
			user1.quitarSeguido(user2);
			Conexion.persist(user2);
			Conexion.persist(user1);
			Conexion.commit();
		}
	}

	@Override
	public void quitarVideo(DtVideo video) {
		Conexion.beginTransaction();
		lista.quitarVideo(video.getNombre());
		Conexion.commit();
	}

	@Override
	public void modificarUsuarioCanal(DtUsuario usr, DtCanal canal) {
		Conexion.beginTransaction();
		Canal c = user1.getCanal();
		user1.setApellido(usr.getApellido());
		user1.setNombre(usr.getNombre());
		user1.setFechaNac(usr.getFechaNac());
		user1.setImg(usr.getImg());
		user1.setPassword(usr.getPassword());
		c.setNombre(canal.getNombre());
		c.setDescripcion(canal.getDescripcion());
		c.setPrivado(canal.isPrivado());
		Conexion.persist(c);
		Conexion.persist(user1);
		Conexion.commit();
	}

	@Override
	public boolean ingresarComentario(DtComentario comentario) {
		DtUsuario u2 = seleccionarUsuario(comentario.getNick());
		if (u2 != null) {
			Conexion.beginTransaction();

			if (comentarioSeleccionado == null) {
				Comentario comment = video.ingresarComentario(comentario, user2);
				Conexion.persist(video);
			} else {
				/*
				 * video.ingresarRespuesta(comentario,user1,comentarioSeleccionado);
				 * Conexion.persist(video);
				 */
				comentarioSeleccionado.ingresarRespuesta(comentario, user2);
				Conexion.persist(comentarioSeleccionado);
			}
			Conexion.commit();
			return true;
		} else {
			return false;
		}
	}

	// Precondicion usuario != null
	@Override
	public DtVideo seleccionarVideo(String nombreVideo) {
		video = user1.getCanal().findVideo(nombreVideo);
		if(video != null) {
			return video.getDt();
		}else {
			return null;
		}
	}

	@Override
	public DtUsuario seleccionarUsuario(String usuario) {
		Usuario aux = Handler.findUsuario(usuario);
		if (user1 == null) {
			user1 = aux;
		} else {
			user2 = aux;
		}
		if (aux != null) {
			return aux.getDt();
		}else {
			return null;
		}
	}

	@Override
	public ArrayList<String> listarVideos() {
		ArrayList<String> aux = new ArrayList<String>();
		if (user1 != null) {
			Canal c = user1.getCanal();
			if (c != null) {
				HashMap<Integer, DtVideo> mapv = user1.getCanal().getDt().getListaVideos();
				for (DtVideo v : mapv.values()) {
					if (aux != null)
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
	public Boolean ingresarVideo(String nombre, Integer duracion, String url, String descripcion, Date fechaPub) {
		Canal canal = this.user1.getCanal();
		Categoria cat = categoria1;
		EntityManager em = Conexion.getEm();
		em.getTransaction().begin();
		Video video = new Video(nombre, true, url, fechaPub, descripcion, duracion, cat);
		System.out.print(video.getDuracion());
		if (cat != null) {
			cat.añadirVideo(video);
			em.persist(cat);
		}
		canal.ingresarVideo(video);
		em.persist(canal);
		em.persist(video);
		em.getTransaction().commit();
		this.finCasoUso();
		return true;
	}

	@Override
	public void editarVideo(DtVideo dtv) {
		Conexion.beginTransaction();
		Canal c = user1.getCanal();
		c.getListaVideos().remove(video.getNombre());
		Categoria oldcat = video.getCategoria();
		Categoria newcat = null;
		if (dtv.getCategoria() != null) {
			newcat = Handler.findCategoria(dtv.getCategoria());
		}
		boolean aux = c.existVideoName(dtv.getNombre());
		if (aux) {
			dtv.setNombre(video.getNombre());
		}
		video.cambiarDatos(dtv, newcat);
		if (oldcat != null) {
			oldcat.quitarVideo(video);
			Conexion.persist(oldcat);
		}
		if (newcat != null) {
			newcat.añadirVideo(video);
			Conexion.persist(newcat);
		}
		c.ingresarVideo(video);
		Conexion.persist(video);
		Conexion.persist(c);
		Conexion.commit();
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
		if (this.defecto) {
			EntityManager em = Conexion.getEm();
			em.getTransaction().begin();
			List<Usuario> usuarios = Handler.getUsuarioList();
			Iterator<Usuario> it = usuarios.iterator();
			Handler.addListaDefecto(nombre);
			if (!usuarios.isEmpty()) {
				while (it.hasNext()) {
					Usuario usr = it.next();
					if (!(usr.agregarListaDefecto(nombre))) {
						res = false;
					} else
						em.merge(usr.getCanal());
				}
				em.getTransaction().commit();
			}
		} else {
			EntityManager em = Conexion.getEm();
			em.getTransaction().begin();
			Categoria cat = null;
			Usuario user = Handler.findUsuario(usuario.getNickname());
			if (categoria != null) {
				cat = Handler.findCategoria(categoria);
			}
			em.persist(user);
			Lista lst = user.agregarListaPart(nombre, privada, cat);
			if (lst != null) {

				if (cat != null) {
					em.flush();
					em.merge(cat);
				}

			} else
				res = false;
			em.getTransaction().commit();
		}
		return res;
	}

	@Override
	public DtLista seleccionarLista(String lst) {
		Map<String, Lista> aux = user1.getCanal().getListasReproduccion();
		Lista lstaux = aux.get(lst);
		if (lista == null) {
			lista = lstaux;
		}
		// System.out.print("Se tiene " + lstaux);
		return lstaux.getDt();
	}

	@Override
	public Map<DtUsuario, DtCanal> listarDatosUsuario(String nick) {
		Map<DtUsuario, DtCanal> datos = new HashMap<DtUsuario, DtCanal>();
		user1 = Handler.findUsuario(nick);
		if (user1 != null) {
			DtUsuario dtu = user1.getDt();
			Canal aux = user1.getCanal();
			if (aux != null) {
				DtCanal dtc = aux.getDt();
				datos.put(dtu, dtc);
			} else {
				datos.put(dtu, null);
			}
		}
		return datos;
	}

	@Override
	public Boolean altaCategoria(String nombre) {
		Boolean res = true;
		Categoria cat = Handler.findCategoria(nombre);
		if (cat != null)
			res = false;
		else {
			cat = new Categoria(nombre);
			Handler.addCategoria(cat);
		}
		return res;
	}

	// Descripcion: Si defecto = true, arma lista por defecto
	@Override
	public void ingresarTipoLista(boolean defecto) {
		// Leo el tipo de lista a ingresar, si es defecto = true, sino false
		this.defecto = defecto;
	}

	// Precondicion video != null
	public JTree mostrarComentarios() {
		return video.getElPutoTree();
	}

	// Lista todas las listas del usuario
	public ArrayList<DtLista> listarListasReproduccion(DtUsuario usuario) {
		ArrayList<DtLista> res = new ArrayList<DtLista>();
		Usuario userSelec = Handler.findUsuario(usuario.getNickname());
		canal = userSelec.getCanal();
		Map<String, Lista> listas = canal.getListasReproduccion();
		for (Lista lst : listas.values()) {
			res.add(lst.getDt());
		}
		return res;
	}

	public DtVideo consultarVideo(String nombreVideo) {
		DtVideo res = null;
		if (lista == null) {
			canal = user1.getCanal();
			Video videoSelec = canal.getListaVideos().get(nombreVideo);
			if (videoSelec != null)
				res = videoSelec.getDt();
		} else {
			Video videoSelec = lista.getVideos().get(nombreVideo);
			if (videoSelec != null)
				res = videoSelec.getDt();
		}
		return res;
	}

	public ArrayList<String> listarCategorias() {
		return Handler.listarCategorias();
	}

	// Lista solo las particulares del usuario
	public List<DtLista> listarListasParticulares(DtUsuario user) {
		List<DtLista> res = new ArrayList<DtLista>();
		Usuario userSelec = Handler.findUsuario(user.getNickname());
		Canal canalSelec = userSelec.getCanal();
		user1 = userSelec;
		canal = canalSelec;
		Map<String, Lista> lista = canal.getListasReproduccion();
		for (Lista lst : lista.values()) {
			if (lst instanceof Particular)
				res.add(lst.getDt());
		}
		return res;
	}

	// No hay que modificar el nombre de la lista
	public void modificarListaParticular(DtLista listaSeleccionada, DtLista datosNuevos) {
		Categoria catVieja = null;
		Categoria catNueva = null;
		Conexion.beginTransaction();
		lista = canal.getListasReproduccion().get(listaSeleccionada.getNombre());
		canal.getListasReproduccion().remove(lista.getNombre());

		if (datosNuevos.getCategoria() != listaSeleccionada.getCategoria()) {
			if (listaSeleccionada.getCategoria() != null) {
				catVieja = Handler.findCategoria(listaSeleccionada.getCategoria());
				catVieja.removeLista(lista);
			}
			if (datosNuevos.getCategoria() != null) {
				catNueva = Handler.findCategoria(datosNuevos.getCategoria());
				catNueva.addLista(lista);
			}
		}
		lista.cambiarDatos(datosNuevos.getNombre(), !datosNuevos.isPrivado(), catNueva);
		canal.getListasReproduccion().put(lista.getNombre(), lista);
		Conexion.persist(lista);
		Conexion.persist(canal);
		Conexion.commit();
	}

	@Override
	public Boolean ingresarUsuario(String nickname, String email,String password, String nombre, String apellido, Date fechaNac,
			String img, DtCanal canal) {
		Boolean res = false;
		Usuario aux = Handler.findUsuario(nickname);
		if (aux == null)
			aux = Handler.findUsuarioEM(email);
		//else
		//	this.existeEmail = false;
		if (aux == null) {
			Handler.addUsuario(nickname, email, password,nombre, apellido, fechaNac, img, canal);
			res = true;
		} //else
		//	this.existeEmail = true;
		return res;
	}

	public void finCasoUso() {
		user1 = null;
		user2 = null;
		video = null;
		comentarioSeleccionado = null;
		lista = null;
		canal = null;
		categoria1 = null;
		//existeEmail = false;
	}

	/*public Boolean existeEmail() {
		return this.existeEmail;
	}*/

	public Map<String, String> videosXCat(String categoria) {
		Map<String, String> res = new HashMap<String, String>();
		ArrayList<String> us = Handler.listarUsuarios();
		Canal c = new Canal();
		Usuario usr = new Usuario();
		Map<String, Video> vid;
		String catnom;
		for (String u : us) {
			usr = Handler.findUsuario(u);
			c = usr.getCanal();
			vid = c.getListaVideos();
			for (Video v : vid.values()) {
				if (v != null) {
					Categoria cat = v.getCategoria();
					if (cat != null) {
						catnom = (String) cat.getNombre();
						if (catnom.equals(categoria)) {
							res.put(v.getNombre(), usr.getNickname());
						}
					}
				}
			}
		}
		return res;
	}
	
	public Map<String, String> videosXCatPublicos(String categoria){
		Map<String, String> res = new HashMap<String, String>();
		ArrayList<String> us = Handler.listarUsuarios();
		Canal c = new Canal();
		Usuario usr = new Usuario();
		Map<String, Video> vid;
		String catnom;
		for (String u : us) {
			usr = Handler.findUsuario(u);
			c = usr.getCanal();
			vid = c.getListaVideos();
			for (Video v : vid.values()) {
				if (v != null) {
					Categoria cat = v.getCategoria();
					if (cat != null) {
						catnom = (String) cat.getNombre();
						if (catnom.equals(categoria) && !v.getPrivado()) {
							res.put(v.getId() + ";" + v.getNombre(), usr.getNickname());
						}
					}
				}
			}
		}
		return res;
	}
	

	public Map<String, String> listasXCat(String categoria) {
		Map<String, String> res = new HashMap<String, String>();
		ArrayList<String> us = Handler.listarUsuarios();
		Canal c = new Canal();
		Usuario usr = new Usuario();
		Map<String, Lista> lst;
		for (String u : us) {
			usr = Handler.findUsuario(u);
			c = usr.getCanal();
			lst = c.getListasReproduccion();
			if (lst != null)
				for (Lista l : lst.values()) {
					if (l instanceof Particular)
						if (l.getCategoria() != null && l.getCategoria().getNombre().equals(categoria))
							res.put(l.getNombre(), usr.getNickname());

				}
		}

		return res;
	}
	
	public Map<String, String> listasXCatPublicas(String categoria){
		Map<String, String> res = new HashMap<String, String>();
		ArrayList<String> us = Handler.listarUsuarios();
		Canal c = new Canal();
		Usuario usr = new Usuario();
		Map<String, Lista> lst;
		for (String u : us) {
			usr = Handler.findUsuario(u);
			c = usr.getCanal();
			lst = c.getListasReproduccion();
			if (lst != null)
				for (Lista l : lst.values()) {
					if (l instanceof Particular)
						if (l.getCategoria() != null && l.getCategoria().getNombre().equals(categoria) && !l.isPrivado())
							res.put(l.getId() + ";" + l.getNombre(), usr.getNickname());
				}
		}

		return res;
	}
	
	/* Nueva función para version WEB */
	
	public Boolean login(String usuario, String password) {
		Usuario usr = Handler.findUsuario(usuario);
		Boolean res = false;
		if (usr == null) {
			usr = Handler.findUsuarioEM(usuario);
			if (usr != null) {
				if (usr.getPassword().equals(password)) {
					res = true;
				}
			}
		}
		else {
			if (usr.getPassword().equals(password))
				res = true;
		}
		return res;		
	}
	
	//@Override
	public HashMap<Integer,String> listarVideosPublicos() {
		return Handler.listarVideosPublicos();
	} 
	//@Override
	public HashMap<Integer,String> listarListasPublicas() {
		return Handler.listarListasPublicas();
	} 
	//@Override
	public HashMap<String,String> listarCanalesPublicos() {
		return Handler.listarCanalesPublicos();
	} 
	
	//@Override
	public ArrayList<DtVideo> buscarVideosPublicos(String dato){
		return Handler.buscarVideosPublicos(dato);
	} 
	//@Override
	public ArrayList<DtLista> buscarListasPublicas(String dato) {
		return Handler.buscarListasPublicas(dato);
	} 
	//@Override
	public ArrayList<DtCanal> buscarCanalesPublicos(String dato){
		return Handler.buscarCanalesPublicos(dato);
	} 
	
	//@Override
	public HashMap<Integer,String> listarVideosPrivados(String nick) {
		return Handler.listarVideosXXX(nick);
	} 
	//@Override
	public DtVideo findVideo(int id) {
		Video v = Handler.findVideo(id);
		if(v != null) {
			return v.getDtFake();
		}else {
			return null;
		}
	} 
	
	public DtLista findLista(int id) {
		Lista l = Handler.findLista(id);
		if (l != null) return l.getDt();
		else return null;
	}
	
	public boolean valorarVideoPublico(int id, String nick, boolean valor) {
		Video v = Handler.findVideo(id);
		Usuario u = Handler.findUsuario(nick);
		if(v != null && u != null) {
			List<Usuario_Video> valoraciones = v.getValoraciones();
			Iterator<Usuario_Video> it = valoraciones.iterator();
			boolean encontrado = false;
			Usuario_Video usrvid = null;
			while (!encontrado && it.hasNext()) {
				usrvid = it.next();
				if (usrvid.getNombreUsuario().getNickname().equals(nick))
					encontrado = true;
			}
			if (encontrado)
				usrvid.setLeGusta(valor);
			else {
				usrvid = new Usuario_Video();
				usrvid.setLeGusta(valor);
				usrvid.setNombreVideo(v);
				usrvid.setNombreUsuario(u);
				u.addValoraciones(usrvid);
				v.addValoraciones(usrvid);
			}
			Conexion.beginTransaction();
			Conexion.persist(usrvid);
			Conexion.commit();
			return true;
		}else {
			return false;
		}
	} 
	
	
	public String findDuenioVideo(int id) {
		String res = null;
		ArrayList<String> us = Handler.listarUsuarios();
		Canal c = new Canal();
		Usuario usr = new Usuario();
		Map<String, Video> vid;
		for (String u : us) {
			usr = Handler.findUsuario(u);
			c = usr.getCanal();
			vid = c.getListaVideos();
			for (Video v : vid.values()) {
				if (v != null && v.getId() == id) {
					return usr.getNickname();
				}
			}
		}
		return res;		
	}
	
	public String findDuenioLista(int id) {
		String res = null;
		ArrayList<String> us = Handler.listarUsuarios();
		Canal c = new Canal();
		Usuario usr = new Usuario();
		Map<String, Lista> lst;
		for (String u : us) {
			usr = Handler.findUsuario(u);
			c = usr.getCanal();
			lst = c.getListasReproduccion();
			if (lst != null)
				for (Lista l : lst.values()) {
					if (l.getId() == id) return usr.getNickname();
				}
		}
		return res;
	}

}
