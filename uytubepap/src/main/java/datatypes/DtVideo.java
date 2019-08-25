package datatypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

import logica.Usuario;

public class DtVideo {	
	private String nombre;	
	private Boolean privado;	
	private String canal;	
	private String descripcion;	
	private Integer duracion;	
	private String categoria;	
	private Date fechaPub;	
	private String url;
	private TreeSet<DtComentario> comentarios;	
	
	public DtVideo() {
		super();
	}
	public DtVideo(String nombre, Boolean privado, String canal, String descripcion, Integer duracion, String categoria,
			Date fechaPub, String url) {
		super();
		this.nombre = nombre;
		this.privado = privado;
		this.canal = canal;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.categoria = categoria;
		this.fechaPub = fechaPub;
		this.url = url;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getPrivado() {
		return privado;
	}

	public void setPrivado(Boolean privado) {
		this.privado = privado;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Date getFechaPub() {
		return fechaPub;
	}

	public void setFechaPub(Date fechaPub) {
		this.fechaPub = fechaPub;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public ArrayList<Usuario> getValoracionesPositivas() {
		return valoracionesPositivas;
	}

	public void setValoracionesPositivas(ArrayList<Usuario> valoracionesPositivas) {
		this.valoracionesPositivas = valoracionesPositivas;
	}

	public ArrayList<Usuario> getValoracionesNegativas() {
		return valoracionesNegativas;
	}

	public void setValoracionesNegativas(ArrayList<Usuario> valoracionesNegativas) {
		this.valoracionesNegativas = valoracionesNegativas;
	}

	public TreeSet<DtComentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(TreeSet<DtComentario> comentarios) {
		this.comentarios = comentarios;
	}

	private ArrayList<Usuario> valoracionesPositivas;
	
	private ArrayList<Usuario> valoracionesNegativas;



}
