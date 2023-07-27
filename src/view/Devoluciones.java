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

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Devoluciones frame = new Devoluciones();
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
		
		JLabel lblNewLabel = new JLabel("Juzgado");
		panel_3.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha");
		panel_3.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		panel_3.add(comboBox);
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		textField = new JTextField();
		panel_4.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Calendario");
		panel_4.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Expediente:");
		panel_6.add(lblNewLabel_3);
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		
		JLabel lblNewLabel_4 = new JLabel("Año:");
		panel_6.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo");
		panel_2.add(lblNewLabel_2);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 3, 0, 0));
		
		textField_2 = new JTextField();
		panel_5.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("/");
		panel_5.add(lblNewLabel_5);
		
		textField_3 = new JTextField();
		panel_5.add(textField_3);
		textField_3.setColumns(10);
		
		textField_1 = new JTextField();
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 5, 0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("Caja:");
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_8 = new JLabel("Ubicación:");
		panel_1.add(lblNewLabel_8);
		
		JLabel lblNewLabel_7 = new JLabel("Notas:");
		panel_1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_10 = new JLabel("Tomos:");
		panel_1.add(lblNewLabel_10);
		
		JLabel lblNewLabel_9 = new JLabel("Lugar:");
		panel_1.add(lblNewLabel_9);
		
		textField_4 = new JTextField();
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		textField_7 = new JTextField();
		panel_1.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		panel_1.add(textField_8);
		textField_8.setColumns(10);
		
		textField_5 = new JTextField();
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnNewButton_1 = new JButton("Buscar ubicación");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Asignar");
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Nuevo");
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Últimas cajas");
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Imprimir");
		panel.add(btnNewButton_5);
	}

}
