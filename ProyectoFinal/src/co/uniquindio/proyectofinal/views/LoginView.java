package co.uniquindio.proyectofinal.views;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import co.uniquindio.proyectofinal.controllers.*;
import co.uniquindio.proyectofinal.exceptions.AdminException;
import co.uniquindio.proyectofinal.exceptions.ConsultaCreditoException;

import org.eclipse.swt.widgets.Button;
public class LoginView extends ApplicationWindow {
	LoginController loginController = new LoginController();
	private Text textNombreUsuario;
	private Text textContrasena;

	/**
	 * Create the application window.
	 */
	public LoginView() {
		super(null);
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(null);

		textNombreUsuario = new Text(container, SWT.BORDER);
		textNombreUsuario.setBounds(229, 184, 147, 21);

		textContrasena = new Text(container, SWT.BORDER);
		textContrasena.setBounds(229, 244, 147, 21);

		Label lblUsername = new Label(container, SWT.NONE);
		lblUsername.setBounds(247, 163, 104, 15);
		lblUsername.setText("Nombre de usuario");

		Label lblContrasea = new Label(container, SWT.NONE);
		lblContrasea.setBounds(272, 223, 73, 15);
		lblContrasea.setText("Contrase\u00F1a");

		Button btnIniciarSesion = new Button(container, SWT.NONE);
		btnIniciarSesion.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					String nombreUsuario = textNombreUsuario.getText();
					String contrasena = textContrasena.getText();
					loginController.login(nombreUsuario, contrasena);

					ContenedorView contenedorView = new ContenedorView();
					contenedorView.open();
					parent.setVisible(false);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
	//			} catch (ConsultaCreditoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (AdminException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensaje de error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnIniciarSesion.setBounds(270, 271, 75, 25);
		btnIniciarSesion.setText("Iniciar Sesion");

		Label lblProyectoFinal = new Label(container, SWT.NONE);
		lblProyectoFinal.setBounds(259, 24, 73, 15);
		lblProyectoFinal.setText("Proyecto Final");

		return container;
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("ProyectoFinal");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(624, 565);
	}
}
