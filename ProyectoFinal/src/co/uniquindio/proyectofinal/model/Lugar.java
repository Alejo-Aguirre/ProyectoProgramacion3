package co.uniquindio.proyectofinal.model;

import java.io.Serializable;

public class Lugar implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo;
	private String nombre;



	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Lugar() {

	}
	public Lugar(String nombre) {
		super();
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Lugar: " +getNombre() + " Codigo: " +getCodigo() + "\n";
	}



}
