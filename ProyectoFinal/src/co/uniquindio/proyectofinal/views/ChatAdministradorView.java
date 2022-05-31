package co.uniquindio.proyectofinal.views;

import org.eclipse.swt.widgets.Composite;
import co.uniquindio.proyectofinal.chat.*;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

import javax.swing.JOptionPane;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import co.uniquindio.proyectofinal.controllers.CRUDCreditoAcademicoController;
import co.uniquindio.proyectofinal.controllers.ModelFactoryController;
import co.uniquindio.proyectofinal.exceptions.CreacionCreditoAcademicoException;
import co.uniquindio.proyectofinal.model.Administrador;
import co.uniquindio.proyectofinal.model.CreditoAcademico;
import co.uniquindio.proyectofinal.model.Horario;
import co.uniquindio.proyectofinal.model.Instructor;
import co.uniquindio.proyectofinal.model.Lugar;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.wb.swt.SWTResourceManager;

public class ChatAdministradorView extends Composite {

	private DataBindingContext m_bindingContext;

	CRUDCreditoAcademicoController cRUDCreditoAcademico = new CRUDCreditoAcademicoController();
	
	ModelFactoryController modelFactoryController = new ModelFactoryController();
	
	
	Administrador admin = cRUDCreditoAcademico.getAdmin();


	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ChatAdministradorView(Composite parent, int style) {
		super(parent, style);
		setLayout(null);

		Group grpVentanaMensajes = new Group(this, SWT.NONE);
		grpVentanaMensajes.setText("Chat");
		grpVentanaMensajes.setBounds(10, 112, 550, 278);
		
		Button btnEnviar = new Button(grpVentanaMensajes, SWT.NONE);
		btnEnviar.setBounds(161, 110, 179, 57);
		btnEnviar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				java.awt.EventQueue.invokeLater(new Runnable() {
		            public void run() {
		                new Frm1().setVisible(true);
		                //new Frm2().setVisible(true);
		            }
		        });
				
			}
		});
		btnEnviar.setText("Iniciar Chat");
		
		Label lblDescripcion = new Label(grpVentanaMensajes, SWT.NONE);
		lblDescripcion.setAlignment(SWT.CENTER);
		lblDescripcion.setBounds(38, 36, 482, 43);
		lblDescripcion.setText("Envie mensajes en tiempo real a los estudiantes inscritos en los creditos");
		
		Label lblChatConEstudiantes = new Label(this, SWT.NONE);
		lblChatConEstudiantes.setFont(SWTResourceManager.getFont("Segoe UI Black", 24, SWT.BOLD));
		lblChatConEstudiantes.setAlignment(SWT.CENTER);
		lblChatConEstudiantes.setBounds(55, 25, 452, 70);
		lblChatConEstudiantes.setText("CHAT CON ESTUDIANTES");

		m_bindingContext = initDataBindings();
	}


	private Lugar ObtenerLugar() {
		// TODO Auto-generated method stub
		return null;
	}

	private Horario Obtenerhorario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		return bindingContext;
	}
}
