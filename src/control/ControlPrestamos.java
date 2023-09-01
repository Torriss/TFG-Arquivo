package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	Prestamos prestamos; //Vista
	FuncComunes func;
	
	public ControlPrestamos (Prestamos pr) {
		prestamos = pr;
		func = new FuncComunes();
	}
	
	private void initView() {
		try {
			JComboBox<String> comboBoxEstado = func.iniciarListaEstado(prestamos.getComboBoxEstado());
			prestamos.setComboBoxEstado(comboBoxEstado);
			JComboBox<String> comboBoxJuzgado;
			comboBoxJuzgado = func.iniciarListaJuzgados(prestamos.getComboBoxJuzgado());
			prestamos.setComboBoxJuzgado(comboBoxJuzgado);
			JComboBox<String> comboBoxTipoExp = func.iniciarListaTipoExp(prestamos.getComboBoxTipoExp());
			prestamos.setComboBoxTipoExp(comboBoxTipoExp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initControl() {
		prestamos.getBtnBuscarUbic().addActionListener(e -> buscarUbicacion());
		prestamos.getBtnImprimir().addActionListener(e -> imprimirPrestamo());
	}

	private void buscarUbicacion() {
		try {
			List<Expediente> expedientes = new ArrayList<>();
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
				//TODO: actualizar estado y habilitar boton imprimir si esta disponible
				// TODO: pensar como mostar la lista en el dialogo
				JOptionPane.showMessageDialog(null,
						"El expediente se encuentra en: ", "Ubicación",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void imprimirPrestamo() {
		try {
			List<Expediente> expedientes = new ArrayList<>();
			int numExp = Integer.parseInt(prestamos.getTextFieldNumExp().getText());
			int anioExp = Integer.parseInt(prestamos.getTextFieldAnioExp().getText());
			String tipoExp = prestamos.getComboBoxTipoExp().getSelectedItem().toString();
			String solicitante = prestamos.getTextFieldSolicitante().getText();
			String juzgado = prestamos.getComboBoxJuzgado().getSelectedItem().toString();
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
				//TODO: crear funcion imprimirPapeleta con toda la info del form menos el estado
				//TODO: crear funcion imprimirTestigo con toda la info del form menos el estado
				//y añadiendo caja, ubicación, notas, tomos, lugar
				JOptionPane.showMessageDialog(null,
						"El expediente se encuentra en: ", "Ubicación",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
