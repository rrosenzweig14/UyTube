package WS;

import java.util.Date;
import datatypes.DtComentario;

public class DtVideoWS {
	public int id = -1;
	public String nombre = null;	
	public Boolean privado = null;	
	public String canal = null;	
	public String descripcion = null;	
	public Integer duracion = null;	
	public String categoria = null;	
	public Date fechaPub = null;	
	public String url = null;
	public DtComentario[] com = null;
	public String[] valoracionesPositivas = null;
	public String[] valoracionesNegativas = null;
	
	public DtVideoWS() {}
	public DtVideoWS(int id, String nom, Boolean p, String c, String desc, Integer dur, String cat, Date fecha, String url, DtComentario[] com, String[] vp, String[] vn) {
		this.id = id;
		this.nombre = nom;
		this.privado = p;
		this.canal = c;
		this.descripcion = desc;
		this.duracion = dur;
		this.categoria = cat;
		this.fechaPub = fecha;
		this.url = url;
		this.com = com;
		this.valoracionesPositivas = vp;
		this.valoracionesNegativas = vn;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}		
		DtVideoWS video = (DtVideoWS) o;		
		return (nombre.equals(video.nombre) && descripcion.equals(video.descripcion) && duracion == video.duracion && categoria.equals(video.categoria) && url.equals(video.url) && fechaPub == video.fechaPub);
	}
}
