package launcher;

import java.awt.EventQueue;
import java.sql.SQLException;

import DAO.*;
import view.*;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		//Nueva manera de acceder a las funciones de las clases de model mediante los DAOS
		ExpedienteDAO exp = new ExpedienteDAOImpl();
		exp.buscaExpediente(0, null, 0, null);
		
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
