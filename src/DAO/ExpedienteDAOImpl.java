package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Conexion;
import model.Expediente;

public class ExpedienteDAOImpl implements ExpedienteDAO {
	
	@Override
	public boolean insert(Expediente exp) throws SQLException{
        // Posible comprobacion de no nulos y rangos

        // Construir la query
        String query = "INSERT INTO Expedientes (numExpediente, tipo, caja, anio, ubicacion, notas, tomos, juzgado, lugar, paginas) VALUES ('"
                + exp.getNumExpediente() + "','" + exp.getTipo() + "','" + exp.getCaja() + "','" + exp.getAnio() + "','"
        		+ exp.getUbicacion() + "','" + exp.getNotas() + "','" + exp.getTomos() + "','"
                + exp.getJuzgado() + "','" + exp.getLugar() + "','" + exp.getPaginas() + "')";

        // Ejecutar la query en la BBDD
        return Conexion.execute(query);
    }

	@Override
    public boolean update(Expediente exp) throws SQLException{
        String query = "UPDATE Expedientes SET tipo = '" + exp.getTipo() + "', anio = " + exp.getAnio() + ", caja = " + exp.getCaja()
                + ", ubicacion = '" + exp.getUbicacion() + "', notas = '" + exp.getNotas() + "', tomos = '" + exp.getTomos()
                + "', juzgado = '" + exp.getJuzgado() + "', lugar = '" + exp.getLugar() + "', paginas = " + exp.getPaginas()
                + " WHERE numExpediente = " + exp.getNumExpediente();

        return Conexion.execute(query);
    }

	@Override
    public boolean delete(int numExpediente) {
        // Posible comprobacion de no nulos y rangos

        // Construir la query
        String query = "DELETE FROM Expedientes WHERE numExpediente = " + numExpediente;

        // Ejecutar la query en la BBDD
        return Conexion.execute(query);
    }

	@Override
    public List<Expediente> buscaExpediente(int numExpe, String type, int year, String judge) throws SQLException{
    	List<Expediente> expedientes = new ArrayList<>();

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
            
            Expediente exp = new Expediente(numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas);
            expedientes.add(exp);
        }
        return expedientes;
    }
    
	@Override
    public boolean existeExpediente(int numExpediente, String tipo, int anio, String juzgado) throws SQLException{
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
    public List<Expediente> getAllExpedientes() throws SQLException {
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
            int paginas = rs.getInt("paginas");
            
            Expediente exp = new Expediente(numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas);
            expedientes.add(exp);
        }
        
        return expedientes;
    }
    
	@Override
    public ArrayList<String> getAllTiposExp() throws SQLException {
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
    public ArrayList<String> getAllJuzgados() throws SQLException {
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
	public String getUbicacionExp(String tipoExp, int numExp, int anioExp, String juzgado) throws SQLException {
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
	
	/*
	public Expediente buscaExpedientesTomos(int numExpe, String tipe, int year, String tomos) throws SQLException{
    	Expediente exp = null;
        String query = "SELECT * FROM Expedientes WHERE numExpediente = " + numExpe +
        		" AND tipo = '" + tipe + "'" +
                " AND anio = " + year + "" +
                " AND tomos = '" + tomos + "'";

        ResultSet rs = Conexion.executeSelect(query);
        if (rs.next()) {
        	int numExpediente = rs.getInt("numExpediente");
            String tipo = rs.getString("tipo");
            int anio = rs.getInt("anio");
            int caja = rs.getInt("caja");
            String ubicacion = rs.getString("ubicacion");
            String notas = rs.getString("notas");
            String tomo = rs.getString("tomos");
            String juzgado = rs.getString("juzgado");
            String lugar = rs.getString("lugar");
            int paginas = rs.getInt("paginas");
            
            exp = new Expediente(numExpediente, tipo, anio, caja, ubicacion, notas, tomo, juzgado, lugar, paginas);
        }
        return exp;
    }
	
	public String getUbicacionExp(Expediente exp) throws SQLException {
    	String res = "";
    	String sql = "SELECT ubicacion FROM Expedientes WHERE numExpediente = " + exp.numExpediente;
        ResultSet rs = Conexion.executeSelect(sql);
        
        if (rs.next()) {
	        res = rs.getString("ubicacion");
        }
        return res;
    }
	
	public Expediente getByID(int id) throws SQLException {
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
            int paginas = rs.getInt("paginas"); // Se obtiene el número de páginas desde la base de datos

            exp = new Expediente(numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas);
        }

        return exp;
    }*/
}
