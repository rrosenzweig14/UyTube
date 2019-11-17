package datatypes;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import logica.Usuario;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtVideo {
	private int id;
	private String nombre;	
	private Boolean privado;	
	private String canal;	
	private String descripcion;	
	private Integer duracion;	
	private String categoria;	
	private Date fechaPub;	
	private Date fechaUltimaConsulta;
	private Integer cantidadConsultas;
	private String url;
	@XmlTransient
	private JTree comentarios;
	private ArrayList<DtComentario> com = null;
	private ArrayList<String> valoracionesPositivas;	
	private ArrayList<String> valoracionesNegativas;
	
	public DtVideo() {
		super();
	}
	public DtVideo(int id,String nombre, Boolean privado, String canal, String descripcion, Integer duracion, String categoria,
			Date fechaPub, String url, Integer cantidadConsultas, Date fechaUltima) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.privado = privado;
		this.canal = canal;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.categoria = categoria;
		this.fechaPub = fechaPub;
		this.url = url;
		this.cantidadConsultas = cantidadConsultas;
		this.fechaUltimaConsulta = fechaUltima;
		this.valoracionesPositivas = new ArrayList<String>();
		this.valoracionesNegativas = new ArrayList<String>();
	}

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

	public Boolean getPrivado() {
		return privado;
	}

	public void setPrivado(Boolean privado) {
		this.privado = privado;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Date getFechaPub() {
		return fechaPub;
	}

	public void setFechaPub(Date fechaPub) {
		this.fechaPub = fechaPub;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public ArrayList<String> getValoracionesPositivas() {
		return valoracionesPositivas;
	}

	public void setValoracionesPositivas(ArrayList<String> valoracionesPositivas) {
		this.valoracionesPositivas = valoracionesPositivas;
	}

	public ArrayList<String> getValoracionesNegativas() {
		return valoracionesNegativas;
	}

	public void setValoracionesNegativas(ArrayList<String> valoracionesNegativas) {
		this.valoracionesNegativas = valoracionesNegativas;
	}

	public JTree getComentarios() {
		return comentarios;
	}

	public void setComentarios(JTree comentarios) {
		this.comentarios = comentarios;
	}

	public ArrayList<DtComentario> getCom() {
		return com;
	}
	public void setCom(ArrayList<DtComentario> com) {
		this.com = com;
	}
	
	public void addValoracionPositiva(String nombre) {
		this.valoracionesPositivas.add(nombre);
	}

	public void addValoracionNegativa(String nombre) {
		this.valoracionesNegativas.add(nombre);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) return false;
		
		DtVideo video = (DtVideo) o;
		
		return nombre.equals(video.nombre) && descripcion.equals(video.descripcion) && duracion == video.duracion && categoria.equals(video.categoria) && url.equals(video.url) && fechaPub == video.fechaPub;
	}
	public Date getFechaUltimaConsulta() {
		return fechaUltimaConsulta;
	}
	public void setFechaUltimaConsulta(Date fechaUltimaConsulta) {
		this.fechaUltimaConsulta = fechaUltimaConsulta;
	}
	public Integer getCantidadConsultas() {
		return cantidadConsultas;
	}
	public void setCantidadConsultas(Integer cantidadConsultas) {
		this.cantidadConsultas = cantidadConsultas;
	}

}
