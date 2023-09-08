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
	    String msg = "<html>El Control de Modificaciones te permite buscar expedientes por número de expediente, tipo, año y juzgado.<br><br>" +
	                 "Sigue estos pasos para buscar una ubicación:<br><br>" +
	                 "1. Ingresa el número de expediente, el año, selecciona el tipo de expediente y el juzgado de tu interés.<br>" +
	                 "2. Haz clic en el botón 'Buscar'.<br>" +
	                 "3. Si el expediente se encuentra disponible, verás los resultados en la tabla.<br>" +
	                 "4. Puedes imprimir la tabla haciendo clic en el botón 'Imprimir'.<br>" +
	                 "5. ¡Listo! Ahora puedes consultar la ubicación de tu expediente.<br><br>" +
	                 "Nota: Si no se encuentran resultados, el expediente puede estar fuera del archivo o prestado.<br></html>";

	    JOptionPane.showMessageDialog(null,
	            msg, "Ayuda para Control de Modificaciones",
	            JOptionPane.INFORMATION_MESSAGE);
	}

}
