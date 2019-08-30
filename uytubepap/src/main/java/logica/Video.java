package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import datatypes.DtComentario;
import datatypes.DtVideo;


@Entity
public class Video {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nombre;	
	private Boolean privado;	
	private String url;	
	private Date fechaPub;	
	private String descripcion;	
	private Integer duracion;		
	@ManyToOne
	private Categoria categoria;
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Comentario> comentarios = new ArrayList<Comentario>();	
	@OneToMany(mappedBy="idVideo",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Usuario_Video> valoraciones;

	public Video(String nombre,boolean privado, String url, Date fechaPub, String descripcion, Integer duracion, Categoria categoria) {
		super();
		this.nombre = nombre;
		this.privado = privado;
		this.url = url;
		this.fechaPub = fechaPub;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.categoria = categoria;
	}
	public Video() {}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public List<Comentario> getComentarios() {
		return comentarios;
	}	
	
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	public List<Usuario_Video> getValoraciones() {
		return valoraciones;
	}
	
	public void addValoraciones(Usuario_Video usrVideo) {
		this.valoraciones.add(usrVideo);
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
	
	private DefaultMutableTreeNode getNodes(Comentario c) {
		DefaultMutableTreeNode nodes = null;
		if (c != null)	nodes = new DefaultMutableTreeNode(c.getDt());
		if(c.getRespuestas().isEmpty()) {
			return nodes;
		}else {
			for(Comentario aux: c.getRespuestas()) {
				DefaultMutableTreeNode node = getNodes(aux);
				if(node != null) {
					nodes.add(node);
				}
			}
			return nodes;
		}
	}
	
	private JTree getElPutoTree(){
		if(this.comentarios.isEmpty()) {
			return null;
		}else {
			DefaultMutableTreeNode root = new DefaultMutableTreeNode(this.nombre); //TreeRoot
			for(Comentario c: this.comentarios) {
				DefaultMutableTreeNode node = getNodes(c);
				if (node != null) root.add(node);				
			}
			JTree  tree = new JTree(root);
			return tree;
		}
	}
//	
	public DtVideo getDt() {
		DtVideo dtv = new DtVideo(this.id,this.nombre,this.privado,null,this.descripcion,this.duracion,null,this.fechaPub,this.url);	
		dtv.setComentarios(getElPutoTree()); 
		return dtv;
	}
	
	public Comentario ingresarComentario(DtComentario comentario,Usuario usr) {
		Comentario comment = new Comentario(comentario.getTexto(),comentario.getFecha(),usr);		
		this.comentarios.add(comment);
		return comment;
		
	}	
	
	public void ingresarRespuesta(DtComentario respuesta, Usuario autor, Comentario comentarioSeleccionado) {
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < this.comentarios.size()) {
			if (this.comentarios.get(i).getId() == comentarioSeleccionado.getId()) encontrado = true;
			else i++;
		}
		Comentario comment = this.comentarios.get(i).ingresarRespuesta(respuesta, autor);
		this.comentarios.add(comment);
	}
	
	public Comentario findComentario(DtComentario comment) {
		Comentario res = null;
		List<Comentario> comentarios = this.comentarios;
		for (Comentario c : comentarios) {
			if(c.getId() == comment.getId()) res = c;
		}
		return res;
	}

}
