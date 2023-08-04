package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class Prestamo {
	private int numExpediente;
	private String tipo;
	private int anio;
	private int caja;
	private String ubicacion;
	private String notas;
	private String tomos;
	private String juzgado;
	private String lugar;
	private String fechaPrestamo;
	
	public Prestamo(int numExpediente, String tipo, int anio, int caja, String ubicacion, String notas, String tomos, 
					String juzgado, String lugar, String fechaPrestamo, String fechaMaxDevolucion) {
		this.numExpediente = numExpediente;
		this.tipo = tipo;
		this.caja = caja;
		this.anio = anio;
		this.ubicacion = ubicacion;
		this.notas = notas;
		this.tomos = tomos;
		this.juzgado = juzgado;
		this.lugar = lugar;
		this.fechaPrestamo = fechaPrestamo;
	}
	
	public static boolean realizarPrestamo(Expediente exp) {
	    // Comprobar que el expediente existe en la BBDD
	    //if (!Expediente.existeExpediente(exp.getNumExpediente(), exp.getTipo(), exp.getAnio(), exp.getCaja(), exp.getUbicacion())) {
		if (!Expediente.existeExpediente(exp.getNumExpediente(), exp.getTipo())) {
	        System.out.println("El expediente no existe en la base de datos.");
	        return false;
	    }
	    
	    // Comprobar que el expediente no esta en la tabla prestamos
	    if (existePrestamo(exp.getNumExpediente(), exp.getTipo(), exp.getAnio(), exp.getCaja(), exp.getUbicacion())) {
	        System.out.println("El expediente ya ha sido prestado.");
	        return false;
	    }
	    
	    // Obtener la fecha y hora actual del sistema
	    Date fechaActual = new Date();
	    Timestamp timestamp = new Timestamp(fechaActual.getTime());
	    
	    // Construir la query del prestamo
	    String query = "INSERT INTO Prestamos (numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, fechaPrestamo) " +
	            "VALUES ('" + exp.getNumExpediente() + "', '" + exp.getTipo() + "', " + exp.getAnio() + ", " + exp.getCaja() +
	            ", '" + exp.getUbicacion() + "', '" + exp.getNotas() + "', '" + exp.getTomos() + "', '" + exp.getJuzgado() +
	            "', '" + exp.getLugar() + "', '" + timestamp + "')";
	    
	    // Ejecutar la query en la BBDD
	    return Conexion.execute(query);
	}


	private static boolean existePrestamo(int numExpediente, String tipo, int anio, int caja, String ubicacion) {
	    String query = "SELECT COUNT(*) AS count FROM Prestamos WHERE numExpediente = " + numExpediente +
	            " AND tipo = '" + tipo + "'" +
	            " AND anio = " + anio +
	            " AND caja = " + caja +
	            " AND ubicacion = '" + ubicacion + "'";
	    ResultSet rs = Conexion.executeSelect(query);
	    
	    try {
	        if (rs.next()) {
	            int count = rs.getInt("count");
	            return count > 0;
	        }
	    } catch (SQLException e) {
	        System.out.println("No se ha podido ejecutar la consulta.");
	        e.printStackTrace();
	    }
	    
	    return false;
	}


	public boolean realizarDevolucion() {
		return false;
	}
	
	
}
