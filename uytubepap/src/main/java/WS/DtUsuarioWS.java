package WS;

import java.util.Date;

public class DtUsuarioWS {
	public String nickname = null;	
	public String email = null;	
	public String password = null;
	public String nombre = null;		
	public String apellido = null;	
	public Date fechaNac = null;		
	public String img = null;	
	public String[] seguidores = null;
	public String[] seguidos = null;
	
	public DtUsuarioWS() {}
	public DtUsuarioWS(String nickname) {
		this.nickname = nickname;
	}
	public DtUsuarioWS(String nickname, String email, String password, String nombre, String apellido, Date fechaNac, String img, String[] seguidores, String[] seguidos) {
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.img = img;
		this.seguidores = seguidores;
		this.seguidos = seguidos;
	}

    @Override
    public boolean equals(Object o) {
    	if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) { 
			return false;
		}
		DtUsuarioWS user = (DtUsuarioWS) o;		
		return (nickname.equals(user.nickname) && nombre.equals(user.nombre) && apellido.equals(user.apellido) && email.equals(user.email) && img.equals(user.img) && fechaNac == user.fechaNac);
    }	
}
