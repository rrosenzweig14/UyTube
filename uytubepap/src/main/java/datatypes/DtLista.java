package datatypes;

public class DtLista {

	//Variables
	private int id;
	private String nombre;
	private boolean privado;
	private boolean defecto;
	private String categoria;	

	//Constructor

	public DtLista() {
		super();
	}
	public DtLista(int id,String nombre, boolean privado, boolean defecto, String categoria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.privado = privado;
		this.defecto = defecto;
		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public boolean isDefecto() {
		return defecto;
	}
	public void setDefecto(boolean defecto) {
		this.defecto = defecto;
	}
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}	
}
