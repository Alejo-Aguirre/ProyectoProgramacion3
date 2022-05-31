package co.uniquindio.proyectofinal.model;

import java.io.Serializable;

public class Materia implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	private double nota;

	public Materia (){

	}

	public Materia(String nombre, double nota) {
		super();
		this.nombre = nombre;
		this.nota = nota;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return " "+ getNombre()+" "+getNota();
	}





}
