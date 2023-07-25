package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Expediente {
	private int numExpediente;
	private String tipo;
	private int anio;
	private int caja;
	private String ubicacion;
	private String notas;
	private String tomos;
	private String juzgado;
	private String lugar;
	
	public Expediente(int numExpediente, String tipo, int anio, int caja, String ubicacion, String notas, String tomos, String juzgado, String lugar) {
		this.numExpediente = numExpediente;
		this.tipo = tipo;
		this.caja = caja;
		this.anio = anio;
		this.ubicacion = ubicacion;
		this.notas = notas;
		this.tomos = tomos;
		this.juzgado = juzgado;
		this.lugar = lugar;
	}
	
	public static boolean insert(Expediente exp) {
		//posible comprobacion de no nulos y rangos
		
		//Construir la query
		String query = "INSERT INTO Expedientes (numExpediente, tipo, caja, anio, ubicacion, notas, tomos, juzgado, lugar) VALUES ('"+exp.numExpediente+"','"+exp.tipo+"','"+exp.anio+"','"+exp.caja+"','"+exp.ubicacion+"','"+exp.notas+"','"+exp.tomos+"','"+exp.juzgado+"','"+exp.lugar+"')";
				
		//Ejecutar la query en la BBDD
		return Conexion.execute(query);
	}
	
	//posiblemente tenga que ser con el ID
	public static boolean delete(Expediente exp) {
		//posible comprobacion de no nulos y rangos
	
		//Construir la query
		String query = "DELETE FROM Expedientes (numExpediente, tipo, caja, anio, ubicacion, notas, tomos, juzgado, lugar) VALUES ('"+exp.numExpediente+"','"+exp.tipo+"','"+exp.anio+"','"+exp.caja+"','"+exp.ubicacion+"','"+exp.notas+"','"+exp.tomos+"','"+exp.juzgado+"','"+exp.lugar+"')";
						
		//Ejecutar la query en la BBDD
		return Conexion.execute(query);
	}
	
	public static boolean update(Expediente exp) {
		String query = "UPDATE Expedientes SET tipo = '" + exp.tipo + "', anio = " + exp.anio + ", caja = " + exp.caja
                + ", ubicacion = '" + exp.ubicacion + "', notas = '" + exp.notas + "', tomos = '" + exp.tomos
                + "', juzgado = '" + exp.juzgado + "', lugar = '" + exp.lugar + "' WHERE numExpediente = "
                + exp.numExpediente;
		
        return Conexion.execute(query); //devuelve numero de filas afectadas
	}
	
	public static Expediente getByID(int id) throws SQLException {
        Expediente exp = null;

        String sql = "SELECT * FROM Expedientes WHERE numExpediente = " + id;
        ResultSet rs = Conexion.executeSelect(sql);

        if (rs.next()) {
            int numExpediente = rs.getInt("numExpediente");
            String tipo = rs.getString("tipo");
            int anio = rs.getInt("anio");
            int caja = rs.getInt("caja");
            String ubicacion = rs.getString("ubicacion");
            String notas = rs.getString("notas");
            String tomos = rs.getString("tomos");
            String juzgado = rs.getString("juzgado");
            String lugar = rs.getString("lugar");

            exp = new Expediente(numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar);
        }

        return exp;
    }
	
	public static boolean existeExpediente(int numExpediente, String tipo, int anio, int caja, String ubicacion) {
	    boolean existe = false;

	    try {
	        String query = "SELECT COUNT(*) AS count FROM Expedientes WHERE numExpediente = " + numExpediente +
	                " AND tipo = '" + tipo + "'" +
	                " AND anio = " + anio +
	                " AND caja = " + caja +
	                " AND ubicacion = '" + ubicacion + "'";

	        ResultSet rs = Conexion.executeSelect(query);
	        if (rs.next()) {
	            int count = rs.getInt("count");
	            existe = count > 0;
	        }
	    } catch (SQLException e) {
	        System.out.println("No se ha podido ejecutar la consulta.");
	        e.printStackTrace();
	    }

	    return existe;
	}

	
	public static List<Expediente> getAllExpedientes() throws SQLException {
	    List<Expediente> expedientes = new ArrayList<>();
	    
	    String sql = "SELECT * FROM Expedientes";
	    ResultSet rs = Conexion.executeSelect(sql);
	    
	    while (rs.next()) {
	        int numExpediente = rs.getInt("numExpediente");
	        String tipo = rs.getString("tipo");
	        int anio = rs.getInt("anio");
	        int caja = rs.getInt("caja");
	        String ubicacion = rs.getString("ubicacion");
	        String notas = rs.getString("notas");
	        String tomos = rs.getString("tomos");
	        String juzgado = rs.getString("juzgado");
	        String lugar = rs.getString("lugar");
	        
	        Expediente exp = new Expediente(numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar);
	        expedientes.add(exp);
	    }
	    
	    return expedientes;
	}
	
	public int getNumExpediente() {
	    return numExpediente;
	}

	public String getTipo() {
	    return tipo;
	}

	public int getAnio() {
	    return anio;
	}

	public int getCaja() {
	    return caja;
	}

	public String getUbicacion() {
	    return ubicacion;
	}

	public String getNotas() {
	    return notas;
	}

	public String getTomos() {
	    return tomos;
	}

	public String getJuzgado() {
	    return juzgado;
	}

	public String getLugar() {
	    return lugar;
	}

}
