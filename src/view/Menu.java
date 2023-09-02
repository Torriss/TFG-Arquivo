package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;

public class Menu extends JFrame {

	private static final long serialVersionUID = 680423924785154336L;
	private JPanel contentPane;
	private JButton btnPrestamos;
	private JButton btnDevoluciones;
	private JButton btnExpurgo;
	private JButton btnTransferencia;

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
	
	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 4, 0, 0));
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		
		btnPrestamos = new JButton("Préstamos");
		panel_3.add(btnPrestamos);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		btnDevoluciones = new JButton("Devoluciones");
		panel_2.add(btnDevoluciones);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		btnExpurgo = new JButton("Expurgo");
		panel_1.add(btnExpurgo);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		btnTransferencia = new JButton("Transferencia");
		panel.add(btnTransferencia);
	}

}
