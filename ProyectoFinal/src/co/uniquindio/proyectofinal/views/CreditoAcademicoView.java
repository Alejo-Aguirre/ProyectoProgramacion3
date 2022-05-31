package co.uniquindio.proyectofinal.views;

import org.eclipse.swt.widgets.Composite;
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

public class CreditoAcademicoView extends Composite {

	private DataBindingContext m_bindingContext;


	private Text txtNombreCredito;
	private Text txtCodigo;
	private Text txtDuracion;
	private Text txtCupo;
	private Text txtHomogable;

	CRUDCreditoAcademicoController cRUDCreditoAcademico = new CRUDCreditoAcademicoController();
	Administrador admin = cRUDCreditoAcademico.getAdmin();

	private TableViewer tableViewer;
	private Table tableCreditosAcademicos;
	private Text txtRutaArchivo;

	// combos

	private Combo comboInstructores;
	private Combo comboLugar;
	private Combo comboHorario;


	//arreglos para el combo

	//private String[] instructores;
//	private String[] lugares;
//	private String[] horarios;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CreditoAcademicoView(Composite parent, int style) {
		super(parent, style);
		setLayout(null);

		Group grpListaCreditosDeportivos = new Group(this, SWT.NONE);
		grpListaCreditosDeportivos.setText("Lista Creditos Deportivos");
		grpListaCreditosDeportivos.setBounds(10, 10, 576, 233);

		tableViewer = new TableViewer(grpListaCreditosDeportivos, SWT.BORDER | SWT.FULL_SELECTION);
		tableViewer.addPostSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent arg0) {
				if(tableCreditosAcademicos.getSelectionIndex()>= 0){
					try{
						CreditoAcademico creditoAcademico = (CreditoAcademico) tableCreditosAcademicos.getItem(tableCreditosAcademicos.getSelectionIndex()).getData();
						Instructor instructor = creditoAcademico.getInstructor();
						txtNombreCredito.setText(creditoAcademico.getNombre());
						txtCodigo.setText(creditoAcademico.getCodigo());
						txtCupo.setText(creditoAcademico.getCupoMaximo());
						txtDuracion.setText(creditoAcademico.getDuracionCredito());
						//para manejar combo de instructores
				        comboInstructores.select(comboInstructores.indexOf( instructor.getCodigo()+"-"+instructor.getNombre()));
						//horario
				        int posicionHorario = cRUDCreditoAcademico.obtenerPosicionHorario(creditoAcademico.getHorario().getCodigo());
						comboHorario.select(posicionHorario);
						int posicionLugar = cRUDCreditoAcademico.obtenerPosicionLugar(creditoAcademico.getLugar().getNombre());
                        comboLugar.select(posicionLugar);
                        txtHomogable.setText(creditoAcademico.getHomologacion());
					}catch (Exception e) {
						cRUDCreditoAcademico.getModelfactoryController().guardarRegistroLog(e.getMessage(), 2, "seleccione credito");

					}

				}

			}

		});
		tableCreditosAcademicos = tableViewer.getTable();
		tableCreditosAcademicos.setLinesVisible(true);
		tableCreditosAcademicos.setHeaderVisible(true);
		tableCreditosAcademicos.setBounds(10, 20, 556, 203);

		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNombre = tableViewerColumn.getColumn();
		tblclmnNombre.setWidth(74);
		tblclmnNombre.setText("Nombre");

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnCodigo = tableViewerColumn_1.getColumn();
		tblclmnCodigo.setWidth(63);
		tblclmnCodigo.setText("Codigo");

		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnCupo = tableViewerColumn_2.getColumn();
		tblclmnCupo.setWidth(53);
		tblclmnCupo.setText("Cupo");

		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnDuracion = tableViewerColumn_3.getColumn();
		tblclmnDuracion.setWidth(72);
		tblclmnDuracion.setText("Duracion");

		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnInstructor = tableViewerColumn_4.getColumn();
		tblclmnInstructor.setWidth(73);
		tblclmnInstructor.setText("Instructor");

		TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnHorario = tableViewerColumn_5.getColumn();
		tblclmnHorario.setWidth(90);
		tblclmnHorario.setText("Horario");

		TableViewerColumn tableViewerColumn_6 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnLugar = tableViewerColumn_6.getColumn();
		tblclmnLugar.setWidth(54);
		tblclmnLugar.setText("Lugar");

		TableViewerColumn tableViewerColumn_7 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnHomolable = tableViewerColumn_7.getColumn();
		tblclmnHomolable.setWidth(74);
		tblclmnHomolable.setText("Homologable");

		Group grpDetalleCreditoDeportivo = new Group(this, SWT.NONE);
		grpDetalleCreditoDeportivo.setText("Detalle Credito Deportivo");
		grpDetalleCreditoDeportivo.setBounds(10, 249, 576, 234);

		Label lblNombre = new Label(grpDetalleCreditoDeportivo, SWT.NONE);
		lblNombre.setBounds(21, 27, 118, 15);
		lblNombre.setText("Nombre del Credito :");

		txtNombreCredito = new Text(grpDetalleCreditoDeportivo, SWT.BORDER);
		txtNombreCredito.setBounds(145, 24, 145, 21);

		Label lblCodigo = new Label(grpDetalleCreditoDeportivo, SWT.NONE);
		lblCodigo.setBounds(21, 56, 118, 15);
		lblCodigo.setText("Codigo del Credito :");

		txtCodigo = new Text(grpDetalleCreditoDeportivo, SWT.BORDER);
		txtCodigo.setBounds(145, 53, 145, 21);

		Label lblCupo = new Label(grpDetalleCreditoDeportivo, SWT.NONE);
		lblCupo.setBounds(22, 89, 55, 15);
		lblCupo.setText("Cupo :");

		txtDuracion = new Text(grpDetalleCreditoDeportivo, SWT.BORDER);
		txtDuracion.setBounds(145, 112, 145, 21);

		Label lblDuracionDelCredito = new Label(grpDetalleCreditoDeportivo, SWT.NONE);
		lblDuracionDelCredito.setBounds(21, 118, 118, 15);
		lblDuracionDelCredito.setText("Duracion del Credito :");

		txtCupo = new Text(grpDetalleCreditoDeportivo, SWT.BORDER);
		txtCupo.setBounds(145, 86, 145, 21);

		comboInstructores = new Combo(grpDetalleCreditoDeportivo, SWT.READ_ONLY);
		comboInstructores.setBounds(402, 24, 154, 23);
		comboInstructores.setItems(cRUDCreditoAcademico.obtenerListaInstructoresCombo());

		Label lblInstructor = new Label(grpDetalleCreditoDeportivo, SWT.NONE);
		lblInstructor.setBounds(321, 30, 55, 15);
		lblInstructor.setText("Instructor :");

		Label lblHorario = new Label(grpDetalleCreditoDeportivo, SWT.NONE);
		lblHorario.setBounds(321, 64, 55, 15);
		lblHorario.setText("Horario :");

	    comboHorario = new Combo(grpDetalleCreditoDeportivo, SWT.READ_ONLY);
		comboHorario.setBounds(402, 56, 154, 23);
		comboHorario.setItems(cRUDCreditoAcademico.obtenerListaHorariosCombo());

		Label lblLugar = new Label(grpDetalleCreditoDeportivo, SWT.NONE);
		lblLugar.setBounds(321, 94, 55, 15);
		lblLugar.setText("Lugar :");

	    comboLugar = new Combo(grpDetalleCreditoDeportivo, SWT.NONE);
		comboLugar.setBounds(402, 86, 154, 23);
		comboLugar.setItems(cRUDCreditoAcademico.obtenerListaLugaresCombo());

		Label lblHomologacion = new Label(grpDetalleCreditoDeportivo, SWT.NONE);
		lblHomologacion.setBounds(321, 121, 75, 15);
		lblHomologacion.setText("Homolagable :");

		txtHomogable = new Text(grpDetalleCreditoDeportivo, SWT.BORDER);
		txtHomogable.setBounds(402, 118, 154, 21);

		Button btnLimpiarCampos = new Button(grpDetalleCreditoDeportivo, SWT.NONE);
		btnLimpiarCampos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				limpiarCampos();
			}
		});
		btnLimpiarCampos.setBounds(70, 164, 99, 25);
		btnLimpiarCampos.setText("Limpiar Campos");

		Button btnEditarCredito = new Button(grpDetalleCreditoDeportivo, SWT.NONE);
		btnEditarCredito.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnEditarCredito.setBounds(299, 164, 97, 25);
		btnEditarCredito.setText("Editar Credito");

		Button btnEliminarCredito = new Button(grpDetalleCreditoDeportivo, SWT.NONE);
		btnEliminarCredito.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnEliminarCredito.setBounds(409, 164, 99, 25);
		btnEliminarCredito.setText("Eliminar Credito");


//		comboInstructores.addSelectionListener(new SelectionListener() {
//
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				try {
//					Instructor instructor = cRUDCreditoAcademico.obtenerInstructorSeleccionado(comboInstructores);
//				} catch (CreacionCreditoAcademicoException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}
//
//			@Override
//			public void widgetDefaultSelected(SelectionEvent arg0) {
//				// TODO Auto-generated method stub
//
//			}
//		});

		Button btnCrearCredito = new Button(grpDetalleCreditoDeportivo, SWT.NONE);
		btnCrearCredito.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					cRUDCreditoAcademico.CrearCreditoAcademico(txtNombreCredito.getText(), txtCodigo.getText(), txtCupo.getText(), txtDuracion.getText(), comboInstructores, comboHorario, comboLugar,txtHomogable.getText());
				} catch (CreacionCreditoAcademicoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				initDataBindings();
				limpiarCampos();
				JOptionPane.showMessageDialog(null, "El r fue registrado");

			}







		});
		btnCrearCredito.setBounds(189, 164, 89, 25);
		btnCrearCredito.setText("Crear Credito");

		txtRutaArchivo = new Text(grpDetalleCreditoDeportivo, SWT.BORDER);
		txtRutaArchivo.setBounds(179, 203, 217, 21);

		Label lblRutaArchivoReporte = new Label(grpDetalleCreditoDeportivo, SWT.NONE);
		lblRutaArchivoReporte.setBounds(35, 206, 118, 15);
		lblRutaArchivoReporte.setText("Ruta Archivo Reporte :");

		Button btnGenerarreporte = new Button(grpDetalleCreditoDeportivo, SWT.NONE);
		btnGenerarreporte.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnGenerarreporte.setBounds(419, 199, 104, 25);
		btnGenerarreporte.setText("GenerarReporte");

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



	private void limpiarCampos() {
		this.txtNombreCredito.setText("");
		this.txtCodigo.setText("");
		this.txtCupo.setText("");
		this.txtDuracion.setText("");
		this.comboInstructores.deselectAll();
		this.comboLugar.deselectAll();
		this.comboHorario.deselectAll();
		this.txtHomogable.setText("");

	}


//	private CreditoAcademico AsiganarValoresAProducto() {
//
//		CreditoAcademico creditoAcademico = new CreditoAcademico();
//		creditoAcademico.setNombre(txtNombreCredito.getText());
//		creditoAcademico.setCodigo(txtCodigo.getText());
//		creditoAcademico.setCupoMaximo(txtCupo.getText());
//		creditoAcademico.setDuracionCredito(txtDuracion.getText());
//
//		if(comboInstructores.getSelectionIndex() != -1) {
//			Instructor instructor = new Instructor(instructores[comboInstructores.getSelectionIndex()]);
//			creditoAcademico.setInstructor(instructor);
//
//		}
//
//		creditoAcademico.setHomologacion(txtHomogable.getText());
//		return creditoAcademico;
//	}
	private DataBindingContext initDataBindings() {
	DataBindingContext bindingContext = new DataBindingContext();
	//
	ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
	IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(),
			CreditoAcademico.class, new String[] {"nombre","codigo","cupoMaximo","duracionCredito", "instructor.nombre","horario.codigo","lugar.nombre","homologacion"});
	tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
	tableViewer.setContentProvider(listContentProvider);
	//
	IObservableList listaCreditosAcademicosAdminObserveList = PojoProperties.list("listaCreditosAcademicos").observe(admin);
	tableViewer.setInput(listaCreditosAcademicosAdminObserveList);
	//
	return bindingContext;

}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
