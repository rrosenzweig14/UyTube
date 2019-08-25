package logica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Categoria {
	@Id
	private String nombre;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)	
	private List<Video> videos = new ArrayList<Video>();
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Lista> listas = new ArrayList<Lista>();
	

	public Categoria() {
		// TODO Auto-generated constructor stub
	}
	
	public Categoria(String nombre) {
		this.nombre = nombre;
		
//		this.videos = new ArrayList<Video>();
//		
//		this.listas = new ArrayList<Lista>()
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Lista> getListas() {
		return listas;
	}

	public void setListas(List<Lista> listas) {
		this.listas = listas;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	
	public void añadirVideo(Video v) {
		this.videos.add(v);
	}
	
	public void añadirLista (Lista lst) {
		this.listas.add(lst);
	}

}
