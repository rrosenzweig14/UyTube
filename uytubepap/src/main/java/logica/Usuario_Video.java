package logica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(Usuario_VideoID.class)
public class Usuario_Video {
	
	@Id 
	@ManyToOne
	@JoinColumn(
			insertable=false,
			updatable=false
	)
	private Usuario nombreUsuario;
	
	@Id
	@ManyToOne
	@JoinColumn(
			insertable=false,
			updatable=false
	)
	private Video nombreVideo;	
	@Column(nullable = true)
	private boolean leGusta;
	
	
	public boolean isLeGusta() {
		return leGusta;
	}

	public void setLeGusta(boolean leGusta) {
		this.leGusta = leGusta;
	}

	public Usuario_Video() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Usuario getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(Usuario nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Video getNombreVideo() {
		return nombreVideo;
	}

	public void setNombreVideo(Video nombreVideo) {
		this.nombreVideo = nombreVideo;
	}

	
	


}
