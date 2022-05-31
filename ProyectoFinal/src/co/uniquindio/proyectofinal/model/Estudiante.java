package co.uniquindio.proyectofinal.model;

import java.io.Serializable;

public class Estudiante extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;


	public Estudiante() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Estudiante(String nombre, String codigo) {
		super(nombre, codigo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return super.toString();
	}





}
