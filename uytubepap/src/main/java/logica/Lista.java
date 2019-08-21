package logica;

public class Lista {

	private String nombre;
	
	private boolean privado;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isPrivado() {
		return privado;
	}

	public void setPrivado(boolean privado) {
		this.privado = privado;
	}

	public Lista(String nombre, boolean privado) {
		super();
		this.nombre = nombre;
		this.privado = privado;
	}

}
