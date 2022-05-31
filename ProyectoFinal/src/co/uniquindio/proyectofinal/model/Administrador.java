package co.uniquindio.proyectofinal.model;

import co.uniquindio.proyectofinal.exceptions.CreacionCreditoAcademicoException;
import co.uniquindio.proyectofinal.exceptions.CreacionCreditoCulturalException;
import co.uniquindio.proyectofinal.exceptions.CreacionCreditoDeportivoException;
import co.uniquindio.proyectofinal.exceptions.CreacionEstudianteException;
import co.uniquindio.proyectofinal.exceptions.CreacionHorarioException;
import co.uniquindio.proyectofinal.exceptions.CreacionInstructorException;
import co.uniquindio.proyectofinal.exceptions.CreacionLugarException;
import co.uniquindio.proyectofinal.exceptions.CreditoAcademicoEliminarException;
import co.uniquindio.proyectofinal.exceptions.CreditoDeportivoEliminarException;
import co.uniquindio.proyectofinal.exceptions.EditarCreditoAcademicoException;
import co.uniquindio.proyectofinal.exceptions.EditarCreditoCulturalException;
import co.uniquindio.proyectofinal.exceptions.EditarCreditoDeportivoException;
import co.uniquindio.proyectofinal.exceptions.EditarEstudianteException;
import co.uniquindio.proyectofinal.exceptions.EditarHorarioException;
import co.uniquindio.proyectofinal.exceptions.EditarInstructorException;
import co.uniquindio.proyectofinal.exceptions.EliminarCreditoCulturalException;
import co.uniquindio.proyectofinal.exceptions.EstudianteEliminarException;
import co.uniquindio.proyectofinal.exceptions.HorarioEliminarException;
import co.uniquindio.proyectofinal.exceptions.InstructorEliminarException;
import co.uniquindio.proyectofinal.exceptions.LugarEliminarException;
import javafx.beans.property.IntegerProperty;

import java.io.Serializable;
import java.util.ArrayList;

public class Administrador implements Serializable {

	/**
	 * Atributo que dtermina el serial
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Atributo que determina el nombre
	 */
	private String nombre;

	/**
	 * Atributo que determina el usuarioSesion
	 */
	private Usuario usuarioSesion;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuarioSesion() {
		return usuarioSesion;
	}

	public void setUsuarioSesion(Usuario usuarioSesion) {
		this.usuarioSesion = usuarioSesion;
	}

	/**
	 * Atributo que determina la lista de creditos Academicos
	 */
	ArrayList<CreditoAcademico> listaCreditosAcademicos = new ArrayList<>();

	/**
	 * Atributo que determina la lista de creditos Culturales
	 */
	ArrayList<CreditoCultural> listaCreditosCulturales = new ArrayList<>();

	/**
	 * Atributo que determina la lista de creditos Deportivos
	 */
	ArrayList<CreditoDeportivo> listaCreditosDeportivos = new ArrayList<>();

	/**
	 * Atributo que determina la lista de estudiantes
	 */
	ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();

	/**
	 * Atributo que determina la lista de instructores
	 */
	ArrayList<Instructor> listaInstructores = new ArrayList<>();

	/**
	 * Atributo que determina la lista de lugares
	 */
	ArrayList<Lugar> listaLugares = new ArrayList<>();

	/**
	 * Atributo que determina la lista de lugares la lista de horarios
	 */
	ArrayList<Horario> listaHorarios = new ArrayList<>();

	/**
	 * Constructor de la clase
	 */
	public Administrador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<CreditoAcademico> getListaCreditosAcademicos() {
		return listaCreditosAcademicos;
	}

	public void setListaCreditosAcademicos(ArrayList<CreditoAcademico> listaCreditosAcademicos) {
		this.listaCreditosAcademicos = listaCreditosAcademicos;
	}

	public ArrayList<CreditoCultural> getListaCreditosCulturales() {
		return listaCreditosCulturales;
	}

	public void setListaCreditosCulturales(ArrayList<CreditoCultural> listaCreditosCulturales) {
		this.listaCreditosCulturales = listaCreditosCulturales;
	}

	public ArrayList<CreditoDeportivo> getListaCreditosDeportivos() {
		return listaCreditosDeportivos;
	}

	public void setListaCreditoDeportivos(ArrayList<CreditoDeportivo> listaCreditosDeportivos) {
		this.listaCreditosDeportivos = listaCreditosDeportivos;
	}

	public ArrayList<Estudiante> getListaEstudiantes() {
		return listaEstudiantes;
	}

	public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
		this.listaEstudiantes = listaEstudiantes;
	}

	public ArrayList<Instructor> getListaInstructores() {
		return listaInstructores;
	}

	public void setListaInstructores(ArrayList<Instructor> listaInstructores) {
		this.listaInstructores = listaInstructores;
	}

	public ArrayList<Lugar> getListaLugares() {
		return listaLugares;
	}

	public void setListaLugares(ArrayList<Lugar> listaLugares) {
		this.listaLugares = listaLugares;
	}

	public ArrayList<Horario> getListaHorarios() {
		return listaHorarios;
	}

	public void setListaHorarios(ArrayList<Horario> listaHorarios) {
		this.listaHorarios = listaHorarios;
	}

	public void setListaCreditosDeportivos(ArrayList<CreditoDeportivo> listaCreditosDeportivos) {
		this.listaCreditosDeportivos = listaCreditosDeportivos;
	}

	// CRUD
	// Estudiantes-----------------------------------------------------------------------------------------------

	/**
	 * autor : alejo
	 *
	 * metodo para agregar un estudiante a la lista de estudiantes
	 *
	 * @param nombre,codigo
	 * @return
	 * @throws CreacionEstudianteException
	 */
	public void agregarEstudiante(String nombre, String codigo) throws CreacionEstudianteException {

		if (buscarEstudiante(codigo) == null) {
			Estudiante nuevoEstudiante = new Estudiante();
			// Settear informacion al estudiante
			nuevoEstudiante.setNombre(nombre);
			nuevoEstudiante.setCodigo(codigo);

			// Agregar estudiante a la lista
			this.listaEstudiantes.add(nuevoEstudiante);
		} else {
			throw new CreacionEstudianteException(" El estudiante ya se encuentra creado ");
		}

	}

	/**
	 * autor : alejo
	 *
	 * metodo para modificar la informacion del estudiante a la lista de
	 * estudiantes
	 *
	 * @param nombre,
	 *            codigo
	 * @return
	 * @throws EditarEstudianteException
	 */
	public boolean actualizarEstudiante(String documentoActual, String nombre, String codigo)
			throws EditarEstudianteException {

		Estudiante estudianteActualizar = null;

		estudianteActualizar = buscarEstudiante(documentoActual);

		if (estudianteActualizar != null) {

			// Settear informacion nueva al estudiante
			estudianteActualizar.setNombre(nombre);
			estudianteActualizar.setCodigo(codigo);

			return true;
		} else {
			throw new EditarEstudianteException(" El instructor no se puede editar");

		}

	}

	/**
	 * autor alejo
	 *
	 * metodo para eliminar un estudiante
	 *
	 * @param codigo
	 * @return
	 * @throws EliminacionEstudianteException
	 */
	public void eliminarEstudiante(String codigo) throws EstudianteEliminarException {

		if (codigo == null || codigo.isEmpty()) {
			throw new EstudianteEliminarException(
					"El codigo del estudiante enviado es nulo o vacio, no se puede continuar el proceso.");
		}

		Estudiante estudianteConsultado = buscarEstudiante(codigo);

		if (estudianteConsultado == null) {
			throw new EstudianteEliminarException(
					"El Estudiante con codigo" + codigo + " no existe para poder eliminarlo.");
		}
		getListaEstudiantes().remove(estudianteConsultado);

	}

	/**
	 * autor: alejo
	 *
	 * metodo que permite buscar un estudiante por codigo
	 *
	 * @param codigo
	 * @return
	 */
	public Estudiante buscarEstudiante(String codigo) {
		for (Estudiante estudiante : listaEstudiantes) {
			if (estudiante.getCodigo().equalsIgnoreCase(codigo)) {
				return estudiante;
			}

		}
		return null;
	}

	// CRUD
	// INSTRUCTORES-------------------------------------------------------------------------------------------
	// agregar instructor
	/**
	 * autor alejo metodo para crear un instructor
	 *
	 * @param nombre
	 * @param codigo
	 * @return
	 * @throws CreacionInstructorException
	 */
	public void agregarInstructor(String nombre, String codigo) throws CreacionInstructorException {
		if (buscarInstructor(codigo) == null) {
			Instructor instructorNuevo = new Instructor();
			// Settear informacion al instructor
			instructorNuevo.setNombre(nombre);
			instructorNuevo.setCodigo(codigo);
			this.listaInstructores.add(instructorNuevo);

		} else {
			throw new CreacionInstructorException(" El instructor ya se encuentra creado ");
		}
	}

	/**
	 * autor : alejo
	 *
	 * metodo para modificar la informacion del estudiante a la lista de
	 * estudiantes
	 *
	 * @param nombre,
	 *            codigo
	 * @return
	 * @throws EditarInstructorException
	 */
	public boolean actualizarInstructor(String documentoActual, String nombre, String codigo)
			throws EditarInstructorException {

		Instructor instructorActualizar = null;

		instructorActualizar = buscarInstructor(documentoActual);

		if (instructorActualizar != null) {

			// Settear informacion nueva al estudiante
			instructorActualizar.setNombre(nombre);
			instructorActualizar.setCodigo(codigo);

			return true;
		} else {
			throw new EditarInstructorException(" El instructor no se puede editar");
		}

	}

	/**
	 * metodo que te permite eliminar un instructor
	 *
	 * @param codigo
	 * @throws EliminacionInstructorException
	 */

	public void eliminarIntructor(String codigo) throws InstructorEliminarException {
		if (codigo == null || codigo.isEmpty()) {
			throw new InstructorEliminarException(
					"El codigo del Instrcutor enviado es nulo o vacio, no se puede continuar el proceso.");
		}

		Instructor instructorConsultado = buscarInstructor(codigo);

		if (instructorConsultado == null) {
			throw new InstructorEliminarException(
					"El Instructor con codigo" + codigo + " no existe para poder eliminarlo.");
		}
		getListaInstructores().remove(instructorConsultado);

	}

	/**
	 * autor alejo metodo para buscar un instructor por codigo
	 *
	 * @param codigo
	 * @return
	 */
	public Instructor buscarInstructor(String codigo) {
		for (Instructor instructor : listaInstructores) {
			if (instructor.getCodigo().equalsIgnoreCase(codigo)) {
				return instructor;

			}

		}
		return null;
	}

	// CRUD
	// creditosDeportivos-------------------------------------------------------------------------------------------------
	/**
	 * Metodo para crear un credito deportivo
	 *
	 * @param nombre,codigo,cupoMaximo,duracionCredito,
	 *            listaEstudiantes,instructor,horario,lugar,asistencia
	 * @return
	 * @throws CreacionCreditoDeportivoException
	 */
	public CreditoDeportivo agregarCreditoDeportivo(String nombre, String codigo, String cupoMaximo,
			String duracionCredito, Instructor instructor, Horario horario, Lugar lugar, IntegerProperty asistencia)
			throws CreacionCreditoDeportivoException {

		if (buscarCreditoDeportivo(codigo) == null) {
			CreditoDeportivo creditoDeportivo = new CreditoDeportivo();
			// Settear informacion al estudiante
			creditoDeportivo.setNombre(nombre);
			creditoDeportivo.setCodigo(codigo);
			creditoDeportivo.setCupoMaximo(cupoMaximo);
			creditoDeportivo.setDuracionCredito(duracionCredito);
			creditoDeportivo.setInstructor(instructor);
			creditoDeportivo.setHorario(horario);
			creditoDeportivo.setLugar(lugar);
			creditoDeportivo.setAsistencia(asistencia);

			// Agregar estudiante a la lista
			this.listaCreditosDeportivos.add(creditoDeportivo);
			return creditoDeportivo;
		} else {
			throw new CreacionCreditoDeportivoException(" El credito ya se encuentra creado en la base de datos ");
		}

	}

	/**
	 * metodo para actulizar la informacion de un credito deportivo
	 *
	 * @param nombre,
	 *            codigo,cupoMaximo,duracionCredito,instructor,horario,lugar,asistencia
	 * @return
	 * @throws EditarInstructorException
	 * @throws EditarCreditoDeportivoException
	 */
	public boolean actualizarCreditoDeportivo(String codigoActual, String nombre, String codigo, String cupoMaximo,
			String duracionCredito, Instructor instructor, Horario horario, Lugar lugar, IntegerProperty asistencia)
			throws EditarInstructorException, EditarCreditoDeportivoException {

		CreditoDeportivo creditoDeportivoActualizar = null;

		creditoDeportivoActualizar = (CreditoDeportivo) buscarCreditoDeportivo(codigoActual);

		if (creditoDeportivoActualizar != null) {

			// settear informacion
			creditoDeportivoActualizar.setNombre(nombre);
			creditoDeportivoActualizar.setCodigo(codigo);
			creditoDeportivoActualizar.setCupoMaximo(cupoMaximo);
			creditoDeportivoActualizar.setDuracionCredito(duracionCredito);
			creditoDeportivoActualizar.setInstructor(instructor);
			creditoDeportivoActualizar.setHorario(horario);
			creditoDeportivoActualizar.setLugar(lugar);
			creditoDeportivoActualizar.setAsistencia(asistencia);

			return true;
		} else {
			throw new EditarCreditoDeportivoException(" El credito Deportivo no se puede editar");

		}

	}

	/**
	 * metodo para eliminar un credito deportivo
	 *
	 * @param codigo
	 * @return
	 * @throws CreditoDeportivoEliminarException
	 */
	public void eliminarCreditoDeportivo(String codigo) throws CreditoDeportivoEliminarException {

		if (codigo == null || codigo.isEmpty()) {
			throw new CreditoDeportivoEliminarException(
					"El codigo del CreditoDeportivo enviado es nulo o vacio, no se puede continuar el proceso.");
		}

		CreditoDeportivo creditoDeportivoConsultado = (CreditoDeportivo) buscarCreditoDeportivo(codigo);

		if (creditoDeportivoConsultado == null) {
			throw new CreditoDeportivoEliminarException(
					"El Credito Deportivo con codigo" + codigo + " no existe para poder eliminarlo.");
		}
		getListaCreditosDeportivos().remove(creditoDeportivoConsultado);

	}

	/**
	 * autor alejo
	 *
	 * @param Metodo
	 *            para encontrar un credito deportivo
	 * @param codigo
	 * @return creditoEncontrado
	 */
	public CreditoDeportivo buscarCreditoDeportivo(String codigo) {
		for (CreditoDeportivo creditoDeportivo : listaCreditosDeportivos) {
			if (creditoDeportivo.getCodigo().equalsIgnoreCase(codigo)) {
				return creditoDeportivo;

			}
		}
		return null;

	}

	// CRUD CREDITOS
	// ACADEMICOS-----------------------------------------------------------------------------------------------------

	/**
	 * metodo para agregagar un credito academico
	 *
	 * @param nombre,codigo,cupoMaximo,duracionCredito,instructor,horario,lugar
	 * @return
	 * @throws CreacionCreditoAcademicoException
	 */
	public CreditoAcademico agregarCreditoAcademico(String nombre, String codigo, String cupoMaximo, String duracionCredito, Instructor instructor, Horario horario, Lugar lugar,String homologacion )
			throws CreacionCreditoAcademicoException {

		if (buscarCreditoAcademico(codigo) == null) {
			CreditoAcademico creditoAcademico = new CreditoAcademico();
			// settear informacion
			creditoAcademico.setNombre(nombre);
			creditoAcademico.setCodigo(codigo);
			creditoAcademico.setCupoMaximo(cupoMaximo);
			creditoAcademico.setDuracionCredito(duracionCredito);
			creditoAcademico.setInstructor(instructor);
			creditoAcademico.setHorario(horario);
			creditoAcademico.setLugar(lugar);
			creditoAcademico.setHomologacion(homologacion);

			// Agregar credito a la lista

			this.listaCreditosAcademicos.add(creditoAcademico);
			return creditoAcademico;
		} else {
			throw new CreacionCreditoAcademicoException("El credito academico ya existe en la base de datos");
		}

	}

	/**
	 * autor edisson medina Metodo para actualizar la informacion el credito
	 * academico
	 *
	 * @param nombre,codigo,cupoMaximo,
	 *            duracionCredito, instructor,horario,lugar
	 * @return
	 * @throws EditarCreditoAcademicoException
	 */
	public boolean actualizarCreditoAcademico(String codigoActual, String nombre, String codigo, String cupoMaximo,
			String duracionCredito, Instructor instructor, Horario horario, Lugar lugar)
			throws EditarCreditoAcademicoException {

		CreditoAcademico creditoAcademicoActualizar = null;

		creditoAcademicoActualizar = (CreditoAcademico) buscarCreditoAcademico(codigoActual);

		if (creditoAcademicoActualizar != null) {

			creditoAcademicoActualizar.setNombre(nombre);
			creditoAcademicoActualizar.setCodigo(codigo);
			creditoAcademicoActualizar.setCupoMaximo(cupoMaximo);
			creditoAcademicoActualizar.setDuracionCredito(duracionCredito);
			creditoAcademicoActualizar.setInstructor(instructor);
			creditoAcademicoActualizar.setHorario(horario);
			creditoAcademicoActualizar.setLugar(lugar);

			return true;
		} else {
			throw new EditarCreditoAcademicoException(" El instructor no se puede editar");

		}

	}

	/**
	 * Metodo para eliminar un credito Academico
	 *
	 * @param codigo
	 * @return
	 * @throws CreditoAcademicoEliminarException
	 */
	public void eliminarCreditoAcademico(String codigo) throws CreditoAcademicoEliminarException {
		if (codigo == null || codigo.isEmpty()) {
			throw new CreditoAcademicoEliminarException(
					"El codigo del Credito Academico enviado es nulo o vacio, no se puede continuar el proceso.");
		}

		CreditoAcademico creditoAcademicoConsultado = (CreditoAcademico) buscarCreditoAcademico(codigo);

		if (creditoAcademicoConsultado == null) {
			throw new CreditoAcademicoEliminarException(
					"El Credito Academico con codigo" + codigo + " no existe para poder eliminarlo.");
		}
		getListaCreditosDeportivos().remove(creditoAcademicoConsultado);

	}

	/**
	 * autor:Edisson Medina
	 *
	 * Metodo para encontrar un credito academico
	 *
	 * @param codigo
	 * @return creditoEncontrado
	 */
	public CreditoAcademico buscarCreditoAcademico(String codigo) {
		for (CreditoAcademico creditoAcademico : listaCreditosAcademicos) {
			if (creditoAcademico.getCodigo().equalsIgnoreCase(codigo)) {
				return creditoAcademico;
			}
		}
		return null;

	}

	// CRUD CREDITOS CULTURALES

	// CRUD
	// creditosCulturales-------------------------------------------------------------------------------------------------
	/**
	 * Metodo para crear un credito cultural
	 *
	 * @param nombre,codigo,cupoMaximo,duracionCredito,
	 *            listaEstudiantes,instructor,horario,lugar,asistencia
	 * @return
	 * @throws CreacionCreditoDeportivoException
	 */
	public CreditoCultural agregarCreditoCultural(String nombre, String codigo, String cupoMaximo,
			String duracionCredito, Instructor instructor, Horario horario, Lugar lugar, double costo)
			throws CreacionCreditoCulturalException {

		if (buscarCreditoCultural(codigo) == null) {
			CreditoCultural creditoCultural = new CreditoCultural();
			// Settear informacion al estudiante en el credito
			creditoCultural.setNombre(nombre);
			creditoCultural.setCodigo(codigo);
			creditoCultural.setCupoMaximo(cupoMaximo);
			creditoCultural.setDuracionCredito(duracionCredito);
			creditoCultural.setInstructor(instructor);
			creditoCultural.setHorario(horario);
			creditoCultural.setLugar(lugar);
			creditoCultural.setCosto(costo);

			// Agregar credito a la lista
			this.listaCreditosCulturales.add(creditoCultural);
			return creditoCultural;
		} else {
			throw new CreacionCreditoCulturalException(
					" El credito cultural ya se encuentra creado en la base de datos ");
		}

	}

	/**
	 * metodo para actulizar la informacion de un credito cultural
	 *
	 * @param nombre,
	 *            codigo,cupoMaximo,duracionCredito,instructor,horario,lugar,costo
	 * @return
	 * @throws EditarCulturalException
	 */
	public boolean actualizarCreditoCultural(String codigoActual, String nombre, String codigo, String cupoMaximo,
			String duracionCredito, Instructor instructor, Horario horario, Lugar lugar, double costo)
			throws EditarCreditoCulturalException {

		CreditoCultural creditoCulturalActualizar = null;

		creditoCulturalActualizar = (CreditoCultural) buscarCreditoCultural(codigoActual);

		if (creditoCulturalActualizar != null) {

			// settear informacion
			creditoCulturalActualizar.setNombre(nombre);
			creditoCulturalActualizar.setCodigo(codigo);
			creditoCulturalActualizar.setCupoMaximo(cupoMaximo);
			creditoCulturalActualizar.setDuracionCredito(duracionCredito);
			creditoCulturalActualizar.setInstructor(instructor);
			creditoCulturalActualizar.setHorario(horario);
			creditoCulturalActualizar.setLugar(lugar);
			creditoCulturalActualizar.setCosto(costo);

			return true;
		} else {
			throw new EditarCreditoCulturalException("El Credito cultural no se puede Actualizar");

		}

	}

	/**
	 * metodo para eliminar un credito cultural
	 *
	 * @param codigo
	 * @return
	 * @throws CreditoCulturalEliminarException
	 */
	public void eliminarCreditoCultural(String codigo) throws EliminarCreditoCulturalException {

		if (codigo == null || codigo.isEmpty()) {
			throw new EliminarCreditoCulturalException(
					"El codigo del Credito Cultural enviado es nulo o vacio, no se puede continuar el proceso.");
		}

		CreditoCultural creditoCulturalConsultado = (CreditoCultural) buscarCreditoCultural(codigo);

		if (creditoCulturalConsultado == null) {
			throw new EliminarCreditoCulturalException(
					"El Credito Cultural con codigo" + codigo + " no existe para poder eliminarlo.");
		}
		getListaCreditosCulturales().remove(creditoCulturalConsultado);

	}

	/**
	 * autor Edisson
	 *
	 * @param Metodo
	 *            para encontrar un credito Cultural
	 * @param codigo
	 * @return creditoEncontrado
	 */
	public CreditoCultural buscarCreditoCultural(String codigo) {
		for (CreditoCultural creditoCultural : listaCreditosCulturales) {
			if (creditoCultural.getCodigo().equalsIgnoreCase(codigo)) {
				return creditoCultural;

			}
		}
		return null;

	}


	// CRUD
			// LUGAR-------------------------------------------------------------------------------------------
			// agregar lugar
			/**
			 * autor Edisson
			 * metodo para crear un lugar
			 *
			 * @param nombre
			 * @param codigo
			 * @return
			 * @throws CreacionInstructorException
			 */
			public void agregarLugar(String nombre, String codigo) throws CreacionLugarException {
				if (buscarLugar(codigo) == null) {
					Lugar lugarNuevo = new Lugar();
					// Settear informacion al instructor
					lugarNuevo.setNombre(nombre);
					lugarNuevo.setCodigo(codigo);
					this.listaLugares.add(lugarNuevo);

				} else {
					throw new CreacionLugarException(" El lugar ya se encuentra creado ");
				}
			}


			/**
			 * autor : edisson
			 *
			 * metodo para modificar la informacion del lugar a la lista de
			 * estudiantes
			 *
			 * @param nombre,
			 *            codigo
			 * @return
			 * @throws EditarInstructorException
			 */
			public boolean actualizarLugar(String codigoActual,String nombre, String codigo) throws EditarInstructorException {

				Lugar lugarActualizar = null;

				lugarActualizar = buscarLugar(codigoActual);

				if (lugarActualizar != null) {

					// Settear informacion nueva al lugar
					lugarActualizar.setNombre(nombre);
					lugarActualizar.setCodigo(codigo);

					return true;
				} else {
					throw new EditarInstructorException(" El lugar no se puede editar");
				}

			}

			/**
			 * metodo que te permite eliminar un lugar
			 * @param codigo
			 * @throws InstructorEliminarException
			 * @throws EliminacionInstructorException
			 */

			public void eliminarLugar(String codigo) throws LugarEliminarException, InstructorEliminarException {
				if (codigo == null || codigo.isEmpty()) {
					throw new LugarEliminarException(
							"El codigo del Instrcutor enviado es nulo o vacio, no se puede continuar el proceso.");
				}

				Lugar lugarConsultado = buscarLugar(codigo);

				if (lugarConsultado == null) {
					throw new InstructorEliminarException(
							"El Lugar con codigo" + codigo + " no existe para poder eliminarlo.");
				}
				getListaLugares().remove(lugarConsultado);

			}

			/**
			 * autor Edisson metodo para buscar un lugar por codigo
			 *
			 * @param codigo
			 * @return
			 */
			public Lugar buscarLugar(String codigo) {
				for (Lugar lugar : listaLugares) {
					if (lugar.getCodigo().equalsIgnoreCase(codigo)) {
						return lugar;

					}

				}
				return null;
			}

			// CRUD
			// HORARIOS-------------------------------------------------------------------------------------------
			// agregar Horario
			/**
			 * autor Edisson
			 * metodo para crear un lugar
			 *
			 * @param nombre
			 * @param codigo

			 * @throws CreacionHorarioException
			 * @throws CreacionLugarException
			 */
			public void agregarHorario(String codigo, String horaInicio, String horaFinal) throws CreacionHorarioException {
				if (buscarHorario(codigo) == null) {
					Horario horarioNuevo = new Horario();
					// Settear informacion al horario
					horarioNuevo.setCodigo(codigo);
					horarioNuevo.setHoraInicio(horaInicio);
					horarioNuevo.setHoraFinal(horaFinal);

					this.listaHorarios.add(horarioNuevo);

				} else {
					throw new CreacionHorarioException(" El horario ya se encuentra creado ");
				}
			}


			/**
			 * autor : edisson
			 *
			 * metodo para modificar la informacion del lugar a la lista de
			 * estudiantes
			 *
			 * @param nombre,
			 *            codigo
			 * @return
			 * @throws EditarInstructorException
			 * @throws EditarHorarioException
			 */
			public boolean actualizarHorario(String codigoActual, String codigo, String horaInicio, String horaFinal) throws EditarHorarioException{

				Horario horarioActualizar = null;

				horarioActualizar = buscarHorario(codigoActual);

				if (horarioActualizar != null) {

					// Settear informacion nueva al lugar
					horarioActualizar.setCodigo(codigo);
					horarioActualizar.setHoraInicio(horaInicio);
					horarioActualizar.setHoraFinal(horaFinal);

					return true;
				} else {
					throw new EditarHorarioException(" El horario no se puede editar");
				}

			}

			/**
			 * metodo que te permite eliminar un Horario
			 * @param codigo
			 * @throws EliminacionInstructorException
			 */

			public void eliminarHorario(String codigo) throws HorarioEliminarException {
				if (codigo == null || codigo.isEmpty()) {
					throw new HorarioEliminarException(
							"El codigo del Horario enviado es nulo o vacio, no se puede continuar el proceso.");
				}

				Horario horarioConsultado = buscarHorario(codigo);

				if (horarioConsultado == null) {
					throw new HorarioEliminarException(
							"El Horario con codigo" + codigo + " no existe para poder eliminarlo.");
				}
				getListaHorarios().remove(horarioConsultado);

			}

			/**
			 * autor Edisson metodo para buscar un horario por codigo
			 *
			 * @param codigo
			 * @return
			 */
			public Horario buscarHorario(String codigo) {
				for (Horario horario : listaHorarios) {
					if (horario.getCodigo().equalsIgnoreCase(codigo)) {
						return horario;

					}

				}
				return null;
			}


	/**
	 * alejo
	 *
	 * obtener la posicion del instructor en el arreglo
	 * @param nombre
	 * @return
	 */
	public int obtenerPosicionInstructor(String nombre) {
		int posicionCategoria = -1;

		for (int i = 0; i < listaInstructores.size(); i++) {
			Instructor instructor = listaInstructores.get(i);
			if (instructor.getNombre().equals(nombre)){
				posicionCategoria= i;
			}


		}
		return posicionCategoria;
	}

	/**
	 * alejo
	 *
	 * obtener la posicion del lugar en el arreglo
	 * @param nombre
	 * @return
	 */
	public int obtenerPosicionLugar(String nombre) {
		int posicionCategoria = -1;

		for (int i = 0; i < listaLugares.size(); i++) {
			Lugar lugar = listaLugares.get(i);
			if (lugar.getNombre().equals(nombre)){
				posicionCategoria= i;
			}


		}
		return posicionCategoria;
	}


	/**
	 * alejo
	 *
	 * obtener la posicion del horario en el arreglo
	 * @param nombre
	 * @return
	 */
	public int obtenerPosicionHorario(String codigo) {
		int posicionCategoria = -1;

		for (int i = 0; i < listaHorarios.size(); i++) {
			Horario horario = listaHorarios.get(i);
			if (horario.getCodigo().equals(codigo)){
				posicionCategoria= i;
			}


		}
		return posicionCategoria;
	}
	// Metodos buscar
	// ---------------------------------------------------------------------------------------------------------



	/**
	 * autor alejo
	 * metodo para obtener nombres para utilizar en el combo de lugares
	 * @return
	 */
	public String[] obtenerNombresLugaresExistentes() {
		String[] arregloLugares = new String [listaLugares.size()];

		for (int i = 0; i < listaLugares.size(); i++) {
			Lugar lugar = listaLugares.get(i);
			arregloLugares[i] = lugar.getNombre();

		}
		return arregloLugares ;
	}

	/**
	 * autor alejo
	 * metodo para obtener nombres para utilizar en el combo de lugares
	 * @return
	 */
	public String[] obtenerNombresHorariosExistentes() {
		String[] arregloLugares = new String [listaLugares.size()];

		for (int i = 0; i < listaLugares.size(); i++) {
			Lugar lugar = listaLugares.get(i);
			arregloLugares[i] = lugar.getNombre();

		}
		return arregloLugares ;
	}

	public Instructor obtenerInstructor(String codigo) {

		for (Instructor instructor : listaInstructores) {
			if(instructor.existeInstructor(codigo)){
				return instructor;
			}

		}
		return null;
	}
}
