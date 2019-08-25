package logica;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import datatypes.DtCanal;
import datatypes.DtLista;
import datatypes.DtVideo;

@Entity
public class Canal {
	@Id
	@OneToOne
	private Usuario usuario;
	private String nombre;	
	private String descripcion;
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private Map<Integer,Lista> listasReproduccion;
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private Map<Integer,Video> listaVideos;		
	@ManyToOne
	private Categoria categoria;
	private boolean privado;

	public Canal() {
		// TODO Auto-generated constructor stub
	}	
	
	public Canal(Usuario u,String nombre, String descripcion, Categoria categoria, boolean privado) {
		super();
		this.usuario = u;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.privado = privado;
	}	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void ingresarVideo(Video v) {
		this.listaVideos.put(v.getId(), v);
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

	public Map<Integer,Lista> getListasReproduccion() {
		return listasReproduccion;
	}

	public void setListasReproduccion(Map<Integer,Lista> listasReproduccion) {
		this.listasReproduccion = listasReproduccion;
	}

	public Map<Integer,Video> getListaVideos() {
		return listaVideos;
	}

	public void setListaVideos(Map<Integer,Video> listaVideos) {
		this.listaVideos = listaVideos;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public DtCanal getDt() {
		DtCanal dtc =  new DtCanal(this.nombre,this.descripcion,null,this.privado,this.categoria.getNombre());
		HashMap<Integer, DtVideo> mapv = new HashMap<Integer, DtVideo>();
		for(Video v: listaVideos.values()) {
			mapv.put(v.getId(),v.getDt());
		}
		HashMap<Integer,DtLista> mapl = new HashMap<Integer,DtLista>();
		for(Lista l: listasReproduccion.values()) {
			mapl.put(l.getId(),l.getDt());
		}
		dtc.setListaVideos(mapv);
		return dtc;
	}

}
