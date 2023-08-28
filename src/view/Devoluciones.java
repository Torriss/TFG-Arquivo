package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.ExpedienteDAOImpl;
import model.Expediente;
import model.Juzgado;
import utils.FuncComunes;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	private JButton btnDevolver;
	private JButton btnNuevo;
	private JTextField textFieldTipoExp;
	private JTextField textFieldJuzgado;
	private JTextField textFieldPaginas;

	private ExpedienteDAOImpl expediente = new ExpedienteDAOImpl();
	
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
		contentPane.setLayout(new GridLayout(5, 0, 0, 0));
		
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
		
		textFieldJuzgado = new JTextField();
		panel_3.add(textFieldJuzgado);
		textFieldJuzgado.setColumns(10);
		
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
		
		textFieldTipoExp = new JTextField();
		panel_2.add(textFieldTipoExp);
		textFieldTipoExp.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 5, 0, 0));
		
		JLabel lblPaginas = new JLabel("Paginas:");
		panel_1.add(lblPaginas);
		
		JLabel lblCaja = new JLabel("Caja:");
		panel_1.add(lblCaja);
		
		JLabel lblUbicacion = new JLabel("Ubicación:");
		panel_1.add(lblUbicacion);
		
		textFieldPaginas = new JTextField();
		panel_1.add(textFieldPaginas);
		textFieldPaginas.setColumns(10);
		
		textFieldCaja = new JTextField();
		panel_1.add(textFieldCaja);
		textFieldCaja.setColumns(10);
		
		textFieldUbicacion = new JTextField();
		panel_1.add(textFieldUbicacion);
		textFieldUbicacion.setColumns(10);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(2, 3, 0, 0));
		
		JLabel lblNotas = new JLabel("Notas:");
		panel.add(lblNotas);
		
		JLabel lblTomos = new JLabel("Tomos:");
		panel.add(lblTomos);
		
		JLabel lblLugar = new JLabel("Lugar:");
		panel.add(lblLugar);
		
		textFieldNotas = new JTextField();
		panel.add(textFieldNotas);
		textFieldNotas.setColumns(10);
		
		textFieldTomos = new JTextField();
		panel.add(textFieldTomos);
		textFieldTomos.setColumns(10);
		
		textFieldLugar = new JTextField();
		panel.add(textFieldLugar);
		textFieldLugar.setColumns(10);
		
		FuncComunes.CalendarioListener calendario = func.new CalendarioListener();
		//TODO: revisar con LGoodDatePicker
		JButton btnCalendario = new JButton("Calendario");
		btnCalendario.addActionListener(calendario);
		panel_4.add(btnCalendario);
		
		JPanel panel_8 = new JPanel();
		contentPane.add(panel_8);
		
		btnBuscarUbic = new JButton("Buscar ubicación");
		panel_8.add(btnBuscarUbic);
		
		btnDevolver = new JButton("Devolver");
		panel_8.add(btnDevolver);
		btnDevolver.addActionListener(new DevolverListener());
		btnDevolver.setEnabled(false);
		
		btnNuevo = new JButton("Nuevo");
		panel_8.add(btnNuevo);
		btnNuevo.setEnabled(false);
		btnNuevo.addActionListener(new NuevoListener());
		btnBuscarUbic.addActionListener(new BuscarUbicListener());
		
		//FuncComunes.UltCajasListener ultCajas = func.new UltCajasListener();
		
		//FuncComunes.ImprimirDevolucListener imprimir = func.new ImprimirDevolucListener();
	}
	
	public class BuscarUbicListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				List<Expediente> expedientes = new ArrayList<>();
				expedientes = expediente.buscaExpediente(Integer.parseInt(textFieldNumExp.getText()),
						textFieldTipoExp.getText(),Integer.parseInt(textFieldAnioExp.getText()), textFieldJuzgado.getText());
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
	}
	
	public class DevolverListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//TODO: pasar info de los textBox a devolucion(...)
			
		}
	}
	
	public class NuevoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Expediente exp = new Expediente(Integer.parseInt(textFieldNumExp.getText()), textFieldTipoExp.getText(),
					Integer.parseInt(textFieldAnioExp.getText()), Integer.parseInt(textFieldCaja.getText()), 
					textFieldUbicacion.getText(), textFieldNotas.getText(), textFieldTomos.getText(), 
					textFieldJuzgado.getText(), textFieldLugar.getText(), Integer.parseInt(textFieldPaginas.getText()));
			try {
				expediente.insert(exp);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
