package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;

import model.Expediente;
import model.Juzgado;
import model.Prestamo;
import view.Consultas;
import view.Devoluciones;
import view.Prestamos;

public class FuncComunes {
	
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
	
	public JComboBox<Juzgado> iniciarListaJuzgados(JComboBox<Juzgado> comboBoxJuzgado) {
		Prestamo prestamo = new Prestamo();
		ArrayList<Juzgado> listaJuzgados = prestamo.getJuzgados();
		
		comboBoxJuzgado.removeAllItems();
		for (int i = 0; i<listaJuzgados.size(); i++)
		{
			comboBoxJuzgado.addItem(listaJuzgados.get(i));
		}
		return comboBoxJuzgado;
	}
	
	public JComboBox<String> iniciarListaTipoExp(JComboBox<String> comboBoxTipoExp) throws SQLException {
		ArrayList<String> listaTiposExp = Expediente.getAllTiposExp();
		
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
	
	public class BuscarUbicListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO: crear funcion buscar ubicacion del expediente con tipoExp, numExp, anioExp, juzgado
			try {
				Object obj = e.getSource();
				Consultas cons = new Consultas();
				Prestamos pr = new Prestamos();
				Devoluciones dev = new Devoluciones();
				if (obj.equals(cons))
				{
					
				}
				else if (obj.equals(pr))
				{
					
				}
				else if (obj.equals(dev))
				{
					
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public class AsignarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {	
			//TODO: crear funcion buscar ubicacion del expediente con tipoExp, numExp, anioExp, juzgado
		}
	}
	
	public class NuevoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {	
			//TODO: crear funcion buscar ubicacion del expediente con tipoExp, numExp, anioExp, juzgado
		}
	}
	
	public class UltCajasListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {	
			//TODO: crear funcion buscar ubicacion del expediente con tipoExp, numExp, anioExp, juzgado
		}
	}
	
	public class ImprimirListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {	
			//TODO: crear funcion imprimirPapeleta con toda la info del form menos el estado
			
			//TODO: crear funcion imprimirTestigo con toda la info del form menos el estado
			//y añadiendo caja, ubicación, notas, tomos, lugar
		}
	}
}
