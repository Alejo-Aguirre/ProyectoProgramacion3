package co.uniquindio.proyectofinal.controllers;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Combo;


import co.uniquindio.proyectofinal.exceptions.CreacionCreditoAcademicoException;
import co.uniquindio.proyectofinal.exceptions.CreditoAcademicoEliminarException;
import co.uniquindio.proyectofinal.exceptions.EditarCreditoAcademicoException;
import co.uniquindio.proyectofinal.model.Administrador;
import co.uniquindio.proyectofinal.model.Horario;
import co.uniquindio.proyectofinal.model.Instructor;
import co.uniquindio.proyectofinal.model.Lugar;

public class CRUDCreditoAcademicoController {
	/**
	 * Atributo que determina el administrador
	 */
	private Administrador admin;

	private ArrayList<Instructor>listaInstructores = new ArrayList<>();
	private ArrayList<Horario>listaHorarios = new ArrayList<>();
	private ArrayList<Lugar>listaLugares = new ArrayList<>();

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

	public CRUDCreditoAcademicoController() {

		modelfactoryController = modelfactoryController.getInstance();
		admin = modelfactoryController.getAdmin();
		listaInstructores = admin.getListaInstructores();
		listaHorarios = admin.getListaHorarios();
		listaLugares = admin.getListaLugares();
	}


	/**
	 * Metodo encargado de crear un Credito Academico
	 *
	 * @param nombre
	 * @param codigo
	 * @throws creacionCreditoAcademicoException
	 */
	public void CrearCreditoAcademico(String nombre, String codigo, String cupoMaximo, String duracionCredito,Combo comboInstructores, Combo comboHorarios, Combo comboLugares,String homologacion) throws CreacionCreditoAcademicoException {
		//this.verificarDatosCreditoAcademico(nombre, codigo, cupoMaximo, duracionCredito, comboInstructores, horario, lugar);

		if(comboInstructores.getSelectionIndex() == -1){
			throw new CreacionCreditoAcademicoException("seleccione un instructor.");
		}

		Instructor instructor = obtenerInstructorSeleccionado (comboInstructores);
		Horario horario = obtenerHorarioSeleccionado (comboInstructores);
		Lugar lugar =  obtenerLugarSeleccionado (comboLugares);
		modelfactoryController.crearCreditoAcademico(nombre, codigo, cupoMaximo, duracionCredito,instructor, horario, lugar ,homologacion);

	}

	public Instructor obtenerInstructorSeleccionado(Combo comboInstructores) throws CreacionCreditoAcademicoException {
		String itemSelecionCombo = comboInstructores.getItem(comboInstructores.getSelectionIndex());

		String codigo = itemSelecionCombo.substring(0,itemSelecionCombo.indexOf("-"));

		Instructor instructor = admin.buscarInstructor(codigo);

		if(instructor == null){
			throw new CreacionCreditoAcademicoException("El instructor no existe");
		}


		return instructor;
	}

	public Horario obtenerHorarioSeleccionado(Combo comboHorarios) throws CreacionCreditoAcademicoException {
		String itemSelecionCombo = comboHorarios.getItem(comboHorarios.getSelectionIndex());

		String codigo = itemSelecionCombo.substring(0,itemSelecionCombo.indexOf("-"));

		Horario horario = admin.buscarHorario(codigo);

		if(horario == null){
			throw new CreacionCreditoAcademicoException("El horario no existe");
		}


		return horario;
	}


	public Lugar obtenerLugarSeleccionado(Combo comboLugares) throws CreacionCreditoAcademicoException {
		String itemSelecionCombo = comboLugares.getItem(comboLugares.getSelectionIndex());

		String codigo = itemSelecionCombo.substring(0,itemSelecionCombo.indexOf("-"));

		Lugar lugar = admin.buscarLugar(codigo);

		if(lugar == null){
			throw new CreacionCreditoAcademicoException("El lugar no existe");
		}


		return lugar;
	}
	public boolean actualizarCreditoAcademico(String codigoActual,String nombre, String codigo, String cupoMaximo, String duracionCredito,
			Instructor instructor, Horario horario, Lugar lugar) throws CreacionCreditoAcademicoException, EditarCreditoAcademicoException {

			return modelfactoryController.actualizarCreditoAcademico(codigoActual,nombre, codigo, cupoMaximo, duracionCredito, instructor,
					horario, lugar);
	}



	public void eliminarCreditoAcademico(String codigo) throws CreacionCreditoAcademicoException, CreditoAcademicoEliminarException {

			modelfactoryController.eliminarCreditoAcademico(codigo);

	}

//	private boolean verificarDatosCreditoAcademico(String nombre, String codigo, String cupoMaximo,
//			String duracionCredito, Instructor instructor, Horario horario, Lugar lugar)
//			throws CreacionCreditoAcademicoException {
//		if (nombre.equalsIgnoreCase("") && codigo.equalsIgnoreCase("") && cupoMaximo.equalsIgnoreCase("")
//				&& duracionCredito.equalsIgnoreCase("")) {
//			try {
//				throw new CreacionCreditoAcademicoException(" los datos ingresados son incorrectos : ");
//			} catch (CreacionCreditoAcademicoException e) {
//
//				e.printStackTrace();
//			}
//		}
//		return true;
//	}

	public int obtenerPosicionInstructor(String nombre) {

		return modelfactoryController.obtenerPosicionInstructor(nombre);
	}

	public int obtenerPosicionLugar(String nombre) {

		return modelfactoryController.obtenerPosicionLugar(nombre);
	}

	public int obtenerPosicionHorario(String codigo) {

		return modelfactoryController.obtenerPosicionLugar(codigo);
	}


	/**
	 * autor alejo
	 * metodo para obtener nombres para utilizar en el combo de instructores
	 * @return
	 */
	public String[] obtenerListaInstructoresCombo() {
		String[] instructores = new String [listaInstructores.size()];

		for (int i = 0; i < listaInstructores.size(); i++) {
			Instructor instructor = listaInstructores.get(i);
			System.out.println("instructor"+instructor.getCodigo());
			instructores[i] = instructor.getNombre()+ "-"+ instructor.getCodigo();


		}
		return instructores ;
	}


	/**
	 * autor alejo
	 * metodo para obtener nombres para utilizar en el combo de horarios
	 * @return
	 */
	public String[] obtenerListaHorariosCombo() {
		String[] horarios = new String [listaHorarios.size()];

		for (int i = 0; i < listaHorarios.size(); i++) {
			Horario horario = listaHorarios.get(i);
			horarios[i] = horario.getCodigo()+ "-"+ horario.getHoraInicio();

		}
		return horarios ;
	}


	/**
	 * autor alejo
	 * metodo para obtener nombres para utilizar en el combo de horarios
	 * @return
	 */
	public String[] obtenerListaLugaresCombo() {
		String[] lugares = new String [listaLugares.size()];

		for (int i = 0; i < listaLugares.size(); i++) {
			Lugar lugar = listaLugares.get(i);
			lugares[i] = lugar.getCodigo()+ "-"+ lugar.getNombre();

		}
		return lugares ;
	}



}
