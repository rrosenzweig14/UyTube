package logica;

import java.util.Date;
import java.util.TreeSet;

public class Comentario {
	
	private String texto;
	
	private Date fecha;
	
	private Usuario autor;
	
	private TreeSet<Comentario> respuestas;

	public Comentario() {
		// TODO Auto-generated constructor stub
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;		
	}

	public TreeSet<Comentario> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(TreeSet<Comentario> respuestas) {
		this.respuestas = respuestas;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

}
