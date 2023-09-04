package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Conexion;
import model.Historico;

public class HistoricoDAOImpl implements HistoricoDAO {

	@Override
	public boolean insert(Historico fila, String tabla) throws SQLException, ClassNotFoundException{
        // Construir la query
        String query = "INSERT INTO " + tabla + "(numExpediente, tipo, anio, juzgado, fechaHito) VALUES ('"
                + fila.getNumExpediente() + "','" + fila.getTipo() + 
        		+ fila.getAnio() + "','" + fila.getJuzgado() + "','" + fila.getFechaHito() + "')";

        // Ejecutar la query en la BBDD
        return Conexion.execute(query);
    }
	
	@Override
	public List<Historico> getAllRows(String tabla) throws ClassNotFoundException, SQLException {
		List<Historico> filas = new ArrayList<>();
        
        String sql = "SELECT * FROM" + tabla;
        ResultSet rs = Conexion.executeSelect(sql);
        
        while (rs.next()) {
            int numExpediente = rs.getInt("numExpediente");
            String tipo = rs.getString("tipo");
            int anio = rs.getInt("anio");
            String juzgado = rs.getString("juzgado");
            String fechaHito = rs.getString("fechaHito");
            
            Historico fila = new Historico(numExpediente, tipo, anio, juzgado, fechaHito);
            filas.add(fila);
        }
        
        return filas;
	}

	@Override
	public boolean existeHistorico(Historico fila, String tabla) throws SQLException, ClassNotFoundException {
		boolean existe = false;

        String query = "SELECT COUNT(*) AS count FROM " + tabla + " WHERE numExpediente = " + fila.getNumExpediente() +
        		" AND tipo = '" + fila.getTipo() + "'" + " AND anio = " + fila.getAnio() + "" +
                " AND fechaHito = '" + fila.getFechaHito() +" AND juzgado = '" + fila.getJuzgado() + "'";

        ResultSet rs = Conexion.executeSelect(query);
        if (rs.next()) {
        	int count = rs.getInt("count");
        	existe = count > 0;
        }
        return existe;
	}

}
