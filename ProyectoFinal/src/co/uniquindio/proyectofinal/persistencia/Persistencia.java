package co.uniquindio.proyectofinal.persistencia;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


import co.uniquindio.proyectofinal.persistencia.*;
import co.uniquindio.proyectofinal.model.Administrador;
import co.uniquindio.proyectofinal.model.Estudiante;
import co.uniquindio.proyectofinal.model.Instructor;

public class Persistencia {

	public static final String RUTA_ARCHIVO_ESTUDIANTES = "C:/td1/persistencia/archivos/estudiante.txt";
	public static final String RUTA_ARCHIVO_INSTRUCTORES = "C:/td1/persistencia/archivos/instructor.txt";
	public static final String RUTA_ARCHIV_LOG = "C:/td1/persistencia/log/proyectoFinal.txt";
	public static final String RUTA_ARCHIVO_MODELO_PROYECTO_BINARIO = "C:/td1/persistencia/model.dat";
	public static final String RUTA_ARCHIVO_MODELO_PROYECTO_XML = "C:/td1/persistencia/model.xml";
	public static final String RUTA_ARCHIVO_RESPALDO_PROYECTO_BINARIO = "C:/td1/persistencia/respaldo/model.dat";
	public static final String RUTA_ARCHIVO_RESPALDO_PROYECTO_XML = "C:/td1/persistencia/respaldo/model.xml";

	/**
	 * Metodo que determina de guardar el registro log
	 *
	 * @param mensaje
	 * @param nivel
	 * @param accion
	 */
	public static void guardarRegistroLog(String mensaje, int nivel, String accion) {

		ArchivoUtils.guardarRegistroLog(RUTA_ARCHIV_LOG, mensaje, nivel, accion);
	}

	/**
	 * Metodo encargado de agregar un insteuctor al archivo
	 *
	 * @param admin
	 * @throws IOException
	 */
	public static void agregarInstructor(Administrador admin) throws IOException {
		String contenido = "";

		ArrayList<Instructor> listaInstructores = admin.getListaInstructores();
		for (Instructor instructor : listaInstructores) {
			contenido += instructor.toString();

		}
		ArchivoUtils.guardarArchivo(RUTA_ARCHIVO_INSTRUCTORES, contenido, false);

	}

	/**
	 * Metodo encargado de agregar un estudiante al archivo
	 *
	 * @param admin
	 * @throws IOException
	 */
	public static void agregarEstudiante(Administrador admin) throws IOException {
		String contenido = "";

		ArrayList<Estudiante> listaEstudiantes = admin.getListaEstudiantes();
		for (Estudiante estudiante : listaEstudiantes) {
			contenido += estudiante.toString();

		}
		ArchivoUtils.guardarArchivo(RUTA_ARCHIVO_ESTUDIANTES, contenido, false);

	}

	public static void cargarInstructores(Administrador admin) throws IOException {

		ArrayList<String> contenido = new ArrayList<>();

		contenido = ArchivoUtils.leerArchivo(RUTA_ARCHIVO_INSTRUCTORES);
		admin.getListaInstructores().clear();

		for (String lineaInstructor : contenido) {

			String[] split = lineaInstructor.split("@@");

			Instructor instructor = new Instructor();

			if (!split[0].equalsIgnoreCase("null") && split[0] != null) {
				instructor.setNombre(split[0]);
			}

			if (!split[1].equalsIgnoreCase("null") && split[1] != null) {
				instructor.setCodigo(split[1]);

				// if(!split[2].equalsIgnoreCase( "null") && split[2] != null){
				// instructor.setFechaCreacion(LocalDate.parse(split[2]));
				// }
				//
				// if(!split[3].equalsIgnoreCase( "null") && split[3] != null){
				// instructor.setFechaModificacion(LocalDate.parse(split[3]));
				// }

				admin.getListaInstructores().add(instructor);
			}
		}
	}

}