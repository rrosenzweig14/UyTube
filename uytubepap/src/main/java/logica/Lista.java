package logica;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import datatypes.DtLista;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Lista {

	//Variables
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nombre;	
	private boolean privado;
	@ManyToOne
	private Categoria categoria;
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private Map<String,Video> videos;

	//Constructores	
	public Lista() {
		
	}
	public Lista(String nombre, boolean privado) {
		super();
		this.nombre = nombre;
		this.privado = privado;
	}

	
	//Getters & Setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
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
	
	public void addVideo(Video video) {
		this.videos.put(video.getNombre(), video);
	}
	public DtLista getDt() {
		DtLista dtl;
		if (this instanceof Defecto) {
			dtl =  new DtLista(this.id,this.nombre,this.privado,true,this.categoria.getNombre());
		}else {
			dtl =  new DtLista(this.id,this.nombre,this.privado,false,this.categoria.getNombre());
		}
		return dtl;
	}
}
