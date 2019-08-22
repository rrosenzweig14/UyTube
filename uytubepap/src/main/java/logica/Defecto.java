package logica;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Defecto")
public class Defecto extends Lista{

	//Constructor
	public Defecto(String nombre, boolean privado) {
		super(nombre, privado);
		// TODO Auto-generated constructor stub
	}
	
	public Defecto() {
		
	}
}
