package co.uniquindio.proyectofinal.model;

import java.io.Serializable;

public class Instructor extends Persona  implements Serializable{

	private static final long serialVersionUID = 1L;

	public Instructor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Instructor(String nombre, String codigo) {
		super(nombre, codigo);
		// TODO Auto-generated constructor stub
	}

	public Instructor(String nombre){
		super(nombre);
	}


	@Override
	public String toString() {
		return " Instructor : "+super.toString() ;

	}

	public boolean existeInstructor(String codigo) {
		if(this.getCodigo().equals(codigo)){
			return true;
		}
		return false;
	}


}
