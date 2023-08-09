package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Prestamo {
	private int numExpediente;
	private String tipo;
	private int anio;
	private int caja;
	private String ubicacion;
	private String notas;
	private String tomos;
	private String juzgado;
	private String lugar;
	private String fechaPrestamo;
	
	public Prestamo() {}
	
	public Prestamo(int numExpediente, String tipo, int anio, int caja, String ubicacion, String notas, String tomos, 
					String juzgado, String lugar, String fechaPrestamo, String fechaMaxDevolucion) {
		this.numExpediente = numExpediente;
		this.tipo = tipo;
		this.caja = caja;
		this.anio = anio;
		this.ubicacion = ubicacion;
		this.notas = notas;
		this.tomos = tomos;
		this.juzgado = juzgado;
		this.lugar = lugar;
		this.fechaPrestamo = fechaPrestamo;
	}
	
	public static List<Expediente> realizarPrestamo(int numExp, String tipo, int anio) throws SQLException{
	    // Comprobar que el expediente existe en la BBDD
		// TODO: revisar: al solicitar un prestamo no sabes en que caja ni ubicacion esta el expediente.
		// Solicitas un expediente de un tipo, con numero, año, y de un juzgado. Esto hace una consulta de ese expediente en la bd
		// y esa consulta es la que devuelve toda la info del expediente, incluida la ubicacion
	    
		List<Expediente> expList = new ArrayList<>();
		
		if (!Expediente.existeExpediente(numExp, tipo, anio)) {
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
	    expList = Expediente.buscaExpedientes(numExp, tipo, anio);
	    for (Expediente exp : expList) {
	    	//Construimos query
	    	String query = "INSERT INTO Prestamos (numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, fechaPrestamo) " +
	                "VALUES ('" + exp.getNumExpediente() + "', '" + exp.getTipo() + "', " + exp.getAnio() + ", " + exp.getCaja() +
	                ", '" + exp.getUbicacion() + "', '" + exp.getNotas() + "', '" + exp.getTomos() + "', '" + exp.getJuzgado() +
	                "', '" + exp.getLugar() + "', '" + fechaActual + "')";
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

//	public boolean realizarDevolucion() throws SQLException {
//		// Comprobar si el préstamo existe en la base de datos
//		if (!existePrestamo(numExpediente, tipo, anio, caja, ubicacion)) {
//		// Si el préstamo no existe, buscar una ubicación disponible para el expediente
//			String nuevaUbicacion = Caja.buscarUbi(paginas);
//			if (nuevaUbicacion != null) {
//				return nuevaUbicacion; // Devolver nueva ubicación encontrada
//			} else {
//				System.out.println("No hay cajas disponibles para el expediente.");
//				return false;
//		    }
//		}
//
//		  // Obtener el expediente asociado al préstamo
//		  Expediente expediente = Expediente.getByID(numExpediente);
//
//		  // Comparar el número de páginas del expediente con el expediente en la base de datos
//		  int paginasExpedienteEnDB = expediente.getPaginas();
//		  if (paginas > paginasExpedienteEnDB) {
//			  // Si el número de páginas ha aumentado, comprobar si cabe en la caja original
//			  Caja cajaOriginal = Caja.getCajaPorUbicacion(ubicacion);
//			  if (cajaOriginal.getPaginasDisponibles() >= (paginas - paginasExpedienteEnDB)) {
//				  // Si cabe, actualizar el número de páginas en el expediente de la base de datos
//				  expediente.setPaginas(paginas);
//				  Expediente.update(expediente);
//				  cajaOriginal.restarPaginasUtilizadas(paginas - paginasExpedienteEnDB); // Restar páginas utilizadas
//				  return cajaOriginal.getUbicacion(); // Devolver ubicación de la caja original
//			  } else {
//				  // Si no cabe, buscar una nueva ubicación en otra caja
//				  String nuevaUbicacion = Caja.buscarUbi(paginas);
//				  if (nuevaUbicacion != null) {
//					  expediente.setUbicacion(nuevaUbicacion);
//					  expediente.setPaginas(paginas);
//					  Expediente.update(expediente);
//					  return nuevaUbicacion; // Devolver nueva ubicación encontrada
//				  } else {
//					  System.out.println("No hay cajas disponibles para el expediente.");
//					  return false;
//				  }
//			  }
//		  } else {
//		            // Si el número de páginas no ha aumentado, simplemente devolver la ubicación de la caja
//		            return caja.getUbicacion();
//		  }
//	}
//	
	public ArrayList<Juzgado> getJuzgados() {
		ArrayList<Juzgado> listaJuzgados = new ArrayList<>();

		try {
			ResultSet rs = Conexion.executeSelect("");
			while (rs.next())
			{
				Juzgado juzgado = new Juzgado();
				juzgado.setNombre(rs.getString("nombre"));
				listaJuzgados.add(juzgado);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaJuzgados;
	}
}