package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Conexion;
import model.Expediente;
import model.Prestamo;

public class PrestamoDAOImpl implements PrestamoDAO{
	
	@Override
	public ArrayList<Expediente> realizarPrestamo(int numExp, String tipo, int anio, int solicitante, String juzgado, String fechaPrestamo) throws SQLException, IllegalArgumentException, ClassNotFoundException{
		ExpedienteDAO exp = new ExpedienteDAOImpl();
		SolicitanteDAO sol = new SolicitanteDAOImpl();
		ArrayList<Expediente> expList = new ArrayList<>();
		
		//Comprobamos que el solicitante tiene permiso
		if(!sol.existeEmpleado(solicitante)) throw new IllegalArgumentException("Dicho empleado no puede solicitar el expediente");
		//Comprobamos que existe expediente
		if (!exp.existeExpediente(numExp, tipo, anio, juzgado)) throw new IllegalArgumentException("No existe ese expediente en la BBDD");
	    //Comprobamos que expediente se puede prestar(su estado no es ni expurgado ni prestado)
		if(!expedienteDisponible(numExp, tipo, anio, juzgado)) System.out.println("Esta prestado");
			//throw new IllegalArgumentException("Este expediente se encuentra prestado o expurgado");
		else {
			//Insertamos prestamo en la bbdd
			Prestamo prestamo = new Prestamo(numExp, tipo, anio, juzgado, fechaPrestamo, "", solicitante);
			this.insert(prestamo);
			//Actualizamos expedientes en bbdd
		    expList = exp.buscaExpediente(numExp, tipo, anio, juzgado);
		    for (Expediente expediente : expList) {
		    	//Actualizamos estado
		    	expediente.setEstado("prestado");
		    	//Actualizamos en bbdd
		    	exp.update(expediente);
		    }
	    }
	    
	    //Devolvemos lista expedientes a prestar (solo tendra una posicion en caso de no estar dividido entre varias cajas)
	    return expList;
	}
	
	@Override
	public boolean insert(Prestamo prest) throws SQLException, ClassNotFoundException{
        // Construir la query
        String query = "INSERT INTO prestamos (idSolicitante, numExpediente, tipo, anio, juzgado, fechaPrestamo, fechaDevolucion) VALUES ('"
                + prest.getSolicitante() + "','" + prest.getNumExpediente() + "','" + prest.getTipo() + "','"
        		+ prest.getAnio() + "','" + prest.getJuzgado() + "','" + prest.getFechaPrestamo() + "','"
        		+ prest.getFechaDevolucion() + "')";

        // Ejecutar la query en la BBDD
        return Conexion.execute(query);
    }
	
	@Override
    public boolean update(Prestamo prest) throws SQLException, ClassNotFoundException{
        String query = "UPDATE prestamos SET fechaDevolucion = '" + prest.getFechaDevolucion()  + "'" 
                + " WHERE numExpediente = " + prest.getNumExpediente() + " AND tipo = '" + prest.getTipo() 
                + "' AND anio = " + prest.getAnio() + " AND fechaPrestamo = '" + prest.getFechaPrestamo() + "'";

        return Conexion.execute(query);
    }
	
	@Override
	public boolean expedienteDisponible(int numExpediente, String tipo, int anio, String juzgado) throws SQLException, ClassNotFoundException{
	    String query = "SELECT DISTINCT * FROM expedientes WHERE numExpediente = " + numExpediente + " AND tipo = '" + tipo + "'" 
	    				+ " AND anio = " + anio + "" + " AND juzgado = '" + juzgado + "'";
	    ResultSet rs = Conexion.executeSelect(query);
	    
	    String estado = null;
	    if (rs.next()) {
	    	estado = rs.getString("estado");
	    	 System.out.println(estado);
	    }
	    if(estado == "expurgado" || estado == "prestado") return false;
	    else return true;
	}
	
	@Override
	public Prestamo existePrestamoSinDevolver(int numExpediente, String tipo, int anio, String juzgado) throws ClassNotFoundException, SQLException {
		//TODO: cambiar a esta select cuando insertemos los String como ""
//		String query = "SELECT * FROM prestamos WHERE numExpediente = " + numExpediente + " AND tipo = '" + tipo + "'" 
//				+ " AND anio = " + anio + "" + " AND juzgado = '" + juzgado + "'" + " AND fechaDevolucion = \"\"";
		String query = "SELECT * FROM prestamos WHERE numExpediente = " + numExpediente + " AND tipo = '" + tipo + "'" 
				+ " AND anio = " + anio + "" + " AND juzgado = '" + juzgado + "'" + " AND fechaDevolucion = 'null'";
		ResultSet rs = Conexion.executeSelect(query);
		Prestamo prestamo = null;

		if (rs.next()) {
            String fechaPrestamo = rs.getString("fechaPrestamo");
            int idSolicitante = rs.getInt("idSolicitante");
            prestamo = new Prestamo(numExpediente, tipo, anio, juzgado, fechaPrestamo, "", idSolicitante);
		}
		else { //TODO: BORRAR!!! Arreglo temporal para que funcione mientras haya fechaDevolucion como null y como "" en la bd
			query = "SELECT * FROM prestamos WHERE numExpediente = " + numExpediente + " AND tipo = '" + tipo + "'" 
					+ " AND anio = " + anio + "" + " AND juzgado = '" + juzgado + "'" + " AND fechaDevolucion = \"\"";
			rs = Conexion.executeSelect(query);
			prestamo = null;

			if (rs.next()) {
	            String fechaPrestamo = rs.getString("fechaPrestamo");
	            int idSolicitante = rs.getInt("idSolicitante");
	            prestamo = new Prestamo(numExpediente, tipo, anio, juzgado, fechaPrestamo, "", idSolicitante);
			}
			else {
				query = "SELECT * FROM prestamos WHERE numExpediente = " + numExpediente + " AND tipo = '" + tipo + "'" 
						+ " AND anio = " + anio + "" + " AND juzgado = '" + juzgado + "'" + " AND fechaDevolucion IS NULL";
				rs = Conexion.executeSelect(query);
				prestamo = null;

				if (rs.next()) {
		            String fechaPrestamo = rs.getString("fechaPrestamo");
		            int idSolicitante = rs.getInt("idSolicitante");
		            prestamo = new Prestamo(numExpediente, tipo, anio, juzgado, fechaPrestamo, "", idSolicitante);
				}
			}
		} // Borrar hasta aqui
		return prestamo;
	}
}
