package WS;

import datatypes.DtLista;

public class DtCanalWS {	
	public String nombre = null;	
	public String descripcion = null;
	public String nick = null;
	public Boolean privado = null;
	public DtLista[] listasReproduccion = null;
	public DtVideoWS[] listaVideos = null;
	
	public DtCanalWS() {}
	public DtCanalWS(String nom, String desc, String nick, Boolean p, DtLista[] lr, DtVideoWS[] lv) {
		this.nombre = nom;
		this.descripcion = desc;
		this.nick = nick;
		this.privado = p;
		this.listasReproduccion = lr;
		this.listaVideos = lv;
	}	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}		
		DtCanalWS canal = (DtCanalWS) o;		
		return (nombre.equals(canal.nombre) && descripcion.equals(canal.descripcion) && privado == canal.privado);
	}
}
