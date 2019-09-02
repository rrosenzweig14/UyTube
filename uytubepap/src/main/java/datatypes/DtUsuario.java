package datatypes;

import java.util.Date;
import java.util.HashMap;


public class DtUsuario {	
	
	private String nickname;	
	private String email;	
	private String nombre;		
	private String apellido;	
	private Date fechaNac;		
	private String img;	
	
	private HashMap<String,DtUsuario> seguidores = new HashMap<String,DtUsuario>();		
	private HashMap<String,DtUsuario> seguidos = new HashMap<String,DtUsuario>();	
	
	public DtUsuario() {}
	
	public DtUsuario(String nickname, String email, String nombre, String apellido, Date fechaNac, String img) {
		this.nickname = nickname;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.img = img;
	}
	
	public DtUsuario(String nickname) {
		this.nickname = nickname;
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

	public HashMap<String, DtUsuario> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(HashMap<String, DtUsuario> seguidores) {
		this.seguidores = seguidores;
	}

	public HashMap<String, DtUsuario> getSeguidos() {
		return seguidos;
	}

	public void setSeguidos(HashMap<String, DtUsuario> seguidos) {
		this.seguidos = seguidos;
	}	
    
    public void addSeguido(DtUsuario u) {
    	this.seguidos.put(u.getNickname(), u);
    }
    public void addSeguidor(DtUsuario u) {
    	this.seguidores.put(u.getNickname(), u);
    }
    
    @Override
    public boolean equals(Object o) {
    	if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) return false;
		
		DtUsuario user = (DtUsuario) o;
		
		return nickname.equals(user.nickname) && nombre.equals(user.nombre) && apellido.equals(user.apellido) && email.equals(user.email) && img.equals(user.img) && fechaNac == user.fechaNac;
    }
}
