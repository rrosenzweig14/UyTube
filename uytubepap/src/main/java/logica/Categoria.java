package logica;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Categoria {
	@Id
	private String nombre;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)	
	private ArrayList<Video> videos;
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private ArrayList<Lista> listas;
	

	public Categoria() {
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Lista> getListas() {
		return listas;
	}

	public void setListas(ArrayList<Lista> listas) {
		this.listas = listas;
	}

	public ArrayList<Video> getVideos() {
		return videos;
	}

	public void setVideos(ArrayList<Video> videos) {
		this.videos = videos;
	}

}
