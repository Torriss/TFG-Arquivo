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
import view.Modificaciones;
import view.Prestamos;

public class ControlMenu {

	private Menu menu; //Vista
	private Devoluciones devoluciones; //Vista
	private Prestamos prestamos; //Vista
	private Exportar exportar; //Vista
	private Modificaciones modificaciones; //Vista
	private ExpurgoDAOImpl expurgo;
	private TransferenciaDAOImpl transferencia;
	
	public ControlMenu (Menu men) {
		try {
			menu = men;
			devoluciones = new Devoluciones();
			prestamos = new Prestamos();
			exportar = new Exportar();
			modificaciones = new Modificaciones();
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
		menu.getBtnModificaciones().addActionListener(e -> mostrarModificaciones());
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
	
	private void mostrarModificaciones() {
		modificaciones.setVisible(true);
		ControlModificaciones modControl = new ControlModificaciones(modificaciones);
		modControl.initControl();
	}
	
	private void elegirArchivo(String func) {
		int retVal = menu.getFc().showOpenDialog(menu);
		
		if (retVal == JFileChooser.APPROVE_OPTION) {
            String file = menu.getFc().getSelectedFile().getAbsolutePath();
            try {
            	if (func == "expurgar") {
            		if (expurgo.expurgo(file))
            		{
            			JOptionPane.showMessageDialog(null,
            					"Expurgo realizado con éxito.", "Expurgar",
            					JOptionPane.INFORMATION_MESSAGE);            			
            		}
            		else
            		{
            			JOptionPane.showMessageDialog(null,
            					"Error durante el expurgo, por favor asegúrese de haber introducido"
            					+ " un archivo con formato correcto e inténtelo de nuevo.", "Expurgar",
            					JOptionPane.ERROR_MESSAGE);
            		}
            	}
            	else {
            		if(transferencia.transferirExpedientes(file))
            		{
            			JOptionPane.showMessageDialog(null,
        						"Transferencia realizada con éxito.", "Transferir",
        						JOptionPane.INFORMATION_MESSAGE);
            			
            		}
            		else
            		{
            			JOptionPane.showMessageDialog(null,
            					"Error durante la transferencia, por favor asegúrese de haber introducido"
            					+ " un archivo con formato correcto e inténtelo de nuevo.", "Transferir",
            					JOptionPane.ERROR_MESSAGE);
            		}
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
				+ "Modificaciones: permite modificar o eliminar la información de un expediente.<br><br>"
				+ "Transferencia: permite insertar automáticamente un listado de Expedientes transferidos al Archivo desde un documento Excel.<br><br>"
				+ "Expurgo: permite expurgar auntomáticamente un listado de Expedientes del Archivo desde un documento Excel.<br><br>"
				+ "Exportar datos: permite exportar información histórica del Archivo sobre Expedientes, Transferencias o Expurgos en formato Excel.";

		JOptionPane.showMessageDialog(null,
				msg, "Ayuda",
				JOptionPane.QUESTION_MESSAGE);
	}
}
