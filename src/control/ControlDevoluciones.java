package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import DAO.ExpedienteDAOImpl;
import DAO.PrestamoDAOImpl;
import model.Expediente;
import utils.FuncComunes;
import view.Devoluciones;
import view.Prestamos;
import view.Devoluciones.BuscarUbicListener;
import view.Devoluciones.DevolverListener;
import view.Devoluciones.NuevoListener;

public class ControlDevoluciones {
	
	private ExpedienteDAOImpl expediente = new ExpedienteDAOImpl(); //Modelo
	Devoluciones devoluciones; //Vista

	FuncComunes func;
	
	public ControlDevoluciones (Devoluciones dev) {
		devoluciones = dev;
		func = new FuncComunes();
	}
	
	private void initView() {
		try {
			JComboBox<String> comboBoxJuzgado;
			comboBoxJuzgado = func.iniciarListaJuzgados(devoluciones.getComboBoxJuzgado());
			devoluciones.setComboBoxJuzgado(comboBoxJuzgado);
			JComboBox<String> comboBoxTipoExp = func.iniciarListaTipoExp(devoluciones.getComboBoxTipoExp());
			devoluciones.setComboBoxTipoExp(comboBoxTipoExp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initControl() {
		devoluciones.getBtnBuscarUbic().addActionListener(e -> buscarUbicacion());
		devoluciones.getBtnDevolver().addActionListener(e -> devolverExpediente());
		devoluciones.getBtnNuevo().addActionListener(e -> nuevoExpediente());
	}

	private void buscarUbicacion() {
		try {
			List<Expediente> expedientes = new ArrayList<>();
			expedientes = expediente.buscaExpediente(Integer.parseInt(textFieldNumExp.getText()),
					comboBoxTipoExp.getSelectedItem().toString(), Integer.parseInt(textFieldAnioExp.getText()),
					comboBoxJuzgado.getSelectedItem().toString());
			if(expedientes.isEmpty())
			{
				btnDevolver.setEnabled(false);
				btnNuevo.setEnabled(true);
			}
			else
			{
				btnDevolver.setEnabled(true);
				btnNuevo.setEnabled(false);
				//TODO: parsear info devuelta e insertarla en los textBox
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void devolverExpediente() {
		//TODO: pasar info de los textBox a devolucion(...)
	}

	private void nuevoExpediente() {
		Expediente exp = new Expediente(Integer.parseInt(textFieldNumExp.getText()), comboBoxTipoExp.getSelectedItem().toString(),
				Integer.parseInt(textFieldAnioExp.getText()), Integer.parseInt(textFieldCaja.getText()), 
				textFieldUbicacion.getText(), textFieldNotas.getText(), textFieldTomos.getText(), 
				comboBoxJuzgado.getSelectedItem().toString(), textFieldLugar.getText(), 
				Integer.parseInt(textFieldPaginas.getText()));
		try {
			expediente.insert(exp);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
