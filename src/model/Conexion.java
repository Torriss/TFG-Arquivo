package model;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.HashMap;
//import java.util.Map;

public class Conexion {
	
	//public enum consult { Insert, Update, CreateTable }
	
	protected static Connection instance = null;
	private static String usuario ="root";
	private static String password = "";
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
			System.out.println("Hubo un problema al intentar conecarse a la base de datos"+ url);
			ex.printStackTrace();
		}
	}
	
	public static void closeConnection() throws SQLException {
		if (instance != null || instance.isClosed()){
			System.out.println("Conexion CERRADA!");
			instance.close();
			
		}
	}
	
	public static boolean execute(String query) {
		int r = 0;
		try {
			if (instance == null || instance.isClosed()) initConnection();
			Statement statement = instance.createStatement();
			r = statement.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("No se ha podido ejecutar la query: '" + query + "'");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return r > 0;
	}
	
	public static ResultSet executeSelect(String query) {
		ResultSet rs = null;
		try {
			if (instance == null || instance.isClosed()) initConnection();
			Statement statement =  instance.createStatement();
			rs = statement.executeQuery(query); 
		} catch (SQLException e) {
			System.out.println("No se ha podido ejecutar al query: '" + query + "'");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
	
	//esta la hice yo, posiblemente para nada
	/*public static Connection getConexion() {
		try {
			if (instance == null || instance.isClosed()) {
				initConnection();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return instance;
	}*/
	
	/*private void checkConnection() {
		try {
			if (instance == null || instance.isClosed()) {
				initConnection();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	/*public T readByParam(String paramName, String value) {
		T result = null;

		Statement statement;

		try {
			this.checkConnection();
			statement = c.createStatement();
			ResultSet res = statement.executeQuery(
					"SELECT * FROM " + getTableName() + " WHERE " + paramName + " = '" + value + "' LIMIT 1;");

			if (res.next())
				result = convToTransfer(res);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public Map<Long, T> readAll() {
		Map<Long, T> listaT = new HashMap<Long, T>();
		Statement statement;
		ResultSet result = null;
		String query;
		
		try {
		this.checkConnection();
		statement = c.createStatement();
		query = "SELECT * FROM " + getTableName() + ";";
	
		result = statement.executeQuery(query);
		while (result.next()) {
			T r = convToTransfer(result);
			listaT.put(this.getId(r), r);
		}
		result.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaT;
	}
	
	public Map<Long,T> readAllBy(String param, String value) {
		Map<Long,T> list = new HashMap<Long,T>();
		Statement statement;
		ResultSet result = null;
		String query;
		
		try {
			this.checkConnection();
			statement = c.createStatement();
			query = "SELECT * FROM " + getTableName() + " WHERE " + param + " LIKE '" + value + "' ;";
			result = statement.executeQuery(query);
			
			while (result.next()) {
				T r = convToTransfer(result);
				list.put(this.getId(r), r);
			}
			result.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public long insert(T d) {
		PreparedStatement statement;
		try {
			this.checkConnection();
			String[] data = extractDatos(d);
			statement = c.prepareStatement(this.structConsult.get(consult.Insert),Statement.RETURN_GENERATED_KEYS);
			
			for	(int i = 1; i < data.length; i++)
				statement.setObject(i, data[i]);
			
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Creating " + getTableName() + " failed, no rows affected.");
			}
			
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					setId(d, generatedKeys.getLong(1));
				} else {
					throw new SQLException("Creating " + getTableName() + " failed, no ID obtained.");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getId(d);
	}
	
	public void update(T d) {
		try {
			this.checkConnection();
			PreparedStatement statement = c.prepareStatement(this.structConsult.get(consult.Update));
			String[] data = extractDatos(d);

			for	(int i = 1; i < data.length; i++)
				statement.setObject(i, data[i]);
			
			statement.setObject(data.length, data[0]);
			
			System.out.println("Ejecutando actualizacion: " + statement);

			int affectedRows = statement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Updating " + getTableName() + " failed, no rows affected.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	//public abstract String getTableName();
	
	//public abstract T convToTransfer(ResultSet r);

	//public abstract void setId(T t, long l);

	//public abstract long getId(T t);

	//protected abstract void initStructure();

	//protected abstract String[] extractDatos(T t);