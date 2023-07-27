package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Consultas extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consultas frame = new Consultas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Consultas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Nueva busqueda:");
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Juzgado:");
		panel_5.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		panel_5.add(comboBox);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_3.add(panel_7);
		panel_7.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Fecha inicio:");
		panel_7.add(lblNewLabel_2);
		
		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		textField = new JTextField();
		panel_9.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Calendario");
		panel_9.add(btnNewButton);
		
		JPanel panel_8 = new JPanel();
		panel_3.add(panel_8);
		panel_8.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Fecha fin:");
		panel_8.add(lblNewLabel_3);
		
		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 2, 0, 0));
		
		textField_1 = new JTextField();
		panel_10.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Calendario");
		panel_10.add(btnNewButton_1);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_12 = new JPanel();
		panel_4.add(panel_12);
		panel_12.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Tipo de expediente:");
		panel_12.add(lblNewLabel_4);
		
		textField_2 = new JTextField();
		panel_12.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_13 = new JPanel();
		panel_4.add(panel_13);
		panel_13.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Lugar:");
		panel_13.add(lblNewLabel_5);
		
		textField_3 = new JTextField();
		panel_13.add(textField_3);
		textField_3.setColumns(10);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_11 = new JPanel();
		panel.add(panel_11);
		
		JButton btnNewButton_2 = new JButton("Buscar");
		panel.add(btnNewButton_2);
	}

}
