package co.uniquindio.proyectofinal.model;

import java.io.Serializable;
import java.util.ArrayList;

import co.uniquindio.proyectofinal.model.Lugar;

public class Credito implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nombre;
	private String codigo;
	private String cupoMaximo;
	private String duracionCredito;
	private Instructor instructor;
	private Horario horario;
	private Lugar lugar;
	//private ArrayList<Estudiante> listaEstudiantes;


	public Credito() {
	}

	public Credito(String nombre, String codigo, String cupoMaximo, String duracionCredito, Instructor instructor, Horario horario, Lugar lugar) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.cupoMaximo = cupoMaximo;
		this.duracionCredito = duracionCredito;
		//this.listaEstudiantes = listaEstudiantes;
		this.instructor = instructor;
		this.horario = horario;
		this.lugar = lugar;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCupoMaximo() {
		return cupoMaximo;
	}

	public void setCupoMaximo(String cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
	}

	public String getDuracionCredito() {
		return duracionCredito;
	}

	public void setDuracionCredito(String duracionCredito) {
		this.duracionCredito = duracionCredito;
	}

//	public ArrayList<Estudiante> getListaEstudiantes() {
//		return listaEstudiantes;
//	}
//
//	public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
//		this.listaEstudiantes = listaEstudiantes;
//	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	@Override
	public String toString() {
		return " " + getNombre() + " " + getCodigo() + " " + getCupoMaximo() + " " + getDuracionCredito() + " "
				 + " " + getInstructor() + " " + getHorario() + " " + getLugar();

	}


}