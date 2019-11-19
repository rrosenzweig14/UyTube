package datatypes;

import java.util.Date;

public class DtUsuarioEliminado {
	
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
	
	public DtUsuarioEliminado() {
		
	}
	
	public DtUsuarioEliminado(String nickname, String email, String nombre, String apellido, Date fechaNac,
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
	
	public String getNickname() {
		return nickname;
	}
	public String getEmail() {
		return email;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public String getImg() {
		return img;
	}
	public String getNombreCanal() {
		return nombreCanal;
	}
	public String[] getVideos() {
		return videos;
	}
	public String[] getListas() {
		return listas;
	}

	public Date getFechaEliminacion() {
		return fechaEliminacion;
	}
	
}
