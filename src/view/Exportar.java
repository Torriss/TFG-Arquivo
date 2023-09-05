package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;

public class Exportar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2708828160008419936L;
	private JPanel contentPane;
	private JButton btnExportExpedientes;
	private JButton btnExportTransferencias;
	private JButton btnExportExpurgos;
	
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

	/**
	 * Create the frame.
	 */
	public Exportar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 675, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(12, 3, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_6 = new JPanel();
		contentPane.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_7 = new JPanel();
		contentPane.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnExportExpedientes = new JButton("Expedientes");
		panel_7.add(btnExportExpedientes);
		
		JPanel panel_8 = new JPanel();
		contentPane.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_9 = new JPanel();
		contentPane.add(panel_9);
		panel_9.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_10 = new JPanel();
		contentPane.add(panel_10);
		panel_10.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_11 = new JPanel();
		contentPane.add(panel_11);
		panel_11.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_12 = new JPanel();
		contentPane.add(panel_12);
		panel_12.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_13 = new JPanel();
		contentPane.add(panel_13);
		panel_13.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_14 = new JPanel();
		contentPane.add(panel_14);
		panel_14.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_15 = new JPanel();
		contentPane.add(panel_15);
		panel_15.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_16 = new JPanel();
		contentPane.add(panel_16);
		panel_16.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnExportTransferencias = new JButton("Transferencias");
		panel_16.add(btnExportTransferencias);
		
		JPanel panel_17 = new JPanel();
		contentPane.add(panel_17);
		panel_17.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_18 = new JPanel();
		contentPane.add(panel_18);
		panel_18.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_19 = new JPanel();
		contentPane.add(panel_19);
		panel_19.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_20 = new JPanel();
		contentPane.add(panel_20);
		panel_20.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_21 = new JPanel();
		contentPane.add(panel_21);
		panel_21.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_22 = new JPanel();
		contentPane.add(panel_22);
		panel_22.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_23 = new JPanel();
		contentPane.add(panel_23);
		panel_23.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_24 = new JPanel();
		contentPane.add(panel_24);
		panel_24.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_25 = new JPanel();
		contentPane.add(panel_25);
		panel_25.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnExportExpurgos = new JButton("Expurgos");
		panel_25.add(btnExportExpurgos);
		
		JPanel panel_26 = new JPanel();
		contentPane.add(panel_26);
		panel_26.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_27 = new JPanel();
		contentPane.add(panel_27);
		panel_27.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_28 = new JPanel();
		contentPane.add(panel_28);
		panel_28.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_29 = new JPanel();
		contentPane.add(panel_29);
		panel_29.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_30 = new JPanel();
		contentPane.add(panel_30);
		panel_30.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_31 = new JPanel();
		contentPane.add(panel_31);
		panel_31.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_32 = new JPanel();
		contentPane.add(panel_32);
		panel_32.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_33 = new JPanel();
		contentPane.add(panel_33);
		panel_33.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_34 = new JPanel();
		contentPane.add(panel_34);
		panel_34.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_35 = new JPanel();
		contentPane.add(panel_35);
		panel_35.setLayout(new GridLayout(1, 0, 0, 0));
	}

}
