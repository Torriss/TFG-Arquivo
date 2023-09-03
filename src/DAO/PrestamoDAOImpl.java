package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Expediente;

public class PrestamoDAOImpl implements PrestamoDAO{
	
	public List<Expediente> realizarPrestamo(int numExp, String tipo, int anio, String solicitante, String juzgado, String fechaPrestamo) throws SQLException{
		ExpedienteDAO exp = new ExpedienteDAOImpl();
		List<Expediente> expList = new ArrayList<>();
		
		if (!exp.existeExpediente(numExp, tipo, anio, juzgado)) {
	        return expList;
	    }
	    
	    expList = exp.buscaExpediente(numExp, tipo, anio, juzgado);
	    for (Expediente expediente : expList) {
	    	//cambiamos solicitante y fechaPrestamo
	    	expediente.setFechaPrestamo(fechaPrestamo);
	    	expediente.setSolicitante(solicitante);
	    	//actualizamos en bbdd
	    	exp.update(expediente);
	    }
	    
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
