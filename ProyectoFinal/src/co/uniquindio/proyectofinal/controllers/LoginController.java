package co.uniquindio.proyectofinal.controllers;

import java.io.IOException;
import java.net.UnknownHostException;

import co.uniquindio.proyectofinal.exceptions.AdminException;
import co.uniquindio.proyectofinal.model.Administrador;
import co.uniquindio.proyectofinal.model.Usuario;
import co.uniquindio.proyectofinal.persistencia.Propiedades;

import co.uniquindio.proyectofinal.model.Administrador;

public class LoginController {

	/**
	 * Atributo que determina el administrador
	 */
	private Administrador admin;

	/**
	 * Atributo que determina el modelfactoryController
	 */
	private ModelFactoryController modelfactoryController;

	/**
	 * Constructor de la clase
	 *
	 * @param admin
	 * @param modelfactoryController
	 */
	public LoginController() {
		super();
		modelfactoryController = modelfactoryController.getInstance();
		admin = modelfactoryController.getAdmin();

	}

	/**
	 * Metodo encargado de retornar el valor del atributo modelfactoryController
	 *
	 * @return El modelfactoryController asociado a la clase
	 */
	public ModelFactoryController getModelfactoryController() {
		return modelfactoryController;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo
	 * modelfactoryController
	 *
	 * @param modelfactoryController
	 *            El nuevo modelfactoryController a modificar.
	 */
	public void setModelfactoryController(ModelFactoryController modelfactoryController) {
		this.modelfactoryController = modelfactoryController;
	}

	public void login(String nombreUsuario, String contrasena) throws  UnknownHostException, ClassNotFoundException, IOException, AdminException {
		Usuario usuario = modelfactoryController.login(nombreUsuario, contrasena);

		Propiedades propiedades = new Propiedades();
		propiedades.properties.setProperty("nombreUsuario", usuario.getNombreUsuario());
		propiedades.properties.setProperty("rol", usuario.getRol());
		propiedades.guardarArchivoPropiedad();
	}



}
