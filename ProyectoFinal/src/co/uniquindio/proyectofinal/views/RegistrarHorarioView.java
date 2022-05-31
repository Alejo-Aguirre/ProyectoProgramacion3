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

import co.uniquindio.proyectofinal.exceptions.HorarioCrearException;
import co.uniquindio.proyectofinal.exceptions.HorarioEliminarException;
import co.uniquindio.proyectofinal.exceptions.InstructorEliminarException;
import co.uniquindio.proyectofinal.controllers.CRUDHorarioController;
import co.uniquindio.proyectofinal.controllers.CRUDInstructorController;
import co.uniquindio.proyectofinal.model.*;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;



public class RegistrarHorarioView extends Composite {
	private DataBindingContext m_bindingContext;

	private Text txtIdCodigo;
	private Text txtIdHoraInicio;
	private Text txtIdHoraFinal;
//cambiar por LugarCrud
	CRUDHorarioController cRUDHorarioController = new CRUDHorarioController();
	//fin cambiar

	Administrador admin = cRUDHorarioController.getAdmin();
	private TableViewer tableViewerHorarios;
	private Table tableHorarios;
	private Text textRutaArchivo;


	/**
	 * Create the composite.
	 *
	 * @param parent
	 * @param style
	 */
	public RegistrarHorarioView(Composite parent, int style) {
		super(parent, style);
		setLayout(null);

		Group grpListaHorarios = new Group(this, SWT.NONE);
		grpListaHorarios.setBounds(10, 10, 250, 347);
		grpListaHorarios.setText("Lista Horarios");
		grpListaHorarios.setLayout(new FormLayout());

		tableViewerHorarios = new TableViewer(grpListaHorarios, SWT.BORDER | SWT.FULL_SELECTION);

		tableViewerHorarios.addPostSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent arg0) {
				if (tableHorarios.getSelectionIndex() >= 0) {

					Horario horario = (Horario) tableHorarios
							.getItem(tableHorarios.getSelectionIndex()).getData();
					txtIdCodigo.setText(horario.getCodigo());
					txtIdHoraInicio.setText(horario.getHoraInicio());
					txtIdHoraFinal.setText(horario.getHoraFinal());

				}
			}
		});

		tableHorarios = tableViewerHorarios.getTable();
		tableHorarios.setLinesVisible(true);
		tableHorarios.setHeaderVisible(true);

		FormData fd_tableHorarios = new FormData();
		fd_tableHorarios.right = new FormAttachment(100, -7);
		fd_tableHorarios.left = new FormAttachment(0);
		fd_tableHorarios.bottom = new FormAttachment(100, -10);
		fd_tableHorarios.top = new FormAttachment(0, 9);
		tableHorarios.setLayoutData(fd_tableHorarios);

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewerHorarios, SWT.NONE);
		TableColumn tblclmnCodigo = tableViewerColumn_1.getColumn();
		tblclmnCodigo.setAlignment(SWT.CENTER);
		tblclmnCodigo.setWidth(70);
		tblclmnCodigo.setText("Codigo");

		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewerHorarios, SWT.NONE);
		TableColumn tblclmnHoraInicio = tableViewerColumn.getColumn();
		tblclmnHoraInicio.setAlignment(SWT.CENTER);
		tblclmnHoraInicio.setWidth(81);
		tblclmnHoraInicio.setText("Hora Inicio");


		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewerHorarios, SWT.NONE);
		TableColumn tblclmnHoraFinal = tableViewerColumn_2.getColumn();
		tblclmnHoraFinal.setWidth(81);
		tblclmnHoraFinal.setText("Hora Fin");

		Group grpDetalleHorario = new Group(this, SWT.NONE);
		grpDetalleHorario.setText("Detalle Horario");
		grpDetalleHorario.setBounds(266, 10, 294, 347);


		Label lblCodigoHorario = new Label(grpDetalleHorario, SWT.NONE);
		lblCodigoHorario.setBounds(10, 26, 105, 15);
		lblCodigoHorario.setText("Codigo :");

		txtIdCodigo = new Text(grpDetalleHorario, SWT.BORDER);
		txtIdCodigo.setBounds(10, 47, 274, 21);


		Label lblHoraInicio = new Label(grpDetalleHorario, SWT.NONE);
		lblHoraInicio.setBounds(10, 79, 105, 15);
		lblHoraInicio.setText("Hora Inicio:");

		txtIdHoraInicio = new Text(grpDetalleHorario, SWT.BORDER);
		txtIdHoraInicio.setBounds(10, 100, 120, 21);

		Label lblHoraFinal = new Label(grpDetalleHorario, SWT.NONE);
		lblHoraFinal.setText("Hora Fin:");
		lblHoraFinal.setBounds(164, 79, 105, 15);

		txtIdHoraFinal = new Text(grpDetalleHorario, SWT.BORDER);
		txtIdHoraFinal.setBounds(164, 100, 120, 21);



		Button btnAgregarHorario = new Button(grpDetalleHorario, SWT.NONE);

		btnAgregarHorario.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					cRUDHorarioController.agregarHorario(txtIdCodigo.getText(), txtIdHoraInicio.getText(), txtIdHoraFinal.getText() );
					initDataBindings();
					limpiarCampos();
					JOptionPane.showMessageDialog(null, "El Horario fue registrado");
					cRUDHorarioController.getModelfactoryController().guardarRegistroLog("El Horario fue creado exitosamente.", 1, "crearHorario");
				} catch (HorarioCrearException errorAdmin) {
					JOptionPane.showMessageDialog(null, errorAdmin.getMessage());
				} catch (IOException ioException) {
					JOptionPane.showMessageDialog(null, ioException.getMessage());
					cRUDHorarioController.getModelfactoryController().guardarRegistroLog(ioException.getMessage(), 2,"crearHorario");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensaje de error", JOptionPane.ERROR_MESSAGE);
					cRUDHorarioController.getModelfactoryController().guardarRegistroLog(e1.getMessage(), 3,"crearHorario");
				}
			}

		});

		btnAgregarHorario.setBounds(101, 134, 105, 25);
		btnAgregarHorario.setText("Agregar Horario");


		Button btnActualizarHorario = new Button(grpDetalleHorario, SWT.NONE);
		btnActualizarHorario.setGrayed(true);
		btnActualizarHorario.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e){
				if (tableHorarios.getSelectionIndex() >= 0) {
					Horario horario = (Horario) tableHorarios
							.getItem(tableHorarios.getSelectionIndex()).getData();
					cRUDHorarioController.actualizarHorario(horario.getCodigo(),
							txtIdCodigo.getText(), txtIdHoraInicio.getText(), txtIdHoraFinal.getText());
					initDataBindings();
					limpiarCampos();
					JOptionPane.showMessageDialog(null, "El Horario fue editado");
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un Horario");

				}
			}
		});
		btnActualizarHorario.setText("Editar Horario");
		btnActualizarHorario.setBounds(101, 181, 105, 25);


		Button btnEliminarHorario = new Button(grpDetalleHorario, SWT.NONE);
		btnEliminarHorario.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
				if (tableHorarios.getSelectionIndex() >= 0) {
					Horario horarioSeleccionado = (Horario) tableHorarios.getItem(tableHorarios.getSelectionIndex()).getData();
					int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que quiere eliminarlo?","Confirmar eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (respuesta == 0) {
					    cRUDHorarioController.eliminarHorario(horarioSeleccionado.getCodigo());
						JOptionPane.showMessageDialog(null, "El horario fue eliminado correctamente.");
						limpiarCampos();
						initDataBindings();
					}
				}else{
						JOptionPane.showMessageDialog(null, "Seleccione un Horario");
					}
				} catch (HorarioEliminarException errorHorarioCrear) {
					JOptionPane.showMessageDialog(null, errorHorarioCrear.getMessage());
				} catch (IOException ioException) {
					cRUDHorarioController.getModelfactoryController().guardarRegistroLog(ioException.getMessage(), 2, "eliminarHorario");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensaje de error", JOptionPane.ERROR_MESSAGE);
					cRUDHorarioController.getModelfactoryController().guardarRegistroLog(e1.getMessage(), 3, "eliminarHorario");
				}
				}
		});
		btnEliminarHorario.setText("Eliminar Horario");
		btnEliminarHorario.setBounds(101, 233, 105, 25);



		Button btnLimpiarCampos = new Button(grpDetalleHorario, SWT.NONE);
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
					cRUDHorarioController.generarReporteHorarios (textRutaArchivo.getText());
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
		txtIdCodigo.setText("");
		txtIdHoraInicio.setText("");
		txtIdHoraFinal.setText("");

		// TODO Auto-generated method stub

	}

	private DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(),
				Horario.class, new String[] { "codigo", "horaInicio", "horaFinal" });
		tableViewerHorarios.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewerHorarios.setContentProvider(listContentProvider);
		//
		IObservableList listaHorariosAdminObserveList = PojoProperties.list("listaHorarios").observe(admin);
		tableViewerHorarios.setInput(listaHorariosAdminObserveList);
		//
		return bindingContext;
	}
}
