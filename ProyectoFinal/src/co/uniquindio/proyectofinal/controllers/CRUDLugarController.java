package co.uniquindio.proyectofinal.controllers;

import java.io.IOException;

import co.uniquindio.proyectofinal.exceptions.AdminException;
import co.uniquindio.proyectofinal.exceptions.InstructorEliminarException;
import co.uniquindio.proyectofinal.exceptions.LugarCrearException;
import co.uniquindio.proyectofinal.model.Administrador;
import co.uniquindio.proyectofinal.model.Lugar;

/**
 *
 * @author alejo
 *
 */
public class CRUDLugarController {

	/**
	 * Atributo que determina el administrador
	 */
	private Administrador admin;

	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	/**
	 * Atributo que determina el modelfactoryController
	 */
	private ModelFactoryController modelfactoryController;

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


	/**
	 * Constructor de la clase
	 *
	 * @param admin
	 * @param modelfactoryController
	 */
	public CRUDLugarController() {
		super();
		modelfactoryController = modelfactoryController.getInstance();
		admin = modelfactoryController.getAdmin();

	}

	/**
	 * autor alejo Metodo encargado de crear un instructor
	 *
	 * @param nombre
	 * @param codigo
	 * @throws Exception
	 */
	public void crearLugar(String nombre, String codigo) throws Exception {

		this.verificarDatosAgregarLugar(nombre, codigo);

		modelfactoryController.crearLugar(nombre, codigo);
	}

	/**
	 * Metodo que verifica si los datos que se ingresan son correctos
	 *
	 * @param nombre
	 * @param codigo
	 * @return
	 */
	private boolean verificarDatosAgregarLugar(String nombre, String codigo) {
		if (nombre.equalsIgnoreCase("") || codigo.equalsIgnoreCase("")) {
			try {
				throw new LugarCrearException(" los datos ingresados son incorrectos : ");
			} catch (LugarCrearException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean editarLugar(String codigoActual, String nombre, String codigo) {
		return modelfactoryController.editarLugar(codigoActual, nombre, codigo);
	}

	public void eliminarLugar(String codigo) throws Exception  {
		modelfactoryController.eliminarLugar(codigo);

	}

	public void generarReporteLugares(String rutaArchivo) throws IOException, AdminException {
		modelfactoryController.generarReporteLugares(rutaArchivo);

	}

}