package co.uniquindio.proyectofinal.persistencia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Propiedades {

	public static final String RUTA_ARCHIVO_PROPIEDADES = "C:/td1/persistencia/propiedades.properties";
	public static Properties properties = new Properties();

	public Propiedades(){
		try {
			cargarArchivoPropiedad();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void cargarArchivoPropiedad() throws FileNotFoundException, IOException{
		properties.load(new FileReader(RUTA_ARCHIVO_PROPIEDADES));
	}

	public void guardarArchivoPropiedad() throws IOException {
		properties.store(new FileWriter(RUTA_ARCHIVO_PROPIEDADES), "Propiedades");
	}

}
