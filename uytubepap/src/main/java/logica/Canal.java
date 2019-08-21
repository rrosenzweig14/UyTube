package logica;

import java.util.ArrayList;

public class Canal {
	
	private String nombre;
	
	private String descripcion;
	
	private ArrayList<Lista> listasReproduccion;
	
	private ArrayList<Video> listaVideos;
	
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

	public ArrayList<Lista> getListasReproduccion() {
		return listasReproduccion;
	}

	public void setListasReproduccion(ArrayList<Lista> listasReproduccion) {
		this.listasReproduccion = listasReproduccion;
	}

	public ArrayList<Video> getListaVideos() {
		return listaVideos;
	}

	public void setListaVideos(ArrayList<Video> listaVideos) {
		this.listaVideos = listaVideos;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
