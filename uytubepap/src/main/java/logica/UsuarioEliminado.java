package logica;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import datatypes.DtUsuarioEliminado;

@Entity
public class UsuarioEliminado {
	@Id
	private String nickname;
	private String email;
	private String nombre;
	private String apellido;
	private Date fechaNac;
	private Date fechaEliminacion;
	private String img;
	private String nombreCanal;
	private String[] videos;
	private String[] listas;
	
	public UsuarioEliminado() {
		
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public UsuarioEliminado(String nickname, String email, String nombre, String apellido, Date fechaNac,
			Date fechaEliminacion, String img, String nombreCanal, String[] videos, String[] listas) {
		super();
		this.nickname = nickname;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.fechaEliminacion = fechaEliminacion;
		this.img = img;
		this.nombreCanal = nombreCanal;
		this.videos = videos;
		this.listas = listas;
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

	public Date getFechaEliminacion() {
		return fechaEliminacion;
	}

	public void setFechaEliminacion(Date fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getNombreCanal() {
		return nombreCanal;
	}

	public void setNombreCanal(String nombreCanal) {
		this.nombreCanal = nombreCanal;
	}

	public String[] getVideos() {
		return videos;
	}

	public void setVideos(String[] videos) {
		this.videos = videos;
	}

	public String[] getListas() {
		return listas;
	}

	public void setListas(String[] listas) {
		this.listas = listas;
	}
	
	public DtUsuarioEliminado getDt() {
		return new DtUsuarioEliminado(this.nickname, this.email, this.nombre, this.apellido, this.fechaNac, this.fechaEliminacion, this.img, this.nombreCanal, this.videos, this.listas);
	}
	

}
