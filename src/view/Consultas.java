//package view;
//
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import java.awt.GridLayout;
//import javax.swing.JLabel;
//import javax.swing.JComboBox;
//import javax.swing.JTextField;
//import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.util.Locale;
//import java.awt.event.ActionEvent;
//
//import com.github.lgooddatepicker.components.DatePicker;
//import com.github.lgooddatepicker.components.DatePickerSettings;
//
//import model.Juzgado;
//import utils.FuncComunes;
//import utils.FuncComunes.CalendarioListener;
//
//public class Consultas extends JFrame {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 2099470413042195375L;
//	private JPanel contentPane;
//	private JComboBox<String> comboBoxTipoExp;
//	private JComboBox<Juzgado> comboBoxJuzgado;
//	private JButton btnBuscar;
//	private JTextField textFieldLugar;
//	private JTextField textFieldTipoExp;
//	private JTextField textFieldJuzgado;
//
//	/**
//	 * Create the frame.
//	 * @throws SQLException 
//	 */
//	public Consultas() throws SQLException {
//		setTitle("Consultas");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//		setContentPane(contentPane);
//		contentPane.setLayout(new GridLayout(5, 0, 0, 0));
//		
//		FuncComunes func = new FuncComunes();
//		
//		JPanel panel_1 = new JPanel();
//		contentPane.add(panel_1);
//		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
//		
//		JLabel lblNuevaBusq = new JLabel("Nueva busqueda:");
//		panel_1.add(lblNuevaBusq);
//		
//		JPanel panel_2 = new JPanel();
//		contentPane.add(panel_2);
//		panel_2.setLayout(new GridLayout(1, 2, 0, 0));
//		
//		JPanel panel_5 = new JPanel();
//		panel_2.add(panel_5);
//		panel_5.setLayout(new GridLayout(2, 0, 0, 0));
//		
//		JLabel lblJuzgado = new JLabel("Juzgado:");
//		panel_5.add(lblJuzgado);
//		
//		comboBoxJuzgado = new JComboBox<Juzgado>();
//		comboBoxJuzgado = func.iniciarListaJuzgados(comboBoxJuzgado);
//		panel_5.add(comboBoxJuzgado);
//		
//		textFieldJuzgado = new JTextField();
//		panel_5.add(textFieldJuzgado);
//		textFieldJuzgado.setColumns(10);
//		
//		JPanel panel_6 = new JPanel();		
//		panel_2.add(panel_6);
//				
//		JPanel panel_3 = new JPanel();
//		contentPane.add(panel_3);
//		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
//		
//		JPanel panel_7 = new JPanel();
//		panel_3.add(panel_7);
//		panel_7.setLayout(new GridLayout(2, 0, 0, 0));
//		
//		//TODO: revisar con LGoodDatePicker
//		FuncComunes.CalendarioListener calendarioIni = func.new CalendarioListener();
//		FuncComunes.CalendarioListener calendarioFin = func.new CalendarioListener();
//		
//		JLabel lblFechaIni = new JLabel("Fecha inicio:");
//		panel_7.add(lblFechaIni);
//		
//		JPanel panel_9 = new JPanel();
//		panel_7.add(panel_9);
//		panel_9.setLayout(new GridLayout(0, 1, 0, 0));
//		
//		final LocalDate today = LocalDate.now();
//		DatePickerSettings dateSettings = new DatePickerSettings(Locale.getDefault());
//	    dateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
//	    //dateSettings.setDateRangeLimits(today.minusYears(200), today);
//	    
//	    
//	    DatePicker datePickerIni = new DatePicker(dateSettings);
//	    datePickerIni.getComponentToggleCalendarButton().addActionListener(new ActionListener() {
//	    	public void actionPerformed(ActionEvent e) {
//	    	}
//	    });
//	    datePickerIni.setDateToToday();
//		panel_9.add(datePickerIni);
//		
//		JPanel panel_8 = new JPanel();
//		panel_3.add(panel_8);
//		panel_8.setLayout(new GridLayout(2, 0, 0, 0));
//		
//		JLabel lblFechaFin = new JLabel("Fecha fin:");
//		panel_8.add(lblFechaFin);
//		
//		JPanel panel_10 = new JPanel();
//		panel_8.add(panel_10);
//		panel_10.setLayout(new GridLayout(0, 1, 0, 0));
//		DatePicker datePickerFin = new DatePicker();
//		datePickerFin.setDateToToday();
//		panel_10.add(datePickerFin);
//		
//		JPanel panel_4 = new JPanel();
//		contentPane.add(panel_4);
//		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
//		
//		JPanel panel_12 = new JPanel();
//		panel_4.add(panel_12);
//		panel_12.setLayout(new GridLayout(2, 0, 0, 0));
//		
//		JLabel lblTipo = new JLabel("Tipo de expediente:");
//		panel_12.add(lblTipo);
//		
//		comboBoxTipoExp = new JComboBox<String>();
//		comboBoxTipoExp = func.iniciarListaTipoExp(comboBoxTipoExp);
//		panel_12.add(comboBoxTipoExp);
//		
//		textFieldTipoExp = new JTextField();
//		panel_12.add(textFieldTipoExp);
//		textFieldTipoExp.setColumns(10);
//		
//		JPanel panel_13 = new JPanel();
//		panel_4.add(panel_13);
//		panel_13.setLayout(new GridLayout(2, 0, 0, 0));
//		
//		JLabel lblLugar = new JLabel("Lugar:");
//		panel_13.add(lblLugar);
//		
//		textFieldLugar = new JTextField();
//		panel_13.add(textFieldLugar);
//		textFieldLugar.setColumns(10);
//		
//		JPanel panel = new JPanel();
//		contentPane.add(panel);
//		panel.setLayout(new GridLayout(0, 2, 0, 0));
//		
//		JPanel panel_11 = new JPanel();
//		panel.add(panel_11);
//		
////		FuncComunes.BuscarUbicListener buscarUbic = func.new BuscarUbicListener(textFieldTipoExp.getText(), 
////				Integer.parseInt(textFieldNumExp.getText()), Integer.parseInt(textFieldAnioExp.getText()), 
////				textFieldJuzgado.getText());		
//		btnBuscar = new JButton("Buscar");
////		btnBuscar.addActionListener(buscarUbic);
//		panel.add(btnBuscar);
//	}
//
//}
