package launcher;

import java.awt.EventQueue;
import java.sql.SQLException;

import control.ControlMenu;
import view.Menu;

public class Main {

	public static void main(String[] args) throws SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu mainWindow = new Menu();
					mainWindow.setVisible(true);
					ControlMenu mainWindowContr = new ControlMenu(mainWindow);
					mainWindowContr.initControl();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
