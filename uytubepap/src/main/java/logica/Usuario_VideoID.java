package logica;

import java.io.Serializable;




public class Usuario_VideoID implements Serializable{	
	private static final long serialVersionUID = 1L;	
	
	private String nombreUsuario;	
	private int idVideo;

	public Usuario_VideoID() {
		super();
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public int getIdVideo() {
		return idVideo;
	}

	public void setIdVideo(int idVideo) {
		this.idVideo = idVideo;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idVideo;
		result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario_VideoID other = (Usuario_VideoID) obj;
		if (idVideo != other.idVideo)
			return false;
		if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;
		return true;
	}

	

	

}
