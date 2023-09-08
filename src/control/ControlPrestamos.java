package control;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import DAO.ExpedienteDAOImpl;
import DAO.PrestamoDAOImpl;
import model.Expediente;
import utils.FuncComunes;
import view.Prestamos;
import view.TablaResultados;

public class ControlPrestamos{

	private PrestamoDAOImpl prestamo = new PrestamoDAOImpl(); //Modelo
	private ExpedienteDAOImpl expediente = new ExpedienteDAOImpl(); //Modelo
	private Prestamos prestamos; //Vista
	private FuncComunes func;
	private ArrayList<Expediente> expedientes;
	
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
		prestamos.getBtnPrestar().addActionListener(e -> realizarPrestamo());
		prestamos.getBtnAyuda().addActionListener(e -> mostrarAyuda());
	}

	private void clearControl() {
		prestamos.getTextFieldNumExp().setText("");
		prestamos.getTextFieldAnioExp().setText("");
		prestamos.getTextFieldSolicitante().setText("");
		prestamos.getDatePicker().setText("");
		prestamos.getBtnPrestar().setEnabled(false);
		prestamos.getDatePicker().setDate(func.fecha());
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
				//TODO: revisar si el expediente no esta en el archivo o esta prestado, no distingue
				JOptionPane.showMessageDialog(null,
						"El expediente no se encuentra disponible", "Ubicación",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				TablaResultados tabla = new TablaResultados();
				ControlTablaResultados tablaContr = new ControlTablaResultados(tabla, expedientes);
				tablaContr.initControl();
				tabla.setVisible(true);
				prestamos.getBtnPrestar().setEnabled(true);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void realizarPrestamo() {
		try {
			String solicitante = prestamos.getTextFieldSolicitante().getText();
			String fecha = prestamos.getDatePicker().getDateStringOrEmptyString();
			
			if (solicitante.compareTo("") != 0 && fecha.compareTo("") != 0) {
				int solic = Integer.parseInt(solicitante);
				int numExp = expedientes.get(0).getNumExpediente();
				int anioExp = expedientes.get(0).getAnio();
				String tipoExp = expedientes.get(0).getTipo();
				String juzgado = expedientes.get(0).getJuzgado();
				
				
				
				expedientes = prestamo.realizarPrestamo(numExp, tipoExp, anioExp, solic, juzgado, fecha);
				if(expedientes.isEmpty())
				{
					//TODO: revisar
					JOptionPane.showMessageDialog(null,
							"El expediente no se encuentra disponible", "Ubicación",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null,
							"Prestamo realizado con éxito.", "Préstamo",
							JOptionPane.INFORMATION_MESSAGE);
					//TODO: crear funcion imprimirPapeleta con toda la info del form
					//TODO: crear funcion imprimirTestigo con toda la info del form
					//y añadiendo caja, ubicación, notas, tomos, lugar
				}
			}
			else {
				JOptionPane.showMessageDialog(null,
						"Por favor, rellene los campos obligatorios: Número de empleado, provincia y fecha.", "Préstamo",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (IllegalArgumentException e2) {
			JOptionPane.showMessageDialog(null,
					e2.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			
			System.out.println(e2.getMessage());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void mostrarAyuda() {
	    String msg = "<html>El Control de Préstamos te permite buscar expedientes disponibles y realizar préstamos.<br><br>" +
	                 "Sigue estos pasos para realizar un préstamo:<br><br>" +
	                 "1. Ingresa el número de expediente, el año, selecciona el tipo de expediente y el juzgado de tu interés.<br>" +
	                 "2. Haz clic en el botón 'Buscar Ubicación'.<br>" +
	                 "3. Si el expediente está disponible, verás los resultados en la tabla y se habilitará el botón 'Prestar'.<br>" +
	                 "4. Ingresa el número de empleado del solicitante y la fecha del préstamo.<br>" +
	                 "5. Haz clic en el botón 'Prestar' para realizar el préstamo.<br>" +
	                 "6. Si el préstamo se realiza con éxito, verás un mensaje de confirmación.<br>" +
	                 "7. Puedes imprimir la papeleta y el testigo del préstamo para registrar la información.<br><br>" +
	                 "Nota: Asegúrate de llenar todos los campos obligatorios antes de realizar el préstamo.<br></html>";

	    JOptionPane.showMessageDialog(null,
	            msg, "Ayuda para Control de Préstamos",
	            JOptionPane.INFORMATION_MESSAGE);
	}

}
