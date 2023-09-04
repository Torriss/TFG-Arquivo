package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
		menu.getBtnExportar().addActionListener(e -> exportar());
		menu.getBtnAyuda().addActionListener(e -> ayuda());
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
            try {
				expurgo.expurgo(file);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	private void transferir() {
		int retVal = menu.getFc().showOpenDialog(menu);
		
		if (retVal == JFileChooser.APPROVE_OPTION) {
            String file = menu.getFc().getSelectedFile().getAbsolutePath();
            try {
				transferencia.transferirExpedientes(file);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	private void exportar() {
		
	}
	
	private void ayuda() {
		String msg = "<html>Préstamos: permite buscar expedientes disponibles en el Archivo para solicitar un préstamo.<br><br>"
				+ "Devoluciones: permite realizar la devolución de un préstamo o la inserción de un nuevo expediente en el Archivo.<br><br>"
				+ "Modificaciones: permite modificar la información de uno o varios expedientes mediante un documento Excel.<br><br>"
				+ "Transferencia: permite insertar automáticamente un listado de Expedientes transferidos al Archivo desde un documento Excel.<br><br>"
				+ "Expurgo: permite expurgar auntomáticamente un listado de Expedientes del Archivo desde un documento Excel.<br><br>"
				+ "Exportar datos: permite exportar información histórica del Archivo sobre Expedientes, Transferencias o Expurgos en formato Excel.";

		JOptionPane.showMessageDialog(null,
				msg, "Ayuda",
				JOptionPane.QUESTION_MESSAGE);
	}
}
