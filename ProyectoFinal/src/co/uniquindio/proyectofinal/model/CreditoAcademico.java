package co.uniquindio.proyectofinal.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CreditoAcademico extends Credito implements Serializable{

	private static final long serialVersionUID = 1L;

	private String homologacion;

	public CreditoAcademico(){
	}


	public CreditoAcademico(String nombre, String codigo, String cupoMaximo, String duracionCredito, Instructor instructor, Horario horario, Lugar lugar,String homologacion) {
		super(nombre, codigo, cupoMaximo, duracionCredito, instructor, horario, lugar);
		this.homologacion=homologacion;

	}


	public String getHomologacion() {
		return homologacion;
	}

	public void setHomologacion(String homologacion) {
		this.homologacion = homologacion;
	}


	@Override
	public String toString() {
		return super.toString()+" "+ getHomologacion();
	}






}
