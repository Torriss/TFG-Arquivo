package control;

import java.sql.SQLException;

import view.Devoluciones;
import view.Menu;
import view.Prestamos;

public class ControlMenu {

	private Menu menu; //Vista
	private Devoluciones devoluciones; //Vista
	private Prestamos prestamos; //Vista
	
	public ControlMenu () {
		try {
			menu = new Menu();
			devoluciones = new Devoluciones();
			prestamos = new Prestamos();
			initView();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initView() {
		devoluciones.setVisible(false);
		prestamos.setVisible(false);
	}
	
	public void initControl() {
		menu.getBtnDevoluciones().addActionListener(e -> mostrarDevoluciones());
		menu.getBtnPrestamos().addActionListener(e -> mostrarPrestamos());
	}
	
	private void mostrarDevoluciones() {
		devoluciones.setVisible(true);
		ControlDevoluciones devControl = new ControlDevoluciones(devoluciones);
		devControl.initControl();
	}

	private void mostrarPrestamos() {
		prestamos.setVisible(true);
		ControlPrestamos prControl = new ControlPrestamos(prestamos);
		prControl.initControl();
	}
}
