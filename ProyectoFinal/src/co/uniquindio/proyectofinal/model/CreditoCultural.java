package co.uniquindio.proyectofinal.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CreditoCultural extends Credito  implements Serializable{
	private static final long serialVersionUID = 1L;

	private double costo;

	public CreditoCultural() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreditoCultural(String nombre, String codigo, String cupoMaximo, String duracionCredito, Instructor instructor, Horario horario, Lugar lugar,double costo) {
		super(nombre, codigo, cupoMaximo, duracionCredito, instructor, horario, lugar);
		this.costo = costo;
		// TODO Auto-generated constructor stub
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	@Override
	public String toString() {
		return super.toString()+" "+getCosto();
	}








}
