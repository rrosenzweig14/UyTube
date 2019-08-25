package datatypes;

public class DtLista {

	//Variables
	private String nombre;
	private boolean privado;
	private String categoria;

	//Getters
	public String getNombre() {
		return nombre;
	}

	public boolean isPrivado() {
		return privado;
	}

	//Constructor
	public DtLista(String nombre, boolean privado, String categoria) {
		super();
		this.nombre = nombre;
		this.privado = privado;
		this.categoria = categoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}	
}
