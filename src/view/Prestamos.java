package view;

import model.Expediente;
import model.Juzgado;
import model.Prestamo;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Prestamos extends JFrame {

	private static final long serialVersionUID = 247041135132800079L;
	private JPanel contentPanePrestamos;
	private JTextField textFieldNumExp;
	private JTextField textFieldAnioExp;
	private JTextField textFieldSolicitante;
	private JTextField textFieldLugar;
	private JTextField textFieldFecha;
	private JComboBox<String> comboBoxTipoExp;
	private JComboBox<String> comboBoxEstado;
	private JComboBox<Juzgado> comboBoxJuzgado;
	private JButton btnBuscarUbic;
	private JButton btnImprimir;

	public Prestamos() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPanePrestamos = new JPanel();
		contentPanePrestamos.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanePrestamos);
		contentPanePrestamos.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPanePrestamos.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblExpediente = new JLabel("Expediente");
		panel_3.add(lblExpediente);
		
		comboBoxTipoExp = new JComboBox<String>();
		iniciarListaTipoExp();
		panel_3.add(comboBoxTipoExp);
		
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
		
		JLabel lblProvincia = new JLabel("Provincia");
		panel_6.add(lblProvincia);
		
		textFieldLugar = new JTextField();
		panel_6.add(textFieldLugar);
		textFieldLugar.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblJuzgado = new JLabel("Juzgado");
		panel_7.add(lblJuzgado);
		
		comboBoxJuzgado = new JComboBox<Juzgado>();
		iniciarListaJuzgados();
		panel_7.add(comboBoxJuzgado);
		
		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9);
		panel_9.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblFecha = new JLabel("Fecha");
		panel_9.add(lblFecha);
		
		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 2, 0, 0));
		
		textFieldFecha = new JTextField();
		panel_10.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		// TODO: cambiar por LGoodDatePicker
		JButton btnCalendario = new JButton("Calendario");
		btnCalendario.addActionListener(new CalendarioListener());
		panel_10.add(btnCalendario);
		
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
		iniciarListaEstado();
		panel_8.add(comboBoxEstado);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnBuscarUbic = new JButton("Buscar ubicacion");
		btnBuscarUbic.addActionListener(new BuscarUbicListener());
		panel_5.add(btnBuscarUbic);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ImprimirListener());
		panel_5.add(btnImprimir);
	}
	
	private void iniciarListaTipoExp() throws SQLException {
		ArrayList<String> listaTiposExp = Expediente.getAllTiposExp();
		
		comboBoxTipoExp.removeAllItems();
		
		for (int i = 0; i<listaTiposExp.size(); i++)
		{
			comboBoxEstado.addItem(listaTiposExp.get(i));
		}
	}
	
	private void iniciarListaJuzgados() {
		Prestamo prestamo = new Prestamo();
		ArrayList<Juzgado> listaJuzgados = prestamo.getJuzgados();
		
		comboBoxJuzgado.removeAllItems();
		for (int i = 0; i<listaJuzgados.size(); i++)
		{
			comboBoxJuzgado.addItem(listaJuzgados.get(i));
		}
	}
	
	private void iniciarListaEstado() {
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
		
	}
	
	public class BuscarUbicListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {	
			//TODO: crear funcion buscar ubicacion del expediente con tipoExp, numExp, anioExp, juzgado
		}
	}
	
	public class ImprimirListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {	
			//TODO: crear funcion imprimir
		}
	}
	
	public class CalendarioListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {	
			//TODO: revisar con LGoodDatePicker
		}
	}
}

