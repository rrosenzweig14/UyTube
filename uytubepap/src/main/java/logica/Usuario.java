package logica;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {
	
	private String nickname;
	
	private String email;
	
	private String nombre;
	
	private String apellido;
	
	private Date fechaNac;
	
	private String img;
	
	private ArrayList<Usuario> seguidores;
	
	private ArrayList<Usuario> seguidos;
	
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

	public ArrayList<Usuario> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(ArrayList<Usuario> seguidores) {
		this.seguidores = seguidores;
	}

	public ArrayList<Usuario> getSeguidos() {
		return seguidos;
	}

	public void setSeguidos(ArrayList<Usuario> seguidos) {
		this.seguidos = seguidos;
	}

}
