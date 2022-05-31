package co.uniquindio.proyectofinal.model;

import java.io.Serializable;

public class Horario implements Serializable{

	private static final long serialVersionUID = 1L;

	private String horaInicio;
	private String horaFinal;
	private String codigo;


	public Horario() {

	}
	public Horario(String codigo,String horaInicio, String horaFinal) {
		this.codigo= codigo;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFinal() {
		return horaFinal;
	}
	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return " Hora de inico :"+ getHoraInicio()+" Hora de finalizacion :"+ getHoraFinal() + "\n";
	}




}
