package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Modificaciones extends JFrame {

	private static final long serialVersionUID = -3009682336085335849L;
	private JPanel contentPane;
	private JTextField textFieldNumExp;
	private JTextField textFieldAnio;
	private JButton btnAyuda;
	private JButton btnBuscar;
	private JComboBox<String> comboBoxExpediente;
	private JComboBox<String> comboBoxJuzgado;

	public JTextField getTextFieldNumExp() {
		return textFieldNumExp;
	}

	public void setTextFieldNumExp(JTextField textFieldNumExp) {
		this.textFieldNumExp = textFieldNumExp;
	}

	public JTextField getTextFieldAnio() {
		return textFieldAnio;
	}

	public void setTextFieldAnio(JTextField textFieldAnio) {
		this.textFieldAnio = textFieldAnio;
	}

	public JButton getBtnAyuda() {
		return btnAyuda;
	}

	public void setBtnAyuda(JButton btnAyuda) {
		this.btnAyuda = btnAyuda;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JComboBox<String> getComboBoxExpediente() {
		return comboBoxExpediente;
	}

	public void setComboBoxExpediente(JComboBox<String> comboBoxExpediente) {
		this.comboBoxExpediente = comboBoxExpediente;
	}

	public JComboBox<String> getComboBoxJuzgado() {
		return comboBoxJuzgado;
	}

	public void setComboBoxJuzgado(JComboBox<String> comboBoxJuzgado) {
		this.comboBoxJuzgado = comboBoxJuzgado;
	}

	public Modificaciones() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/Arquivo.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 675, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel panel_9 = new JPanel();
		contentPane.add(panel_9);
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[]{190, 100, 190, 0};
		gbl_panel_9.rowHeights = new int[]{20, 40, 30, 0};
		gbl_panel_9.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_9.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		gbc_comboBoxExpediente.fill = GridBagConstraints.BOTH;
		gbc_comboBoxExpediente.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxExpediente.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxExpediente.gridx = 1;
		gbc_comboBoxExpediente.gridy = 2;
		panel_9.add(comboBoxExpediente, gbc_comboBoxExpediente);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{30, 130, 10, 130, 190, 0};
		gbl_panel.rowHeights = new int[]{35, 30, 35, 10, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		textFieldNumExp = new JTextField();
		textFieldNumExp.setToolTipText("Número de expediente");
		textFieldNumExp.setText("");
		textFieldNumExp.setColumns(10);
		GridBagConstraints gbc_textFieldNumExp = new GridBagConstraints();
		gbc_textFieldNumExp.fill = GridBagConstraints.BOTH;
		gbc_textFieldNumExp.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNumExp.gridx = 1;
		gbc_textFieldNumExp.gridy = 1;
		panel.add(textFieldNumExp, gbc_textFieldNumExp);
		
		JLabel lblNewLabel = new JLabel("/");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		textFieldAnio = new JTextField();
		textFieldAnio.setToolTipText("Año de expediente");
		textFieldAnio.setColumns(10);
		GridBagConstraints gbc_textFieldAnio = new GridBagConstraints();
		gbc_textFieldAnio.fill = GridBagConstraints.BOTH;
		gbc_textFieldAnio.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAnio.gridx = 3;
		gbc_textFieldAnio.gridy = 1;
		panel.add(textFieldAnio, gbc_textFieldAnio);
		
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
		gbl_panel_9_1.columnWidths = new int[]{190, 100, 190, 0};
		gbl_panel_9_1.rowHeights = new int[]{40, 30, 30, 0};
		gbl_panel_9_1.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_9_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnBuscar = new JButton("Buscar expediente");
		panel_2.add(btnBuscar);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8);
		
		JPanel panel_10 = new JPanel();
		panel_2.add(panel_10);
		panel_10.setLayout(new GridLayout(1, 0, 0, 0));
	}

}
