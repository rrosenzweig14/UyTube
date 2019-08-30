package logica;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
public class Categoria {
	@Id
	private String nombre;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=false)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Video> videos = new LinkedHashSet<Video>();
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true, fetch = FetchType.EAGER)	
	private Set<Lista> listas = new LinkedHashSet<Lista>();
	

	public Categoria() {
		// TODO Auto-generated constructor stub
	}
	
	public Categoria(String nombre) {
		this.nombre = nombre;
		
		this.videos = new LinkedHashSet<Video>();
		
		this.listas = new LinkedHashSet<Lista>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Lista> getListas() {
		return listas;
	}

	public void setListas(Set<Lista> listas) {
		this.listas = listas;
	}

	public Set<Video> getVideos() {
		return videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}
	
	public void añadirVideo(Video v) {
		this.videos.add(v);
	}
	
	public void quitarVideo(Video v) {
		this.videos.remove(v);
	}
	
	public void addLista (Lista lst) {
		this.listas.add(lst);
	}

}
