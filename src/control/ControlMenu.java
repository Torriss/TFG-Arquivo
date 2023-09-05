package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import DAO.ExpurgoDAOImpl;
import DAO.TransferenciaDAOImpl;
import view.Devoluciones;
import view.Exportar;
import view.Menu;
import view.Prestamos;

public class ControlMenu {

	private Menu menu; //Vista
	private Devoluciones devoluciones; //Vista
	private Prestamos prestamos; //Vista
	private Exportar exportar; //Vista
	private ExpurgoDAOImpl expurgo;
	private TransferenciaDAOImpl transferencia;
	
	public ControlMenu (Menu men) {
		try {
			menu = men;
			devoluciones = new Devoluciones();
			prestamos = new Prestamos();
			exportar = new Exportar();
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
		exportar.setVisible(false);
	}
	
	public void initControl() {
		menu.getBtnDevoluciones().addActionListener(e -> mostrarDevoluciones());
		menu.getBtnPrestamos().addActionListener(e -> mostrarPrestamos());
		menu.getBtnModificaciones().addActionListener(e -> elegirArchivo("modificar"));
		menu.getBtnTransferencia().addActionListener(e -> elegirArchivo("transferir"));
		menu.getBtnExpurgo().addActionListener(e -> elegirArchivo("expurgar"));
		menu.getBtnExportar().addActionListener(e -> mostrarExportar());
		menu.getBtnAyuda().addActionListener(e -> mostrarAyuda());
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
	
	private void elegirArchivo(String func) {
		int retVal = menu.getFc().showOpenDialog(menu);
		
		if (retVal == JFileChooser.APPROVE_OPTION) {
            String file = menu.getFc().getSelectedFile().getAbsolutePath();
            try {
            	if (func == "expurgar") {
            		expurgo.expurgo(file);
            	}
            	else {
            		// Se utiliza la misma funcion para insertar o actualizar expedientes en la BBDD
            		transferencia.transferirExpedientes(file);
            	}
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
	
	private void mostrarExportar() {
		exportar.setVisible(true);
		ControlExportar expControl = new ControlExportar(exportar);
		expControl.initControl();
	}
	
	private void mostrarAyuda() {
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
