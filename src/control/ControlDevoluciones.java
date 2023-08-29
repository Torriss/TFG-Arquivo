package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import DAO.ExpedienteDAOImpl;
import model.Expediente;
import utils.FuncComunes;
import view.Devoluciones;

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
			int numExp = Integer.parseInt(devoluciones.getTextFieldNumExp().getText());
			int anioExp = Integer.parseInt(devoluciones.getTextFieldAnioExp().getText());
			String tipoExp = devoluciones.getComboBoxTipoExp().getSelectedItem().toString();
			String juzgado = devoluciones.getComboBoxJuzgado().getSelectedItem().toString();
			expedientes = expediente.buscaExpediente(numExp, tipoExp, anioExp, juzgado);
			if(expedientes.isEmpty())
			{
				devoluciones.getBtnDevolver().setEnabled(false);
				devoluciones.getBtnNuevo().setEnabled(true);
			}
			else
			{
				devoluciones.getBtnDevolver().setEnabled(true);
				devoluciones.getBtnNuevo().setEnabled(false);

				//TODO: parsear info devuelta e insertarla en textbox
				if (!expedientes.isEmpty())
				{
		            int caja = expedientes.get(0).getCaja();
		            String ubicacion = expedientes.get(0).getUbicacion();
		            String tomos = expedientes.get(0).getTomos();
		            String lugar = expedientes.get(0).getLugar();
		            int paginas = expedientes.get(0).getPaginas();
		            
		            //devoluciones.getTextFieldCaja().setText(caja);
		            devoluciones.getTextFieldUbicacion().setText(ubicacion);
		            devoluciones.getTextFieldTomos().setText(tomos);
		            devoluciones.getTextFieldLugar().setText(lugar);
		            //devoluciones.getTextFieldPaginas().setText(paginas);
				}
				
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
		
		Expediente exp = new Expediente(numExp, tipoExp, anioExp, caja, ubicacion, notas, tomos, juzgado, lugar, pags);
		try {
			expediente.insert(exp);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
