package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Conexion;
import model.Historico;

public class HistoricoDAOImpl implements HistoricoDAO {

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

}
