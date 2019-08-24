package logica;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Canal {
	@Id
	private String nombre;
	
	private String descripcion;
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private Map<String,Lista> listasReproduccion;
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private Map<String,Video> listaVideos;		
	@ManyToOne
	private Categoria categoria;
	
	
	public void ingresarVideo(Video v) {
		this.listaVideos.put(v.getNombre(), v);
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

	public Map<String,Lista> getListasReproduccion() {
		return listasReproduccion;
	}

	public void setListasReproduccion(Map<String,Lista> listasReproduccion) {
		this.listasReproduccion = listasReproduccion;
	}

	public Map<String,Video> getListaVideos() {
		return listaVideos;
	}

	public void setListaVideos(Map<String,Video> listaVideos) {
		this.listaVideos = listaVideos;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
