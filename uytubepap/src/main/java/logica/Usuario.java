package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import datatypes.DtUsuario;
import datatypes.DtVideo;


@Entity
public class Usuario {	 
	@Id
	private String nickname;	
	private String email;	
	private String nombre;	
	private String apellido;	
	private Date fechaNac;	
	private String img;	
	@ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="Usuarios_Relacion", joinColumns={@JoinColumn(name="nickname")}, inverseJoinColumns={@JoinColumn(name="seguidor_id")})
	private List<Usuario> seguidores = new ArrayList<Usuario>();	
	@ManyToMany(mappedBy="seguidores", cascade={CascadeType.ALL})
	private List<Usuario> seguidos = new ArrayList<Usuario>();
	@OneToOne (mappedBy="usuario",cascade=CascadeType.ALL,orphanRemoval=true)
	private Canal canal;
	@OneToMany(mappedBy="nombreUsuario",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Usuario_Video> valoraciones;
		
	//Metodos
	
	public Usuario(String nickname, String nombre, String apellido, String email, Date fechaNac, String img) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNac = fechaNac;
		this.img = img;		
		this.seguidores = new ArrayList<Usuario>();
		this.seguidos = new ArrayList<Usuario>();
	}
	
	public void añadirSeguidor(Usuario user) {
		this.seguidores.add(user);
		user.añadirSeguido(this);
	}
	
	public void añadirSeguido(Usuario user) {
		this.seguidos.add(user);
	}
	
	//getters - setters	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}	
	
	//Constructores
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public List<Usuario> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(List<Usuario> seguidores) {
		this.seguidores = seguidores;
	}

	public List<Usuario> getSeguidos() {
		return seguidos;
	}

	public void setSeguidos(List<Usuario> seguidos) {
		this.seguidos = seguidos;
	}
	
	

	public List<Usuario_Video> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(List<Usuario_Video> valoraciones) {
		this.valoraciones = valoraciones;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public DtUsuario getDtUsuario() {
		return new DtUsuario(this.nickname,this.email,this.nombre,this.apellido, this.fechaNac, this.getImg());
	}
	
	public Lista agregarListaPart(String nombreLista, boolean privada, Categoria categoria) {
		Lista res = this.canal.agregarListaPart(nombreLista, privada, categoria);
		return res;
	};
	
	public boolean agregarListaDefecto(String nombreLista) {
		return this.canal.agregarListaDefecto(nombreLista);
	}
	
	public Usuario(String nickname, String email, String nombre, String apellido, Date fechaNac, String img,String nombreCanal) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNac = fechaNac;
		this.img = img;		
		//No deberia ser necesario ya que se inicializa cuando se crea la entidad
//		this.seguidores = new HashMap<String,Usuario>();
//		this.seguidos = new HashMap<String,Usuario>();
		this.canal = new Canal(nombreCanal);
	}

	public Usuario(String nickname, String email, String nombre, String apellido, Date fechaNac, String img,Canal nombreCanal) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNac = fechaNac;
		this.img = img;		
		//No deberia ser necesario ya que se inicializa cuando se crea la entidad
//		this.seguidores = new HashMap<String,Usuario>();
//		this.seguidos = new HashMap<String,Usuario>();
		this.canal = canal;
	}
	
}