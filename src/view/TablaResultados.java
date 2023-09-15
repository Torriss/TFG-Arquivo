package view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

public class TablaResultados extends JFrame {

	private static final long serialVersionUID = 4865371116578260380L;
	private JPanel contentPane;
	private JScrollPane scrollPaneTabla;
	private JTable tabla;
	private int filasTabla;
	private int columnasTabla;
	private JButton btnImprimir;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JPanel panel;
	
	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public void setBtnImprimir(JButton btnImprimir) {
		this.btnImprimir = btnImprimir;
	}
	
	public JButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(JButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
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

	public TablaResultados() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/Arquivo.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 875, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		scrollPaneTabla = new JScrollPane();
		contentPane.add(scrollPaneTabla);
		  
		tabla = new JTable();
		tabla.setBackground(Color.WHITE);
		//tabla.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		//tabla.addMouseListener(this);
		//tablaSeguimiento.addKeyListener(this);
		tabla.setOpaque(false);
		scrollPaneTabla.setViewportView(tabla);
		
		panel = new JPanel();
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{210, 75, 77, 71, 0};
		gbl_panel.rowHeights = new int[]{23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnImprimir = new JButton("Imprimir");
		GridBagConstraints gbc_btnImprimir = new GridBagConstraints();
		gbc_btnImprimir.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnImprimir.insets = new Insets(0, 0, 0, 5);
		gbc_btnImprimir.gridx = 1;
		gbc_btnImprimir.gridy = 0;
		panel.add(btnImprimir, gbc_btnImprimir);
		
		btnModificar = new JButton("Modificar");
		GridBagConstraints gbc_btnModificar = new GridBagConstraints();
		gbc_btnModificar.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnModificar.insets = new Insets(0, 0, 0, 5);
		gbc_btnModificar.gridx = 2;
		gbc_btnModificar.gridy = 0;
		panel.add(btnModificar, gbc_btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnEliminar.gridx = 3;
		gbc_btnEliminar.gridy = 0;
		panel.add(btnEliminar, gbc_btnEliminar);
		
	}

}
