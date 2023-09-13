package control;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import DAO.DevolucionDAOImpl;
import DAO.ExpedienteDAOImpl;
import model.Expediente;
import utils.FuncComunes;
import view.Devoluciones;
import view.TablaResultados;

public class ControlDevoluciones {
	
	private ExpedienteDAOImpl expediente = new ExpedienteDAOImpl(); //Modelo
	private DevolucionDAOImpl devolucion = new DevolucionDAOImpl(); //Modelo
	private Devoluciones devoluciones; //Vista
	private FuncComunes func;
	private ArrayList<Expediente> expedientes;
	
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
			clearControl();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initControl() {
		devoluciones.getBtnBuscarUbic().addActionListener(e -> buscarUbicacion());
		devoluciones.getBtnDevolver().addActionListener(e -> devolverExpediente());
		devoluciones.getBtnNuevo().addActionListener(e -> nuevoExpediente());
		devoluciones.getBtnAyuda().addActionListener(e -> mostrarAyuda());
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
		devoluciones.getTextFieldCaja().setEnabled(false);
		devoluciones.getTextFieldLugar().setEnabled(false);
		devoluciones.getTextFieldNotas().setEnabled(false);
		devoluciones.getTextFieldTomos().setEnabled(false);
		devoluciones.getTextFieldUbicacion().setEnabled(false);
		devoluciones.getBtnDevolver().setEnabled(false);
		devoluciones.getBtnNuevo().setEnabled(false);
		devoluciones.getDatePicker().setDate(func.fecha());
		expedientes.clear();
	}
	private void buscarUbicacion() {
		try {
			int numExp = Integer.parseInt(devoluciones.getTextFieldNumExp().getText());
			int anioExp = Integer.parseInt(devoluciones.getTextFieldAnioExp().getText());
			String tipoExp = devoluciones.getComboBoxTipoExp().getSelectedItem().toString();
			String juzgado = devoluciones.getComboBoxJuzgado().getSelectedItem().toString();
			expedientes = expediente.buscaExpediente(numExp, tipoExp, anioExp, juzgado);
			//TODO: si existe el exp y su estado es prestado se activa devoluciones
			if(expedientes.isEmpty())
			{
				devoluciones.getBtnDevolver().setEnabled(false);
				devoluciones.getBtnNuevo().setEnabled(true);
				devoluciones.getTextFieldPaginas().setEnabled(true);
				devoluciones.getTextFieldNotas().setEnabled(true);
				devoluciones.getTextFieldTomos().setEnabled(true);
				devoluciones.getTextFieldLugar().setEnabled(true);
			}
			else
			{
				devoluciones.getBtnDevolver().setEnabled(true);
				devoluciones.getBtnNuevo().setEnabled(false);
				devoluciones.getTextFieldPaginas().setEnabled(true);
				devoluciones.getTextFieldNotas().setEnabled(true);

				
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void devolverExpediente() {
		
		String fecha = devoluciones.getDatePicker().getDateStringOrEmptyString();
		
		if (fecha.compareTo("") != 0) {
			int numExp = expedientes.get(0).getNumExpediente();
			int anioExp = expedientes.get(0).getAnio();
			String tipoExp = expedientes.get(0).getTipo();
			String juzgado = expedientes.get(0).getJuzgado();
			String notas = devoluciones.getTextFieldNotas().getText();
			int paginas = Integer.parseInt(devoluciones.getTextFieldPaginas().getText());
			
			try {
				expedientes = devolucion.devolucion(numExp, anioExp, tipoExp, juzgado, notas, paginas, fecha);
				if(expedientes.isEmpty())
				{
					JOptionPane.showMessageDialog(null,
							"El expediente no se encuentra en el archivo", "Devolución",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null,
							"Devolución realizada con éxito.", "Devolución",
							JOptionPane.INFORMATION_MESSAGE);
					// TODO: llamar a funcion imprimirDevolucion con los datos del expediente
					TablaResultados tabla = new TablaResultados();
					ControlTablaResultados tablaContr = new ControlTablaResultados(tabla, expedientes);
					tablaContr.initControl();
					tabla.setVisible(true);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			JOptionPane.showMessageDialog(null,
					"Por favor, rellene los campos obligatorios: Fecha.", "Devolución",
					JOptionPane.ERROR_MESSAGE);
		}
		
		
	}

	private void nuevoExpediente() {
		int numExp = Integer.parseInt(devoluciones.getTextFieldNumExp().getText());
		int anioExp = Integer.parseInt(devoluciones.getTextFieldAnioExp().getText());
		int pags = Integer.parseInt(devoluciones.getTextFieldPaginas().getText());
		String tipoExp = devoluciones.getComboBoxTipoExp().getSelectedItem().toString();
		String juzgado = devoluciones.getComboBoxJuzgado().getSelectedItem().toString();
		String lugar = devoluciones.getTextFieldLugar().getText();
		String tomos = devoluciones.getTextFieldTomos().getText();
		String notas = devoluciones.getTextFieldNotas().getText();
		
		try {
			Expediente exp = devolucion.nuevo(numExp, anioExp, tipoExp, juzgado, pags, tomos, lugar, notas);
			
			String caja = Integer.toString(exp.getCaja());
            String ubicacion = exp.getUbicacion();
			
			devoluciones.getTextFieldCaja().setText(caja);
            devoluciones.getTextFieldUbicacion().setText(ubicacion);

            expedientes.add(exp);
            TablaResultados tabla = new TablaResultados();
			ControlTablaResultados tablaContr = new ControlTablaResultados(tabla, expedientes);
			tablaContr.initControl();
			tabla.setVisible(true);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void mostrarAyuda() {
		//TODO: meter texto de ayuda
		String msg = "<html>Para devolver un expediente, sigue estos pasos:<br><br>" +
				"1. Verifica que el expediente que deseas devolver se encuentra en tu posesión.<br>" +
				"2. Ingresa el número de expediente, tipo, año y juzgado correspondientes.<br>" +
				"3. Añade notas adicionales si es necesario.<br><br>" +
				"4. Ingresa el número de páginas nuevas si el expediente ha aumentado su tamaño.<br>" +
				"5. Indica la fecha de devolución.<br>" +
				"6. Confirma la devolución del expediente.<br>" +
				"7. El expediente será marcado como 'disponible' y estará listo para ser prestado nuevamente.<br>" +
				"8. ¡Listo! El expediente ha sido devuelto con éxito.</html>";

		JOptionPane.showMessageDialog(null,
				msg, "Ayuda",
				JOptionPane.QUESTION_MESSAGE);
	}
}
