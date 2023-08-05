package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public MainWindow() throws SQLException {
		Consultas consultas = new Consultas();
		Prestamos prestamos = new Prestamos();
		Devoluciones devoluciones = new Devoluciones();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmPrestamos = new JMenuItem("Prestamos");
		mntmPrestamos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prestamos.setVisible(true);
			}
		});
		mnMenu.add(mntmPrestamos);
		
		JMenuItem mntmDevoluciones = new JMenuItem("Devoluciones");
		mntmDevoluciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				devoluciones.setVisible(true);
			}
		});
		mnMenu.add(mntmDevoluciones);
		
		JMenuItem mntmConsultas = new JMenuItem("Consultas");
		mntmConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultas.setVisible(true);
			}
		});
		mnMenu.add(mntmConsultas);
	}
}
