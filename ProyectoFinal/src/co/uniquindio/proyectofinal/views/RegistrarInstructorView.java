package co.uniquindio.proyectofinal.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import co.uniquindio.proyectofinal.exceptions.CreacionInstructorException;
import co.uniquindio.proyectofinal.exceptions.InstructorEliminarException;
import co.uniquindio.proyectofinal.controllers.CRUDInstructorController;
import co.uniquindio.proyectofinal.model.*;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class RegistrarInstructorView extends Composite {
	private DataBindingContext m_bindingContext;

	private Text txtIdNombre;
	private Text txtIdCodigo;

	CRUDInstructorController cRUDInstructorController = new CRUDInstructorController();
	Administrador admin = cRUDInstructorController.getAdmin();
	private TableViewer tableViewer_1;
	private Table tableInstructores;
	private Text textRutaArchivo;

	/**
	 * Create the composite.
	 *
	 * @param parent
	 * @param style
	 */
	public RegistrarInstructorView(Composite parent, int style) {
		super(parent, style);
		setLayout(null);

		Group grpListaIstructores = new Group(this, SWT.NONE);
		grpListaIstructores.setBounds(10, 10, 250, 347);
		grpListaIstructores.setText("Lista Instructores");
		grpListaIstructores.setLayout(new FormLayout());

		tableViewer_1 = new TableViewer(grpListaIstructores, SWT.BORDER | SWT.FULL_SELECTION);

		tableViewer_1.addPostSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent arg0) {
				if (tableInstructores.getSelectionIndex() >= 0) {

					Instructor instructor = (Instructor) tableInstructores
							.getItem(tableInstructores.getSelectionIndex()).getData();
					txtIdNombre.setText(instructor.getNombre());
					txtIdCodigo.setText(instructor.getCodigo());
				}
			}
		});

		tableInstructores = tableViewer_1.getTable();
		tableInstructores.setLinesVisible(true);
		tableInstructores.setHeaderVisible(true);

		FormData fd_tableInstructores = new FormData();
		fd_tableInstructores.right = new FormAttachment(100, -7);
		fd_tableInstructores.left = new FormAttachment(0);
		fd_tableInstructores.bottom = new FormAttachment(100, -10);
		fd_tableInstructores.top = new FormAttachment(0, 9);
		tableInstructores.setLayoutData(fd_tableInstructores);

		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNombre = tableViewerColumn.getColumn();
		tblclmnNombre.setAlignment(SWT.CENTER);
		tblclmnNombre.setWidth(112);
		tblclmnNombre.setText("Nombre");

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnCodigo = tableViewerColumn_1.getColumn();
		tblclmnCodigo.setAlignment(SWT.CENTER);
		tblclmnCodigo.setWidth(121);
		tblclmnCodigo.setText("Codigo");

		Group grpDetalleInstructor = new Group(this, SWT.NONE);
		grpDetalleInstructor.setText("Detalle Instructor");
		grpDetalleInstructor.setBounds(266, 10, 294, 347);

		Label lblNombreIstructor = new Label(grpDetalleInstructor, SWT.NONE);
		lblNombreIstructor.setBounds(10, 26, 105, 15);
		lblNombreIstructor.setText("Nombre Instructor :");

		txtIdNombre = new Text(grpDetalleInstructor, SWT.BORDER);
		txtIdNombre.setBounds(10, 47, 274, 21);

		Label lblCedulaCodigo = new Label(grpDetalleInstructor, SWT.NONE);
		lblCedulaCodigo.setBounds(10, 74, 105, 15);
		lblCedulaCodigo.setText("Cedula / Codigo :");

		txtIdCodigo = new Text(grpDetalleInstructor, SWT.BORDER);
		txtIdCodigo.setBounds(10, 95, 274, 21);

		Button btnCrearInstructor = new Button(grpDetalleInstructor, SWT.NONE);

		btnCrearInstructor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					cRUDInstructorController.CrearInstructor(txtIdNombre.getText(), txtIdCodigo.getText());
					initDataBindings();
					limpiarCampos();
					JOptionPane.showMessageDialog(null, "El Instructor fue registrado");
					cRUDInstructorController.getModelfactoryController().guardarRegistroLog("El Instructor fue creado exitosamente.", 1, "crearInstructor");
				} catch (CreacionInstructorException errorAdmin) {
					JOptionPane.showMessageDialog(null, errorAdmin.getMessage());
				} catch (IOException ioException) {
					JOptionPane.showMessageDialog(null, ioException.getMessage());
					cRUDInstructorController.getModelfactoryController().guardarRegistroLog(ioException.getMessage(), 2,"crearInstructor");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensaje de error", JOptionPane.ERROR_MESSAGE);
					cRUDInstructorController.getModelfactoryController().guardarRegistroLog(e1.getMessage(), 3,"crearInstructor");
				}
			}

		});

		btnCrearInstructor.setBounds(101, 134, 105, 25);
		btnCrearInstructor.setText("Crear Instructor");

		Button btnEliminarInstructor = new Button(grpDetalleInstructor, SWT.NONE);
		btnEliminarInstructor.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
				if (tableInstructores.getSelectionIndex() >= 0) {
					Instructor instructorSeleccionado = (Instructor) tableInstructores.getItem(tableInstructores.getSelectionIndex()).getData();
					int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que quiere eliminarlo?","Confirmar eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (respuesta == 0) {
					    cRUDInstructorController.eliminarInstructor(instructorSeleccionado.getCodigo());
						JOptionPane.showMessageDialog(null, "El producto fue eliminado correctamente.");
						limpiarCampos();
						initDataBindings();
					}
				}else{
						JOptionPane.showMessageDialog(null, "Seleccione un instructor");
					}
				} catch (InstructorEliminarException errorInstructorCrear) {
					JOptionPane.showMessageDialog(null, errorInstructorCrear.getMessage());
				} catch (IOException ioException) {
					cRUDInstructorController.getModelfactoryController().guardarRegistroLog(ioException.getMessage(), 2, "eliminarInstructor");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensaje de error", JOptionPane.ERROR_MESSAGE);
					cRUDInstructorController.getModelfactoryController().guardarRegistroLog(e1.getMessage(), 3, "eliminarInstructor");
				}
				}
		});
		btnEliminarInstructor.setText("Eliminar Instructor");
		btnEliminarInstructor.setBounds(101, 233, 105, 25);

		Button btnEditarInstructor = new Button(grpDetalleInstructor, SWT.NONE);
		btnEditarInstructor.setGrayed(true);
		btnEditarInstructor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (tableInstructores.getSelectionIndex() >= 0) {
					Instructor instructor = (Instructor) tableInstructores
							.getItem(tableInstructores.getSelectionIndex()).getData();
					cRUDInstructorController.editarInstructor(instructor.getCodigo(), txtIdNombre.getText(),
							txtIdCodigo.getText());
					initDataBindings();
					limpiarCampos();
					JOptionPane.showMessageDialog(null, "el instructor fue editado");
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un instructor");

				}
			}
		});
		btnEditarInstructor.setText("Editar Instructor");
		btnEditarInstructor.setBounds(101, 181, 105, 25);

		Button btnLimpiarCampos = new Button(grpDetalleInstructor, SWT.NONE);
		btnLimpiarCampos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				limpiarCampos();
			}
		});
		btnLimpiarCampos.setText("Limpiar Campos");
		btnLimpiarCampos.setBounds(101, 277, 105, 25);

		textRutaArchivo = new Text(this, SWT.BORDER);
		textRutaArchivo.setBounds(137, 367, 280, 21);

		Label lblRutaArchivoReporte = new Label(this, SWT.NONE);
		lblRutaArchivoReporte.setBounds(10, 373, 121, 15);
		lblRutaArchivoReporte.setText("Ruta Archivo Reporte :");

		Button btnGenerarReporte = new Button(this, SWT.NONE);
		btnGenerarReporte.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (textRutaArchivo.getText().isEmpty()){
					JOptionPane.showConfirmDialog(null, "Debe escribir una ruta de archivo", "Mensaje de error", JOptionPane.ERROR_MESSAGE);
				}

				try{
					cRUDInstructorController.generarReporteInstructores (textRutaArchivo.getText());
					JOptionPane.showMessageDialog(null, "El reporte fue generado exitosamente.");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensaje de error", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		btnGenerarReporte.setBounds(439, 365, 121, 25);
		btnGenerarReporte.setText("Generar Reporte");
		m_bindingContext = initDataBindings();

	}

	private void limpiarCampos() {
		txtIdNombre.setText("");
		txtIdCodigo.setText("");
		// TODO Auto-generated method stub

	}

	private DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(),
				Instructor.class, new String[] { "nombre", "codigo" });
		tableViewer_1.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer_1.setContentProvider(listContentProvider);
		//
		IObservableList listaInstructoresAdminObserveList = PojoProperties.list("listaInstructores").observe(admin);
		tableViewer_1.setInput(listaInstructoresAdminObserveList);
		//
		return bindingContext;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
