package view;

import javax.swing.*;

import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PrestamoForm extends JFrame {
    private JTextField txtNumExpediente;
    private JTextField txtTipo;
    private JTextField txtAnio;
    private JTextField txtCaja;
    private JTextField txtUbicacion;

    public PrestamoForm() {
        setTitle("Formulario de Pr�stamo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel lblNumExpediente = new JLabel("N�mero de Expediente:");
        txtNumExpediente = new JTextField();
        JLabel lblTipo = new JLabel("Tipo:");
        txtTipo = new JTextField();
        JLabel lblAnio = new JLabel("A�o:");
        txtAnio = new JTextField();
        JLabel lblCaja = new JLabel("Caja:");
        txtCaja = new JTextField();
        JLabel lblUbicacion = new JLabel("Ubicaci�n:");
        txtUbicacion = new JTextField();

        panel.add(lblNumExpediente);
        panel.add(txtNumExpediente);
        panel.add(lblTipo);
        panel.add(txtTipo);
        panel.add(lblAnio);
        panel.add(txtAnio);
        panel.add(lblCaja);
        panel.add(txtCaja);
        panel.add(lblUbicacion);
        panel.add(txtUbicacion);

        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener los datos del formulario
                    int numExpediente = Integer.parseInt(txtNumExpediente.getText());
                    String tipo = txtTipo.getText();
                    int anio = Integer.parseInt(txtAnio.getText());
                    int caja = Integer.parseInt(txtCaja.getText());
                    String ubicacion = txtUbicacion.getText();

                    // Crear el objeto Expediente con los datos del formulario
                    // TODO: revisar numPaginas a 0
                    Expediente expediente = new Expediente(numExpediente, tipo, anio, caja, ubicacion, null, null, null, null, 0);

                    // Realizar el pr�stamo
                    boolean prestamoExitoso = false;
//					try {
//						prestamoExitoso = Prestamo.realizarPrestamo(expediente);
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}

                    // Mostrar mensaje de �xito o error
                    if (prestamoExitoso) {
                        JOptionPane.showMessageDialog(PrestamoForm.this, "Pr�stamo realizado exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(PrestamoForm.this, "Error al realizar el pr�stamo.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PrestamoForm.this, "Por favor, ingrese valores num�ricos v�lidos.");
                }
            }
        });

        panel.add(btnEnviar);

        add(panel);
        setVisible(true);
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PrestamoForm();
            }
        });
    }*/
}
