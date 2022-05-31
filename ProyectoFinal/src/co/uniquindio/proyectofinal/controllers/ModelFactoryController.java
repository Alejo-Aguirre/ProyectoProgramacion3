package co.uniquindio.proyectofinal.controllers;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import co.uniquindio.proyectofinal.exceptions.AdminException;
import co.uniquindio.proyectofinal.exceptions.CreacionCreditoAcademicoException;
import co.uniquindio.proyectofinal.exceptions.CreacionCreditoDeportivoException;
import co.uniquindio.proyectofinal.exceptions.CreacionEstudianteException;
import co.uniquindio.proyectofinal.exceptions.CreacionInstructorException;
import co.uniquindio.proyectofinal.exceptions.CreditoAcademicoEliminarException;
import co.uniquindio.proyectofinal.exceptions.EditarCreditoAcademicoException;
import co.uniquindio.proyectofinal.exceptions.EditarCreditoDeportivoException;
import co.uniquindio.proyectofinal.exceptions.EditarEstudianteException;
import co.uniquindio.proyectofinal.exceptions.EditarHorarioException;
import co.uniquindio.proyectofinal.exceptions.EditarInstructorException;
import co.uniquindio.proyectofinal.exceptions.InstructorEliminarException;
import co.uniquindio.proyectofinal.model.Administrador;
import co.uniquindio.proyectofinal.model.Estudiante;
import co.uniquindio.proyectofinal.model.Horario;
import co.uniquindio.proyectofinal.model.Instructor;
import co.uniquindio.proyectofinal.model.Lugar;
import co.uniquindio.proyectofinal.model.Usuario;
import co.uniquindio.proyectofinal.persistencia.ArchivoUtils;
import co.uniquindio.proyectofinal.persistencia.Persistencia;
import co.uniquindio.proyectofinal.persistencia.Propiedades;
import co.uniquindio.proyectofinal.services.*;
import javafx.beans.property.IntegerProperty;

public class ModelFactoryController implements Runnable {

	private Administrador admin;
	private String mensaje;
	private int nivel;
	private String accion;
	Thread hiloServicio_guardarXML;
	Thread hiloServicio_guardarLog;
	ServidorService servidorService = new ServidorService();

	// ---------- singlenton ----------//
	private static class SinglentonHolder {
		// El constructor de singlento puede ser llamdao desde aqui al ser
		// protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// Metodo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SinglentonHolder.eINSTANCE;
	}

	public ModelFactoryController() {

		try{
			admin = servidorService.cargarPersistencia();

			admin.setNombre("BIENESTAR");
			servidorService.guardarPersistencia(admin, true);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	private void inicializarDatos() {
//		admin = new Administrador();
//
//	}

	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	public Usuario login(String nombreUsuario, String contrasena)
			throws UnknownHostException, ClassNotFoundException, IOException, AdminException {
		Usuario usuario = servidorService.login(nombreUsuario, contrasena);

		if (usuario == null) {
			throw new AdminException("El usuario No existe");
		}

		return usuario;
	}

	// crud instructor
	/**
	 * metodo para crear instructor
	 *
	 * @param nombre
	 * @param codigo
	 * @throws Exception
	 */
	public void crearInstructor(String nombre, String codigo) throws Exception {
		admin.agregarInstructor(nombre, codigo);
		guardarRecursoXML();
	}


	/**
	 * Metodo guardar archivo xml
	 * @author
	 *
	 * @throws Exception
	 */
	public void guardarRecursoXML() throws Exception {
		hiloServicio_guardarXML = new Thread(this);
		hiloServicio_guardarXML.start();
	}

	public boolean editarInstructor(String documentoActual, String nombre, String codigo) {
		try {
			return admin.actualizarInstructor(documentoActual, nombre, codigo);
		} catch (EditarInstructorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void eliminarInstructor(String codigo) throws Exception {
		admin.eliminarIntructor(codigo);

	}

	// crud estudiantes
	public void crearEstudiante(String nombre, String codigo) throws Exception {
		admin.agregarEstudiante(nombre, codigo);

	}

	public boolean editarEstudiante(String documentoActual, String nombre, String codigo) throws Exception {
		return admin.actualizarEstudiante(documentoActual, nombre, codigo);
	}

	public void eliminarEstudiante(String codigo) throws Exception {
		admin.eliminarEstudiante(codigo);

	}

	// crud credito deportivo
	public void CrearCreditoDeportivo(String nombre, String codigo, String cupoMaximo, String duracionCredito,
			Instructor instructor, Horario horario, Lugar lugar, IntegerProperty asistencia)
			throws CreacionCreditoDeportivoException {
		admin.agregarCreditoDeportivo(nombre, codigo, cupoMaximo, duracionCredito, instructor, horario, lugar,
				asistencia);

	}

	public boolean actualizarCreditoDeportivo(String codigoActual, String nombre, String codigo, String cupoMaximo,
			String duracionCredito, Instructor instructor, Horario horario, Lugar lugar, IntegerProperty asistencia)
			throws EditarInstructorException, EditarCreditoDeportivoException {
		return admin.actualizarCreditoDeportivo(codigoActual, nombre, codigo, cupoMaximo, duracionCredito, instructor,
				horario, lugar, asistencia);
	}

	// Crud credito academico

	public void crearCreditoAcademico(String nombre, String codigo, String cupoMaximo, String duracionCredito, Instructor instructor, Horario horario, Lugar lugar,String homologacion) throws CreacionCreditoAcademicoException {
		admin.agregarCreditoAcademico(nombre, codigo, cupoMaximo, duracionCredito, instructor, horario, lugar,homologacion);
		// TODO Auto-generated method stub

	}

	public boolean actualizarCreditoAcademico(String codigoActual, String nombre, String codigo, String cupoMaximo,
			String duracionCredito, Instructor instructor, Horario horario, Lugar lugar)
			throws EditarCreditoAcademicoException {

		return admin.actualizarCreditoAcademico(codigoActual, nombre, codigo, cupoMaximo, duracionCredito, instructor,
				horario, lugar);
	}

	public void eliminarCreditoAcademico(String codigo) throws CreditoAcademicoEliminarException {
		admin.eliminarCreditoAcademico(codigo);

	}

	/// CRUD LUGAR

	public void crearLugar(String nombre, String codigo) throws Exception {
		admin.agregarLugar(nombre, codigo);
	}

	public boolean editarLugar(String codigoActual, String nombre, String codigo) {
		try {
			return admin.actualizarLugar(codigoActual, nombre, codigo);
		} catch (EditarInstructorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void eliminarLugar(String codigo) throws Exception {
		admin.eliminarLugar(codigo);

	}

	/// CRUD HORARIO

	public void agregarHorario(String codigo, String horaInicio, String horaFinal) throws Exception {
		admin.agregarHorario(codigo, horaInicio, horaFinal);
	}

	public boolean actualizarHorario(String codigoActual, String codigo, String horaInicio, String horaFinal) {
		try {
			return admin.actualizarHorario(codigoActual, codigo, horaInicio, horaFinal);
		} catch (EditarHorarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void eliminarHorario(String codigo) throws Exception {
		admin.eliminarHorario(codigo);

	}

	public void guardarRegistroLog(String mensaje, int nivel, String accion) {
		this.mensaje = mensaje;
		this.nivel = nivel;
		this.accion = accion;
		hiloServicio_guardarLog = new Thread(this);
		hiloServicio_guardarLog.start();

	}

	@Override
	public void run() {
		Thread hiloEjecucion = Thread.currentThread();

		if (hiloEjecucion == hiloServicio_guardarXML) {
			try {
				servidorService.guardarPersistencia(admin, false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (hiloEjecucion == hiloServicio_guardarLog) {
			Persistencia.guardarRegistroLog(mensaje, nivel, accion);
		}

	}

	/**
	 * metodo para generar los reportes de los instructores
	 *
	 * @param rutaArchivo
	 * @throws IOException
	 * @throws AdminException
	 */
	public void generarReporteInstructores(String rutaArchivo) throws IOException, AdminException {
		File rutaCarpeta = new File(rutaArchivo);
		String contenido = "";

		if (!rutaCarpeta.isDirectory()) {
			throw new AdminException("La ruta de la carpeta para generar el reporte es incorrecta");
		}

		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String fechaSistema = localDate.format(formatter);

		// Obtener usuarioSesion del sistema
		Propiedades propiedades = new Propiedades();
		String nombreUsuario = propiedades.properties.getProperty("nombreUsuario");

		contenido += "Reporte de Listado de Instructores";
		contenido += "\n";
		contenido += "Fecha: " + fechaSistema;
		contenido += "\n";
		contenido += "Reporte realizado por: " + nombreUsuario;
		contenido += "\n";
		contenido += "\n";

		ArrayList<Instructor> listaInstructores = admin.getListaInstructores();

		for (Instructor instructor : listaInstructores) {

			contenido += instructor.toString();
		}

		rutaArchivo += "/reporteInstructores.txt";

		ArchivoUtils.guardarArchivo(rutaArchivo, contenido, false);

	}

	/**
	 * metodo para generar los reportes de los lugares
	 *
	 * @param rutaArchivo
	 * @throws IOException
	 * @throws AdminException
	 */
	public void generarReporteLugares(String rutaArchivo) throws IOException, AdminException {
		File rutaCarpeta = new File(rutaArchivo);
		String contenido = "";

		if (!rutaCarpeta.isDirectory()) {
			throw new AdminException("La ruta de la carpeta para generar el reporte es incorrecta");
		}

		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String fechaSistema = localDate.format(formatter);

		// Obtener usuarioSesion del sistema
		Propiedades propiedades = new Propiedades();
		String nombreUsuario = propiedades.properties.getProperty("nombreUsuario");

		contenido += "Reporte de Listado de Lugares";
		contenido += "\n";
		contenido += "Fecha: " + fechaSistema;
		contenido += "\n";
		contenido += "Reporte realizado por: " + nombreUsuario;
		contenido += "\n";
		contenido += "\n";

		ArrayList<Lugar> listaLugares = admin.getListaLugares();

		for (Lugar lugar : listaLugares) {

			contenido += lugar.toString();
		}

		rutaArchivo += "/reporteLugares.txt";

		ArchivoUtils.guardarArchivo(rutaArchivo, contenido, false);

	}

	/**
	 * metodo para generar los reportes de horarios
	 *
	 * @param rutaArchivo
	 * @throws IOException
	 * @throws AdminException
	 */
	public void generarReporteHorarios(String rutaArchivo) throws IOException, AdminException {
		File rutaCarpeta = new File(rutaArchivo);
		String contenido = "";

		if (!rutaCarpeta.isDirectory()) {
			throw new AdminException("La ruta de la carpeta para generar el reporte es incorrecta");
		}

		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String fechaSistema = localDate.format(formatter);

		// Obtener usuarioSesion del sistema
		Propiedades propiedades = new Propiedades();
		String nombreUsuario = propiedades.properties.getProperty("nombreUsuario");

		contenido += "Reporte de Listado de Horarios";
		contenido += "\n";
		contenido += "Fecha: " + fechaSistema;
		contenido += "\n";
		contenido += "Reporte realizado por: " + nombreUsuario;
		contenido += "\n";
		contenido += "\n";

		ArrayList<Horario> listaHorarios = admin.getListaHorarios();

		for (Horario horario : listaHorarios) {

			contenido += horario.toString();
		}

		rutaArchivo += "/reporteHorarios.txt";

		ArchivoUtils.guardarArchivo(rutaArchivo, contenido, false);
	}

	/**
	 * metodo para generar el reporte de estudiantes
	 *
	 * @param rutaArchivo
	 * @throws IOException
	 * @throws AdminException
	 */
	public void generarReporteEstudiantes(String rutaArchivo) throws IOException, AdminException {
		File rutaCarpeta = new File(rutaArchivo);
		String contenido = "";

		if (!rutaCarpeta.isDirectory()) {
			throw new AdminException("La ruta de la carpeta para generar el reporte es incorrecta");
		}

		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String fechaSistema = localDate.format(formatter);

		// Obtener usuarioSesion del sistema
		Propiedades propiedades = new Propiedades();
		String nombreUsuario = propiedades.properties.getProperty("nombreUsuario");

		contenido += "Reporte de Listado de Estudiantes";
		contenido += "\n";
		contenido += "Fecha: " + fechaSistema;
		contenido += "\n";
		contenido += "Reporte realizado por: " + nombreUsuario;
		contenido += "\n";
		contenido += "\n";

		ArrayList<Estudiante> listaEstudiantes = admin.getListaEstudiantes();

		for (Estudiante estudiante : listaEstudiantes) {
			contenido += estudiante.toString();
		}

		rutaArchivo += "/reporteEstudiantes.txt";

		ArchivoUtils.guardarArchivo(rutaArchivo, contenido, false);
	}

	public int obtenerPosicionInstructor(String nombre) {
		// TODO Auto-generated method stub
		return admin.obtenerPosicionInstructor(nombre);
	}

	public int obtenerPosicionLugar(String nombre) {
		// TODO Auto-generated method stub
		return admin.obtenerPosicionLugar(nombre);
	}

	public int obtenerPosicionHorario(String codigo) {
		// TODO Auto-generated method stub
		return admin.obtenerPosicionHorario(codigo);
	}

}
