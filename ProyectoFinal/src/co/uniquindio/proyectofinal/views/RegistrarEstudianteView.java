package co.uniquindio.proyectofinal.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;

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

import co.uniquindio.proyectofinal.controllers.CRUDInstructorController;
import co.uniquindio.proyectofinal.controllers.CRUDestudianteController;
import co.uniquindio.proyectofinal.model.Administrador;
import co.uniquindio.proyectofinal.model.Estudiante;
import co.uniquindio.proyectofinal.model.Instructor;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class RegistrarEstudianteView extends Composite {
	private DataBindingContext m_bindingContext;

	private Table tableEstudiantes;
	private Text txtIdNombre;
	private Text txtIdCodigo;
	private TableViewer tableViewer;

	CRUDestudianteController cRUDestudianteController = new CRUDestudianteController();
	Administrador admin = cRUDestudianteController.getAdmin();
	private Text textRutaArchivo;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RegistrarEstudianteView(Composite parent, int style) {
		super(parent, style);
		setLayout(null);

		Group grpListaIstructores = new Group(this, SWT.NONE);
		grpListaIstructores.setBounds(10, 10, 253, 351);
		grpListaIstructores.setText("Lista Estudiantes");
		grpListaIstructores.setLayout(new FormLayout());

		tableViewer = new TableViewer(grpListaIstructores, SWT.BORDER | SWT.FULL_SELECTION);
		tableViewer.addPostSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent arg0) {
				if (tableEstudiantes.getSelectionIndex() >= 0) {

					Estudiante estudiante = (Estudiante) tableEstudiantes
							.getItem(tableEstudiantes.getSelectionIndex()).getData();
					txtIdNombre.setText(estudiante.getNombre());
					txtIdCodigo.setText(estudiante.getCodigo());
				}
			}
		});
		tableEstudiantes = tableViewer.getTable();
		tableEstudiantes.setLinesVisible(true);
		tableEstudiantes.setHeaderVisible(true);
		FormData fd_table = new FormData();
		fd_table.right = new FormAttachment(100, -7);
		fd_table.left = new FormAttachment(0);
		fd_table.bottom = new FormAttachment(100, -10);
		fd_table.top = new FormAttachment(0, 9);
		tableEstudiantes.setLayoutData(fd_table);

		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNombre = tableViewerColumn.getColumn();
		tblclmnNombre.setAlignment(SWT.CENTER);
		tblclmnNombre.setWidth(119);
		tblclmnNombre.setText("Nombre");

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnCodigo = tableViewerColumn_1.getColumn();
		tblclmnCodigo.setAlignment(SWT.CENTER);
		tblclmnCodigo.setWidth(118);
		tblclmnCodigo.setText("Codigo");

		Group grpDetalleInstructor = new Group(this, SWT.NONE);
		grpDetalleInstructor.setText("Detalle Estudiante");
		grpDetalleInstructor.setBounds(269, 10, 291, 351);

		Label lblNombreIstructor = new Label(grpDetalleInstructor, SWT.NONE);
		lblNombreIstructor.setBounds(10, 26, 105, 15);
		lblNombreIstructor.setText("Nombre Alumno :");

		txtIdNombre = new Text(grpDetalleInstructor, SWT.BORDER);
		txtIdNombre.setBounds(10, 47, 271, 21);

		Label lblCedulaCodigo = new Label(grpDetalleInstructor, SWT.NONE);
		lblCedulaCodigo.setBounds(10, 74, 105, 15);
		lblCedulaCodigo.setText("Cedula / Codigo :");

		txtIdCodigo = new Text(grpDetalleInstructor, SWT.BORDER);
		txtIdCodigo.setBounds(10, 95, 271, 21);

		Button btnCrearInstructor = new Button(grpDetalleInstructor, SWT.NONE);
		btnCrearInstructor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					cRUDestudianteController.CrearEstudiante(txtIdNombre.getText(),txtIdCodigo.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				initDataBindings();
				limpiarCampos();
				JOptionPane.showMessageDialog(null, "El Estudiante fue registrado");

			}
		});
		btnCrearInstructor.setBounds(81, 134, 118, 25);
		btnCrearInstructor.setText("Crear Estudiante");

		Button btnEliminarInstructor = new Button(grpDetalleInstructor, SWT.NONE);
		btnEliminarInstructor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Estudiante  estudianteSeleccionado = (Estudiante) tableEstudiantes.getItem(tableEstudiantes.getSelectionIndex()).getData();
				int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que quiere eliminarlo?", "Confirmar eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(respuesta == 0){
					try {
						cRUDestudianteController.eliminarEstudiante(estudianteSeleccionado.getCodigo());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "El Estudiante fue eliminado correctamente.");
					limpiarCampos();
					initDataBindings();
			}
			}
		});
		btnEliminarInstructor.setText("Eliminar Estudiante");
		btnEliminarInstructor.setBounds(81, 241, 118, 25);

		Button btnEditarInstructor = new Button(grpDetalleInstructor, SWT.NONE);
		btnEditarInstructor.setGrayed(true);
		btnEditarInstructor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (tableEstudiantes.getSelectionIndex() >= 0) {
					Estudiante estudiante = (Estudiante) tableEstudiantes
							.getItem(tableEstudiantes.getSelectionIndex()).getData();
					try {
						cRUDestudianteController.editarEstudiante(estudiante.getCodigo(), txtIdNombre.getText(),
								txtIdCodigo.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					initDataBindings();
					limpiarCampos();
					JOptionPane.showMessageDialog(null, "El Estudiante fue editado");
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un instructor");

				}

			}
		});
		btnEditarInstructor.setText("Editar Estudiante");
		btnEditarInstructor.setBounds(81, 185, 118, 25);

		Button btnLimpiarCampos = new Button(grpDetalleInstructor, SWT.NONE);
		btnLimpiarCampos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				limpiarCampos();
			}
		});
		btnLimpiarCampos.setText("Limpiar Campos");
		btnLimpiarCampos.setBounds(81, 294, 118, 25);

		textRutaArchivo = new Text(this, SWT.BORDER);
		textRutaArchivo.setBounds(139, 369, 253, 21);

		Label lblRutaArchivoReporte = new Label(this, SWT.NONE);
		lblRutaArchivoReporte.setBounds(10, 370, 123, 15);
		lblRutaArchivoReporte.setText("Ruta Archivo Reporte :");

		Button btnGenerarreporte = new Button(this, SWT.NONE);
		btnGenerarreporte.addSelectionListener(new SelectionAdapter() {
			@Override
				public void widgetSelected(SelectionEvent e) {
					if (textRutaArchivo.getText().isEmpty()){
						JOptionPane.showConfirmDialog(null, "Debe escribir una ruta de archivo", "Mensaje de error", JOptionPane.ERROR_MESSAGE);
					}

					try{
						cRUDestudianteController.generarReporteInstructores (textRutaArchivo.getText());
						JOptionPane.showMessageDialog(null, "El reporte fue generado exitosamente.");
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensaje de error", JOptionPane.ERROR_MESSAGE);
						}
			}
		});
		btnGenerarreporte.setBounds(428, 365, 94, 25);
		btnGenerarreporte.setText("GenerarReporte");
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
				Estudiante.class, new String[] { "nombre", "codigo" });
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList listaInstructoresAdminObserveList = PojoProperties.list("listaEstudiantes").observe(admin);
		tableViewer.setInput(listaInstructoresAdminObserveList);
		//
		return bindingContext;
	}
}
