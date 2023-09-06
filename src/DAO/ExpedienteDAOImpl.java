package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Conexion;
import model.Expediente;

public class ExpedienteDAOImpl implements ExpedienteDAO {
	
	@Override
	public boolean insert(Expediente exp) throws SQLException, ClassNotFoundException{
        // Posible comprobacion de no nulos y rangos

        // Construir la query
        String query = "INSERT INTO Expedientes (numExpediente, tipo, caja, anio, ubicacion, notas, tomos, juzgado, lugar, paginas, estado) VALUES ('"
                + exp.getNumExpediente() + "','" + exp.getTipo() + "','" + exp.getCaja() + "','"
        		+ exp.getAnio() + "','" + exp.getUbicacion() + "','" + exp.getNotas() + "','"
                + exp.getTomos() + "','" + exp.getJuzgado() + "','" + exp.getLugar() + "','"
        		+ exp.getPaginas() + "','" + exp.getEstado() + "')";

        // Ejecutar la query en la BBDD
        return Conexion.execute(query);
    }

	public boolean update(Expediente exp) throws SQLException, ClassNotFoundException {
	    String query = "UPDATE Expedientes SET tipo = '" + exp.getTipo() + "', anio = " + exp.getAnio() + ", caja = " + exp.getCaja()
	                + ", ubicacion = '" + exp.getUbicacion() + "', notas = " 
	                + (exp.getNotas().compareTo("") != 0 ? "'" + exp.getNotas() + "'" : "''") + ", tomos = " 
	                + (exp.getTomos().compareTo("") != 0 ? "'" + exp.getTomos() + "'" : "''") + ", juzgado = '" + exp.getJuzgado() + "', lugar = '" 
	                + exp.getLugar() + "', paginas = " + exp.getPaginas() + ", estado = '" + exp.getEstado() 
	                + "' WHERE numExpediente = " + exp.getNumExpediente() + " AND tipo = '" + exp.getTipo() 
	                + "' AND anio = " + exp.getAnio() + " AND (tomos = '" + exp.getTomos() + "' OR tomos = '')";

	    return Conexion.execute(query);
	}



	@Override
    public boolean delete(int numExpediente) throws ClassNotFoundException, SQLException {
        // Posible comprobacion de no nulos y rangos

        // Construir la query
        String query = "DELETE FROM Expedientes WHERE numExpediente = " + numExpediente;

        // Ejecutar la query en la BBDD
        return Conexion.execute(query);
    }

	@Override
    public ArrayList<Expediente> buscaExpediente(int numExpe, String type, int year, String judge) throws SQLException, ClassNotFoundException{
		ArrayList<Expediente> expedientes = new ArrayList<>();

        String query = "SELECT * FROM Expedientes WHERE numExpediente = " + numExpe +
        		" AND tipo = '" + type + "'" +
                " AND anio = " + year + "" +
        		" AND juzgado = '" + judge + "'";

        ResultSet rs = Conexion.executeSelect(query);
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
            int paginas = rs.getInt("paginas");
            String estado = rs.getString("estado");
            
            Expediente exp = new Expediente(numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas, estado);
            expedientes.add(exp);
        }
        return expedientes;
    }
	
	@Override
    public ArrayList<Expediente> aplicaFiltrosExpediente(int numExp, String tipo, int anio, String juzgado) throws SQLException, ClassNotFoundException{
		ArrayList<Expediente> expedientes = new ArrayList<>();
        
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM expedientes WHERE ");
        
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
            int caja = rs.getInt("caja");
            String ubicacion = rs.getString("ubicacion");
            String notas = rs.getString("notas");
            String tomos = rs.getString("tomos");
            String juzgado_ = rs.getString("juzgado");
            String lugar = rs.getString("lugar");
            int paginas = rs.getInt("paginas");
            String estado = rs.getString("estado");
            
            Expediente exp = new Expediente(numExpediente, tipo_, anio_, caja, ubicacion, notas, tomos, juzgado_, lugar, paginas, estado);
            expedientes.add(exp);
        }
        return expedientes;
    }
    
	@Override
    public boolean existeExpediente(int numExpediente, String tipo, int anio, String juzgado) throws SQLException, ClassNotFoundException{
        boolean existe = false;

        String query = "SELECT COUNT(*) AS count FROM Expedientes WHERE numExpediente = " + numExpediente +
        		" AND tipo = '" + tipo + "'" +
                " AND anio = " + anio + "" +
                " AND juzgado = '" + juzgado + "'";

        ResultSet rs = Conexion.executeSelect(query);
        if (rs.next()) {
        	int count = rs.getInt("count");
        	existe = count > 0;
        }
        return existe;
    }
    
	@Override
    public ArrayList<Expediente> getAllExpedientes() throws SQLException, ClassNotFoundException {
		ArrayList<Expediente> expedientes = new ArrayList<>();
        
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
            int paginas = rs.getInt("paginas");
            String estado = rs.getString("estado");
            
            Expediente exp = new Expediente(numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas, estado);
            expedientes.add(exp);
        }
        
        return expedientes;
    }
    
	@Override
    public ArrayList<String> getAllTiposExp() throws SQLException, ClassNotFoundException {
		ArrayList<String> tiposExp = new ArrayList<>();

		String sql = "SELECT DISTINCT tipo FROM Expedientes";
	    ResultSet rs = Conexion.executeSelect(sql);

	    while (rs.next()) {
	        String tipo = rs.getString("tipo");
	        tiposExp.add(tipo);
	    }
	    return tiposExp;
	}

	@Override
    public ArrayList<String> getAllJuzgados() throws SQLException, ClassNotFoundException {
		ArrayList<String> juzgados = new ArrayList<>();

		String sql = "SELECT DISTINCT juzgado FROM Expedientes";
	    ResultSet rs = Conexion.executeSelect(sql);

	    while (rs.next()) {
	        String juzgado = rs.getString("juzgado");
	        juzgados.add(juzgado);
	    }
	    return juzgados;
	}
    
	@Override
	public String getUbicacionExp(String tipoExp, int numExp, int anioExp, String juzgado) throws SQLException, ClassNotFoundException {
		String res = "";
    	String sql = "SELECT ubicacion FROM Expedientes WHERE numExpediente = " + numExp + 
    			" AND tipo = '" + tipoExp + "'" +
                " AND anio = " + anioExp + 
                " AND juzgado = '" + juzgado + "'";
        ResultSet rs = Conexion.executeSelect(sql);
        
        if (rs.next()) {
	        res = rs.getString("ubicacion");
        }
        return res;
	}
	
}
