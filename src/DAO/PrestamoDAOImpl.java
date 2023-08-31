package DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Conexion;
import model.Expediente;

public class PrestamoDAOImpl implements PrestamoDAO{
	
	public List<Expediente> realizarPrestamo(int numExp, String tipo, int anio, String solicitante, String juzgado, Date fechaPrestamo) throws SQLException{
	    // Comprobar que el expediente existe en la BBDD
		// TODO: revisar: al solicitar un prestamo no sabes en que caja ni ubicacion esta el expediente.
		// Solicitas un expediente de un tipo, con numero, año, y de un juzgado. Esto hace una consulta de ese expediente en la bd
		// y esa consulta es la que devuelve toda la info del expediente, incluida la ubicacion
	    
		ExpedienteDAO exp = new ExpedienteDAOImpl();
		List<Expediente> expList = new ArrayList<>();
		
		if (!exp.existeExpediente(numExp, tipo, anio, juzgado)) {
	        return expList;
	    }
	    
	    // Obtener la fecha y hora actual del sistema
	    //LocalDate fechaActual = LocalDate.now();
	    
	    expList = exp.buscaExpediente(numExp, tipo, anio, juzgado);
	    for (Expediente expediente : expList) {
	    	expediente.setFechaPrestamo(fechaPrestamo);
	    	expediente.setSolicitante(solicitante);
	    }
	    
	    //Cogemos primer expediente y actualizamos fecha y solicitante de todos en la bbdd
	    exp.update(expList.get(0));
	    
	    return expList;
	}

//	@Override
//	public boolean existePrestamo(int numExpediente, String tipo, int anio, String juzgado) throws SQLException{
//	    String query = "SELECT COUNT(*) AS count FROM Prestamos WHERE numExpediente = " + numExpediente +
//	            " AND tipo = '" + tipo + "'" +
//	            " AND anio = " + anio + "";
//	    ResultSet rs = Conexion.executeSelect(query);
//	    
//	    if (rs.next()) {
//	    	int count = rs.getInt("count");
//	        return count > 0;
//	    }
//	    return false;
//	}
//	
//	@Override
//	public boolean eliminarPrestamo(int numExpediente, String tipo, int anio, String tomos) throws SQLException{
//        String query = "DELETE FROM Prestamos WHERE numExpediente = " + numExpediente +
//                " AND tipo = '" + tipo + "'" +
//                " AND anio = " + anio +
//                " AND tomos = '" + tomos + "'";
//        return Conexion.execute(query);
//    }
}
