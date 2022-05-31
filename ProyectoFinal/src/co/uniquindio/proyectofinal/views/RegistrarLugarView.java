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



import co.uniquindio.proyectofinal.exceptions.InstructorEliminarException;
import co.uniquindio.proyectofinal.exceptions.LugarCrearException;
import co.uniquindio.proyectofinal.controllers.CRUDInstructorController;
import co.uniquindio.proyectofinal.controllers.CRUDLugarController;
import co.uniquindio.proyectofinal.model.*;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class RegistrarLugarView extends Composite {
	private DataBindingContext m_bindingContext;

	private Text txtIdNombre;
	private Text txtIdCodigo;
//cambiar por LugarCrud
	CRUDLugarController cRUDLugarController = new CRUDLugarController();
	//fin cambiar

	Administrador admin = cRUDLugarController.getAdmin();
	private TableViewer tableViewerLugares;
	private Table tableLugares;
	private Text textRutaArchivo;

	/**
	 * Create the composite.
	 *
	 * @param parent
	 * @param style
	 */
	public RegistrarLugarView(Composite parent, int style) {
		super(parent, style);
		setLayout(null);

		Group grpListaLugares = new Group(this, SWT.NONE);
		grpListaLugares.setBounds(10, 10, 250, 347);
		grpListaLugares.setText("Lista Lugares");
		grpListaLugares.setLayout(new FormLayout());

		tableViewerLugares = new TableViewer(grpListaLugares, SWT.BORDER | SWT.FULL_SELECTION);

		tableViewerLugares.addPostSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent arg0) {
				if (tableLugares.getSelectionIndex() >= 0) {

					Lugar lugar = (Lugar) tableLugares
							.getItem(tableLugares.getSelectionIndex()).getData();
					txtIdNombre.setText(lugar.getNombre());
					txtIdCodigo.setText(lugar.getCodigo());
				}
			}
		});

		tableLugares = tableViewerLugares.getTable();
		tableLugares.setLinesVisible(true);
		tableLugares.setHeaderVisible(true);

		FormData fd_tableLugares = new FormData();
		fd_tableLugares.right = new FormAttachment(100, -7);
		fd_tableLugares.left = new FormAttachment(0);
		fd_tableLugares.bottom = new FormAttachment(100, -10);
		fd_tableLugares.top = new FormAttachment(0, 9);
		tableLugares.setLayoutData(fd_tableLugares);

		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewerLugares, SWT.NONE);
		TableColumn tblclmnNombre = tableViewerColumn.getColumn();
		tblclmnNombre.setAlignment(SWT.CENTER);
		tblclmnNombre.setWidth(112);
		tblclmnNombre.setText("Nombre");

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewerLugares, SWT.NONE);
		TableColumn tblclmnCodigo = tableViewerColumn_1.getColumn();
		tblclmnCodigo.setAlignment(SWT.CENTER);
		tblclmnCodigo.setWidth(121);
		tblclmnCodigo.setText("Codigo");

		Group grpDetalleLugar = new Group(this, SWT.NONE);
		grpDetalleLugar.setText("Detalle Lugar");
		grpDetalleLugar.setBounds(266, 10, 294, 347);

		Label lblNombreLugar = new Label(grpDetalleLugar, SWT.NONE);
		lblNombreLugar.setBounds(10, 26, 105, 15);
		lblNombreLugar.setText("Nombre Lugar :");

		txtIdNombre = new Text(grpDetalleLugar, SWT.BORDER);
		txtIdNombre.setBounds(10, 47, 274, 21);

		Label lblCodigoLugar = new Label(grpDetalleLugar, SWT.NONE);
		lblCodigoLugar.setBounds(10, 74, 105, 15);
		lblCodigoLugar.setText("Codigo :");

		txtIdCodigo = new Text(grpDetalleLugar, SWT.BORDER);
		txtIdCodigo.setBounds(10, 95, 274, 21);

		Button btnCrearLugar = new Button(grpDetalleLugar, SWT.NONE);

		btnCrearLugar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					cRUDLugarController.crearLugar(txtIdNombre.getText(), txtIdCodigo.getText());
					initDataBindings();
					limpiarCampos();
					JOptionPane.showMessageDialog(null, "El Lugar fue registrado");
					cRUDLugarController.getModelfactoryController().guardarRegistroLog("El Lugar fue creado exitosamente.", 1, "crearInstructor");
				} catch (LugarCrearException errorAdmin) {
					JOptionPane.showMessageDialog(null, errorAdmin.getMessage());
				} catch (IOException ioException) {
					JOptionPane.showMessageDialog(null, ioException.getMessage());
					cRUDLugarController.getModelfactoryController().guardarRegistroLog(ioException.getMessage(), 2,"crearLugar");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensaje de error", JOptionPane.ERROR_MESSAGE);
					cRUDLugarController.getModelfactoryController().guardarRegistroLog(e1.getMessage(), 3,"crearLugar");
				}
			}

		});

		btnCrearLugar.setBounds(101, 134, 105, 25);
		btnCrearLugar.setText("Crear Lugar");

		Button btnEliminarLugar = new Button(grpDetalleLugar, SWT.NONE);
		btnEliminarLugar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
				if (tableLugares.getSelectionIndex() >= 0) {
					Lugar lugarSeleccionado = (Lugar) tableLugares.getItem(tableLugares.getSelectionIndex()).getData();
					int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que quiere eliminarlo?","Confirmar eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (respuesta == 0) {
					    cRUDLugarController.eliminarLugar(lugarSeleccionado.getCodigo());
						JOptionPane.showMessageDialog(null, "El lugar fue eliminado correctamente.");
						limpiarCampos();
						initDataBindings();
					}
				}else{
						JOptionPane.showMessageDialog(null, "Seleccione un Lugar");
					}
				} catch (InstructorEliminarException errorInstructorCrear) {
					JOptionPane.showMessageDialog(null, errorInstructorCrear.getMessage());
				} catch (IOException ioException) {
					cRUDLugarController.getModelfactoryController().guardarRegistroLog(ioException.getMessage(), 2, "eliminarLugar");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensaje de error", JOptionPane.ERROR_MESSAGE);
					cRUDLugarController.getModelfactoryController().guardarRegistroLog(e1.getMessage(), 3, "eliminarLugar");
				}
				}
		});
		btnEliminarLugar.setText("Eliminar Lugar");
		btnEliminarLugar.setBounds(101, 233, 105, 25);

		Button btnEditarLugar = new Button(grpDetalleLugar, SWT.NONE);
		btnEditarLugar.setGrayed(true);
		btnEditarLugar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (tableLugares.getSelectionIndex() >= 0) {
					Lugar lugar = (Lugar) tableLugares
							.getItem(tableLugares.getSelectionIndex()).getData();
					cRUDLugarController.editarLugar(lugar.getCodigo(), txtIdNombre.getText(),
							txtIdCodigo.getText());
					initDataBindings();
					limpiarCampos();
					JOptionPane.showMessageDialog(null, "el lugar fue editado");
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un lugar");

				}
			}
		});
		btnEditarLugar.setText("Editar Lugar");
		btnEditarLugar.setBounds(101, 181, 105, 25);

		Button btnLimpiarCampos = new Button(grpDetalleLugar, SWT.NONE);
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
					cRUDLugarController.generarReporteLugares (textRutaArchivo.getText());
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
				Lugar.class, new String[] { "nombre", "codigo" });
		tableViewerLugares.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewerLugares.setContentProvider(listContentProvider);
		//
		IObservableList listaLugaresAdminObserveList = PojoProperties.list("listaLugares").observe(admin);
		tableViewerLugares.setInput(listaLugaresAdminObserveList);
		//
		return bindingContext;
	}
}
