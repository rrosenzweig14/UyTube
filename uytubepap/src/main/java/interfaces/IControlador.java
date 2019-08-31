package interfaces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JTree;

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
	
	public void dejarSeguir();
	
	public void quitarVideo(DtVideo video);
	
	public void modificarUsuarioCanal(DtUsuario usr, DtCanal canal);
	
	public void ingresarComentario(DtComentario comment);
	
	public DtVideo seleccionarVideo(String video);
	
	public DtUsuario seleccionarUsuario(String usuario);
		
	public ArrayList<String> listarVideos();
	
	public ArrayList<String> listarUsuarios();
	
	public Boolean ingresarVideo(String nombre, Integer duracion, String url, String descripcion, Date fecha);

	public void editarVideo(DtVideo video);
	
	public boolean agregarVideo(String video, DtLista lista);
	
	public boolean crearLista(DtUsuario usuario, String nombre, boolean privada, String categoria);
	
	public Map<ArrayList<DtVideo>, ArrayList<DtLista>> listarXCat(String categoria);
	
	public Map<DtUsuario, DtCanal> listarDatosUsuario(String nick);
	
	public Boolean altaCategoria(String nombre);
	
	public Boolean ingresarUsuario(String nickname, String nombre, String apellido, String email, Date fechaNac, String img, DtCanal canal);
	
	public void ingresarTipoLista(boolean defecto);	
	
	public JTree mostrarComentarios();

	public void finCasoUso();

	public DtLista seleccionarLista(String lista);
	
	public ArrayList<DtLista> listarListasReproduccion(DtUsuario usuario);
	
	public DtVideo consultarVideo(String nombreVideo); 
	
	public ArrayList<String> listarCategorias();
	
	public List<DtLista> listarListasParticulares(DtUsuario user);
	
	public void modificarListaParticular(DtLista listaSeleccionada,DtLista datosNuevos);
	
	public Boolean existeEmail();
	
	public Map<String, String> videosXCat(String categoria);
	
	public Map<String, String> listasXCat(String categoria);

}
