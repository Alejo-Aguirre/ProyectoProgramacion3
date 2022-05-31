package co.uniquindio.proyectofinal.model;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;

public class CreditoDeportivo extends Credito implements Serializable{

	private static final long serialVersionUID = 1L;

	private IntegerProperty asistencia;

	public CreditoDeportivo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreditoDeportivo(String nombre, String codigo, String cupoMaximo, String duracionCredito, Instructor instructor, Horario horario, Lugar lugar,IntegerProperty asistencia) {
		super(nombre, codigo, cupoMaximo, duracionCredito, instructor, horario, lugar);
		this.asistencia = asistencia;
	}

	public IntegerProperty getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(IntegerProperty asistencia) {
		this.asistencia = asistencia;
	}

	@Override
	public String toString() {
		return super.toString()+" "+ getAsistencia();
	}





}
