package co.uniquindio.proyectofinal.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


import co.uniquindio.proyectofinal.model.*;

public class ServidorService {

	private int puerto = 9999;
	private int puertoServerObjeto = 9991;
	private Socket socketComunicacion;
	private Socket socketTransferenciaObjeto;
	private DataInputStream flujoEntradaComunicacion;
	private DataOutputStream flujoSalidaComunicacion;
	private ObjectInputStream flujoEntradaObjeto;
	private ObjectOutputStream flujoSalidaObjeto;

	public Administrador cargarPersistencia() throws ClassNotFoundException, IOException {
		crearConexion();
		solicitarInformacionPersitencia();
		Administrador admin = leerObjetoPresitencia();
		close();

		return admin;
	}

	public void guardarPersistencia(Administrador admin, boolean esRespaldo) throws ClassNotFoundException, IOException {
		crearConexion();
		solicitarGuardarPersitencia(esRespaldo);
		enviarObjeto(admin);
		close();
	}

	/**
	 * autor alejo
	 * Metodo que recibe el objeto Principal
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private Administrador leerObjetoPresitencia()throws ClassNotFoundException, IOException {
		Administrador admin = (Administrador) flujoEntradaObjeto.readObject();
		System.out.println(" Objeto Principal Recibido");
		return admin;
	}

	private void solicitarInformacionPersitencia() {
		try {
			flujoSalidaComunicacion.writeInt(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    /**
     * autor alejo
     * Metodo que permite cerrar las comunicaciones
     * @throws IOException
     */
	private void close() throws IOException{
		flujoEntradaComunicacion.close();
		flujoSalidaComunicacion.close();
		flujoEntradaObjeto.close();
		flujoSalidaObjeto.close();
		socketComunicacion.close();
		socketTransferenciaObjeto.close();

	}

	/**
	 * autor alejo
	 * metodo para crear la conexion
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private void crearConexion() throws UnknownHostException, IOException {
		socketComunicacion = new Socket("localhost",puerto);
		socketTransferenciaObjeto = new Socket("localhost",puertoServerObjeto);


		flujoEntradaComunicacion = new DataInputStream(socketComunicacion.getInputStream());
		flujoSalidaComunicacion = new DataOutputStream(socketComunicacion.getOutputStream());

		flujoEntradaObjeto = new ObjectInputStream(socketTransferenciaObjeto.getInputStream());
		flujoSalidaObjeto= new ObjectOutputStream(socketTransferenciaObjeto.getOutputStream());
	}

	private void enviarObjeto(Administrador admin) {
		try {
			flujoSalidaObjeto.writeObject(admin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void solicitarGuardarPersitencia(boolean esRespaldo) {
		try {
			if(!esRespaldo){
				flujoSalidaComunicacion.writeInt(2);
			}else{
				flujoSalidaComunicacion.writeInt(3);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Usuario login(String nombreUsuario, String contrasena) throws UnknownHostException, IOException, ClassNotFoundException {
		crearConexion();
		solicitarLogin(nombreUsuario, contrasena);
		Usuario usuario = leerObjetoLogin();
		close();
		return usuario;
	}

	private Usuario leerObjetoLogin() throws ClassNotFoundException, IOException {
		Usuario usuario = (Usuario) flujoEntradaObjeto.readObject();
		System.out.println("Objeto login");
		return usuario;
	}

	private void solicitarLogin(String nombreUsuario, String contrasena) {
		try {
			Usuario usuario  = new Usuario();
			usuario.setNombreUsuario(nombreUsuario);
			usuario.setContrasena(contrasena);

			flujoSalidaComunicacion.writeInt(4);
			flujoSalidaObjeto.writeObject(usuario);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
