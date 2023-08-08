package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

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
	
	public static boolean realizarPrestamo(Expediente exp) throws SQLException{
	    // Comprobar que el expediente existe en la BBDD
	    if (!Expediente.existeExpediente(exp.getNumExpediente(), exp.getTipo(), exp.getAnio(), exp.getCaja(), exp.getUbicacion())) {
	        System.out.println("El expediente no existe en la base de datos.");
	        return false;
	    }
	    
	    // Comprobar que el expediente no esta en la tabla prestamos
	    if (existePrestamo(exp.getNumExpediente(), exp.getTipo(), exp.getAnio(), exp.getCaja(), exp.getUbicacion())) {
	        System.out.println("El expediente ya ha sido prestado.");
	        return false;
	    }
	    
	    // Obtener la fecha y hora actual del sistema
	    LocalDate fechaActual = LocalDate.now();
	    
	    // Construir la query del prestamo
	    String query = "INSERT INTO Prestamos (numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, fechaPrestamo) " +
                "VALUES ('" + exp.getNumExpediente() + "', '" + exp.getTipo() + "', " + exp.getAnio() + ", " + exp.getCaja() +
                ", '" + exp.getUbicacion() + "', '" + exp.getNotas() + "', '" + exp.getTomos() + "', '" + exp.getJuzgado() +
                "', '" + exp.getLugar() + "', '" + fechaActual + "')";

        // Ejecutar la query en la BBDD
	    return Conexion.execute(query);
	}


	private static boolean existePrestamo(int numExpediente, String tipo, int anio, int caja, String ubicacion) throws SQLException{
	    String query = "SELECT COUNT(*) AS count FROM Prestamos WHERE numExpediente = " + numExpediente +
	            " AND tipo = '" + tipo + "'" +
	            " AND anio = " + anio +
	            " AND caja = " + caja +
	            " AND ubicacion = '" + ubicacion + "'";
	    ResultSet rs = Conexion.executeSelect(query);
	    
	    if (rs.next()) {
	    	int count = rs.getInt("count");
	        return count > 0;
	    }
	    return false;
	}

	public boolean realizarDevolucion() throws SQLException {
		// Comprobar si el préstamo existe en la base de datos
		if (!existePrestamo(numExpediente, tipo, anio, caja, ubicacion)) {
		// Si el préstamo no existe, buscar una ubicación disponible para el expediente
			String nuevaUbicacion = Caja.buscarUbi(paginas);
			if (nuevaUbicacion != null) {
				return nuevaUbicacion; // Devolver nueva ubicación encontrada
			} else {
				System.out.println("No hay cajas disponibles para el expediente.");
				return false;
		    }
		}

		  // Obtener el expediente asociado al préstamo
		  Expediente expediente = Expediente.getByID(numExpediente);

		  // Comparar el número de páginas del expediente con el expediente en la base de datos
		  int paginasExpedienteEnDB = expediente.getPaginas();
		  if (paginas > paginasExpedienteEnDB) {
			  // Si el número de páginas ha aumentado, comprobar si cabe en la caja original
			  Caja cajaOriginal = Caja.getCajaPorUbicacion(ubicacion);
			  if (cajaOriginal.getPaginasDisponibles() >= (paginas - paginasExpedienteEnDB)) {
				  // Si cabe, actualizar el número de páginas en el expediente de la base de datos
				  expediente.setPaginas(paginas);
				  Expediente.update(expediente);
				  cajaOriginal.restarPaginasUtilizadas(paginas - paginasExpedienteEnDB); // Restar páginas utilizadas
				  return cajaOriginal.getUbicacion(); // Devolver ubicación de la caja original
			  } else {
				  // Si no cabe, buscar una nueva ubicación en otra caja
				  String nuevaUbicacion = Caja.buscarUbi(paginas);
				  if (nuevaUbicacion != null) {
					  expediente.setUbicacion(nuevaUbicacion);
					  expediente.setPaginas(paginas);
					  Expediente.update(expediente);
					  return nuevaUbicacion; // Devolver nueva ubicación encontrada
				  } else {
					  System.out.println("No hay cajas disponibles para el expediente.");
					  return false;
				  }
			  }
		  } else {
		            // Si el número de páginas no ha aumentado, simplemente devolver la ubicación de la caja
		            return caja.getUbicacion();
		  }
	}
}