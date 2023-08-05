package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Juzgado;
import utils.FuncComunes;

import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Devoluciones extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3777120968440321060L;
	private JPanel contentPane;
	private JTextField textFieldFecha;
	private JTextField textFieldNumExp;
	private JTextField textFieldAnioExp;
	private JTextField textFieldCaja;
	private JTextField textFieldTomos;
	private JTextField textFieldUbicacion;
	private JTextField textFieldNotas;
	private JTextField textFieldLugar;
	private JComboBox<String> comboBoxTipoExp;
	private JComboBox<String> comboBoxEstado;
	private JComboBox<Juzgado> comboBoxJuzgado;
	private JButton btnBuscarUbic;
	private JButton btnAsignar;
	private JButton btnNuevo;
	private JButton btnUltCajas;
	private JButton btnImprimir;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Devoluciones() throws SQLException {
		setTitle("Devoluciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		FuncComunes func = new FuncComunes();

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblJuzgado = new JLabel("Juzgado");
		panel_3.add(lblJuzgado);
		
		JLabel lblFecha = new JLabel("Fecha");
		panel_3.add(lblFecha);
		
		comboBoxJuzgado = new JComboBox<Juzgado>();
		comboBoxJuzgado = func.iniciarListaJuzgados(comboBoxJuzgado);
		panel_3.add(comboBoxJuzgado);
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		textFieldFecha = new JTextField();
		panel_4.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
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
		
		comboBoxTipoExp = new JComboBox<String>();
		comboBoxTipoExp = func.iniciarListaTipoExp(comboBoxTipoExp);
		panel_2.add(comboBoxTipoExp);
		
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
		
		FuncComunes.BuscarUbicListener buscarUbic = func.new BuscarUbicListener();
		btnBuscarUbic = new JButton("Buscar ubicacion");
		btnBuscarUbic.addActionListener(buscarUbic);
		panel.add(btnBuscarUbic);
		
		FuncComunes.CalendarioListener calendario = func.new CalendarioListener();
		//TODO: revisar con LGoodDatePicker
		JButton btnCalendario = new JButton("Calendario");
		btnCalendario.addActionListener(calendario);
		panel_4.add(btnCalendario);
		
		FuncComunes.AsignarListener asignar = func.new AsignarListener();
		btnAsignar = new JButton("Asignar");
		btnAsignar.addActionListener(asignar);
		panel.add(btnAsignar);
		
		FuncComunes.NuevoListener nuevo = func.new NuevoListener();
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(nuevo);
		panel.add(btnNuevo);
		
		FuncComunes.UltCajasListener ultCajas = func.new UltCajasListener();
		btnUltCajas = new JButton("Últimas cajas");
		btnUltCajas.addActionListener(ultCajas);
		panel.add(btnUltCajas);
		
		FuncComunes.ImprimirListener imprimir = func.new ImprimirListener();
		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(imprimir);
		panel.add(btnImprimir);
	}

}
