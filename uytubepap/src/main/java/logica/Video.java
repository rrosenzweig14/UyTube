package logica;

import java.util.Date;
import java.util.List;
import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Video {
	@Id
	private String nombre;	

	private String url;
	
	private Date fechaPub;
	
	private String descripcion;
	
	private Integer duracion;		
	@ManyToOne
	private Categoria categoria;
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private Set<Comentario> comentarios;
	
	@OneToMany(mappedBy="nombreVideo",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Usuario_Video> valoraciones;

	public Video() {
		// TODO Auto-generated constructor stub
	}

	public Date getFechaPub() {
		return fechaPub;
	}

	public void setFechaPub(Date fechaPub) {
		this.fechaPub = fechaPub;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

}
