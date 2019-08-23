package logica;

import java.io.Serializable;




public class Usuario_VideoID implements Serializable{	
	private static final long serialVersionUID = 1L;	
	
	private String nombreUsuario;	
	private String nombreVideo;

	public Usuario_VideoID() {
		super();
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreVideo() {
		return nombreVideo;
	}

	public void setNombreVideo(String nombreVideo) {
		this.nombreVideo = nombreVideo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
		result = prime * result + ((nombreVideo == null) ? 0 : nombreVideo.hashCode());
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
		if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;
		if (nombreVideo == null) {
			if (other.nombreVideo != null)
				return false;
		} else if (!nombreVideo.equals(other.nombreVideo))
			return false;
		return true;
	}

}
