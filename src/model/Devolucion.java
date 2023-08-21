package model;

import java.sql.SQLException;
import java.util.List;

import DAO.*;

public class Devolucion {
	public static List<Caja> buscarUbi(int numExp, int anio, String tipo, String juzgado) throws SQLException {
		//Comprobamos si expediente ha sido prestado y devolvemos list<caja> (vacia en caso de no encontrarse prestamo)
		
		return null;
	}
	
	public static void devolucion(int numExp, int anio, String tipo, String juzgado) throws SQLException {
		//en caso de que exista prestamo se trata de una devolucion
		//Obtener el expediente asociado al prestamo
		//Esto podria no ser necesario segun la informacion  que introduzca el usuario en el formulario, de momento se queda para evitar posibles problemas
		
		ExpedienteDAO exp = new ExpedienteDAOImpl();
		List<Expediente> expList = exp.buscaExpediente(numExp, tipo, anio, juzgado);

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
			  
			  
			String tipoExpediente = expediente.getTipo();
			int anioExpediente = expediente.getAnio();
			int numPaginasExpediente = expediente.getNumPaginas();
				
			Caja cajaAdecuada = Caja.buscarCajaDisponible(tipoExpediente, anioExpediente, numPaginasExpediente);
				
			if (cajaAdecuada != null) {
				// Insertar el expediente en la caja adecuada
				//falta eliminar de la caja anterior las paginas que tenia el expediente, que no he sido capaz
				expediente.setCaja(cajaAdecuada.getIdCaja());
				expediente.setUbicacion(cajaAdecuada.getUbicacion());
				Expediente.insert(expediente);
				cajaAdecuada.restarPaginas(numPaginasExpediente);
				Caja.update(cajaAdecuada);
				System.out.println("Expediente insertado correctamente en la caja con ID: " + cajaAdecuada.getIdCaja());
				} else {
				    System.out.println("No se encontró una caja adecuada para el expediente.");
				}
			  
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
}
