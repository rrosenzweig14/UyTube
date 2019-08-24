package logica;


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
    @JoinTable(name="Usuarios_Relacion", joinColumns={@JoinColumn(name="seguido_id")}, inverseJoinColumns={@JoinColumn(name="seguidor_id")})
	private Map<String,Usuario> seguidores;	
	@ManyToMany(mappedBy="seguidores", cascade={CascadeType.ALL})
	private Map<String,Usuario> seguidos;
	@OneToOne
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
		
		this.seguidores = new HashMap<String,Usuario>();
		this.seguidos = new HashMap<String,Usuario>();
	}
	
	public void añadirSeguidor(Usuario user) {
		this.seguidores.put(user.getNickname(), user);
	}
	
	public void añadirSeguido(Usuario user) {
		this.seguidos.put(user.getNickname(), user);
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

	public Map<String,Usuario> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(Map<String,Usuario> seguidores) {
		this.seguidores = seguidores;
	}

	public Map<String,Usuario> getSeguidos() {
		return seguidos;
	}

	public void setSeguidos(HashMap<String,Usuario> seguidos) {
		this.seguidos = seguidos;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public DtUsuario getDtUsuario() {
		DtUsuario usuario = new DtUsuario(this.nickname,this.nombre,this.apellido,this.email, this.fechaNac, this.getImg());
		return usuario;
	}

}