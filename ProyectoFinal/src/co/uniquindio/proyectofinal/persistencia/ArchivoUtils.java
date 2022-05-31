package co.uniquindio.proyectofinal.persistencia;

import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import co.uniquindio.proyectofinal.model.Administrador;



/**
 * <b>Descripcion:<b> Clase que determina la utilidad del
 * @author Alejo
 */
public class ArchivoUtils {

	/**
	 * Metodo encargado de guardar el archivo
	 * @author Alejo
	 *
	 * @param rutaArchivo ruta del archivo donde esta ubicado
	 * @param contenido contenido a guardar en el archivo
	 * @param flagSobreEscribir indicador sobre sobreescribir
	 * @throws IOException excepcion que puede lanzar el metodo
	 */
	public static void guardarArchivo(String rutaArchivo, String contenido, boolean flagSobreEscribir) throws IOException {

		FileWriter fileWriter = new FileWriter(rutaArchivo, flagSobreEscribir);

		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		fileWriter.write(contenido);

		bufferedWriter.close();

		fileWriter.close();
	}

	/**
	 * Metodo encargado de leer el archivo
	 * @author Alejo
	 *
	 * @param rutaArchivo ruta del archivo donde esta ubicado
	 * @return contenido contenido del archivo
	 * @throws IOException excepcion que puede lanzar el metodo
	 */
	public static ArrayList<String> leerArchivo(String rutaArchivo) throws IOException {

		ArrayList<String> contenido = new ArrayList<>();

		FileReader fileReader = new FileReader(rutaArchivo);

		BufferedReader bufferedReader = new BufferedReader(fileReader);

		String linea = " ";

		while ((linea = bufferedReader.readLine()) != null) {
			contenido.add(linea);
		}

		bufferedReader.close();

		return contenido;
	}

	/**
	 * Metodo encargado de guardar el registro de log
	 * @author Alejo
	 *
	 * @param rutaArchivo ruta del archivo donde esta ubicado
	 * @param mensajeLog mensaje de log a mostrar
	 * @param nivel nivel del log
	 * @param accion accion realizada
	 */
	public static void guardarRegistroLog(String rutaArchivo, String mensajeLog, int nivel, String accion) {

		Logger LOGGER = Logger.getLogger(accion);

		FileHandler fileHandler = null;
		Administrador admin = null;

		try {

			//Obtener usuarioSesion del sistema
			Propiedades propiedades = new Propiedades();
			String nombreUsuario = propiedades.properties.getProperty("nombreUsuario");


			fileHandler = new FileHandler(rutaArchivo, true);

			fileHandler.setFormatter(new SimpleFormatter());

			LOGGER.addHandler(fileHandler);

			switch (nivel) {
			case 1:
				LOGGER.log(Level.INFO, accion + "," + mensajeLog + " - Realizado por: " + nombreUsuario);
				break;
			case 2:
				LOGGER.log(Level.WARNING, accion + "," + mensajeLog + " - Realizado por: " + nombreUsuario);
				break;
			case 3:
				LOGGER.log(Level.SEVERE, accion + "," + mensajeLog + " - Realizado por: " + nombreUsuario);
				break;

			default:
				break;
			}

		} catch (SecurityException e) {
			LOGGER.log(Level.SEVERE, accion + "," + e.getMessage() + " - Realizado por: " + admin.getUsuarioSesion().getNombreUsuario());
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, accion + "," + e.getMessage() + " - Realizado por: " + admin.getUsuarioSesion().getNombreUsuario());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, accion + "," + e.getMessage() + " - Realizado por: " + admin.getUsuarioSesion().getNombreUsuario());
			e.printStackTrace();
		} finally {
			fileHandler.close();
		}
	}


	/**
	 * Metodo encargado de serializar el objeto
	 * @author Alejo
	 *
	 * @param rutaArchivo rutaArchivo del archivo
	 * @param objecto objeto que se desea serializar
	 * @param isGuardarRespaldo si se desea crear el archivo en la carpeta de respaldo
	 * @throws IOException excepcion que puede lanzar el metodo
	 */
	public static void serializarObjeto(String rutaArchivo, Object objeto, boolean isGuardarRespaldo)
			throws IOException {
		ObjectOutputStream oss = null;

		try {

			//Se agrega la fecha del sistema al nombre del archivo en caso que se deba guardar en el respaldo
			if (isGuardarRespaldo) {
				String[] parts = rutaArchivo.split("\\.");
				rutaArchivo = parts[0]+cargarFechaSistema()+parts[1];
			}

			oss = new ObjectOutputStream(new FileOutputStream(rutaArchivo));
			oss.writeObject(objeto);

		} catch (Exception e) {
			throw e;
		} finally {
			if (oss != null) {
				oss.close();
			}
		}
	}

	/**
	 * Metodo encargado de deserializar el objeto a partir del nombre del archivo donde esta ubicado
	 * @author Alejo
	 *
	 * @param rutaArchivo ruta del archivo
	 * @return objeto deserializado
	 * @throws Exception  excepcion que puede lanzar el metodo
	 */
	public static Object deserializarObjeto(String rutaArchivo) throws Exception {

		Object aux;
		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(new FileInputStream(rutaArchivo));

			aux = ois.readObject();
		} catch (Exception e) {
			throw e;
		} finally {
			if (ois != null) {
				ois.close();
			}
		}

		return aux;
	}


	/**
	 * Metodo encargado de guardar el recurso en un archivo xml
	 * @author Alejo
	 *
	 * @param rutaArchivo  ruta del archivo
	 * @param objecto objeto que se desea guardar en el xml
	 * @param isGuardarRespaldo si se desea crear el archivo en la carpeta de respaldo
	 * @throws Exception excepcion que puede lanzar el metodo
	 */
	public static void guardarRecursoXML(String rutaArchivo, Object objecto, boolean isGuardarRespaldo)
			throws Exception {
		XMLEncoder codificadorXML;

		//Se agrega la fecha del sistema al nombre del archivo en caso que se deba guardar en el respaldo
		if (isGuardarRespaldo) {
			String[] parts = rutaArchivo.split("\\.");
			rutaArchivo = parts[0]+cargarFechaSistema()+parts[1];
		}

		codificadorXML = new XMLEncoder(new FileOutputStream(rutaArchivo));
		codificadorXML.setPersistenceDelegate(LocalDate.class,
				new PersistenceDelegate() {
			@Override
			protected Expression instantiate(Object localDate, Encoder encdr) {
				return new Expression(localDate,
						LocalDate.class,
						"parse",
						new Object[]{localDate.toString()});
			}
		});
		codificadorXML.writeObject(objecto);
		codificadorXML.close();
	}

	/**
	 * Metodo encargado de cargar los recursos a partir de un xml
	 * @author Alejo
	 *
	 * @param rutaArchivo ruta del archivo
	 * @return objeto obtenido del xml
	 * @throws Exception excepcion que puede lanzar el metodo
	 */
	public static Object cargarRecursosXML(String rutaArchivo) throws Exception {

		XMLDecoder decodificadorXML;
		Object objetoXML;

		decodificadorXML = new XMLDecoder(new FileInputStream(rutaArchivo));

		objetoXML = decodificadorXML.readObject();

		decodificadorXML.close();

		return objetoXML;
	}

	 /**
	  * Metodo encargado de cargar la fecha de sistema
	  * @author Alejo
	 * @return
	 */
	private static String cargarFechaSistema() {
	        String diaN = "";
	        String mesN = "";
	        String anioN = "";
	        Calendar cal1 = Calendar.getInstance();
	        int  dia = cal1.get(Calendar.DATE);
	        int mes = cal1.get(Calendar.MONTH)+1;
	        int anio = cal1.get(Calendar.YEAR);
	        int hora = cal1.get(Calendar.HOUR);
	        int minuto = cal1.get(Calendar.MINUTE);
	        int segundo = cal1.get(Calendar.SECOND);
	        if(dia < 10){
	            diaN+="0"+dia;
	        }
	        else{
	            diaN+=""+dia;
	        }
	        if(mes < 10){
	            mesN+="0"+mes;
	        }
	        else{
	            mesN+=""+mes;
	        }
	        //        fecha_Actual+= a�o+"-"+mesN+"-"+ diaN;
	        //        fechaSistema = a�o+"-"+mesN+"-"+diaN+"-"+hora+"-"+minuto;
	        String fechaSistema = "_"+diaN+""+mesN+""+anio+"_"+hora+"_"+minuto+"_"+segundo+".";
	        //        horaFechaSistema = hora+"-"+minuto;
			return fechaSistema;
	    }

}
