package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Menu extends JFrame {

	private static final long serialVersionUID = 680423924785154336L;
	private JPanel contentPane;
	private JButton btnExpurgo;
	private JButton btnTransferencia;
	private JButton btnExportar;
	private JFileChooser fc;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_11;
	private JPanel panel_12;
	private JPanel panel_13;
	private JPanel panel_14;
	private JPanel panel_15;
	private JPanel panel_16;
	private JPanel panel_17;
	private JPanel panel_18;
	private JPanel panel_19;
	private JPanel panel_20;
	private JPanel panel_21;
	private JPanel panel_22;
	private JPanel panel_23;
	private JPanel panel_24;
	private JPanel panel_25;
	private JPanel panel_26;
	private JPanel panel_27;
	private JPanel panel_28;
	private JPanel panel_29;
	private JPanel panel_31;
	private JPanel panel_32;
	private JPanel panel_33;
	private JPanel panel_34;
	private JPanel panel_35;
	private JPanel panel_36;
	private JPanel panel_30;
	private JButton btnAyuda;
	private JButton btnPrestamos;
	private JButton btnDevoluciones;
	private JButton btnModificaciones;

	public JButton getBtnModificaciones() {
		return btnModificaciones;
	}

	//TODO: el delete de expediente cambia el expediente al estado borrado, que es lo que quiere
	public void setBtnModificaciones(JButton btnModificaciones) {
		this.btnModificaciones = btnModificaciones;
	}

	public JButton getBtnPrestamos() {
		return btnPrestamos;
	}

	public void setBtnPrestamos(JButton btnPrestamos) {
		this.btnPrestamos = btnPrestamos;
	}

	public JButton getBtnDevoluciones() {
		return btnDevoluciones;
	}

	public void setBtnDevoluciones(JButton btnDevoluciones) {
		this.btnDevoluciones = btnDevoluciones;
	}

	public JButton getBtnExpurgo() {
		return btnExpurgo;
	}

	public void setBtnExpurgo(JButton btnExpurgo) {
		this.btnExpurgo = btnExpurgo;
	}

	public JButton getBtnTransferencia() {
		return btnTransferencia;
	}

	public void setBtnTransferencia(JButton btnTransferencia) {
		this.btnTransferencia = btnTransferencia;
	}
	
	public JButton getBtnExportar() {
		return btnExportar;
	}

	public void setBtnExportar(JButton btnExportar) {
		this.btnExportar = btnExportar;
	}

	public JButton getBtnAyuda() {
		return btnAyuda;
	}

	public void setBtnAyuda(JButton btnAyuda) {
		this.btnAyuda = btnAyuda;
	}
	
	public JFileChooser getFc() {
		return fc;
	}

	public void setFc(JFileChooser fc) {
		this.fc = fc;
	}
	
	/**
	 * Create the frame.
	 */
	public Menu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/Arquivo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(12, 3, 0, 0));
		
		panel_36 = new JPanel();
		contentPane.add(panel_36);
		panel_36.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_35 = new JPanel();
		contentPane.add(panel_35);
		panel_35.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_34 = new JPanel();
		contentPane.add(panel_34);
		panel_34.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_30 = new JPanel();
		panel_34.add(panel_30);
		panel_30.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnAyuda = new JButton("Ayuda");
		panel_34.add(btnAyuda);
		
		panel_33 = new JPanel();
		contentPane.add(panel_33);
		panel_33.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_32 = new JPanel();
		contentPane.add(panel_32);
		panel_32.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnPrestamos = new JButton("Préstamos");
		panel_32.add(btnPrestamos);
		
		panel_31 = new JPanel();
		contentPane.add(panel_31);
		panel_31.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_29 = new JPanel();
		contentPane.add(panel_29);
		panel_29.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_28 = new JPanel();
		contentPane.add(panel_28);
		panel_28.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_27 = new JPanel();
		contentPane.add(panel_27);
		panel_27.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_26 = new JPanel();
		contentPane.add(panel_26);
		panel_26.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_25 = new JPanel();
		contentPane.add(panel_25);
		panel_25.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnDevoluciones = new JButton("Devoluciones");
		panel_25.add(btnDevoluciones);
		
		panel_24 = new JPanel();
		contentPane.add(panel_24);
		panel_24.setLayout(new GridLayout(0, 3, 0, 0));
		
		panel_23 = new JPanel();
		contentPane.add(panel_23);
		panel_23.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_22 = new JPanel();
		contentPane.add(panel_22);
		panel_22.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_21 = new JPanel();
		contentPane.add(panel_21);
		panel_21.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_20 = new JPanel();
		contentPane.add(panel_20);
		panel_20.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_19 = new JPanel();
		contentPane.add(panel_19);
		panel_19.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnModificaciones = new JButton("Modificaciones");
		panel_19.add(btnModificaciones);
		
		panel_18 = new JPanel();
		contentPane.add(panel_18);
		panel_18.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_17 = new JPanel();
		contentPane.add(panel_17);
		panel_17.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_16 = new JPanel();
		contentPane.add(panel_16);
		panel_16.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_15 = new JPanel();
		contentPane.add(panel_15);
		panel_15.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_14 = new JPanel();
		contentPane.add(panel_14);
		panel_14.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_13 = new JPanel();
		contentPane.add(panel_13);
		panel_13.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnTransferencia = new JButton("Transferencia");
		panel_13.add(btnTransferencia);
		
		panel_12 = new JPanel();
		contentPane.add(panel_12);
		panel_12.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_11 = new JPanel();
		contentPane.add(panel_11);
		panel_11.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_10 = new JPanel();
		contentPane.add(panel_10);
		panel_10.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_9 = new JPanel();
		contentPane.add(panel_9);
		panel_9.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_8 = new JPanel();
		contentPane.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_7 = new JPanel();
		contentPane.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnExpurgo = new JButton("Expurgo");
		panel_7.add(btnExpurgo);
		
		panel_6 = new JPanel();
		contentPane.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_5 = new JPanel();
		contentPane.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_4 = new JPanel();
		contentPane.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnExportar = new JButton("Exportar datos");
		panel_1.add(btnExportar);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		fc = new JFileChooser();
		fc.setApproveButtonText("Seleccionar");
		fc.setApproveButtonToolTipText("Seleccionar archivo");

		fc.setAcceptAllFileFilterUsed(false);
		fc.setDialogTitle("Seleccione un archivo Excel");
		FileNameExtensionFilter restrict = new FileNameExtensionFilter("Solo archivos .xls o .xlsx", "xls", "xlsx");
		fc.addChoosableFileFilter(restrict);
	}

}
