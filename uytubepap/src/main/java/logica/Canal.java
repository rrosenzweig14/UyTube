package logica;

import java.util.ArrayList;
import java.util.HashMap;

public class Canal {
	
	private String nombre;
	
	private String descripcion;
	
	private HashMap<String,Lista> listasReproduccion;
	
	private HashMap<String,Video> listaVideos;
	
	private Categoria categoria;
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isPrivado() {
		return privado;
	}

	public void setPrivado(boolean privado) {
		this.privado = privado;
	}

	private boolean privado;

	public Canal() {
		// TODO Auto-generated constructor stub
	}

	public HashMap<String,Lista> getListasReproduccion() {
		return listasReproduccion;
	}

	public void setListasReproduccion(HashMap<String,Lista> listasReproduccion) {
		this.listasReproduccion = listasReproduccion;
	}

	public HashMap<String,Video> getListaVideos() {
		return listaVideos;
	}

	public void setListaVideos(HashMap<String,Video> listaVideos) {
		this.listaVideos = listaVideos;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
