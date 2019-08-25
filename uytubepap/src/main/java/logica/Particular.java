package logica;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Particular")
public class Particular extends	Lista {

	//Constructor
	public Particular(){
		super();
	}
	
	public Particular(String nombre, boolean privado) {
		super(nombre, privado);
		// TODO Auto-generated constructor stub
	}
	
	public Particular (String nombre, boolean privado, Categoria categoria) {
		super();
		this.setNombre(nombre);
		this.setPrivado(privado);
		this.setCategoria(categoria);
	}
}
