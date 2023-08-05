package launcher;

import java.awt.EventQueue;
import java.sql.SQLException;

import model.Expediente;
import view.*;

public class Main {

	public static void main(String[] args) throws SQLException {
//
//		Expediente exp = Expediente.getByID(1);
//		System.out.println(exp.getNumExpediente() + "  " + exp.getAnio());
//		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow mainWindow = new MainWindow();
					mainWindow.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
