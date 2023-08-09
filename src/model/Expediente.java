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
    private int paginas; // Nuevo atributo paginas

    public Expediente(int numExpediente, String tipo, int anio, int caja, String ubicacion, String notas, String tomos, String juzgado, String lugar, int paginas) {
        this.numExpediente = numExpediente;
        this.tipo = tipo;
        this.anio = anio;
        this.caja = caja;
        this.ubicacion = ubicacion;
        this.notas = notas;
        this.tomos = tomos;
        this.juzgado = juzgado;
        this.lugar = lugar;
        this.paginas = paginas; // Se añade el atributo paginas al constructor
    }

    public Expediente(String tipoExp, int numExp, int anioExp) {
        this.tipo = tipoExp;
        this.numExpediente = numExp;
        this.anio = anioExp;
	}

	public int getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(int numExpediente) {
        this.numExpediente = numExpediente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getCaja() {
        return caja;
    }

    public void setCaja(int caja) {
        this.caja = caja;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getTomos() {
        return tomos;
    }

    public void setTomos(String tomos) {
        this.tomos = tomos;
    }

    public String getJuzgado() {
        return juzgado;
    }

    public void setJuzgado(String juzgado) {
        this.juzgado = juzgado;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    // Métodos CRUD

    public static boolean insert(Expediente exp) throws SQLException{
        // Posible comprobación de no nulos y rangos

        // Construir la query
        String query = "INSERT INTO Expedientes (numExpediente, tipo, caja, anio, ubicacion, notas, tomos, juzgado, lugar, paginas) VALUES ('"
                + exp.numExpediente + "','" + exp.tipo + "','" + exp.caja + "','" + exp.anio + "','" + exp.ubicacion + "','" + exp.notas + "','" + exp.tomos + "','"
                + exp.juzgado + "','" + exp.lugar + "','" + exp.paginas + "')";

        // Ejecutar la query en la BBDD
        return Conexion.execute(query);
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
            int paginas = rs.getInt("paginas"); // Se obtiene el número de páginas desde la base de datos

            exp = new Expediente(numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas);
        }

        return exp;
    }

    public static boolean update(Expediente exp) throws SQLException{
        String query = "UPDATE Expedientes SET tipo = '" + exp.tipo + "', anio = " + exp.anio + ", caja = " + exp.caja
                + ", ubicacion = '" + exp.ubicacion + "', notas = '" + exp.notas + "', tomos = '" + exp.tomos
                + "', juzgado = '" + exp.juzgado + "', lugar = '" + exp.lugar + "', paginas = " + exp.paginas
                + " WHERE numExpediente = " + exp.numExpediente;

        return Conexion.execute(query);
    }

    public static boolean delete(Expediente exp) {
        // Posible comprobacion de no nulos y rangos

        // Construir la query
        String query = "DELETE FROM Expedientes WHERE numExpediente = " + exp.numExpediente;

        // Ejecutar la query en la BBDD
        return Conexion.execute(query);
    }

    public static List<Expediente> buscaExpedientes(int numExpe, String tipe, int year) throws SQLException{
    	List<Expediente> expedientes = new ArrayList<>();

        String query = "SELECT * FROM Expedientes WHERE numExpediente = " + numExpe +
        		" AND tipo = '" + tipe + "'" +
                " AND anio = " + year + "";

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
    
    public static boolean existeExpediente(int numExpediente, String tipo, int anio) throws SQLException{
        boolean existe = false;

        String query = "SELECT COUNT(*) AS count FROM Expedientes WHERE numExpediente = " + numExpediente +
        		" AND tipo = '" + tipo + "'" +
                " AND anio = " + anio + "";

        ResultSet rs = Conexion.executeSelect(query);
        if (rs.next()) {
        	int count = rs.getInt("count");
        	existe = count > 0;
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
            int paginas = rs.getInt("paginas");
            
            Expediente exp = new Expediente(numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas);
            expedientes.add(exp);
        }
        
        return expedientes;
    }
    
    public static ArrayList<String> getAllTiposExp() throws SQLException {
		ArrayList<String> tiposExp = new ArrayList<>();

		String sql = "SELECT DISTINCT tipo FROM Expedientes";
	    ResultSet rs = Conexion.executeSelect(sql);

	    while (rs.next()) {
	        String tipo = rs.getString("tipo");
	        tiposExp.add(tipo);
	    }
	    return tiposExp;
	}
    
    public static String getUbicacionExp(Expediente exp) throws SQLException {
    	String res = "";
    	String sql = "SELECT ubicacion FROM Expedientes WHERE numExpediente = " + exp.numExpediente;
        ResultSet rs = Conexion.executeSelect(sql);
        
        if (rs.next()) {
	        res = rs.getString("ubicacion");
        }
        return res;
    }
}