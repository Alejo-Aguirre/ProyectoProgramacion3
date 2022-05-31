package co.uniquindio.proyectofinal.views;

import javax.swing.JPanel;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import co.uniquindio.proyectofinal.model.CreditoAcademico;
import co.uniquindio.proyectofinal.persistencia.Propiedades;
import swing2swt.layout.BorderLayout;

import co.uniquindio.proyectofinal.views.*;
public class ContenedorView extends ApplicationWindow {

	/**
	 * Create the application window.
	 */
	public ContenedorView() {
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
		container.setLayout(new BorderLayout(0, 0));

		TabFolder tabFolder = new TabFolder(container, SWT.NONE);

		Propiedades propiedades = new Propiedades();
		String rolUsuario = propiedades.properties.getProperty("rol");

		if(rolUsuario.equals("administrador")){

		//agregar la view del registro de instructor al contenedor
		TabItem tbtmInstructor = new TabItem(tabFolder, SWT.NONE);
		tbtmInstructor.setText("Instructor");

		RegistrarInstructorView instructorView = new RegistrarInstructorView(tabFolder, SWT.NONE);
		tbtmInstructor.setControl(instructorView);

		//agregar view del registro de estudiante
		TabItem tbtmEstudiante = new TabItem(tabFolder, SWT.NONE);
		tbtmEstudiante.setText("Estudiante");

		RegistrarEstudianteView estudianteView = new RegistrarEstudianteView(tabFolder, SWT.NONE);
		tbtmEstudiante.setControl(estudianteView);

		//
		TabItem tbtmLugar = new TabItem(tabFolder, SWT.NONE);
		tbtmLugar.setText("Lugar");

		RegistrarLugarView lugarView = new RegistrarLugarView(tabFolder, SWT.NONE);
		tbtmLugar.setControl(lugarView);


		//
		TabItem tbtmHorario = new TabItem(tabFolder, SWT.NONE);
		tbtmHorario.setText("Horario");

		RegistrarHorarioView horarioView = new RegistrarHorarioView(tabFolder, SWT.NONE);
		tbtmHorario.setControl(horarioView);

		// agregar view de credito academico
		TabItem tbtmCreditoAcademico = new TabItem(tabFolder, SWT.NONE);
		tbtmCreditoAcademico.setText("CreditoAcademico");

		CreditoAcademicoView creditoAcademico = new CreditoAcademicoView(tabFolder, SWT.NONE);
		tbtmCreditoAcademico.setControl(creditoAcademico);

		}

		if(rolUsuario.equals("instructor")){

		TabItem tbtmChat = new TabItem(tabFolder, SWT.NONE);
		tbtmChat.setText("Chat Con Estudiantes");

		ChatAdministradorView chatView = new ChatAdministradorView(tabFolder, SWT.NONE);
		tbtmChat.setControl(chatView);
		}



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
		return new Point(600, 500);
	}

}
