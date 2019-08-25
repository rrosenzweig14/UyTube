package datatypes;

import java.util.HashMap;

public class DtCanal {	
	private String nombre;	
	private String descripcion;
	private String nick;
	private boolean privado;
	private String categoria;	
	private HashMap<Integer,DtLista> listasReproduccion;	
	private HashMap<Integer,DtVideo> listaVideos;
	
	public DtCanal() {}
	public DtCanal(String nombre, String descripcion, String nick, boolean privado, String categoria) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nick = nick;
		this.privado = privado;
		this.categoria = categoria;
	}
	
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
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public boolean isPrivado() {
		return privado;
	}
	public void setPrivado(boolean privado) {
		this.privado = privado;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public HashMap<Integer, DtLista> getListasReproduccion() {
		return listasReproduccion;
	}
	public void setListasReproduccion(HashMap<Integer, DtLista> listasReproduccion) {
		this.listasReproduccion = listasReproduccion;
	}
	public HashMap<Integer, DtVideo> getListaVideos() {
		return listaVideos;
	}
	public void setListaVideos(HashMap<Integer, DtVideo> listaVideos) {
		this.listaVideos = listaVideos;
	}
	

}
