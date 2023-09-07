package utils;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JComboBox;

import DAO.ExpedienteDAOImpl;

public class FuncComunes {

	private ExpedienteDAOImpl expediente = new ExpedienteDAOImpl();
	
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

	public static String getFechaHora() {
		LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String fecha = fechaActual.format(formatoFecha);
        
        return fecha;
	}
}
