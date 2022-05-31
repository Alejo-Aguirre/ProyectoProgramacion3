package co.uniquindio.proyectofinal.controllers;

import co.uniquindio.proyectofinal.exceptions.CreacionCreditoDeportivoException;
import co.uniquindio.proyectofinal.exceptions.EditarCreditoDeportivoException;
import co.uniquindio.proyectofinal.exceptions.EditarInstructorException;
import co.uniquindio.proyectofinal.model.Administrador;
import co.uniquindio.proyectofinal.model.Horario;
import co.uniquindio.proyectofinal.model.Instructor;
import co.uniquindio.proyectofinal.model.Lugar;
import javafx.beans.property.IntegerProperty;

public class CRUDCreditoDeportivoController
{
	/**
	 * Atributo que determina el administrador
	 */
	private Administrador admin;


	public Administrador getAdmin()
	{
		return admin;
	}

	public void setAdmin(Administrador admin)
	{
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
	public ModelFactoryController getModelfactoryController()
	{
		return modelfactoryController;
	}
	/**
	 * Metodo encargado de modificar el valor del atributo
	 * modelfactoryController
	 *
	 * @param modelfactoryController
	 *            El nuevo modelfactoryController a modificar.
	 */
	public void setModelfactoryController(ModelFactoryController modelfactoryController)
	{
		this.modelfactoryController = modelfactoryController;
	}

	/**
	 * Constructor de la clase
	 *
	 * @param admin
	 * @param modelfactoryController
	 */

	public CRUDCreditoDeportivoController()
	{
		super();
		admin = modelfactoryController.getAdmin();
		this.modelfactoryController = modelfactoryController.getInstance();
	}




	/**
	 *  Metodo encargado de crear un Credito Deportivo
	 *
	 * @param nombre
	 * @param codigo
	 * @throws creacionCreditoDeportivoException
	 */
	public void CrearCreditoDeportivo(String nombre, String codigo, String cupoMaximo,String duracionCredito, Instructor instructor, Horario horario, Lugar lugar, IntegerProperty asistencia ) throws CreacionCreditoDeportivoException{

		this.verificarDatosCreditoDeportivo( nombre,  codigo,  cupoMaximo, duracionCredito,  instructor,  horario,  lugar,  asistencia);

		modelfactoryController.CrearCreditoDeportivo(nombre,  codigo,  cupoMaximo,duracionCredito,  instructor,  horario,  lugar,  asistencia);

	}
	/**
	 *
	 * @return
	 * @throws CreacionCreditoDeportivoException
	 */
	private boolean verificarDatosCreditoDeportivo(String nombre, String codigo, String cupoMaximo,
			String duracionCredito, Instructor instructor, Horario horario, Lugar lugar, IntegerProperty asistencia)
			throws CreacionCreditoDeportivoException
	{
		if(nombre.equalsIgnoreCase("")&& codigo.equalsIgnoreCase("")&& cupoMaximo.equalsIgnoreCase("")&& duracionCredito.equalsIgnoreCase(""))
       {
			try {
				throw new CreacionCreditoDeportivoException( " los datos ingresados son incorrectos : ");
			} catch (CreacionCreditoDeportivoException e)
			{

				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * metodo para actualizar el credito deportivo
	 * @param nombre,codigo,cupoMaximo,duracionCredito,instructor,horario, lugar,asistencia
	 * @throws CreacionCreditoDeportivoException
	 * @throws EditarInstructorException
	 * @throws EditarCreditoDeportivoException
	 */
	public boolean actualizarCreditoDeportivo (String codigoActual ,String nombre, String codigo, String cupoMaximo, String duracionCredito, Instructor instructor, Horario horario, Lugar lugar, IntegerProperty asistencia ) throws CreacionCreditoDeportivoException, EditarInstructorException, EditarCreditoDeportivoException
	{
		    //this.verificarDatosCreditoDeportivo( nombre,  codigo,  cupoMaximo, duracionCredito,  instructor,  horario,  lugar,  asistencia);

			return modelfactoryController.actualizarCreditoDeportivo(codigoActual,nombre, codigo, cupoMaximo, duracionCredito, instructor, horario, lugar, asistencia);
	}






}
