package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import DAO.ExpedienteDAOImpl;
import DAO.PrestamoDAOImpl;
import model.Expediente;
import utils.FuncComunes;
import view.Prestamos;

public class ControlPrestamos{

	private PrestamoDAOImpl prestamo = new PrestamoDAOImpl(); //Modelo
	private ExpedienteDAOImpl expediente = new ExpedienteDAOImpl(); //Modelo
	private Prestamos prestamos; //Vista
	private FuncComunes func;
	private List<Expediente> expedientes;
	
	public ControlPrestamos (Prestamos pr) {
		prestamos = pr;
		func = new FuncComunes();
		expedientes = new ArrayList<>();
		initView();
	}
	
	private void initView() {
		try {
			JComboBox<String> comboBoxJuzgado;
			comboBoxJuzgado = func.iniciarListaJuzgados(prestamos.getComboBoxJuzgado());
			prestamos.setComboBoxJuzgado(comboBoxJuzgado);
			JComboBox<String> comboBoxTipoExp = func.iniciarListaTipoExp(prestamos.getComboBoxTipoExp());
			prestamos.setComboBoxTipoExp(comboBoxTipoExp);
			clearControl();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initControl() {
		prestamos.getBtnBuscarUbic().addActionListener(e -> buscarUbicacion());
		prestamos.getBtnImprimir().addActionListener(e -> imprimirPrestamo());
	}

	private void clearControl() {
		prestamos.getTextFieldNumExp().setText("");
		prestamos.getTextFieldAnioExp().setText("");
		prestamos.getTextFieldSolicitante().setText("");
		prestamos.getDatePicker().setText("");
		prestamos.getTextFieldLugar().setText("");
		prestamos.getBtnImprimir().setEnabled(false);
		
		expedientes.clear();
	}
	
	private void buscarUbicacion() {
		try {
			int numExp = Integer.parseInt(prestamos.getTextFieldNumExp().getText());
			int anioExp = Integer.parseInt(prestamos.getTextFieldAnioExp().getText());
			String tipoExp = prestamos.getComboBoxTipoExp().getSelectedItem().toString();
			String juzgado = prestamos.getComboBoxJuzgado().getSelectedItem().toString();
			expedientes = expediente.buscaExpediente(numExp, tipoExp, anioExp, juzgado);
			if(expedientes.isEmpty())
			{
				JOptionPane.showMessageDialog(null,
						"El expediente no se encuentra en el archivo", "Ubicación",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				prestamos.getBtnImprimir().setEnabled(true);	
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void imprimirPrestamo() {
		try {
			int numExp = expedientes.get(0).getNumExpediente();
			int anioExp = expedientes.get(0).getAnio();
			String tipoExp = expedientes.get(0).getTipo();
			String juzgado = expedientes.get(0).getJuzgado();
			int solicitante = Integer.parseInt(prestamos.getTextFieldSolicitante().getText());
			String fecha = prestamos.getDatePicker().getDateStringOrEmptyString();

			expedientes = prestamo.realizarPrestamo(numExp, tipoExp, anioExp, solicitante, juzgado, fecha);
			if(expedientes.isEmpty())
			{
				JOptionPane.showMessageDialog(null,
						"El expediente no se encuentra en el archivo", "Ubicación",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				//TODO: crear funcion imprimirPapeleta con toda la info del form
				//TODO: crear funcion imprimirTestigo con toda la info del form
				//y añadiendo caja, ubicación, notas, tomos, lugar
				JOptionPane.showMessageDialog(null,
						"El expediente se encuentra en: ", "Ubicación",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
			clearControl();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
