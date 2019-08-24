package logica;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Lista {

	//Variables
	@Id
	private String nombre;
	
	private boolean privado;
	@ManyToOne
	private Categoria categoria;
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private Map<String,Video> videos;
	
	//Getters & Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isPrivado() {
		return privado;
	}

	public void setPrivado(boolean privado) {
		this.privado = privado;
	}

	//Constructor
	public Lista(String nombre, boolean privado) {
		super();
		this.nombre = nombre;
		this.privado = privado;
	}
	
	public Lista() {
		
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Map<String,Video> getVideos() {
		return videos;
	}

	public void setVideos(Map<String,Video> videos) {
		this.videos = videos;
	}
	
	public void añadirVideo(Video video) {
		this.videos.put(video.getNombre(), video);
	}
}
