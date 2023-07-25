package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Prestamos extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prestamos frame = new Prestamos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Prestamos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Expediente");
		panel_3.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		panel_3.add(comboBox);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Solicitante");
		panel_4.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		panel_4.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("/");
		panel_2.add(lblNewLabel_6);
		
		textField_1 = new JTextField();
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		panel_6.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Lugar");
		panel_6.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		panel_6.add(textField_3);
		textField_3.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Juzgado");
		panel_7.add(lblNewLabel_1);
		
		JComboBox comboBox_1 = new JComboBox();
		panel_7.add(comboBox_1);
		
		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9);
		panel_9.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Fecha");
		panel_9.add(lblNewLabel_4);
		
		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 2, 0, 0));
		
		textField_4 = new JTextField();
		panel_10.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("Calendario");
		panel_10.add(btnNewButton);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_8 = new JPanel();
		panel.add(panel_8);
		panel_8.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Estado");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblNewLabel_5);
		
		JComboBox comboBox_2 = new JComboBox();
		panel_8.add(comboBox_2);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnNewButton_1 = new JButton("Buscar ubicacion");
		panel_5.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Imprimir");
		panel_5.add(btnNewButton_2);
	}

}
