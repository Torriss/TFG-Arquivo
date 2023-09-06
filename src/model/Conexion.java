package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	
	//public enum consult { Insert, Update, CreateTable }
	
	protected static Connection instance = null;
	private static String usuario ="root";
	private static String password = "1234";
	private static String _bd = "arquivo";
	private static String url = "jdbc:mysql://localhost/" + _bd;
	//protected HashMap<consult, String> structConsult;
	
	private static void initConnection() throws ClassNotFoundException, SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			instance = (Connection)DriverManager.getConnection(url, usuario, password);
			if (instance != null) {
				System.out.println("Conexion correcta!");
			}
			
		}
		catch(SQLException ex){
			//System.out.println("Hubo un problema al intentar conecarse a la base de datos"+ url);
			//ex.printStackTrace();
			throw ex;
		}
	}
	
	public static void closeConnection() throws SQLException {
		if (instance != null || instance.isClosed()){
			//System.out.println("Conexion CERRADA!");
			instance.close();
		}
	}
	
	public static boolean execute(String query) throws ClassNotFoundException, SQLException{
		int r = 0;
		try {
			if (instance == null || instance.isClosed()) initConnection();
			Statement statement = instance.createStatement();
			r = statement.executeUpdate(query);
		} catch (SQLException e) {
			//System.out.println("No se ha podido ejecutar la query: '" + query + "'");
			//e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			throw e;
		}

		return r > 0;
	}
	
	public static ResultSet executeSelect(String query) throws ClassNotFoundException, SQLException{
		ResultSet rs = null;
		try {
			if (instance == null || instance.isClosed()) initConnection();
			Statement statement =  instance.createStatement();
			rs = statement.executeQuery(query); 
		} catch (SQLException e) {
			System.out.println("No se ha podido ejecutar al query: '" + query + "'");
			//e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			throw e;
		}
		return rs;
	}
	
  
}
