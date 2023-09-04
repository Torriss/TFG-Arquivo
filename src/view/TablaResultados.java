package view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class TablaResultados extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4865371116578260380L;
	private JPanel contentPane;
	private JScrollPane scrollPaneTabla;
	private JTable tabla;
	private int filasTabla;
	private int columnasTabla;
	private JButton btnImprimir;
	
	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public void setBtnImprimir(JButton btnImprimir) {
		this.btnImprimir = btnImprimir;
	}

	public int getFilasTabla() {
		return filasTabla;
	}

	public void setFilasTabla(int filasTabla) {
		this.filasTabla = filasTabla;
	}

	public int getColumnasTabla() {
		return columnasTabla;
	}

	public void setColumnasTabla(int columnasTabla) {
		this.columnasTabla = columnasTabla;
	}
	
	public JTable getTabla() {
		return tabla;
	}

	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}

	/**
	 * Create the frame.
	 */
	

	public TablaResultados() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		scrollPaneTabla = new JScrollPane();
		contentPane.add(scrollPaneTabla);
		  
		tabla = new JTable();
		tabla.setBackground(Color.WHITE);
		//tabla.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		//tabla.addMouseListener(this);
		//tablaSeguimiento.addKeyListener(this);
		tabla.setOpaque(false);
		scrollPaneTabla.setViewportView(tabla);
		
	}

}
