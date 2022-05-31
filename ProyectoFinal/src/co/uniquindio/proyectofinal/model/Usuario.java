package co.uniquindio.proyectofinal.model;

import java.io.Serializable;

public class Usuario implements Serializable{

	/**
	 * Atributo que determina el nombreUsuario
	 */
	private String nombreUsuario;

	/**
	 * Atributo que determina la contrasena
	 */
	private String contrasena;

	/**
	 * Atributo que determina la contrasena
	 */
	private String rol;

	/**
	 * Constructor de la clase.
	 */
	public Usuario() {
	}

	/**
	 * Metodo encargado de retornar el valor del atributo nombreUsuario
	 * @return El nombreUsuario asociado a la clase
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo nombreUsuario
	 * @param nombreUsuario El nuevo nombreUsuario a modificar.
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo contrasena
	 * @return El contrasena asociado a la clase
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo contrasena
	 * @param contrasena El nuevo contrasena a modificar.
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo rol
	 * @return El rol asociado a la clase
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo rol
	 * @param rol El nuevo rol a modificar.
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

}
