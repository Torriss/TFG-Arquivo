package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Prestamos extends JFrame {

	private static final long serialVersionUID = 247041135132800079L;
	private JTextField textFieldNumExp;
	private JTextField textFieldAnioExp;
	private JTextField textFieldSolicitante;
	private JComboBox<String> comboBoxTipoExp;
	private JComboBox<String> comboBoxJuzgado;
	private JButton btnBuscarUbic;
	private JButton btnPrestar;
	private JTextField textFieldJuzgado;
	private DatePicker datePicker;
	private JButton btnAyuda;
	
	public JButton getBtnAyuda() {
		return btnAyuda;
	}

	public void setBtnAyuda(JButton btnAyuda) {
		this.btnAyuda = btnAyuda;
	}
	
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

	public JTextField getTextFieldSolicitante() {
		return textFieldSolicitante;
	}

	public void setTextFieldSolicitante(JTextField textFieldSolicitante) {
		this.textFieldSolicitante = textFieldSolicitante;
	}

	public JComboBox<String> getComboBoxTipoExp() {
		return comboBoxTipoExp;
	}

	public void setComboBoxTipoExp(JComboBox<String> comboBoxTipoExp) {
		this.comboBoxTipoExp = comboBoxTipoExp;
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

	public JButton getBtnPrestar() {
		return btnPrestar;
	}

	public void setBtnPrestar(JButton btnPrestar) {
		this.btnPrestar = btnPrestar;
	}

	public JTextField getTextFieldJuzgado() {
		return textFieldJuzgado;
	}

	public void setTextFieldJuzgado(JTextField textFieldJuzgado) {
		this.textFieldJuzgado = textFieldJuzgado;
	}

	public DatePicker getDatePicker() {
		return datePicker;
	}

	public void setDatePicker(DatePicker datePicker) {
		this.datePicker = datePicker;
	}

	public Prestamos() throws SQLException {
		setTitle("Préstamos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 675, 450);
		JPanel contentPanePrestamos = new JPanel();
		contentPanePrestamos.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanePrestamos);
		contentPanePrestamos.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panel_1 = new JPanel();
		contentPanePrestamos.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblExpediente = new JLabel("Expediente:");
		panel_3.add(lblExpediente);
		
		comboBoxTipoExp = new JComboBox<String>();
		panel_3.add(comboBoxTipoExp);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		btnAyuda = new JButton("Ayuda");
		GridBagConstraints gbc_btnAyuda = new GridBagConstraints();
		gbc_btnAyuda.insets = new Insets(0, 0, 5, 0);
		gbc_btnAyuda.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAyuda.gridwidth = 2;
		gbc_btnAyuda.gridx = 7;
		gbc_btnAyuda.gridy = 0;
		panel_4.add(btnAyuda, gbc_btnAyuda);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		textFieldNumExp = new JTextField();
		textFieldNumExp.setToolTipText("Número de expediente");
		panel_2.add(textFieldNumExp);
		textFieldNumExp.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("/");
		panel_2.add(lblNewLabel);
		
		textFieldAnioExp = new JTextField();
		textFieldAnioExp.setToolTipText("Año de expediente");
		panel_2.add(textFieldAnioExp);
		textFieldAnioExp.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		panel_6.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblFecha = new JLabel("Fecha:");
		panel_6.add(lblFecha);
		
		JPanel panel_10 = new JPanel();
		panel_6.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 1, 0, 0));
		
		datePicker = new DatePicker();
	    datePicker.getComponentToggleCalendarButton().addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	}
	    });
	    panel_10.add(datePicker);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblJuzgado = new JLabel("Juzgado:");
		panel_7.add(lblJuzgado);
		
		comboBoxJuzgado = new JComboBox<String>();
		panel_7.add(comboBoxJuzgado);
		
		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9);
		panel_9.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblSolicitante = new JLabel("Número de empleado:");
		panel_9.add(lblSolicitante);
		
		textFieldSolicitante = new JTextField();
		panel_9.add(textFieldSolicitante);
		textFieldSolicitante.setColumns(10);
		
		JPanel panel = new JPanel();
		contentPanePrestamos.add(panel);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_8 = new JPanel();
		panel.add(panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);

		btnBuscarUbic = new JButton("Buscar ubicación");
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_5.add(btnBuscarUbic);
		
		btnPrestar = new JButton("Prestar");
		btnPrestar.setEnabled(false);
		panel_5.add(btnPrestar);
	}

}

