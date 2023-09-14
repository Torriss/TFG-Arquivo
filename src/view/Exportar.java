package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JTextField;

public class Exportar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2708828160008419936L;
	private JPanel contentPane;
	private JButton btnExportExpedientes;
	private JButton btnExportTransferencias;
	private JButton btnExportExpurgos;
	private JTextField textFieldNumExp;
	private JTextField textFieldAnioExp;
	private JComboBox<String> comboBoxJuzgado;
	private JComboBox<String> comboBoxExpediente;
	private JButton btnAyuda;
	
	public JButton getBtnAyuda() {
		return btnAyuda;
	}

	public void setBtnAyuda(JButton btnAyuda) {
		this.btnAyuda = btnAyuda;
	}
	
	public JButton getBtnExportExpedientes() {
		return btnExportExpedientes;
	}

	public void setBtnExportExpedientes(JButton btnExportExpedientes) {
		this.btnExportExpedientes = btnExportExpedientes;
	}

	public JButton getBtnExportTransferencias() {
		return btnExportTransferencias;
	}

	public void setBtnExportTransferencias(JButton btnExportTransferencias) {
		this.btnExportTransferencias = btnExportTransferencias;
	}

	public JButton getBtnExportExpurgos() {
		return btnExportExpurgos;
	}

	public void setBtnExportExpurgos(JButton btnExportExpurgos) {
		this.btnExportExpurgos = btnExportExpurgos;
	}

	public JComboBox<String> getComboBoxJuzgado() {
		return comboBoxJuzgado;
	}

	public void setComboBoxJuzgado(JComboBox<String> comboBoxJuzgado) {
		this.comboBoxJuzgado = comboBoxJuzgado;
	}

	public JComboBox<String> getComboBoxExpediente() {
		return comboBoxExpediente;
	}

	public void setComboBoxExpediente(JComboBox<String> comboBoxExpediente) {
		this.comboBoxExpediente = comboBoxExpediente;
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
	
	/**
	 * Create the frame.
	 */
	public Exportar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/Arquivo.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 675, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel_9 = new JPanel();
		contentPane.add(panel_9);
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[] {190, 100, 190};
		gbl_panel_9.rowHeights = new int[] {20, 40, 30};
		gbl_panel_9.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_panel_9.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel_9.setLayout(gbl_panel_9);
		
		btnAyuda = new JButton("Ayuda");
		GridBagConstraints gbc_btnAyuda = new GridBagConstraints();
		gbc_btnAyuda.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAyuda.insets = new Insets(0, 0, 5, 0);
		gbc_btnAyuda.gridx = 2;
		gbc_btnAyuda.gridy = 0;
		panel_9.add(btnAyuda, gbc_btnAyuda);
		
		JLabel lblExpediente = new JLabel("Expediente:");
		GridBagConstraints gbc_lblExpediente = new GridBagConstraints();
		gbc_lblExpediente.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblExpediente.insets = new Insets(0, 0, 5, 5);
		gbc_lblExpediente.gridx = 1;
		gbc_lblExpediente.gridy = 1;
		panel_9.add(lblExpediente, gbc_lblExpediente);
		
		comboBoxExpediente = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxExpediente = new GridBagConstraints();
		gbc_comboBoxExpediente.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxExpediente.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxExpediente.fill = GridBagConstraints.BOTH;
		gbc_comboBoxExpediente.gridx = 1;
		gbc_comboBoxExpediente.gridy = 2;
		panel_9.add(comboBoxExpediente, gbc_comboBoxExpediente);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {30, 130, 10, 130, 190};
		gbl_panel.rowHeights = new int[] {35, 30, 35, 10};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		textFieldNumExp = new JTextField();
		textFieldNumExp.setToolTipText("Número de expediente");
		textFieldNumExp.setText("");
		GridBagConstraints gbc_textFieldNumExp = new GridBagConstraints();
		gbc_textFieldNumExp.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNumExp.fill = GridBagConstraints.BOTH;
		gbc_textFieldNumExp.gridx = 1;
		gbc_textFieldNumExp.gridy = 1;
		panel.add(textFieldNumExp, gbc_textFieldNumExp);
		textFieldNumExp.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("/");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		textFieldAnioExp = new JTextField();
		textFieldAnioExp.setToolTipText("Año de expediente");
		GridBagConstraints gbc_textFieldAnioExp = new GridBagConstraints();
		gbc_textFieldAnioExp.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAnioExp.fill = GridBagConstraints.BOTH;
		gbc_textFieldAnioExp.gridx = 3;
		gbc_textFieldAnioExp.gridy = 1;
		panel.add(textFieldAnioExp, gbc_textFieldAnioExp);
		textFieldAnioExp.setColumns(10);
		
		JLabel lblJuzgado = new JLabel("Juzgado:");
		GridBagConstraints gbc_lblJuzgado = new GridBagConstraints();
		gbc_lblJuzgado.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblJuzgado.gridwidth = 3;
		gbc_lblJuzgado.insets = new Insets(0, 0, 0, 5);
		gbc_lblJuzgado.gridx = 1;
		gbc_lblJuzgado.gridy = 2;
		panel.add(lblJuzgado, gbc_lblJuzgado);
		
		JPanel panel_9_1 = new JPanel();
		contentPane.add(panel_9_1);
		GridBagLayout gbl_panel_9_1 = new GridBagLayout();
		gbl_panel_9_1.columnWidths = new int[] {190, 100, 190};
		gbl_panel_9_1.rowHeights = new int[] {40, 30, 30};
		gbl_panel_9_1.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_panel_9_1.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel_9_1.setLayout(gbl_panel_9_1);
		
		comboBoxJuzgado = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxJuzgado = new GridBagConstraints();
		gbc_comboBoxJuzgado.fill = GridBagConstraints.BOTH;
		gbc_comboBoxJuzgado.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxJuzgado.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxJuzgado.gridx = 1;
		gbc_comboBoxJuzgado.gridy = 0;
		panel_9_1.add(comboBoxJuzgado, gbc_comboBoxJuzgado);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		btnExportExpedientes = new JButton("Expedientes");
		panel_2.add(btnExportExpedientes);
		
		btnExportTransferencias = new JButton("Transferencias");
		panel_2.add(btnExportTransferencias);
		
		btnExportExpurgos = new JButton("Expurgos");
		panel_2.add(btnExportExpurgos);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8);
	}

}
