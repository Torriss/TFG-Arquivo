package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JComboBox;

import DAO.ExpedienteDAOImpl;
import DAO.PrestamoDAOImpl;
import model.Expediente;
import model.Juzgado;
import model.Prestamo;

public class FuncComunes {

	private ExpedienteDAOImpl expediente = new ExpedienteDAOImpl();
	
	public JComboBox<String> iniciarListaEstado(JComboBox<String> comboBoxEstado) {
		ArrayList<String> listaEstados = new ArrayList<String>();
		listaEstados.add("Consulta");
		listaEstados.add("No bajado al archivo");
		listaEstados.add("No encontrado");
		listaEstados.add("Se presta ahora");
		listaEstados.add("Ya prestado antes");
		
		comboBoxEstado.removeAllItems();
		
		for (int i = 0; i<listaEstados.size(); i++)
		{
			comboBoxEstado.addItem(listaEstados.get(i));
		}
		return comboBoxEstado;
		
	}
	
	public JComboBox<Juzgado> iniciarListaJuzgados(JComboBox<Juzgado> comboBoxJuzgado) throws SQLException {
		Juzgado juzgado = new Juzgado();
		ArrayList<Juzgado> listaJuzgados = juzgado.getJuzgados();
		
		comboBoxJuzgado.removeAllItems();
		for (int i = 0; i<listaJuzgados.size(); i++)
		{
			comboBoxJuzgado.addItem(listaJuzgados.get(i));
		}
		return comboBoxJuzgado;
	}
	
	public JComboBox<String> iniciarListaTipoExp(JComboBox<String> comboBoxTipoExp) throws SQLException {
		ArrayList<String> listaTiposExp = expediente.getAllTiposExp();
		
		comboBoxTipoExp.removeAllItems();
		
		for (int i = 0; i<listaTiposExp.size(); i++)
		{
			comboBoxTipoExp.addItem(listaTiposExp.get(i));
		}
		return comboBoxTipoExp;
	}
	
	public class CalendarioListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {	
			//TODO: revisar con LGoodDatePicker
		}
	}
	
//	public class UltCajasListener implements ActionListener {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//		}
//	}
	

	
//	public class ImprimirDevolucListener implements ActionListener {
//		@Override
//		public void actionPerformed(ActionEvent e) {	
//			//TODO: crear funcion
//		}
//	}
}
