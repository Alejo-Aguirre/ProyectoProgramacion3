package co.uniquindio.proyectofinal.controllers;

import java.io.IOException;

import co.uniquindio.proyectofinal.exceptions.AdminException;
import co.uniquindio.proyectofinal.exceptions.HorarioCrearException;
import co.uniquindio.proyectofinal.exceptions.InstructorEliminarException;
import co.uniquindio.proyectofinal.exceptions.LugarCrearException;
import co.uniquindio.proyectofinal.model.Administrador;
import co.uniquindio.proyectofinal.model.Horario;

/**
 *
 * @author alejo
 *
 */
public class CRUDHorarioController {

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
	public CRUDHorarioController() {
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
	public void agregarHorario(String codigo, String horaInicio, String horaFinal) throws Exception {

		this.verificarDatosAgregarHorario(codigo, horaInicio, horaFinal);

		modelfactoryController.agregarHorario(codigo, horaInicio, horaFinal);
	}

	/**
	 * Metodo que verifica si los datos que se ingresan son correctos
	 *
	 * @param nombre
	 * @param codigo
	 * @return
	 */
	private boolean verificarDatosAgregarHorario(String codigo, String horaInicio, String horaFinal ) {
		if (codigo.equalsIgnoreCase("") || horaInicio.equalsIgnoreCase("") || horaFinal.equalsIgnoreCase("")) {
			try {
				throw new HorarioCrearException(" los datos ingresados son incorrectos : ");
			} catch (HorarioCrearException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean actualizarHorario(String codigoActual, String codigo, String horaInicio, String horaFinal) {
		return modelfactoryController.actualizarHorario(codigoActual, codigo, horaInicio, horaFinal);
	}

	public void eliminarHorario(String codigo) throws Exception  {
		modelfactoryController.eliminarHorario(codigo);

	}

	public void generarReporteHorarios(String rutaArchivo) throws IOException, AdminException {
		modelfactoryController.generarReporteHorarios(rutaArchivo);

	}

}
