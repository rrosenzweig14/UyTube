package logica;

public abstract class Lista {

	//Variables
	private String nombre;
	
	private boolean privado;
	
	private Categoria categoria;
	
	//Getters & Setters
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

	//Constructor
	public Lista(String nombre, boolean privado) {
		super();
		this.nombre = nombre;
		this.privado = privado;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
