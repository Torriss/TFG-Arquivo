package launcher;

import java.awt.EventQueue;
import java.sql.SQLException;

import DAO.*;
import control.ControlMenu;
import view.*;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		//Nueva manera de acceder a las funciones de las clases de model mediante los DAOS
		ExpedienteDAO exp = new ExpedienteDAOImpl();
		exp.buscaExpediente(0, null, 0, null);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu mainWindow = new Menu();
					mainWindow.setVisible(true);
					ControlMenu mainWindowContr = new ControlMenu();
					mainWindowContr.initControl();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
