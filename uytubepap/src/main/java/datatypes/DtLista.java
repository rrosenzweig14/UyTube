package datatypes;

public class DtLista {

	//Variables
	private String nombre;
	private boolean privado;
	private DtCategoria categoria;

	//Getters
	public String getNombre() {
		return nombre;
	}

	public boolean isPrivado() {
		return privado;
	}

	//Constructor
	public DtLista(String nombre, boolean privado) {
		super();
		this.nombre = nombre;
		this.privado = privado;
	}	
}
