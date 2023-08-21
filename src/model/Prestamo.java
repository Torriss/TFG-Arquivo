package model;

public class Prestamo {
	private int numExpediente;
	private String tipo;
	private int anio;
	//private int caja;
	//private String ubicacion;
	//private String notas;
	//private String tomos;
	private String juzgado;
	//private String lugar;
	private String fechaPrestamo;
	private String solicitante;
	
	public Prestamo() {}
	
	public Prestamo(int numExpediente, String tipo, int anio, String juzgado, String fechaPrestamo, String solicitante) {
		this.numExpediente = numExpediente;
		this.tipo = tipo;
		this.anio = anio;
		this.juzgado = juzgado;
		this.fechaPrestamo = fechaPrestamo;
		this.solicitante = solicitante;
		//this.caja = caja;	
		//this.ubicacion = ubicacion;
		//this.notas = notas;
		//this.tomos = tomos;
		//this.lugar = lugar;
	}
	
	public int getNumExpediente() {
        return numExpediente;
    }

    public String getTipo() {
        return tipo;
    }

    public int getAnio() {
        return anio;
    }

    public String getJuzgado() {
        return juzgado;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }
    
    public String getSolicitante() {
    	return solicitante;
    }
	
	/*public static List<Expediente> realizarPrestamo(int numExp, String tipo, int anio, String solicitante, String juzgado) throws SQLException{
	    // Comprobar que el expediente existe en la BBDD
		// TODO: revisar: al solicitar un prestamo no sabes en que caja ni ubicacion esta el expediente.
		// Solicitas un expediente de un tipo, con numero, año, y de un juzgado. Esto hace una consulta de ese expediente en la bd
		// y esa consulta es la que devuelve toda la info del expediente, incluida la ubicacion
	    
		List<Expediente> expList = new ArrayList<>();
		
		if (!Expediente.existeExpediente(numExp, tipo, anio, juzgado)) {
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
	    expList = Expediente.buscaExpediente(numExp, tipo, anio, juzgado);
	    for (Expediente exp : expList) {
	    	//Construimos query
	    	String query = "INSERT INTO Prestamos (numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, fechaPrestamo, solicitante) " +
	                "VALUES ('" + exp.getNumExpediente() + "', '" + exp.getTipo() + "', " + exp.getAnio() + ", " + exp.getCaja() +
	                ", '" + exp.getUbicacion() + "', '" + exp.getNotas() + "', '" + exp.getTomos() + "', '" + exp.getJuzgado() +
	                "', '" + exp.getLugar() + "', '" + fechaActual + "', '" + solicitante + "')";
	    	//Ejecutamos query
	    	Conexion.execute(query);
	    }
	    
	    return expList;
	}


	private static boolean existePrestamo(int numExpediente, String tipo, int anio) throws SQLException{
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
	
	public static boolean eliminarPrestamo(int numExpediente, String tipo, int anio, String tomos) {
        String query = "DELETE FROM Prestamos WHERE numExpediente = " + numExpediente +
                " AND tipo = '" + tipo + "'" +
                " AND anio = " + anio +
                " AND tomos = '" + tomos + "'";
        return Conexion.execute(query);
    }
	*/
	
}