package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.persistence.EntityManager;

import datatypes.DtCanal;
import datatypes.DtCategoria;
import datatypes.DtComentario;
import datatypes.DtLista;
import datatypes.DtUsuario;
import datatypes.DtVideo;
import interfaces.IControlador;

public class Controlador implements IControlador{

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
	public DtCategoria SeleccionarCategoria(String cat) {
		// TODO Auto-generated method stub
		return null;
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
	public ArrayList<DtUsuario> listarUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ingresarVideo(String nombre, String duaracion, String url, String descripcion, Date fecha,
			DtCategoria categoria) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editarVideo(DtVideo video) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarVideo(String video, DtLista lista) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean crearLista(DtUsuario usuario, String nombre, boolean privada, String categoria) {
		// TODO Auto-generated method stub
		return false;
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
		EntityManager em = Conexion.getEm();
		Handler hldr = new Handler();
		Categoria cat = hldr.findCategoria(nombre);
		if (cat != null) res = false;
		else {
			cat = new Categoria(nombre);
			em.getTransaction().begin();
			em.persist(cat);
			hldr.addCategoria(cat);
			em.getTransaction().commit();
		}
		return res;
	}

	
}
