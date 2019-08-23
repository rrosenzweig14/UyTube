package interfaces;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import datatypes.DtCanal;
import datatypes.DtCategoria;
import datatypes.DtComentario;
import datatypes.DtLista;
import datatypes.DtUsuario;
import datatypes.DtVideo;

public interface IControlador {
	
	public void ValorarVideo(String nick, boolean valor);
	
	public ArrayList<DtVideo> VideoEnLista(DtLista lst);
	
	public DtCategoria SeleccionarCategoria(String cat);
	
	public DtComentario seleccionarComentario(DtComentario comment);
	
	public void seguirUsuario(DtUsuario usr1, DtUsuario usr2);
	
	public void quitarVideo(DtVideo video);
	
	public ArrayList<DtComentario> MostrarComentario();
	
	public void modificarUsuarioCanal(DtUsuario usr, DtCanal canal);
	
	public void IngresarComentario(DtComentario comment);
	
	public DtVideo seleccionarVideo(String video);
	
	public DtUsuario seleccionarUsuario(String usuario);
		
	public ArrayList<DtVideo> listarVideos();
	
	public ArrayList<DtUsuario> listarUsuarios();
	
	public void ingresarVideo(String nombre, String duaracion, String url, String descripcion, Date fecha, DtCategoria categoria);

	public void editarVideo(DtVideo video);
	
	public void agregarVideo(String video, DtLista lista);
	
	public boolean crearLista(DtUsuario usuario, String nombre, boolean privada, String categoria);
	
	public Map<ArrayList<DtVideo>, ArrayList<DtLista>> listarXCat(String categoria);
	
	public Map<DtUsuario, DtCanal> listarDatosUsuario(String nick);
	
	public Boolean altaCategoria(String nombre);
	
}
