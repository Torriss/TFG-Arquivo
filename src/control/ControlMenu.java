package control;

import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.JFileChooser;

import DAO.ExpurgoDAOImpl;
import DAO.TransferenciaDAOImpl;
import view.Devoluciones;
import view.Menu;
import view.Prestamos;

public class ControlMenu {

	private Menu menu; //Vista
	private Devoluciones devoluciones; //Vista
	private Prestamos prestamos; //Vista
	private ExpurgoDAOImpl expurgo;
	private TransferenciaDAOImpl transferencia;
	
	public ControlMenu (Menu men) {
		try {
			menu = men;
			devoluciones = new Devoluciones();
			prestamos = new Prestamos();
			expurgo = new ExpurgoDAOImpl();
			transferencia = new TransferenciaDAOImpl();
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
		menu.getBtnExpurgo().addActionListener(e -> expurgar());
		menu.getBtnTransferencia().addActionListener(e -> transferir());
		
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
	
	private void expurgar() {
		int retVal = menu.getFc().showOpenDialog(menu);
		
		if (retVal == JFileChooser.APPROVE_OPTION) {
            String file = menu.getFc().getSelectedFile().getAbsolutePath();
            expurgo.expurgo(file);
        }
	}
	
	private void transferir() {
		int retVal = menu.getFc().showOpenDialog(menu);
		
		if (retVal == JFileChooser.APPROVE_OPTION) {
            String file = menu.getFc().getSelectedFile().getAbsolutePath();
            transferencia.transferirExpedientes(file);
        }
	}
}
