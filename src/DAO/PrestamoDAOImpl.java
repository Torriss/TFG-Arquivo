package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Conexion;
import model.Expediente;

public class PrestamoDAOImpl implements PrestamoDAO{
	
	public List<Expediente> realizarPrestamo(int numExp, String tipo, int anio, String solicitante, String juzgado) throws SQLException{
	    // Comprobar que el expediente existe en la BBDD
		// TODO: revisar: al solicitar un prestamo no sabes en que caja ni ubicacion esta el expediente.
		// Solicitas un expediente de un tipo, con numero, año, y de un juzgado. Esto hace una consulta de ese expediente en la bd
		// y esa consulta es la que devuelve toda la info del expediente, incluida la ubicacion
	    
		ExpedienteDAO exp = new ExpedienteDAOImpl();
		List<Expediente> expList = new ArrayList<>();
		
		if (!exp.existeExpediente(numExp, tipo, anio, juzgado)) {
	        System.out.println("El expediente no existe en la base de datos.");
	        return expList;
	    }
	    
	    // Comprobar que el expediente no esta en la tabla prestamos
	    if (existePrestamo(numExp, tipo, anio)) {
	        System.out.println("El expediente ya ha sido prestado.");
	        return expList;
	    }
	    
	    // Obtener la fecha y hora actual del sistema
	    LocalDate fechaActual = LocalDate.now();
	    
	    //Recorremos lista expedientes para a�adirlos a la tabla prestamos
	    expList = exp.buscaExpediente(numExp, tipo, anio, juzgado);
	    for (Expediente expediente : expList) {
	    	//Construimos query
	    	String query = "INSERT INTO Prestamos (numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, fechaPrestamo, solicitante) " +
	                "VALUES ('" + expediente.getNumExpediente() + "', '" + expediente.getTipo() + "', " + expediente.getAnio() + ", " + expediente.getCaja() +
	                ", '" + expediente.getUbicacion() + "', '" + expediente.getNotas() + "', '" + expediente.getTomos() + "', '" + expediente.getJuzgado() +
	                "', '" + expediente.getLugar() + "', '" + fechaActual + "', '" + solicitante + "')";
	    	//Ejecutamos query
	    	Conexion.execute(query);
	    }
	    
	    return expList;
	}


	private boolean existePrestamo(int numExpediente, String tipo, int anio) throws SQLException{
	    String query = "SELECT COUNT(*) AS count FROM Prestamos WHERE numExpediente = " + numExpediente +
	            " AND tipo = '" + tipo + "'" +
	            " AND anio = " + anio + "";
	    ResultSet rs = Conexion.executeSelect(query);
	    
	    if (rs.next()) {
	    	int count = rs.getInt("count");
	        return count > 0;
	    }
	    return false;
	}
	
	public boolean eliminarPrestamo(int numExpediente, String tipo, int anio, String tomos) {
        String query = "DELETE FROM Prestamos WHERE numExpediente = " + numExpediente +
                " AND tipo = '" + tipo + "'" +
                " AND anio = " + anio +
                " AND tomos = '" + tomos + "'";
        return Conexion.execute(query);
    }
}
