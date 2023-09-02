package control;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import DAO.DevolucionDAOImpl;
import DAO.ExpedienteDAOImpl;
import model.Expediente;
import utils.FuncComunes;
import view.Devoluciones;

public class ControlDevoluciones {
	
	private ExpedienteDAOImpl expediente = new ExpedienteDAOImpl(); //Modelo
	private DevolucionDAOImpl devolucion = new DevolucionDAOImpl(); //Modelo
	private Devoluciones devoluciones; //Vista
	private FuncComunes func;
	private List<Expediente> expedientes;
	
	public ControlDevoluciones (Devoluciones dev) {
		devoluciones = dev;
		func = new FuncComunes();
		expedientes = new ArrayList<>();
		initView();
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
	
	public void initControl() {
		devoluciones.getBtnBuscarUbic().addActionListener(e -> buscarUbicacion());
		devoluciones.getBtnDevolver().addActionListener(e -> devolverExpediente());
		devoluciones.getBtnNuevo().addActionListener(e -> nuevoExpediente());
	}

	private void clearControl() {
		devoluciones.getTextFieldNumExp().setText("");
		devoluciones.getTextFieldAnioExp().setText("");
		devoluciones.getTextFieldPaginas().setText("");
		devoluciones.getTextFieldCaja().setText("");
		devoluciones.getDatePicker().setText("");
		devoluciones.getTextFieldLugar().setText("");
		devoluciones.getTextFieldNotas().setText("");
		devoluciones.getTextFieldTomos().setText("");
		devoluciones.getTextFieldUbicacion().setText("");
		devoluciones.getTextFieldPaginas().setEnabled(false);
		devoluciones.getTextFieldPaginas().setBackground(Color.lightGray);;
		devoluciones.getTextFieldLugar().setEnabled(false);
		devoluciones.getTextFieldLugar().setBackground(Color.lightGray);
		devoluciones.getTextFieldNotas().setEnabled(false);
		devoluciones.getTextFieldNotas().setBackground(Color.lightGray);
		devoluciones.getTextFieldTomos().setEnabled(false);
		devoluciones.getTextFieldTomos().setBackground(Color.lightGray);
		devoluciones.getTextFieldUbicacion().setEnabled(false);
		devoluciones.getTextFieldUbicacion().setBackground(Color.lightGray);
		devoluciones.getBtnDevolver().setEnabled(false);
		devoluciones.getBtnNuevo().setEnabled(false);
	}
	private void buscarUbicacion() {
		try {
			int numExp = Integer.parseInt(devoluciones.getTextFieldNumExp().getText());
			int anioExp = Integer.parseInt(devoluciones.getTextFieldAnioExp().getText());
			String tipoExp = devoluciones.getComboBoxTipoExp().getSelectedItem().toString();
			String juzgado = devoluciones.getComboBoxJuzgado().getSelectedItem().toString();
			expedientes = expediente.buscaExpediente(numExp, tipoExp, anioExp, juzgado);
			if(expedientes.isEmpty())
			{
				devoluciones.getBtnDevolver().setEnabled(false);
				devoluciones.getBtnNuevo().setEnabled(true);
				devoluciones.getTextFieldPaginas().setEnabled(true);
				devoluciones.getTextFieldPaginas().setBackground(Color.white);
				devoluciones.getTextFieldLugar().setEnabled(true);
				devoluciones.getTextFieldLugar().setBackground(Color.white);
				devoluciones.getTextFieldNotas().setEnabled(true);
				devoluciones.getTextFieldNotas().setBackground(Color.white);
				devoluciones.getTextFieldTomos().setEnabled(true);
				devoluciones.getTextFieldTomos().setBackground(Color.white);
				devoluciones.getTextFieldUbicacion().setEnabled(true);
				devoluciones.getTextFieldUbicacion().setBackground(Color.white);
			}
			else
			{
				devoluciones.getBtnDevolver().setEnabled(true);
				devoluciones.getBtnNuevo().setEnabled(false);
				devoluciones.getTextFieldPaginas().setEnabled(true);
				devoluciones.getTextFieldPaginas().setBackground(Color.white);
				
				String caja = Integer.toString(expedientes.get(0).getCaja());
	            String ubicacion = expedientes.get(0).getUbicacion();
	            String tomos = expedientes.get(0).getTomos();
	            String lugar = expedientes.get(0).getLugar();
	            String paginas = Integer.toString(expedientes.get(0).getPaginas());
	            
	            devoluciones.getTextFieldCaja().setText(caja);
	            devoluciones.getTextFieldUbicacion().setText(ubicacion);
	            devoluciones.getTextFieldTomos().setText(tomos);
	            devoluciones.getTextFieldLugar().setText(lugar);
	            devoluciones.getTextFieldPaginas().setText(paginas);
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void devolverExpediente() {
		int numExp = expedientes.get(0).getNumExpediente();
		int anioExp = expedientes.get(0).getAnio();
		String tipoExp = expedientes.get(0).getTipo();
		String juzgado = expedientes.get(0).getJuzgado();
		String notas = devoluciones.getTextFieldNotas().getText();
		int paginas = Integer.parseInt(devoluciones.getTextFieldPaginas().getText());
		
		try {
			expedientes = devolucion.devolucion(numExp, anioExp, tipoExp, juzgado, notas, paginas);
			if(expedientes.isEmpty())
			{
				JOptionPane.showMessageDialog(null,
						"El expediente no se encuentra en el archivo", "Devolución",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				// TODO: pensar como mostar la lista en el dialogo
				JOptionPane.showMessageDialog(null,
						"Devolver expediente en: ", "Devolución",
						JOptionPane.INFORMATION_MESSAGE);
			}
			expedientes.clear();
			clearControl();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void nuevoExpediente() {
		int numExp = Integer.parseInt(devoluciones.getTextFieldNumExp().getText());
		int anioExp = Integer.parseInt(devoluciones.getTextFieldAnioExp().getText());
		int caja = Integer.parseInt(devoluciones.getTextFieldCaja().getText());
		int pags = Integer.parseInt(devoluciones.getTextFieldPaginas().getText());
		String tipoExp = devoluciones.getComboBoxTipoExp().getSelectedItem().toString();
		String juzgado = devoluciones.getComboBoxJuzgado().getSelectedItem().toString();
		String ubicacion = devoluciones.getTextFieldUbicacion().getText();
		String notas = devoluciones.getTextFieldNotas().getText();
		String tomos = devoluciones.getTextFieldTomos().getText();
		String lugar = devoluciones.getTextFieldLugar().getText();
		
		Expediente exp = new Expediente(numExp, tipoExp, anioExp, caja, ubicacion,
				notas, tomos, juzgado, lugar, pags, null, null);
		try {
			expediente.insert(exp);
			
			expedientes.clear();
			clearControl();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
