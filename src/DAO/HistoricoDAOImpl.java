package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Conexion;
import model.Expediente;
import model.Historico;

public class HistoricoDAOImpl implements HistoricoDAO {

	@Override
	public boolean insert(Historico fila, String tabla) throws SQLException, ClassNotFoundException{
        // Construir la query
        String query = "INSERT INTO " + tabla + "(numExpediente, tipo, anio, juzgado, fechaHito) VALUES ("
                + fila.getNumExpediente() + ",'" + fila.getTipo() + "'," 
        		+ fila.getAnio() + ",'" + fila.getJuzgado() + "','" + fila.getFechaHito() + "')";

        // Ejecutar la query en la BBDD
        return Conexion.execute(query);
    }
	
	@Override
	public ArrayList<Historico> getAllRows(String tabla) throws ClassNotFoundException, SQLException {
		ArrayList<Historico> filas = new ArrayList<Historico>();
        
        String sql = "SELECT * FROM " + tabla;
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
	public ArrayList<Historico> aplicaFiltrosHistorico(int numExp, String tipo, int anio, String juzgado, String tabla) throws SQLException, ClassNotFoundException{
		ArrayList<Historico> filas = new ArrayList<>();
        
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM " + tabla + " WHERE ");
        
        if (numExp > 0) {
            queryBuilder.append(" numExpediente = ");
            queryBuilder.append(numExp);
        }
        if (!tipo.equalsIgnoreCase("")) {
            if (numExp > 0) {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" tipo = '");
            queryBuilder.append(tipo);
            queryBuilder.append("'");
        }
        if (anio > 1900) {
            if (!tipo.equalsIgnoreCase("") || numExp > 0) {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" anio = ");
            queryBuilder.append(anio);
        }
        if (!juzgado.equalsIgnoreCase("")) {
            if (!tipo.equalsIgnoreCase("") || numExp > 0 || anio > 1900) {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" juzgado = '");
            queryBuilder.append(juzgado);
            queryBuilder.append("'");
        }

        ResultSet rs = Conexion.executeSelect(queryBuilder.toString());
        while (rs.next()) {
        	int numExpediente = rs.getInt("numExpediente");
            String tipo_ = rs.getString("tipo");
            int anio_ = rs.getInt("anio");
            String juzgado_ = rs.getString("juzgado");
            String fechaHito = rs.getString("fechaHito");
           
            Historico fila = new Historico(numExpediente, tipo_, anio_, juzgado_, fechaHito);
            filas.add(fila);
        }
        return filas;
    }

	@Override
	public boolean existeHistorico(Historico fila, String tabla) throws SQLException, ClassNotFoundException {
		boolean existe = false;

        String query = "SELECT COUNT(*) AS count FROM " + tabla + " WHERE numExpediente = " + fila.getNumExpediente() +
        		" AND tipo = '" + fila.getTipo() + "'" + " AND anio = " + fila.getAnio() + "" +
                " AND fechaHito = '" + fila.getFechaHito() + "' AND juzgado = '" + fila.getJuzgado() + "'";

        ResultSet rs = Conexion.executeSelect(query);
        if (rs.next()) {
        	int count = rs.getInt("count");
        	existe = count > 0;
        }
        return existe;
	}

}
