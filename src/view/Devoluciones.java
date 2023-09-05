package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class Devoluciones extends JFrame {

	private static final long serialVersionUID = 3777120968440321060L;
	private JTextField textFieldNumExp;
	private JTextField textFieldAnioExp;
	private JTextField textFieldCaja;
	private JTextField textFieldTomos;
	private JTextField textFieldUbicacion;
	private JTextField textFieldNotas;
	private JTextField textFieldLugar;
	private JComboBox<String> comboBoxTipoExp;
	private JComboBox<String> comboBoxEstado;
	private JComboBox<String> comboBoxJuzgado;
	private JButton btnBuscarUbic;
	private JButton btnDevolver;
	private JButton btnNuevo;
	private JTextField textFieldPaginas;
	private DatePicker datePicker;

	public JTextField getTextFieldNumExp() {
		return textFieldNumExp;
	}

	public void setTextFieldNumExp(JTextField textFieldNumExp) {
		this.textFieldNumExp = textFieldNumExp;
	}

	public JTextField getTextFieldAnioExp() {
		return textFieldAnioExp;
	}

	public void setTextFieldAnioExp(JTextField textFieldAnioExp) {
		this.textFieldAnioExp = textFieldAnioExp;
	}

	public JTextField getTextFieldCaja() {
		return textFieldCaja;
	}

	public void setTextFieldCaja(JTextField textFieldCaja) {
		this.textFieldCaja = textFieldCaja;
	}

	public JTextField getTextFieldTomos() {
		return textFieldTomos;
	}

	public void setTextFieldTomos(JTextField textFieldTomos) {
		this.textFieldTomos = textFieldTomos;
	}

	public JTextField getTextFieldUbicacion() {
		return textFieldUbicacion;
	}

	public void setTextFieldUbicacion(JTextField textFieldUbicacion) {
		this.textFieldUbicacion = textFieldUbicacion;
	}

	public JTextField getTextFieldNotas() {
		return textFieldNotas;
	}

	public void setTextFieldNotas(JTextField textFieldNotas) {
		this.textFieldNotas = textFieldNotas;
	}

	public JTextField getTextFieldLugar() {
		return textFieldLugar;
	}

	public void setTextFieldLugar(JTextField textFieldLugar) {
		this.textFieldLugar = textFieldLugar;
	}

	public JComboBox<String> getComboBoxTipoExp() {
		return comboBoxTipoExp;
	}

	public void setComboBoxTipoExp(JComboBox<String> comboBoxTipoExp) {
		this.comboBoxTipoExp = comboBoxTipoExp;
	}

	public JComboBox<String> getComboBoxEstado() {
		return comboBoxEstado;
	}

	public void setComboBoxEstado(JComboBox<String> comboBoxEstado) {
		this.comboBoxEstado = comboBoxEstado;
	}

	public JComboBox<String> getComboBoxJuzgado() {
		return comboBoxJuzgado;
	}

	public void setComboBoxJuzgado(JComboBox<String> comboBoxJuzgado) {
		this.comboBoxJuzgado = comboBoxJuzgado;
	}

	public JButton getBtnBuscarUbic() {
		return btnBuscarUbic;
	}

	public void setBtnBuscarUbic(JButton btnBuscarUbic) {
		this.btnBuscarUbic = btnBuscarUbic;
	}

	public JButton getBtnDevolver() {
		return btnDevolver;
	}

	public void setBtnDevolver(JButton btnDevolver) {
		this.btnDevolver = btnDevolver;
	}

	public JButton getBtnNuevo() {
		return btnNuevo;
	}

	public void setBtnNuevo(JButton btnNuevo) {
		this.btnNuevo = btnNuevo;
	}

	public JTextField getTextFieldPaginas() {
		return textFieldPaginas;
	}

	public void setTextFieldPaginas(JTextField textFieldPaginas) {
		this.textFieldPaginas = textFieldPaginas;
	}

	public DatePicker getDatePicker() {
		return datePicker;
	}

	public void setDatePicker(DatePicker datePicker) {
		this.datePicker = datePicker;
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Devoluciones() throws SQLException {
		setTitle("Devoluciones");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 675, 450);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblJuzgado = new JLabel("Juzgado:");
		panel_3.add(lblJuzgado);
		
		JLabel lblFecha = new JLabel("Fecha:");
		panel_3.add(lblFecha);
		
		comboBoxJuzgado = new JComboBox<String>();
		panel_3.add(comboBoxJuzgado);
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		datePicker = new DatePicker();
	    datePicker.getComponentToggleCalendarButton().addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	}
	    });
	    datePicker.setDateToToday();
		
		panel_4.add(datePicker);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblExpediente = new JLabel("Num. expediente:");
		lblExpediente.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblExpediente);
		
		JLabel lblAnio = new JLabel("Año:");
		lblAnio.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblAnio);
		
		JLabel lblTipo = new JLabel("Tipo:");
		panel_2.add(lblTipo);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textFieldNumExp = new JTextField();
		textFieldNumExp.setToolTipText("Número de expediente");
		panel_5.add(textFieldNumExp);
		textFieldNumExp.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("/");
		panel_5.add(lblNewLabel);
		
		textFieldAnioExp = new JTextField();
		textFieldAnioExp.setToolTipText("Año");
		panel_5.add(textFieldAnioExp);
		textFieldAnioExp.setColumns(10);
		
		comboBoxTipoExp = new JComboBox<String>();
		panel_2.add(comboBoxTipoExp);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 5, 0, 0));
		
		JLabel lblPaginas = new JLabel("Páginas:");
		panel_1.add(lblPaginas);
		
		JLabel lblCaja = new JLabel("Caja:");
		panel_1.add(lblCaja);
		
		JLabel lblUbicacion = new JLabel("Ubicación:");
		panel_1.add(lblUbicacion);
		
		textFieldPaginas = new JTextField();
		textFieldPaginas.setEnabled(false);
		panel_1.add(textFieldPaginas);
		textFieldPaginas.setColumns(10);
		
		textFieldCaja = new JTextField();
		textFieldCaja.setEnabled(false);
		panel_1.add(textFieldCaja);
		textFieldCaja.setColumns(10);
		
		textFieldUbicacion = new JTextField();
		textFieldUbicacion.setEnabled(false);
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
		textFieldNotas.setEnabled(false);
		panel.add(textFieldNotas);
		textFieldNotas.setColumns(10);
		
		textFieldTomos = new JTextField();
		textFieldTomos.setEnabled(false);
		panel.add(textFieldTomos);
		textFieldTomos.setColumns(10);
		
		textFieldLugar = new JTextField();
		textFieldLugar.setEnabled(false);
		panel.add(textFieldLugar);
		textFieldLugar.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		contentPane.add(panel_8);
		
		btnBuscarUbic = new JButton("Buscar ubicación");
		panel_8.add(btnBuscarUbic);
		
		btnDevolver = new JButton("Devolver");
		panel_8.add(btnDevolver);
		btnDevolver.setEnabled(false);
		
		btnNuevo = new JButton("Nuevo");
		panel_8.add(btnNuevo);
		btnNuevo.setEnabled(false);
	}

}
