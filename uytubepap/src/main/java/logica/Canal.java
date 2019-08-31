package logica;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import datatypes.DtCanal;
import datatypes.DtLista;
import datatypes.DtVideo;

@Entity
public class Canal {
	@Id
	private String nickname;
	private String nombre;	
	@OneToOne
    @JoinColumn(name = "nickname")
    @MapsId
	private Usuario usuario;
	private String descripcion;
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private Map<String,Lista> listasReproduccion = new HashMap<String,Lista>();
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private Map<String,Video> listaVideos = new HashMap<String,Video>();		
	//@ManyToOne
	private Boolean privado;

	public Canal() {
		// TODO Auto-generated constructor stub
	}	
	
	public Canal(Usuario u,String nombre, String descripcion, boolean privado) {
		super();
		if(u != null) {
			this.nickname = u.getNickname();
		}
		this.usuario = u;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.privado = privado;
		this.listasReproduccion = new HashMap<String,Lista>();
		this.listaVideos =  new HashMap<String,Video>();
	}	

	public String getNickname() {
		return nickname;
	}

	public Usuario getUsuario() {
		return usuario;
	}	

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		if(usuario != null) {
			this.nickname = usuario.getNickname();
		}
	}

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
	
	public Video findVideo(String nombreVideo) {
		Video res = null;
		res = this.listaVideos.get(nombreVideo);		
		return res;
	}

	public boolean listaExists(String nombreLista) {
		if (this.listasReproduccion.containsKey(nombreLista)) return true;
		else return false;
	}
	
	public DtCanal getDt() {
		DtCanal dtc = null;	
		dtc =  new DtCanal(this.nombre,this.descripcion,this.usuario.getNickname(),this.privado); 
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
	
	public Lista agregarListaPart(String nombreLista, boolean privada, Categoria categoria) {
		Lista res = null;		
		if (!listaExists(nombreLista)) {
			if (categoria != null) {
				res = new Particular(nombreLista, privada, categoria);
				categoria.addLista(res);
			}
			else 
				res = new Particular(nombreLista,privada);
			Conexion.persist(res);
			this.listasReproduccion.put(res.getNombre(), res);			
		}
		return res;
	}
	
	public boolean agregarListaDefecto(String nombreLista) {
		boolean res = false;
		if (!this.listaExists(nombreLista)) {
			Lista lista = new Defecto(nombreLista,true);			
			this.listasReproduccion.put(lista.getNombre(), lista);
		}
		return res;
	}
	
	public boolean existVideoName(String name) {
		for(String s: this.getListaVideos().keySet()) {
			if(s.equals(name)) {
				return true;
			}
		}
		return false;
		
	}
	
	public Canal(DtCanal datosCanal) {
		this.usuario = null;
		this.nombre = datosCanal.getNombre();
		this.descripcion = datosCanal.getDescripcion();
		this.privado = datosCanal.isPrivado();
	}
	
	public Canal(String nombre) {
		this.usuario = null;
		this.nombre = nombre;
		this.descripcion = null;
		this.privado = false;
	}

}
