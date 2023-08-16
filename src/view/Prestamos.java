package view;

import utils.FuncComunes;
import model.Expediente;
import model.Juzgado;
import model.Prestamo;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.TextField;

public class Prestamos extends JFrame {

	private static final long serialVersionUID = 247041135132800079L;
	private JPanel contentPanePrestamos;
	private JTextField textFieldNumExp;
	private JTextField textFieldAnioExp;
	private JTextField textFieldSolicitante;
	private JTextField textFieldLugar;
	private JTextField textFieldTipoExp;
	private JComboBox<String> comboBoxTipoExp;
	private JComboBox<String> comboBoxEstado;
	private JComboBox<Juzgado> comboBoxJuzgado;
	private JButton btnBuscarUbic;
	private JButton btnImprimir;
	private JTextField textFieldJuzgado;

	public Prestamos() throws SQLException {
		setTitle("Préstamos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPanePrestamos = new JPanel();
		contentPanePrestamos.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanePrestamos);
		contentPanePrestamos.setLayout(new GridLayout(2, 1, 0, 0));
		
		FuncComunes func = new FuncComunes();
		
		JPanel panel_1 = new JPanel();
		contentPanePrestamos.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblExpediente = new JLabel("Expediente");
		panel_3.add(lblExpediente);
		
		comboBoxTipoExp = new JComboBox<String>();
		comboBoxTipoExp = func.iniciarListaTipoExp(comboBoxTipoExp);
		panel_3.add(comboBoxTipoExp);
		
		textFieldTipoExp = new JTextField();
		panel_3.add(textFieldTipoExp);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblSolicitante = new JLabel("Solicitante");
		panel_4.add(lblSolicitante);
		
		textFieldSolicitante = new JTextField();
		panel_4.add(textFieldSolicitante);
		textFieldSolicitante.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		textFieldNumExp = new JTextField();
		panel_2.add(textFieldNumExp);
		textFieldNumExp.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("/");
		panel_2.add(lblNewLabel);
		
		textFieldAnioExp = new JTextField();
		panel_2.add(textFieldAnioExp);
		textFieldAnioExp.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		panel_6.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblLugar = new JLabel("Lugar");
		panel_6.add(lblLugar);
		
		textFieldLugar = new JTextField();
		panel_6.add(textFieldLugar);
		textFieldLugar.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblJuzgado = new JLabel("Juzgado");
		panel_7.add(lblJuzgado);
		
		comboBoxJuzgado = new JComboBox<Juzgado>();
		comboBoxJuzgado = func.iniciarListaJuzgados(comboBoxJuzgado);
		panel_7.add(comboBoxJuzgado);
		
		textFieldJuzgado = new JTextField();
		panel_7.add(textFieldJuzgado);
		textFieldJuzgado.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9);
		panel_9.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblFecha = new JLabel("Fecha");
		panel_9.add(lblFecha);
		
		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 1, 0, 0));
		
		DatePicker datePicker = new DatePicker();
	    datePicker.getComponentToggleCalendarButton().addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	}
	    });
	    datePicker.setDateToToday();
	    panel_10.add(datePicker);
		
		FuncComunes.CalendarioListener calendario = func.new CalendarioListener();
		
		JPanel panel = new JPanel();
		contentPanePrestamos.add(panel);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_8 = new JPanel();
		panel.add(panel_8);
		panel_8.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblEstado);
		
		comboBoxEstado = new JComboBox<String>();
		comboBoxEstado = func.iniciarListaEstado(comboBoxEstado);
		panel_8.add(comboBoxEstado);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));

		btnBuscarUbic = new JButton("Buscar ubicacion");
		btnBuscarUbic.addActionListener(new BuscarUbicListener());
		panel_5.add(btnBuscarUbic);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ImprimirPrestamoListener());
		panel_5.add(btnImprimir);
	}

	//TODO ?? En caso de incidencia (expediente ya prestado o no en el archivo) 
	//crear función que permita actualizar solo el estado de un préstamo.
	//es conveniente que la persona del Archivo que introduce los datos vea 
	//la información de la ubicación. Ejemplo: si la aplicación ya le informa
	//que el expediente no se encuentra en el Archivo ya puede actualizar el Estado en ese momento.
	
	public class BuscarUbicListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				List<Expediente> expedientes = new ArrayList<>();
				expedientes = Expediente.buscaExpediente(Integer.parseInt(textFieldNumExp.getText()),
						textFieldTipoExp.getText(),Integer.parseInt(textFieldAnioExp.getText()));
				if(expedientes.isEmpty())
				{
					JOptionPane.showMessageDialog(null,
							"El expediente no se encuentra en el archivo", "Ubicacion",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					// TODO: pensar como mostar la lista en el dialogo
					JOptionPane.showMessageDialog(null,
							"El expediente se encuentra en: ", "Ubicacion",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public class ImprimirPrestamoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {	
			//TODO: crear funcion imprimirPapeleta con toda la info del form menos el estado
			//TODO: crear funcion imprimirTestigo con toda la info del form menos el estado
			//y añadiendo caja, ubicación, notas, tomos, lugar
			try {
				Prestamo.realizarPrestamo(Integer.parseInt(textFieldNumExp.getText()), textFieldTipoExp.getText(),
						Integer.parseInt(textFieldAnioExp.getText()), textFieldSolicitante.getText());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}

