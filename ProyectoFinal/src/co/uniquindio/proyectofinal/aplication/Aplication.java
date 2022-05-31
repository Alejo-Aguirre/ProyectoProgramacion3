package co.uniquindio.proyectofinal.aplication;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Display;

import co.uniquindio.proyectofinal.views.ContenedorView;
import co.uniquindio.proyectofinal.views.LoginView;

public class Aplication {

	/**
	 * Launch the application.
	 *
	 * @param args
	 */
	public static void main(String args[]) {
		Display display = Display.getDefault();

		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {

					LoginView loginView = new LoginView();
					loginView.open();
					loginView.setBlockOnOpen(true);
					loginView.open();
					Display.getCurrent().dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
