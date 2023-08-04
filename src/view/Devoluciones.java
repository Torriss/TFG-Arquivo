package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Devoluciones extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3777120968440321060L;
	private JPanel contentPane;
	private JTextField textFieldFecha;
	private JTextField textFieldTipo;
	private JTextField textFieldNumExp;
	private JTextField textFieldAnioExp;
	private JTextField textFieldCaja;
	private JTextField textFieldTomos;
	private JTextField textFieldLugar;
	private JTextField textFieldUbicacion;
	private JTextField textFieldNotas;

	/**
	 * Create the frame.
	 */
	public Devoluciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblJuzgado = new JLabel("Juzgado");
		panel_3.add(lblJuzgado);
		
		JLabel lblFecha = new JLabel("Fecha");
		panel_3.add(lblFecha);
		
		JComboBox comboBoxJuzgado = new JComboBox();
		panel_3.add(comboBoxJuzgado);
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		textFieldFecha = new JTextField();
		panel_4.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		JButton btnCalendario = new JButton("Calendario");
		panel_4.add(btnCalendario);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblExpediente = new JLabel("Expediente:");
		panel_6.add(lblExpediente);
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		
		JLabel lblAnio = new JLabel("Año:");
		panel_6.add(lblAnio);
		
		JLabel lblTipo = new JLabel("Tipo");
		panel_2.add(lblTipo);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 3, 0, 0));
		
		textFieldNumExp = new JTextField();
		panel_5.add(textFieldNumExp);
		textFieldNumExp.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("/");
		panel_5.add(lblNewLabel);
		
		textFieldAnioExp = new JTextField();
		panel_5.add(textFieldAnioExp);
		textFieldAnioExp.setColumns(10);
		
		textFieldTipo = new JTextField();
		panel_2.add(textFieldTipo);
		textFieldTipo.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 5, 0, 0));
		
		JLabel lblCaja = new JLabel("Caja:");
		panel_1.add(lblCaja);
		
		JLabel lblUbicacion = new JLabel("Ubicación:");
		panel_1.add(lblUbicacion);
		
		JLabel lblNotas = new JLabel("Notas:");
		panel_1.add(lblNotas);
		
		JLabel lblTomos = new JLabel("Tomos:");
		panel_1.add(lblTomos);
		
		JLabel lblLugar = new JLabel("Lugar:");
		panel_1.add(lblLugar);
		
		textFieldCaja = new JTextField();
		panel_1.add(textFieldCaja);
		textFieldCaja.setColumns(10);
		
		textFieldUbicacion = new JTextField();
		panel_1.add(textFieldUbicacion);
		textFieldUbicacion.setColumns(10);
		
		textFieldNotas = new JTextField();
		panel_1.add(textFieldNotas);
		textFieldNotas.setColumns(10);
		
		textFieldTomos = new JTextField();
		panel_1.add(textFieldTomos);
		textFieldTomos.setColumns(10);
		
		textFieldLugar = new JTextField();
		panel_1.add(textFieldLugar);
		textFieldLugar.setColumns(10);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnBuscarUbic = new JButton("Buscar ubicación");
		panel.add(btnBuscarUbic);
		
		JButton btnAsignar = new JButton("Asignar");
		panel.add(btnAsignar);
		
		JButton btnNuevo = new JButton("Nuevo");
		panel.add(btnNuevo);
		
		JButton btnUltCajas = new JButton("Últimas cajas");
		panel.add(btnUltCajas);
		
		JButton btnImprimir = new JButton("Imprimir");
		panel.add(btnImprimir);
	}

}
