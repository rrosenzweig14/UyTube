package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import datatypes.DtUsuario;
import datatypes.DtVideo;

public class Usuario {
	
	private String nickname;
	
	private String email;
	
	private String nombre;
	
	private String apellido;
	
	private Date fechaNac;
	
	private String img;
	
	private HashMap<String,Usuario> seguidores;	
	
	private HashMap<String,Usuario> seguidos;
	
	private Canal canal;	
	//Raro
	private HashMap<DtVideo, Date> comentarios;
	
	private ArrayList<Usuario_Video> valoraciones;
	
	//Metodos
	
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

	public HashMap<String,Usuario> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(HashMap<String,Usuario> seguidores) {
		this.seguidores = seguidores;
	}

	public HashMap<String,Usuario> getSeguidos() {
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

	public HashMap<DtVideo, Date> getComentarios() {
		return comentarios;
	}

	public void setComentarios(HashMap<DtVideo, Date> comentarios) {
		this.comentarios = comentarios;
	}

	public ArrayList<Usuario_Video> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(ArrayList<Usuario_Video> valoraciones) {
		this.valoraciones = valoraciones;
	}

}
