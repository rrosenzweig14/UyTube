package logica;

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
	
	
	
	
	private Boolean like;

	public Boolean getLike() {
		return like;
	}

	public void setLike(Boolean like) {
		this.like = like;
	}
	

}
