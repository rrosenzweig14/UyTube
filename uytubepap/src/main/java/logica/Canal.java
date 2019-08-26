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
	private Map<Integer,Lista> listasReproduccion = new HashMap<Integer,Lista>();
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private Map<Integer,Video> listaVideos = new HashMap<Integer,Video>();		
	@ManyToOne
	private Categoria categoria;
	private boolean privado;

	public Canal() {
		// TODO Auto-generated constructor stub
	}	
	
	public Canal(Usuario u,String nombre, String descripcion, Categoria categoria, boolean privado) {
		super();
		if(u != null) {
			this.nickname = u.getNickname();
		}
		this.usuario = u;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.privado = privado;
		this.listasReproduccion = new HashMap<Integer,Lista>();
		this.listaVideos =  new HashMap<Integer,Video>();
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
	public boolean listaExists(String nombreLista) {
		if (this.listasReproduccion.containsKey(nombreLista)) return true;
		else return false;
	}
	
	public DtCanal getDt() {
		DtCanal dtc = null;	
		dtc =  new DtCanal(this.nombre,this.descripcion,this.usuario.getNickname(),this.privado,null); // null = this.categoria.getNombre()
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
			if (categoria != null) 
				res = new Particular(nombreLista, privada, categoria);
			else 
				res = new Particular(nombreLista,privada);
			Conexion.persist(res);
			this.listasReproduccion.put(res.getId(), res);
		}
		return res;
	}
	
	public boolean agregarListaDefecto(String nombreLista) {
		boolean res = false;
//		if (!this.listasReproduccion.containsKey(nombreLista)) {
//			Lista lista = new Defecto(nombreLista,true);
//			this.listasReproduccion.put(lista.getId(), lista);
//		}
		return res;
	}
	
	public Canal(String nombre) {
		this.usuario = null;
		this.nombre = nombre;
		this.descripcion = null;
		this.categoria = null;
		this.privado = false;
	}

}
