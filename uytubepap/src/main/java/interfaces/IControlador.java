package interfaces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import datatypes.DtCanal;
import datatypes.DtComentario;
import datatypes.DtLista;
import datatypes.DtUsuario;
import datatypes.DtVideo;

public interface IControlador {
	
	public void valorarVideo(String nick, boolean valor);
	
	public ArrayList<DtVideo> videosEnLista(DtLista lst);
	
	public void seleccionarCategoria(String cat);
	
	public void seleccionarComentario(DtComentario comment);
	
	public void seguirUsuario();
	
	public void quitarVideo(DtVideo video);
	
	public void modificarUsuarioCanal(DtUsuario usr, DtCanal canal);
	
	public void ingresarComentario(DtComentario comment);
	
	public DtVideo seleccionarVideo(String video);
	
	public DtUsuario seleccionarUsuario(String usuario);
		
	public ArrayList<String> listarVideos();
	
	public ArrayList<String> listarUsuarios();
	
	public Boolean ingresarVideo(String nombre, Integer duracion, String url, String descripcion, Date fecha, String categoria);

	public void editarVideo(DtVideo video);
	
	public boolean agregarVideo(String video, DtLista lista);
	
	public boolean crearLista(DtUsuario usuario, String nombre, boolean privada, String categoria);
	
	public Map<ArrayList<DtVideo>, ArrayList<DtLista>> listarXCat(String categoria);
	
	public Map<DtUsuario, DtCanal> listarDatosUsuario(String nick);
	
	public Boolean altaCategoria(String nombre);
	
	public Boolean ingresarUsuario(String nickname, String nombre, String apellido, String email, Date fechaNac, String img, String canal);
	
	public void ingresarTipoLista(boolean defecto);	
	
	public Set<DtComentario> mostrarComentarios();

	public void finCasoUso();

	public DtLista seleccionarLista(String lista);
	
	public ArrayList<DtLista> listarListasReproduccion(DtUsuario usuario);
	
	public DtVideo consultarVideo(String nombreVideo); 
}
