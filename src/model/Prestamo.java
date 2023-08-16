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
	
	public static List<Expediente> realizarPrestamo(int numExp, String tipo, int anio, String solicitante) throws SQLException{
	    // Comprobar que el expediente existe en la BBDD
		// TODO: revisar: al solicitar un prestamo no sabes en que caja ni ubicacion esta el expediente.
		// Solicitas un expediente de un tipo, con numero, a√±o, y de un juzgado. Esto hace una consulta de ese expediente en la bd
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
	    
	    //Recorremos lista expedientes para aÒadirlos a la tabla prestamos
	    expList = Expediente.buscaExpedientes(numExp, tipo, anio);
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
	
	public static List<Caja> buscarUbi(int numExp, int anio, String tipo, String juzgado) throws SQLException {
		//Comprobamos si expediente ha sido prestado y devolvemos list<caja> (vacia en caso de no encontrarse prestamo)
		return null;
	}
	
	public static void devolucion(int numExp, int anio, String tipo, String juzgado) throws SQLException {
		//en caso de que exista prestamo se trata de una devolucion
		//Obtener el expediente asociado al prestamo
		//Esto podria no ser necesario segun la informacion  que introduzca el usuario en el formulario, de momento se queda para evitar posibles problemas
		Expediente exp = Expediente.buscaExpedientesTomos(expediente.getNumExpediente(), expediente.getTipo(), expediente.getAnio(), expediente.getTomos());

		//Comparar el numero de paginas del expediente con el expediente en la base de datos
		int paginasNuevas = expediente.getPaginas(), paginasViejas = exp.getPaginas();
		if (paginasNuevas > paginasViejas) {
		  //Si el numero de paginas ha aumentado, comprobar si cabe en la caja original
		  Caja cajaOriginal = Caja.getById(exp.getCaja());
		  if (cajaOriginal.getPaginas() >= (paginasNuevas - paginasViejas)) {
			  //Si cabe, actualizar el numero de paginas en el expediente de la base de datos
			  exp.setPaginas(paginasNuevas);
			  Expediente.update(exp); //Actualizar en BD
			  cajaOriginal.restarPaginas(paginasNuevas-paginasViejas); //Restar paginas utilizadas
			  Caja.update(cajaOriginal); //Actualizar en BD
			  return cajaOriginal; //Devolver caja original
		  } else {
			  //Si no cabe, buscar una nueva ubicacion en otra caja
			  //Cambiar caja del expediente
			  //Restar paginas de nueva caja
			  //Settear paginas del expediente
			  //Actualizar expediente en BD
			  //Aniadir paginas a caja de la que se saca expediente--SUMAR!!!!! NO HACER SET!!!!!!!!!
			  String nuevaUbicacion = Caja.buscarUbi(paginas);
			  if (nuevaUbicacion != null) {
				  expediente.setCaja(nuevaUbicacion);
				  expediente.setPaginas(paginas);
				  Expediente.update(expediente);
				  return cajaNueva; //Devolver nueva ubicacion encontrada
			  } else {
				  System.out.println("No hay cajas disponibles para el expediente.");
				  return false;
			  }
		  }
		} else {
	            //Si el numero de paginas no ha aumentado, simplemente devolver la ubicacion de la caja
	            return caja.getUbicacion();
		}
	}
	
	//ojito con valor de retorno, podria ser una lista
	public static Caja nuevo(int numExp, int anio, String tipo, String juzgado) throws SQLException {
		//Si el prestamo no existe, buscar una ubicacion disponible para el expediente, pues se trata de un alta
		Caja.insertarExpedienteEnCaja(expediente);
		Prestamo.eliminarPrestamo(expediente.getNumExpediente(), expediente.getTipo(), expediente.getAnio(), expediente.getTomos());
		return Caja.getById(expediente.getCaja());
	}
	
	/*public Caja realizarDevolucion(int numExp, int anio, String tipo, String juzgado) throws SQLException {
		//Comprobar si el prestamo existe en la base de datos
		if (!existePrestamo(expediente.getNumExpediente(), expediente.getTipo(), expediente.getAnio())) {
		//Si el prestamo no existe, buscar una ubicacion disponible para el expediente, pues se trata de un alta
			Caja.insertarExpedienteEnCaja(expediente);
			Prestamo.eliminarPrestamo(expediente.getNumExpediente(), expediente.getTipo(), expediente.getAnio(), expediente.getTomos());
			return Caja.getById(expediente.getCaja());
		}
		else {
			//Obtener el expediente asociado al prestamo
			//Esto podria no ser necesario segun la informacion  que introduzca el usuario en el formulario, de momento se queda para evitar posibles problemas
			Expediente exp = Expediente.buscaExpedientesTomos(expediente.getNumExpediente(), expediente.getTipo(), expediente.getAnio(), expediente.getTomos());

			//Comparar el numero de paginas del expediente con el expediente en la base de datos
			int paginasNuevas = expediente.getPaginas(), paginasViejas = exp.getPaginas();
			if (paginasNuevas > paginasViejas) {
			  //Si el numero de paginas ha aumentado, comprobar si cabe en la caja original
			  Caja cajaOriginal = Caja.getById(exp.getCaja());
			  if (cajaOriginal.getPaginas() >= (paginasNuevas - paginasViejas)) {
				  //Si cabe, actualizar el numero de paginas en el expediente de la base de datos
				  exp.setPaginas(paginasNuevas);
				  Expediente.update(exp); //Actualizar en BD
				  cajaOriginal.restarPaginas(paginasNuevas-paginasViejas); //Restar paginas utilizadas
				  Caja.update(cajaOriginal); //Actualizar en BD
				  return cajaOriginal; //Devolver caja original
			  } else {
				  //Si no cabe, buscar una nueva ubicacion en otra caja
				  //Cambiar caja del expediente
				  //Restar paginas de nueva caja
				  //Settear paginas del expediente
				  //Actualizar expediente en BD
				  //Aniadir paginas a caja de la que se saca expediente--SUMAR!!!!! NO HACER SET!!!!!!!!!
				  String nuevaUbicacion = Caja.buscarUbi(paginas);
				  if (nuevaUbicacion != null) {
					  expediente.setCaja(nuevaUbicacion);
					  expediente.setPaginas(paginas);
					  Expediente.update(expediente);
					  return cajaNueva; //Devolver nueva ubicacion encontrada
				  } else {
					  System.out.println("No hay cajas disponibles para el expediente.");
					  return false;
				  }
			  }
			} else {
		            //Si el numero de paginas no ha aumentado, simplemente devolver la ubicacion de la caja
		            return caja.getUbicacion();
			}
		}
	}*/
	
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