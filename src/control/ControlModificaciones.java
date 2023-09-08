package control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import DAO.ExpedienteDAOImpl;
import model.Expediente;
import utils.FuncComunes;
import view.Modificaciones;
import view.TablaResultados;

public class ControlModificaciones {

	private ExpedienteDAOImpl expediente = new ExpedienteDAOImpl(); //Modelo
	private Modificaciones modificaciones; //Vista
	private FuncComunes func;
	private ArrayList<Expediente> expedientes;

	public ControlModificaciones (Modificaciones mod) {
		modificaciones = mod;
		func = new FuncComunes();
		expedientes = new ArrayList<>();
		initView();
	}
	
	private void initView() {
		try {
			JComboBox<String> comboBoxJuzgado;
			comboBoxJuzgado = func.iniciarListaJuzgados(modificaciones.getComboBoxJuzgado());
			modificaciones.setComboBoxJuzgado(comboBoxJuzgado);
			JComboBox<String> comboBoxTipoExp = func.iniciarListaTipoExp(modificaciones.getComboBoxExpediente());
			modificaciones.setComboBoxExpediente(comboBoxTipoExp);
			clearControl();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initControl() {
		modificaciones.getBtnBuscar().addActionListener(e -> buscarUbicacion());
		modificaciones.getBtnAyuda().addActionListener(e -> mostrarAyuda());
	}

	private void clearControl() {
		modificaciones.getTextFieldNumExp().setText("");
		modificaciones.getTextFieldAnio().setText("");
		expedientes.clear();
	}
	
	private void buscarUbicacion() {
		try {
			int numExp = Integer.parseInt(modificaciones.getTextFieldNumExp().getText());
			int anioExp = Integer.parseInt(modificaciones.getTextFieldAnio().getText());
			String tipoExp = modificaciones.getComboBoxExpediente().getSelectedItem().toString();
			String juzgado = modificaciones.getComboBoxJuzgado().getSelectedItem().toString();
			expedientes = expediente.buscaExpediente(numExp, tipoExp, anioExp, juzgado);
			if(expedientes.isEmpty())
			{
				//TODO: revisar si el expediente no esta en el archivo o esta prestado, no distingue
				JOptionPane.showMessageDialog(null,
						"El expediente no se encuentra disponible", "Ubicación",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				TablaResultados tabla = new TablaResultados();
				ControlTablaResultados tablaContr = new ControlTablaResultados(tabla, expedientes);
				tablaContr.initControl();
				tablaContr.hideButtonImprimir();
				tabla.setVisible(true);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void mostrarAyuda() {
		//TODO: meter texto de ayuda
		String msg = "<html>Esto escribe una linea<br><br>"
				+ "Con cada br se añade un salto de linea<br><br>";

		JOptionPane.showMessageDialog(null,
				msg, "Ayuda",
				JOptionPane.QUESTION_MESSAGE);
	}
}
