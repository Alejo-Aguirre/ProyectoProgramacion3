package co.uniquindio.proyectofinal.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CreditoDeportivoView extends Composite {
	private DataBindingContext m_bindingContext;

	private Table tableCreditosDeportivos;
	private Text txtNombreCredito;
	private Text txtCodigo;
	private Text txtDuracion;
	private Text txtCupo;
	private Text txtAsistencia;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CreditoDeportivoView(Composite parent, int style) {
		super(parent, style);
		setLayout(null);

		Group grpListaCreditosDeportivos = new Group(this, SWT.NONE);
		grpListaCreditosDeportivos.setText("Lista Creditos Deportivos");
		grpListaCreditosDeportivos.setBounds(10, 10, 576, 233);

		TableViewer tableViewer = new TableViewer(grpListaCreditosDeportivos, SWT.BORDER | SWT.FULL_SELECTION);
		tableCreditosDeportivos = tableViewer.getTable();
		tableCreditosDeportivos.setLinesVisible(true);
		tableCreditosDeportivos.setHeaderVisible(true);
		tableCreditosDeportivos.setBounds(10, 20, 556, 203);

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
		TableColumn tblclmnAsistencia = tableViewerColumn_7.getColumn();
		tblclmnAsistencia.setWidth(74);
		tblclmnAsistencia.setText("Asistencia");

		Group grpDetalleCreditoDeportivo = new Group(this, SWT.NONE);
		grpDetalleCreditoDeportivo.setText("Detalle Credito Deportivo");
		grpDetalleCreditoDeportivo.setBounds(20, 249, 566, 215);

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
		lblCupo.setBounds(21, 118, 55, 15);
		lblCupo.setText("Cupo :");

		txtDuracion = new Text(grpDetalleCreditoDeportivo, SWT.BORDER);
		txtDuracion.setBounds(145, 83, 145, 21);

		Label lblDuracionDelCredito = new Label(grpDetalleCreditoDeportivo, SWT.NONE);
		lblDuracionDelCredito.setBounds(21, 86, 118, 15);
		lblDuracionDelCredito.setText("Duracion del Credito :");

		txtCupo = new Text(grpDetalleCreditoDeportivo, SWT.BORDER);
		txtCupo.setBounds(145, 115, 145, 21);

		Combo comboInstructores = new Combo(grpDetalleCreditoDeportivo, SWT.NONE);
		comboInstructores.setBounds(392, 24, 164, 23);

		Label lblInstructor = new Label(grpDetalleCreditoDeportivo, SWT.NONE);
		lblInstructor.setBounds(321, 30, 55, 15);
		lblInstructor.setText("Instructor :");

		Label lblHorario = new Label(grpDetalleCreditoDeportivo, SWT.NONE);
		lblHorario.setBounds(321, 64, 55, 15);
		lblHorario.setText("Horario :");

		Combo comboHorario = new Combo(grpDetalleCreditoDeportivo, SWT.NONE);
		comboHorario.setBounds(392, 56, 164, 23);

		Label lblLugar = new Label(grpDetalleCreditoDeportivo, SWT.NONE);
		lblLugar.setBounds(321, 94, 55, 15);
		lblLugar.setText("Lugar :");

		Combo comboLugar = new Combo(grpDetalleCreditoDeportivo, SWT.NONE);
		comboLugar.setBounds(392, 86, 164, 23);

		Label lblAsistencia = new Label(grpDetalleCreditoDeportivo, SWT.NONE);
		lblAsistencia.setBounds(321, 121, 55, 15);
		lblAsistencia.setText("Asistencia :");

		txtAsistencia = new Text(grpDetalleCreditoDeportivo, SWT.BORDER);
		txtAsistencia.setBounds(392, 118, 164, 21);

		Button btnLimpiarCampos = new Button(grpDetalleCreditoDeportivo, SWT.NONE);
		btnLimpiarCampos.setBounds(70, 164, 99, 25);
		btnLimpiarCampos.setText("Limpiar Campos");

		Button btnEditarCredito = new Button(grpDetalleCreditoDeportivo, SWT.NONE);
		btnEditarCredito.setBounds(299, 164, 97, 25);
		btnEditarCredito.setText("Editar Credito");

		Button btnEliminarCredito = new Button(grpDetalleCreditoDeportivo, SWT.NONE);
		btnEliminarCredito.setBounds(409, 164, 99, 25);
		btnEliminarCredito.setText("Eliminar Credito");

		Button btnCrearCredito = new Button(grpDetalleCreditoDeportivo, SWT.NONE);
		btnCrearCredito.setBounds(196, 164, 75, 25);
		btnCrearCredito.setText("Crear Credito");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
