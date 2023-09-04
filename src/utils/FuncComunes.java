package utils;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;

import DAO.ExpedienteDAOImpl;

public class FuncComunes {

	private ExpedienteDAOImpl expediente = new ExpedienteDAOImpl();
	
//	public JComboBox<String> iniciarListaEstado(JComboBox<String> comboBoxEstado) {
//		ArrayList<String> listaEstados = new ArrayList<String>();
//		listaEstados.add("Consulta");
//		listaEstados.add("No bajado al archivo");
//		listaEstados.add("No encontrado");
//		listaEstados.add("Se presta ahora");
//		listaEstados.add("Ya prestado antes");
//		
//		comboBoxEstado.removeAllItems();
//		
//		for (int i = 0; i<listaEstados.size(); i++)
//		{
//			comboBoxEstado.addItem(listaEstados.get(i));
//		}
//		return comboBoxEstado;
//		
//	}
	
	public JComboBox<String> iniciarListaJuzgados(JComboBox<String> comboBoxJuzgado) throws SQLException {
		ArrayList<String> listaJuzgados;
		try {
			listaJuzgados = expediente.getAllJuzgados();
			comboBoxJuzgado.removeAllItems();
			for (int i = 0; i<listaJuzgados.size(); i++)
			{
				comboBoxJuzgado.addItem(listaJuzgados.get(i));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comboBoxJuzgado;
	}
	
	public JComboBox<String> iniciarListaTipoExp(JComboBox<String> comboBoxTipoExp) throws SQLException {
		ArrayList<String> listaTiposExp;
		try {
			listaTiposExp = expediente.getAllTiposExp();
		
			comboBoxTipoExp.removeAllItems();
			for (int i = 0; i<listaTiposExp.size(); i++)
			{
				comboBoxTipoExp.addItem(listaTiposExp.get(i));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comboBoxTipoExp;
	}

}
