package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class Conexion {
	public enum consult {Insert, Update, CreateTable}
	
	protected static Connection c = null;
	static String usuario = "root";
	static String password = "";
	private static String _bd = "arquivo"; 
	static String url = "jdbc:mysql://localhost/"+_bd;
	
	/*public DataSource() {
		this.initStructure();
	}*/
	
	public static void initConnnection() throws ClassNotFoundException, SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = (Connection)DriverManager.getConnection(url, usuario, password);
			if(c != null) {
				System.out.println("Conexion correcta");
			}
		}
		catch(SQLException ex) {
			System.out.println("Problema al conectar con la BD");
			ex.printStackTrace();
		}
	}
}
