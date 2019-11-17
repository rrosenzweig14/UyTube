package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nombre;
	private Boolean privado;
	private String url;
	private Date fechaPub;
	private String descripcion;
	private Date fechaUltimaConsulta;
	private Integer cantidadConsultas = 0;
	private Integer duracion;
	@ManyToOne
	private Categoria categoria;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comentario> comentarios = new ArrayList<Comentario>();
	@OneToMany(mappedBy = "idVideo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Usuario_Video> valoraciones = new ArrayList<Usuario_Video>();

	public Video(String nombre, boolean privado, String url, Date fechaPub, String descripcion, Integer duracion,
			Categoria categoria) {
		super();
		this.nombre = nombre;
		this.privado = privado;
		this.url = url;
		this.fechaPub = fechaPub;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.categoria = categoria;
		this.valoraciones = new ArrayList<>();
	}

	public Video() {
	}

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
		DefaultMutableTreeNode nodes = new DefaultMutableTreeNode(c.getDt());
		if (!(c.getRespuestas().isEmpty())) {
			for (Comentario aux : c.getRespuestas()) {
				DefaultMutableTreeNode node = aux.getNodes();
				if (node != null) {
					nodes.add(node);
				}
			}
		}
		return nodes;
	}

	public JTree getElPutoTree() {
		if (this.comentarios.isEmpty()) {
			return null;
		} else {
			DefaultMutableTreeNode root = new DefaultMutableTreeNode(this.nombre); // TreeRoot
			for (Comentario c : this.comentarios) {
				DefaultMutableTreeNode node = getNodes(c);
				if (node != null) {
					root.add(node);
				}
			}
			return new JTree(root);
		}
	}

//	
	public DtVideo getDt() {
		String s = null;
		if (this.categoria != null) {
			s = this.categoria.getNombre();
		}
		DtVideo dtv = new DtVideo(this.id, this.nombre, this.privado, null, this.descripcion, this.duracion, s,
				this.fechaPub, this.url,this.cantidadConsultas, this.fechaUltimaConsulta);
		dtv.setComentarios(getElPutoTree());
		List<Usuario_Video> valoraciones = this.valoraciones;
		if (this.valoraciones != null) {
			for (Usuario_Video uv : valoraciones) {
				if (uv.isLeGusta())
					dtv.addValoracionPositiva(uv.getNombreUsuario().getNickname());
				else
					dtv.addValoracionNegativa(uv.getNombreUsuario().getNickname());
			}
		}

		return dtv;
	}

	public Comentario ingresarComentario(DtComentario comentario, Usuario usr) {
		Comentario comment = new Comentario(comentario.getTexto(), comentario.getFecha(), usr);
		this.comentarios.add(comment);
		return comment;

	}

	public Comentario findComentario(int id) {
		if (this.comentarios.isEmpty()) {
			return null;
		} else {
			Comentario res = null;
			for (Comentario c : this.comentarios) {
				if (c.getId() == id) {
					return c;
				} else {
					res = c.findComentario(id);
					if (res != null) {
						return res;
					}
				}
			}
			return null;
		}
	}

	public void cambiarDatos(DtVideo dtv, Categoria c) {
		this.nombre = dtv.getNombre();
		this.privado = dtv.getPrivado();
		this.url = dtv.getUrl();
		this.fechaPub = dtv.getFechaPub();
		this.descripcion = dtv.getDescripcion();
		this.duracion = dtv.getDuracion();
		this.categoria = c;
	}
	
	public ArrayList<DtComentario> theFakeTree(){
		ArrayList<DtComentario> com = new ArrayList<DtComentario>();
		for(Comentario c: this.comentarios) {
			DtComentario dtc = c.getDt();
			dtc.setNivel(0);
			com.add(dtc);
			c.noMoreTrees(com,1);			
		}		
		return com;
	}
	public DtVideo getDtFake() {
		String s = null;
		if (this.categoria != null) {
			s = this.categoria.getNombre();
		}
		DtVideo dtv = new DtVideo(this.id, this.nombre, this.privado, null, this.descripcion, this.duracion, s,this.fechaPub, this.url, this.cantidadConsultas, this.fechaUltimaConsulta);
		dtv.setCom(theFakeTree());
		List<Usuario_Video> valoraciones = this.valoraciones;
		if (this.valoraciones != null) {
			for (Usuario_Video uv : valoraciones) {
				if (uv.isLeGusta()) {
					dtv.addValoracionPositiva(uv.getNombreUsuario().getNickname());
				}else {
					dtv.addValoracionNegativa(uv.getNombreUsuario().getNickname());
				}
				System.out.println(uv.getNombreUsuario().getNickname()+"****************************************************************************");
			}
		}

		return dtv;
	}

	public Date getFechaUltimaConsulta() {
		return fechaUltimaConsulta;
	}

	public void setFechaUltimaConsulta(Date ultimaFechaConsulta) {
		this.fechaUltimaConsulta = ultimaFechaConsulta;
	}

	public Integer getCantidadConsultas() {
		return cantidadConsultas;
	}

	public void setCantidadConsultas(Integer cantidadConsultas) {
		this.cantidadConsultas = cantidadConsultas;
	}
}
